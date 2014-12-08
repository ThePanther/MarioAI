package la.application.starter;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.benchmark.tasks.MarioCustomSystemOfValues;
import ch.idsia.tools.MarioAIOptions;
import ch.idsia.benchmark.mario.environments.MarioEnvironment;
import la.application.configManagement.Config;

public class Play extends Thread{
    public MarioAIOptions marioAIOptions;
    private BasicTask basicTask;
    private MarioCustomSystemOfValues mcsov;
    private String[] options;

    public Play(String[] options){
        this.options = options;
    }

    @Override
    public void run() {
        marioAIOptions = new MarioAIOptions(options);
        basicTask = new BasicTask(marioAIOptions);
        marioAIOptions.setVisualization(true);
        marioAIOptions.setFPS(Config.FPS);
        marioAIOptions.setMarioMode(Config.MARIO_STARTMODE);
        marioAIOptions.setLevelDifficulty(Config.DIFFICULTY);
        marioAIOptions.setLevelRandSeed(Config.LEVEL_SEED);
        // basicTask.reset(marioAIOptions);
        mcsov = new MarioCustomSystemOfValues();
        // basicTask.runSingleEpisode();
        // run 1 episode with same options, each time giving output of Evaluation info.
        // verbose = false
        basicTask.doEpisodes(1,true, 1);
        System.out.println("\nEvaluationInfo: \n" + basicTask.getEnvironment().getEvaluationInfoAsString());
        System.out.println("\nCustom : \n" + basicTask.getEnvironment().getEvaluationInfo().computeWeightedFitness(mcsov));
        //System.exit(0); 
        MarioEnvironment env = ((MarioEnvironment) basicTask.getEnvironment());

    }

    public static void main(String[] args){
       Play p = new Play(args);
       p.start();

        /*Thread t = new Thread(new Runnable() {
           @Override
           public void run() {
               while (true) {
                   try {
                       //System.out.println("Sleep Alive:"+p.isAlive());
                       Thread.sleep(3000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
       });
        t.start();
*/

    }
}
