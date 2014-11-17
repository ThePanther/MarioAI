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

import la.rlGlue.application.configManagement.Config;
import la.rlGlue.common.Block;
import la.rlGlue.common.Type;
import la.rlGlue.common.Zone;
import la.rlGlue.common.MarioAction;

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

    private final static double DISCOUNT_FACTOR = 1.0;

    private float[] oldMarioFloatPos = new float[2];

    private int oldMarioMode;
    private int oldKillsByStomp;

    public String env_init() {
        TaskSpecVRLGLUE3 theTaskSpecObject = new TaskSpecVRLGLUE3();

        theTaskSpecObject.setEpisodic();
        theTaskSpecObject.setDiscountFactor(DISCOUNT_FACTOR);
        theTaskSpecObject.addDiscreteObservation(new IntRange(0, STATES_COUNT));
        theTaskSpecObject.addDiscreteAction(new IntRange(0, ACTIONS_COUNT));
//        theTaskSpecObject.setRewardRange(new DoubleRange(Config.REWARD_DEATH, Config.REWARD_WIN));
        theTaskSpecObject.setRewardRange(new DoubleRange(Config.REWARD_DEATH, Config.REWARD_KILL));

        String taskSpecString = theTaskSpecObject.toTaskSpec();

        TaskSpec.checkTaskSpec(taskSpecString);

        //Blocksichtfeld erzeugen
        createVisionField();

        return taskSpecString;
    }

    public Observation env_start() {
    	MarioAIOptions marioAIOptions = new MarioAIOptions();

    	marioAIOptions.setVisualization(Config.VISUALIZATION);
    	marioAIOptions.setFPS(Config.FPS);
    	marioAIOptions.setMarioMode(Config.MARIO_STARTMODE);

    	environment.reset(marioAIOptions);

        oldMarioMode = environment.getMarioMode();
        oldKillsByStomp = environment.getMarioState()[8];
        oldMarioFloatPos[0] = environment.getMarioFloatPos()[0];
        oldMarioFloatPos[1] = environment.getMarioFloatPos()[1];

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

//      for (int x = 0; x < levelScene.length; x++) {
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
//      }
//      System.out.println(returnObservation.intArray[0] * 1000000000000L + returnObservation.intArray[1] * 1000000L + returnObservation.intArray[2]);
        return returnObservation;
    }

    private double calculateReward() {
        double theReward = Config.REWARD_ELAPSED_FRAME;

        Observation observation = getObservation();

//        if(environment.getMarioState()[0] == Mario.STATUS_WIN) {
//        	theReward += Config.REWARD_WIN;
//        } else if(environment.getMarioState()[0] == Mario.STATUS_DEAD){
//        	theReward += Config.REWARD_DEATH;
//        }

        if(environment.getMarioState()[0] == Mario.STATUS_DEAD && environment.getTimeLeft() > 0) {
        	theReward += Config.REWARD_DEATH;
        }

        if(environment.getMarioMode() < oldMarioMode) {
        	theReward += Config.REWARD_HURT;
        }

        if(environment.getMarioState()[8] > oldKillsByStomp) {
        	theReward += Config.REWARD_KILL;
        }

        if(environment.getMarioFloatPos()[0] > oldMarioFloatPos[0] + 1) {
        	theReward += Config.REWARD_MOVE_RIGHT;
        } else if(environment.getMarioFloatPos()[0] < oldMarioFloatPos[0] - 1) {
        	theReward += Config.REWARD_MOVE_LEFT;
        }

        if((observation.intArray[1] / 100) % 100 > 0) {
        	if(environment.getMarioFloatPos()[1] < oldMarioFloatPos[1]) {
            	theReward += Config.REWARD_MOVE_UP;
//        	} else if(environment.getMarioFloatPos()[1] > oldMarioFloatPos[1]) {
//        		theReward += Config.REWARD_MOVE_DOWN;
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

    private static void createVisionField(){

        Block s1b1 = new Block(0,4);
        Block s1b2 = new Block(0,3);
        Block s1b3 = new Block(0,2);

        Block s2b1 = new Block(1,2);

        Block s3b1 = new Block(1,1);

        Block s4b1 = new Block(1,0);

        Block s5b1 = new Block(1,-1);
        Block s5b2 = new Block(1,-2);

        Block s6b1 = new Block(0,-1);

        Block e1b1 = new Block(0,2);
        Block e1b2 = new Block(1,2);

        Block e2b1 = new Block(1,1);
        Block e2b2 = new Block(1,0);
        Block e2b3 = new Block(1,-1);

        Block e3b1 = new Block(2,1);
        Block e3b2 = new Block(2,0);
        Block e3b3 = new Block(2,-1);

        Block e4b1 = new Block(0,-1);

        Block e5b1 = new Block(-1,1);
        Block e5b2 = new Block(-1,0);

        Block e6b1 = new Block(0,0);
        Block e6b2 = new Block(0,1);

        ArrayList<Block> s1b = new ArrayList<Block>();
        s1b.add(s1b1);
        s1b.add(s1b2);
        s1b.add(s1b3);

        ArrayList<Block> s2b = new ArrayList<Block>();
        s2b.add(s2b1);

        ArrayList<Block> s3b = new ArrayList<Block>();
        s3b.add(s3b1);

        ArrayList<Block> s4b = new ArrayList<Block>();
        s4b.add(s4b1);

        ArrayList<Block> s5b = new ArrayList<Block>();
        s5b.add(s5b1);
        s5b.add(s5b2);

        ArrayList<Block> s6b = new ArrayList<Block>();
        s6b.add(s6b1);

        ArrayList<Block> e1b = new ArrayList<Block>();
        e1b.add(e1b1);
        e1b.add(e1b2);

        ArrayList<Block> e2b = new ArrayList<Block>();
        e2b.add(e2b1);
        e2b.add(e2b2);
        e2b.add(e2b3);

        ArrayList<Block> e3b = new ArrayList<Block>();
        e3b.add(e3b1);
        e3b.add(e3b2);
        e3b.add(e3b3);

        ArrayList<Block> e4b = new ArrayList<Block>();
        e4b.add(e4b1);

        ArrayList<Block> e5b = new ArrayList<Block>();
        e5b.add(e5b1);
        e5b.add(e5b2);

        ArrayList<Block> e6b = new ArrayList<Block>();
        e5b.add(e6b1);
        e5b.add(e6b2);

        Zone s1 = new Zone(s1b, Type.BRIDGE);
        Zone s2 = new Zone(s2b, Type.BLOCK);
        Zone s3 = new Zone(s3b, Type.BLOCK);
        Zone s4 = new Zone(s4b, Type.BLOCK);
        Zone s5 = new Zone(s5b, Type.BLOCK);
        Zone s6 = new Zone(s6b, Type.BLOCK);

        Zone e1 = new Zone(e1b, Type.ENEMY);
        Zone e2 = new Zone(e2b, Type.DETAILEDENEMY);
        Zone e3 = new Zone(e3b, Type.DETAILEDENEMY);
        Zone e4 = new Zone(e4b, Type.DETAILEDENEMY);
        Zone e5 = new Zone(e5b, Type.ENEMY);
        Zone e6 = new Zone(e6b, Type.ENEMY);

        visionField.add(s1);
        visionField.add(s2);
        visionField.add(s3);
        visionField.add(s4);
        visionField.add(s5);
        visionField.add(s6);

        visionField.add(e1);
        visionField.add(e2);
        visionField.add(e3);
        visionField.add(e4);
        visionField.add(e5);
        visionField.add(e6);

    }

}