package la.rlGlue.application.starter;

import la.rlGlue.Experiment;
import la.rlGlue.RLGlueEnvironment;
import la.rlGlue.SARSAAgent;
import la.rlGlue.SkeletonAgent;
import la.rlGlue.application.configManagement.Config;
import org.rlcommunity.rlglue.codec.AgentInterface;
import org.rlcommunity.rlglue.codec.EnvironmentInterface;
import org.rlcommunity.rlglue.codec.LocalGlue;
import org.rlcommunity.rlglue.codec.RLGlue;
public class MarioAIStarter extends Thread{

    private AgentInterface theAgent;


    private String[] options;

    public MarioAIStarter(String[] options){
        this.options = options;
    }

    @Override
    public void run() {
        //Create the Agent

        if(Config.AGENT.equals(SARSAAgent.NAME)) {
            theAgent=new SARSAAgent();
        } else {
            theAgent=new SkeletonAgent();
        }

        //Create the Environment
        EnvironmentInterface theEnvironment=new RLGlueEnvironment();

        LocalGlue localGlueImplementation=new LocalGlue(theEnvironment,theAgent);
        RLGlue.setGlue(localGlueImplementation);


        //Run the main method of the Skeleton Experiment, using the arguments were were passed
        //This will run the experiment in the main thread.  The Agent and Environment will run
        //locally, without sockets.
        Experiment.main(options);
        System.out.println("RunAllNoSockets Complete");
    }

}
