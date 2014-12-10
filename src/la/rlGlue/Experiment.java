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
 *  $HeadURL: http://rl-glue-ext.googlecode.com/svn/trunk/projects/codecs/Java/examples/skeleton-sample/SkeletonExperiment.java $
 * 
 */

package la.rlGlue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import la.application.configManagement.Config;
import la.common.RewardsGroup;
import la.common.Try;
import la.persistence.database.Database;
import la.persistence.database.impl.DBCommunication;

import org.rlcommunity.rlglue.codec.RLGlue;

import context.ManagerFactory;

/**
 *
 * MarioAI Experiment
 */
public class Experiment {

	private Database db;
	private List<Try> aTryList; 

	private Random randGenerator = new Random();

	private int numOfEpisodes = Config.EDISODES;
    private int whichEpisode = 0;
	private RewardsGroup rewardsGroup;

    /* Run One Episode of length maximum cutOff*/
    private void runEpisode(int stepLimit) {
    	if(Config.RANDOM_LEVELS) {
    		Config.LEVEL_SEED = randGenerator.nextInt();
    	}

        //Starten der Episode
        int terminal = RLGlue.RL_episode(stepLimit);

        int totalSteps = RLGlue.RL_num_steps();
        double totalReward = RLGlue.RL_return();

        System.out.println("Episode " + whichEpisode + "\t " + totalSteps + " steps \t" + totalReward + " total reward\t " + terminal + " natural end");
        
        
        Try aTry = new Try(Integer.valueOf(RLGlue.RL_env_message("is Mario alive?")), RLGlue.RL_return(), RLGlue.RL_num_steps()); 
		aTry.setReward_win_count(Integer.valueOf(RLGlue.RL_env_message("reward win count")));
		aTry.setReward_death_count(Integer.valueOf(RLGlue.RL_env_message("reward death count")));
		aTry.setReward_hurt_count(Integer.valueOf(RLGlue.RL_env_message("reward hurt count")));
		aTry.setReward_kill_count(Integer.valueOf(RLGlue.RL_env_message("reward kill count")));
		aTry.setReward_elapsed_frame_count(Integer.valueOf(RLGlue.RL_env_message("reward elapsed frame count")));
		aTry.setReward_move_right_count(Integer.valueOf(RLGlue.RL_env_message("reward move right count")));
		aTry.setReward_move_left_count(Integer.valueOf(RLGlue.RL_env_message("reward move left count")));
		aTry.setReward_move_up_count(Integer.valueOf(RLGlue.RL_env_message("reward move up count")));
		aTry.setReward_move_down_count(Integer.valueOf(RLGlue.RL_env_message("reward move down count")));
		aTryList.add(aTry); 

        
        whichEpisode++;
    }

    public void runExperiment() {
        System.out.println("\n\nExperiment starting up!");
        String taskSpec = RLGlue.RL_init();
        System.out.println("RL_init called, the environment sent task spec: " + taskSpec);

        System.out.println("\n\n----------Sending some messages----------");

        /*Talk to the agent and environment a bit...*/
        String responseMessage = RLGlue.RL_agent_message("what is your name?");
        System.out.println("Agent responded to \"what is your name?\" with: " + responseMessage);

        responseMessage = RLGlue.RL_agent_message("If at first you don't succeed; call it version 1.0");
        System.out.println("Agent responded to \"If at first you don't succeed; call it version 1.0  \" with: " + responseMessage + "\n");

        responseMessage = RLGlue.RL_env_message("what is your name?");
        System.out.println("Environment responded to \"what is your name?\" with: " + responseMessage);
        responseMessage = RLGlue.RL_env_message("If at first you don't succeed; call it version 1.0");
        System.out.println("Environment responded to \"If at first you don't succeed; call it version 1.0  \" with: " + responseMessage);

        System.out.println("\n\n----------Running episodes----------");

        /* Remember that stepLimit of 0 means there is no limit at all!*/
        for(int i=0; i<numOfEpisodes; i++) {
            runEpisode(0);
        }

        db.saveAll(aTryList, rewardsGroup);			
        aTryList.clear();
        
        System.out.println("\n\n----------Summary----------");

        int totalSteps = RLGlue.RL_num_steps();
        double totalReward = RLGlue.RL_return();
        System.out.println("It ran for " + totalSteps + " steps, total reward was: " + totalReward);
        RLGlue.RL_cleanup();

    }
    public Experiment() {
        db = ManagerFactory.getManager(Database.class);
        aTryList = new ArrayList<Try>(); 
    	rewardsGroup = db.getLastRewardsGroup();
   	}

    public static void main(String[] args) {
        Experiment theExperiment = new Experiment();
        theExperiment.runExperiment();
    }
}
