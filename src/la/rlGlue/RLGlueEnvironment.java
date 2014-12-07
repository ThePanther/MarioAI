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

import la.application.configManagement.Config;
import la.common.Block;
//import la.common.State;
import la.common.Type;
import la.common.Zone;
import la.common.MarioAction;

import org.rlcommunity.rlglue.codec.EnvironmentInterface;
import org.rlcommunity.rlglue.codec.types.Action;
import org.rlcommunity.rlglue.codec.types.Observation;
import org.rlcommunity.rlglue.codec.types.Reward_observation_terminal;
import org.rlcommunity.rlglue.codec.util.EnvironmentLoader;
import org.rlcommunity.rlglue.codec.taskspec.TaskSpecVRLGLUE3;
import org.rlcommunity.rlglue.codec.taskspec.TaskSpec;
import org.rlcommunity.rlglue.codec.taskspec.ranges.IntRange;
import org.rlcommunity.rlglue.codec.taskspec.ranges.DoubleRange;

import ch.idsia.benchmark.mario.engine.GeneralizerLevelScene;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.engine.sprites.Sprite;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;
import ch.idsia.tools.MarioAIOptions;

import java.util.ArrayList;

public class RLGlueEnvironment implements EnvironmentInterface {
    private final static MarioEnvironment environment = MarioEnvironment.getInstance();

    private static ArrayList<Zone> visionField = new ArrayList<Zone>();

    private final static int STATES_COUNT = 16384; // TODO
    private final static int ACTIONS_COUNT = MarioAction.values().length;
    private final static int ZLEVEL_SCENE = 1;
    private final static int ZLEVEL_ENEMIES = 1;

    private float[] oldMarioFloatPos = new float[2];

    private int oldMarioMode;
    private int oldKillsByStomp;

    private int reward_win_count;
    private int reward_death_count;
    private int reward_hurt_count;
    private int reward_kill_count;
    private int reward_elapsed_frame_count;
    private int reward_move_right_count;
    private int reward_move_left_count;
    private int reward_move_up_count;
    private int reward_move_down_count;

    public String env_init() {
        TaskSpecVRLGLUE3 theTaskSpecObject = new TaskSpecVRLGLUE3();

        theTaskSpecObject.setEpisodic();
        theTaskSpecObject.setDiscountFactor(Config.DISCOUNT_FACTOR);
        theTaskSpecObject.addDiscreteObservation(new IntRange(0, STATES_COUNT));
        theTaskSpecObject.addDiscreteAction(new IntRange(0, ACTIONS_COUNT));
        theTaskSpecObject.setRewardRange(new DoubleRange(Config.REWARD_DEATH, Config.REWARD_WIN));

        String taskSpecString = theTaskSpecObject.toTaskSpec();

        TaskSpec.checkTaskSpec(taskSpecString);

        visionField = Config.VISIONFIELD;

        return taskSpecString;
    }

    public Observation env_start() {
    	MarioAIOptions marioAIOptions = new MarioAIOptions();

    	marioAIOptions.setVisualization(Config.VISUALIZATION);
    	marioAIOptions.setFPS(Config.FPS);
    	marioAIOptions.setMarioMode(Config.MARIO_STARTMODE);
        marioAIOptions.setLevelDifficulty(Config.DIFFICULTY);
        marioAIOptions.setLevelRandSeed(Config.LEVEL_SEED);

    	environment.reset(marioAIOptions);

        oldMarioMode = environment.getMarioMode();
        oldKillsByStomp = environment.getMarioState()[8];
        oldMarioFloatPos[0] = environment.getMarioFloatPos()[0];
        oldMarioFloatPos[1] = environment.getMarioFloatPos()[1];

        reward_win_count = 0;
        reward_death_count = 0;
        reward_hurt_count = 0;
        reward_kill_count = 0;
        reward_elapsed_frame_count = 0;
        reward_move_right_count = 0;
        reward_move_left_count = 0;
        reward_move_up_count = 0;
        reward_move_down_count = 0;

        Observation returnObservation = getObservation();

        return returnObservation;
    }

