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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import context.ManagerFactory;

import org.rlcommunity.rlglue.codec.AgentInterface;
import org.rlcommunity.rlglue.codec.RLGlue;
import org.rlcommunity.rlglue.codec.types.Action;
import org.rlcommunity.rlglue.codec.types.Observation;
import org.rlcommunity.rlglue.codec.util.AgentLoader;
import org.rlcommunity.rlglue.codec.taskspec.TaskSpec;
import org.rlcommunity.rlglue.codec.taskspec.ranges.IntRange;
import org.rlcommunity.rlglue.codec.taskspec.ranges.DoubleRange;

import la.persistence.database.Database;
import la.application.configManagement.Config;
import la.common.Reward;
import la.common.RewardsGroup;
import la.common.State;
import la.common.Try;

public class SARSAAgent implements AgentInterface {
	public static final String NAME = "SARSA";

    private Random randGenerator = new Random();
    private int lastAction;
    private State lastState;
    private double sarsa_stepsize = 0.1;
    private double sarsa_epsilon = 0.1;
    private double sarsa_gamma = 1.0;
    private int numActions;
    private double[] valueFunction;
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

//    public List<Reward> getRewardList(){
//    	List<Reward> rewardsList = new ArrayList<Reward>(); 
//    	rewardsList.add(new Reward("Sieg", 1000));
//    	rewardsList.add(new Reward("Niederlage", -1000));
//    	rewardsList.add(new Reward("Verletzung", -500));
//    	rewardsList.add(new Reward("Toeten eines Gegners", 50));
//    	rewardsList.add(new Reward("Pro Frame", -1));
//    	rewardsList.add(new Reward("Nach rechts gehen", +1));
//    	rewardsList.add(new Reward("Nach links gehen", -2));
//        return rewardsList;
//    }
    public Action agent_start(Observation observation) {
        State state = extractState(observation);        
        
//        List<Reward> rewardsList = db.getLastRewardsGroup(); 
		RewardsGroup rewardsGroup = db.getLastRewardsGroup();
        valueFunction = db.select(state, rewardsGroup); 
        
        int theIntAction = egreedy(valueFunction);

        Action returnAction = new Action(1, 0, 0);
        returnAction.intArray[0] = theIntAction;

        lastAction = theIntAction;
        lastState = state;

        return returnAction;
    }

    public Action agent_step(double reward, Observation observation) {
        State newState = extractState(observation);
        
		RewardsGroup rewardsGroup = db.getLastRewardsGroup(); 
        
        double[] newValueFunction = db.select(newState, rewardsGroup);
        valueFunction = db.select(lastState, rewardsGroup);

        int newActionInt = egreedy(newValueFunction);

        double Q_sa = valueFunction[lastAction];
        double Q_sprime_aprime = newValueFunction[newActionInt];

        double new_Q_sa = Q_sa + sarsa_stepsize * (reward + sarsa_gamma * Q_sprime_aprime - Q_sa);
        /*	Only update the value function if the policy is not frozen */
        if (!Config.FREEZE_POLICY) {
            db.update(lastState, rewardsGroup, lastAction, new_Q_sa);
        }

        /* Creating the action a different way to showcase variety */
        Action returnAction = new Action(1, 0, 0);
        returnAction.intArray[0] = newActionInt;

        lastAction = newActionInt;
        lastState = newState;

        return returnAction;
    }

    public void agent_end(double reward) {
//    	List<Reward> rewardsList = getRewardList(); 
    	RewardsGroup rewardsGroup = db.getLastRewardsGroup();
            
        valueFunction = db.select(lastState, rewardsGroup);

        double Q_sa = valueFunction[lastAction];
        double new_Q_sa = Q_sa + sarsa_stepsize * (reward - Q_sa);

        /*	Only update the value function if the policy is not frozen */
        if (!Config.FREEZE_POLICY) {
            db.update(lastState, rewardsGroup, lastAction, new_Q_sa);
        }
        Try aTry = new Try(Integer.valueOf(RLGlue.RL_env_message("is Mario alive?")), RLGlue.RL_return(), RLGlue.RL_num_steps()); 
		aTry.setReward_win_count(5);
		aTry.setReward_death_count(6);
		aTry.setReward_hurt_count(7);
		aTry.setReward_kill_count(8);
		aTry.setReward_elapsed_frame_count(9);
		aTry.setReward_move_right_count(10);
		aTry.setReward_move_left_count(11);
		aTry.setReward_move_up_count(12);
		aTry.setReward_move_down_count(13);
        db.saveAll(aTry, rewardsGroup);
        lastState = new State(0);
        lastAction = 0;
    }

    public void agent_cleanup() {
        lastAction = 0;
        lastState = new State(0);
        valueFunction = null;
    }

    public String agent_message(String message) {
        if(message.equals("what is your name?")) {
            return "my name is skeleton_agent, Java edition!";
        }
        if (message.equals("freeze learning")) {
        	Config.FREEZE_POLICY = true;
            return "message understood, policy frozen";
        }
        if (message.equals("unfreeze learning")) {
        	Config.FREEZE_POLICY = false;
            return "message understood, policy unfrozen";
        }
        if (message.equals("freeze exploring")) {
        	Config.FREEZE_EXPLORATION = true;
            return "message understood, exploring frozen";
        }
        if (message.equals("unfreeze exploring")) {
        	Config.FREEZE_EXPLORATION = false;
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

    private State extractState(Observation observation) {
        State state = new State(observation.getInt(0), observation.getInt(1), observation.getInt(2));
        //System.out.println("State: " + state);
        return state;
    }

    private int egreedy(double[] valueFunction) {
        if (!Config.FREEZE_EXPLORATION) {
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
