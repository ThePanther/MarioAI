/*
 * Copyright 2008 Brian Tanner
 * http://rl-glue-ext.googlecode.com/
 * brian@tannerpages.com
 * http://brian.tannerpages.com
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
* 
*  $Revision: 676 $
*  $Date: 2009-02-08 18:15:04 -0700 (Sun, 08 Feb 2009) $
*  $Author: brian@tannerpages.com $
*  $HeadURL: http://rl-glue-ext.googlecode.com/svn/trunk/projects/codecs/Java/examples/skeleton-sample/SkeletonAgent.java $
* 
*/

package la.rlGlue;

import java.util.Random;

import context.ManagerFactory;
import org.rlcommunity.rlglue.codec.AgentInterface;
import org.rlcommunity.rlglue.codec.types.Action;
import org.rlcommunity.rlglue.codec.types.Observation;
import org.rlcommunity.rlglue.codec.util.AgentLoader;
import org.rlcommunity.rlglue.codec.taskspec.TaskSpec;
import org.rlcommunity.rlglue.codec.taskspec.ranges.IntRange;
import org.rlcommunity.rlglue.codec.taskspec.ranges.DoubleRange;
import persistence.database.Database;

public class SARSAAgent implements AgentInterface {

    private Random randGenerator = new Random();
    private int lastAction;
    private long lastState;
    private double sarsa_stepsize = 0.1;
    private double sarsa_epsilon = 0.1;
    private double sarsa_gamma = 1.0;
    private long marioMul = 100000000000L;
    private long sceneMul = 100000;
    private long enemyMul = 1;
    private int numActions;
    private double[] valueFunction;
    private boolean policyFrozen = false;
    private boolean exploringFrozen = false;
    private Database db;

    public void agent_init(String taskSpecification) {
        TaskSpec theTaskSpec=new TaskSpec(taskSpecification);
        System.out.println("SARSA agent parsed the task spec.");
        System.out.println("Observation have "+theTaskSpec.getNumDiscreteObsDims()+" integer dimensions");
        System.out.println("Actions have "+theTaskSpec.getNumDiscreteActionDims()+" integer dimensions");
        IntRange theObsRange=theTaskSpec.getDiscreteObservationRange(0);
        System.out.println("Observation (state) range is: "+theObsRange.getMin()+" to "+theObsRange.getMax());
        IntRange theActRange=theTaskSpec.getDiscreteActionRange(0);
        System.out.println("Action range is: "+theActRange.getMin()+" to "+theActRange.getMax());
        DoubleRange theRewardRange=theTaskSpec.getRewardRange();
        System.out.println("Reward range is: "+theRewardRange.getMin()+" to "+theRewardRange.getMax());

        numActions = theTaskSpec.getDiscreteActionRange(0).getMax();
        sarsa_gamma=theTaskSpec.getDiscountFactor();

        db = ManagerFactory.getManager(Database.class);

        //In more complex agents, you would also check for continuous observations and actions, discount factors, etc.
        //Also, these ranges can have special values like "NEGINF, POSINF, UNSPEC (unspecified)".  There is no guarantee
        //that they are all specified and that they are all nice numbers.
    }

    public Action agent_start(Observation observation) {
        long state = extractState(observation);
        valueFunction = db.select(state);
        int theIntAction = egreedy(valueFunction);

        Action returnAction = new Action(1, 0, 0);
        returnAction.intArray[0] = theIntAction;

        lastAction = theIntAction;
        lastState = state;

        return returnAction;
    }

    public Action agent_step(double reward, Observation observation) {
        long newState = extractState(observation);
        double[] newValueFunction = db.select(newState);

        int newActionInt = egreedy(newValueFunction);

        double Q_sa = valueFunction[lastAction];
        double Q_sprime_aprime = newValueFunction[newActionInt];

        double new_Q_sa = Q_sa + sarsa_stepsize * (reward + sarsa_gamma * Q_sprime_aprime - Q_sa);
        /*	Only update the value function if the policy is not frozen */
        if (!policyFrozen) {
            db.update(lastState,lastAction,new_Q_sa);
        }

        /* Creating the action a different way to showcase variety */
        Action returnAction = new Action(1, 0, 0);
        returnAction.intArray[0] = newActionInt;

        lastAction = newActionInt;
        lastState = newState;

        return returnAction;
    }

    public void agent_end(double reward) {
        valueFunction = db.select(lastState);

        double Q_sa = valueFunction[lastAction];
        double new_Q_sa = Q_sa + sarsa_stepsize * (reward - Q_sa);

        /*	Only update the value function if the policy is not frozen */
        if (!policyFrozen) {
            db.update(lastState,lastAction,new_Q_sa);
        }
        db.saveAll();
        lastState = 0;
        lastAction = 0;
    }

    public void agent_cleanup() {
        lastAction = 0;
        lastState = 0;
        valueFunction = null;
    }

    public String agent_message(String message) {
        if(message.equals("what is your name?")) {
            return "my name is skeleton_agent, Java edition!";
        }
        if (message.equals("freeze learning")) {
            policyFrozen = true;
            return "message understood, policy frozen";
        }
        if (message.equals("unfreeze learning")) {
            policyFrozen = false;
            return "message understood, policy unfrozen";
        }
        if (message.equals("freeze exploring")) {
            exploringFrozen = true;
            return "message understood, exploring frozen";
        }
        if (message.equals("unfreeze exploring")) {
            exploringFrozen = false;
            return "message understood, exploring unfrozen";
        }
        return "I don't know how to respond to your message";
    }

    /**
     * This is a trick we can use to make the agent easily loadable.
     * @param args
     */

    public static void main(String[] args){
        AgentLoader theLoader=new AgentLoader(new SkeletonAgent());
        theLoader.run();
    }

    private long extractState(Observation observation) {
        long state;
        state = (observation.getInt(0)*marioMul) + (observation.getInt(1)*sceneMul) + (observation.getInt(2)*enemyMul);
//        System.out.println("State: " + state);
        return state;
    }

    private int egreedy(double[] valueFunction) {
        if (!exploringFrozen) {
            if (randGenerator.nextDouble() <= sarsa_epsilon) {
                return randGenerator.nextInt(numActions);
            }
        }

        int maxIndex = 0;
        for (int a = 0; a < numActions; a++) {
            if (valueFunction[a] > valueFunction[maxIndex]) {
                maxIndex = a;
            }
        }
        return maxIndex;
    }

}