    public Reward_observation_terminal env_step(Action thisAction) {
        boolean[] keys = MarioAction.getKeysOfAction(MarioAction.valueOf(thisAction.intArray[0]));

        boolean episodeOver;

        double theReward;

        Observation returnObservation;

        environment.performAction(keys);
        environment.tick();

        returnObservation = getObservation();
        theReward = calculateReward();

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

        if(message.equals("is Mario alive?")) {
        	if(!(environment.getMarioState()[0] == Mario.STATUS_DEAD))
                return "1";
        	else
                return "0";
        }

        if(message.equals("reward win count")) {
            return Integer.toString(reward_win_count);
        }

        if(message.equals("reward death count")) {
            return Integer.toString(reward_death_count);
        }

        if(message.equals("reward hurt count")) {
            return Integer.toString(reward_hurt_count);
        }

        if(message.equals("reward kill count")) {
            return Integer.toString(reward_kill_count);
        }

        if(message.equals("reward elapsed frame count")) {
            return Integer.toString(reward_elapsed_frame_count);
        }

        if(message.equals("reward move right count")) {
            return Integer.toString(reward_move_right_count);
        }

        if(message.equals("reward move left count")) {
            return Integer.toString(reward_move_left_count);
        }

        if(message.equals("reward move up count")) {
            return Integer.toString(reward_move_up_count);
        }

        if(message.equals("reward move down count")) {
            return Integer.toString(reward_move_down_count);
        }

        return "I don't know how to respond to your message";
    }

    private Observation getObservation() {
        Observation returnObservation = new Observation(3, 0, 0);

        int[] marioEgoPos = environment.getMarioEgoPos();

        byte[][] levelScene = environment.getLevelSceneObservationZ(ZLEVEL_SCENE);
        byte[][] enemies = environment.getEnemiesObservationZ(ZLEVEL_ENEMIES);

        int[] state = observate(marioEgoPos,levelScene,enemies);

        int levelSceneMultiplier = 1;
        int enemyMultiplier = 1;

        returnObservation.intArray[0] = state[0];

        for(int i = visionField.size() - 1; i >= 0; i--) {
        	if(visionField.get(i).getType() == Type.ENEMY || visionField.get(i).getType() == Type.DETAILEDENEMY) {
        		returnObservation.intArray[2] += state[i + 1] * enemyMultiplier;
        		enemyMultiplier *= 10;
			} else if(visionField.get(i).getType() == Type.BLOCK || visionField.get(i).getType() == Type.BRIDGE) {
        		returnObservation.intArray[1] += state[i + 1] * levelSceneMultiplier;
        		levelSceneMultiplier *= 10;
        	}
		}

//        for (int x = 0; x < levelScene.length; x++) {
//			for (int y = 0; y < levelScene[x].length; y++) {
//				if(x == marioEgoPos[0] && y == marioEgoPos[1]) {
//					System.out.print("   M");
//				} else {
//					System.out.printf("%4d", levelScene[x][y]);
//				}
//			}
//			System.out.print("        ");
//			for (int y = 0; y < enemies[x].length; y++) {
//				if(x == marioEgoPos[0] && y == marioEgoPos[1]) {
//					System.out.print("   M");
//				} else {
//					System.out.printf("%4d", enemies[x][y]);
//				}
//			}
//			System.out.println("");
//        }
//        System.out.println(new State(returnObservation.intArray[0], returnObservation.intArray[1], returnObservation.intArray[2]).getStateId());
        return returnObservation;
    }

