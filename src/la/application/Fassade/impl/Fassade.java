package la.application.Fassade.impl;

import context.ManagerFactory;
import la.application.Fassade.RLGlueService;
import la.application.configManagement.Config;
import la.application.exportManagement.ExportManager;
import la.application.starter.MarioAIStarter;
import la.application.starter.Play;
import la.common.Reward;
import la.common.RewardsGroup;
import la.persistence.database.Database;
import la.persistence.database.impl.DatabaseImpl;

import java.util.List;

/**
 * Created by Alex on 31.10.2014.
 */
public class Fassade implements RLGlueService {

    Database db = ManagerFactory.getManager(Database.class);

    @Override
    public void setVisualisation(boolean isVisual) {Config.VISUALIZATION = isVisual;}

    @Override
    public void setFreezPolicy(boolean isFreezPolicy) {Config.FREEZE_POLICY=isFreezPolicy;}

    @Override
    public void setExploration(boolean isExploration) {Config.FREEZE_EXPLORATION=isExploration;}

    @Override
    public void setStartMode(int mode) {Config.MARIO_STARTMODE=mode;}

    @Override
    public void setDifficult(int difficult) {Config.DIFFICULTY=difficult;}

    @Override
    public void setAgent(String agentName) {Config.AGENT=agentName;}

    @Override
    public void setEpisodes(int episodes) {Config.EDISODES=episodes;}

    @Override
    public void setLevelSeed(int seed) {Config.LEVEL_SEED=seed;}

    @Override
    public void setFPS(int fps) {Config.FPS=fps;}

    public String[] getAllMarioModes(){return Config.getAllMarioModes();}
    public Integer[] getAllDifficulties(){return Config.getAllDifficulties();}
    public String[] getAllAgents(){return Config.getAllAgents();}

    @Override
    public RewardsGroup saveRewards(List<Reward> rewards) {
        return db.getRewardsGroup(rewards);
    }

    public String[] getMarioStartState(){
        // TODO: getMarioStartState
        String[] marioStateArr = new String[0];
        return marioStateArr;
    }

    public Integer[] getDifficult(){
        // TODO: getDifficult
        Integer[] difficultArr = new Integer[0];
        return difficultArr;
    }

    public String[] getAgentNames(){
        // TODO: getAgentNames
        String[] agentNameArr = new String[0];
        return agentNameArr;
    }

    public void startAgent(){
        String[] options = new String[0];
        MarioAIStarter starter = new MarioAIStarter(options);
        starter.start();
    }

    public void playMario(){
        String[] options = new String[0];
        Play playThread = new Play(options);
        playThread.start();
    }

    @Override
    public void resetDB() {
        db.reset();
    }

    @Override
    public RewardsGroup getCurrentReward() {
        return null;
    }

    @Override
    public void setRewards(Reward[] rewards) {

    }


    public void exportToPath(String path){
        new ExportManager(path);
    }

}