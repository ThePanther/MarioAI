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

import la.application.configManagement.Config;

import org.rlcommunity.rlglue.codec.RLGlue;

/**
 *
 * MarioAI Experiment
 */
public class Experiment {

    private int numOfEpisodes = Config.EDISODES;
    private int whichEpisode = 0;

    /* Run One Episode of length maximum cutOff*/
    private void runEpisode(int stepLimit) {

        //Starten der Episode
        int terminal = RLGlue.RL_episode(stepLimit);

        int totalSteps = RLGlue.RL_num_steps();
        double totalReward = RLGlue.RL_return();

        System.out.println("Episode " + whichEpisode + "\t " + totalSteps + " steps \t" + totalReward + " total reward\t " + terminal + " natural end");

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

        System.out.println("\n\n----------Summary----------");

        int totalSteps = RLGlue.RL_num_steps();
        double totalReward = RLGlue.RL_return();
        System.out.println("It ran for " + totalSteps + " steps, total reward was: " + totalReward);
        RLGlue.RL_cleanup();

    }

    public static void main(String[] args) {
        Experiment theExperiment = new Experiment();
        theExperiment.runExperiment();
    }
}
