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
*  $HeadURL: http://rl-glue-ext.googlecode.com/svn/trunk/projects/codecs/Java/examples/skeleton-sample/SkeletonEnvironment.java $
* 
*/

package la.rlGlue;

import org.rlcommunity.rlglue.codec.EnvironmentInterface;
import org.rlcommunity.rlglue.codec.types.Action;
import org.rlcommunity.rlglue.codec.types.Observation;
import org.rlcommunity.rlglue.codec.types.Reward_observation_terminal;
import org.rlcommunity.rlglue.codec.util.EnvironmentLoader;
import org.rlcommunity.rlglue.codec.taskspec.TaskSpecVRLGLUE3;
import org.rlcommunity.rlglue.codec.taskspec.TaskSpec;
import org.rlcommunity.rlglue.codec.taskspec.ranges.IntRange;
import org.rlcommunity.rlglue.codec.taskspec.ranges.DoubleRange;

import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;

public class RLGlueEnvironment implements EnvironmentInterface {
    private final static Environment environment = MarioEnvironment.getInstance();

    private final static int OBSERVED_FIELDS[][] = {{-1, 0}, {1, 0}, {1, -1}, {2, 0}};
    private final static int ACTIONS_COUNT = 9;
    private final static int ZLEVEL_SCENE = 1;
    private final static int ZLEVEL_ENEMIES = 1;
    private final static double DISCOUNT_FACTOR = 1.0;
    private final static double REWARD_MAX = 0.0;
    private final static double REWARD_MIN = 0.0;

    public String env_init() {
        TaskSpecVRLGLUE3 theTaskSpecObject = new TaskSpecVRLGLUE3();

        theTaskSpecObject.setEpisodic();
        theTaskSpecObject.setDiscountFactor(DISCOUNT_FACTOR);
        theTaskSpecObject.addDiscreteObservation(new IntRange(0, (int) Math.pow(OBSERVED_FIELDS.length, ZoomLevelObstacle.count * ZoomLevelEnemy.count)));
        theTaskSpecObject.addDiscreteAction(new IntRange(0, ACTIONS_COUNT));
        theTaskSpecObject.setRewardRange(new DoubleRange(REWARD_MIN, REWARD_MAX));

        String taskSpecString = theTaskSpecObject.toTaskSpec();

        TaskSpec.checkTaskSpec(taskSpecString);

        return taskSpecString;
    }

    public Observation env_start() {
        Observation returnObservation = getObservation();

        return returnObservation;
    }

    public Reward_observation_terminal env_step(Action thisAction) {
        double theReward = 0.0;
        boolean[] keys = MarioAction.getKeysOfAction(MarioAction.valueOf(thisAction.intArray[0]));
        boolean episodeOver;

        Observation returnObservation;

        environment.performAction(keys);
        environment.tick();

        episodeOver = environment.isLevelFinished();

        returnObservation = getObservation();

        Reward_observation_terminal returnRewardObs = new Reward_observation_terminal(theReward, returnObservation, episodeOver);

        return returnRewardObs;
    }

    public void env_cleanup() {
    }

    public String env_message(String message) {
        if(message.equals("what is your name?"))
            return "my name is skeleton_environment, Java edition!";

        return "I don't know how to respond to your message";
    }

    private Observation getObservation() {
        Observation returnObservation = new Observation(OBSERVED_FIELDS.length, 0, 0);

        int[] marioEgoPos = environment.getMarioEgoPos();

        byte[][] levelScene = environment.getLevelSceneObservationZ(ZLEVEL_SCENE);
        byte[][] enemies = environment.getEnemiesObservationZ(ZLEVEL_ENEMIES);

        for(int field = 0; field < OBSERVED_FIELDS.length; field++) {
        	int i = ZoomLevelObstacle.valueOf(levelScene[marioEgoPos[0] + OBSERVED_FIELDS[field][0]][marioEgoPos[1] + OBSERVED_FIELDS[field][1]]).getValue() * 10;

        	i = i + ZoomLevelEnemy.valueOf(enemies[marioEgoPos[0] + OBSERVED_FIELDS[field][0]][marioEgoPos[1] + OBSERVED_FIELDS[field][1]]).getValue();

        	returnObservation.intArray[field] = i;
        }

        return returnObservation;
    }

    public static void main(String[] args) {
        EnvironmentLoader theLoader = new EnvironmentLoader(new RLGlueEnvironment());

        theLoader.run();
    }
}