    private double calculateReward() {
        double theReward = Config.REWARD_ELAPSED_FRAME;
        reward_elapsed_frame_count++;

        Observation observation = getObservation();

        if(environment.getMarioState()[0] == Mario.STATUS_WIN) {
        	theReward += Config.REWARD_WIN;
        	reward_win_count++;
        }

        if(environment.getMarioState()[0] == Mario.STATUS_DEAD && environment.getTimeLeft() > 0) {
        	theReward += Config.REWARD_DEATH;
        	reward_death_count++;
        }

        if(environment.getMarioMode() < oldMarioMode) {
        	theReward += Config.REWARD_HURT;
        	reward_hurt_count++;
        }

        if(environment.getMarioState()[8] > oldKillsByStomp) {
        	theReward += Config.REWARD_KILL;
        	reward_kill_count++;
        }

        if(environment.getMarioFloatPos()[0] > oldMarioFloatPos[0] + 1) {
        	theReward += Config.REWARD_MOVE_RIGHT;
        	reward_move_right_count++;
        } else if(environment.getMarioFloatPos()[0] < oldMarioFloatPos[0] - 1) {
        	theReward += Config.REWARD_MOVE_LEFT;
        	reward_move_left_count++;
        }

        if((observation.intArray[1] / 100) % 100 > 0) {
        	if(environment.getMarioFloatPos()[1] < oldMarioFloatPos[1]) {
            	theReward += Config.REWARD_MOVE_UP;
            	reward_move_up_count++;
//        	} else if(environment.getMarioFloatPos()[1] > oldMarioFloatPos[1]) {
//        		theReward += Config.REWARD_MOVE_DOWN;
//            	reward_move_down_count++;
        	}
        }

        oldMarioMode = environment.getMarioMode();
        oldKillsByStomp = environment.getMarioState()[8];
        oldMarioFloatPos[0] = environment.getMarioFloatPos()[0];
        oldMarioFloatPos[1] = environment.getMarioFloatPos()[1];

        return theReward;
    }

    public static void main(String[] args) {
        EnvironmentLoader theLoader = new EnvironmentLoader(new RLGlueEnvironment());

        theLoader.run();
    }

    private int[] observate(int[] marioEgoPos, byte[][] levelScene, byte[][] enemies){
        int[] state = new int[1 + visionField.size()];

        if(environment.getMario().isInvulnerable()) {
        	state[0] = 4;
        } else {
            switch (environment.getMarioMode()) {
            case 0:
                state[0]=1;
                break;
            case 1:
                state[0]=2;
                break;
            case 2:
                state[0]=3;
                break;
            default:
                state[0]=0;
            }
        }

        int i = 1;
        for(Zone z : visionField) {

            int res = 0;
            int tempRes;

            switch (z.getType()) {
                case BRIDGE:
                    for (Block b : z.getBlocks()) {
                        tempRes = levelScene[marioEgoPos[0] - b.getY()][marioEgoPos[1] + b.getX()];
                        if (tempRes == GeneralizerLevelScene.BORDER_HILL) {
                            res = 1; //Bridge
                        }
                    }
                    break;
                case BLOCK:
                    for (Block b : z.getBlocks()) {
                        tempRes = levelScene[marioEgoPos[0] - b.getY()][marioEgoPos[1] + b.getX()];
                        if (tempRes == GeneralizerLevelScene.BRICK || tempRes == GeneralizerLevelScene.BORDER_CANNOT_PASS_THROUGH) {
                            res = 1; //Block
                        }
                    }
                    break;
                case ENEMY:
                    for (Block b : z.getBlocks()) {
                        tempRes = enemies[marioEgoPos[0] - b.getY()][marioEgoPos[1] + b.getX()];
                        if (tempRes != Sprite.KIND_NONE) {
                            res = 1; //Enemy
                        }
                        if (tempRes == Sprite.KIND_PRINCESS) {
                            res = 2; //Princess
                        }
                    }
                    break;
                case DETAILEDENEMY:
                    for (Block b : z.getBlocks()) {
                        tempRes = enemies[marioEgoPos[0] - b.getY()][marioEgoPos[1] + b.getX()];
                        if (tempRes == Sprite.KIND_PRINCESS) {
                            res = 4; //Princess
                        } else if (res < 4 && tempRes == Sprite.KIND_SPIKY) {
                            res = 3; //Spiky
//                        } else if (res < 3 && tempRes == Sprite.KIND_FIREBALL) {
//                            res = 2; //Fireball
                        } else if (res < 2 && tempRes == Sprite.KIND_GOOMBA) {
                            res = 1; //Goomba
                        }
                    }
                    break;
            }
            state[i] = res;
            i++;
        }
        return state;
    }
}