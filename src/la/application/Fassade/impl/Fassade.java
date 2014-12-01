package la.application.Fassade.impl;

import context.ManagerFactory;
import la.application.Fassade.RLGlueService;
import la.application.configManagement.Config;
import la.application.exportManagement.ExportManager;
import la.application.starter.MarioAIStarter;
import la.application.starter.Play;
import la.common.Reward;
import la.common.RewardsGroup;
import la.common.Zone;
import la.persistence.database.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 31.10.2014.
 */
public class Fassade implements RLGlueService {

    Database db = ManagerFactory.getManager(Database.class);

    @Override
    public void setVisualisation(boolean isVisual) {Config.VISUALIZATION = isVisual;}

    @Override
    public void setRandomLevels(boolean isRandom) {Config.RANDOM_LEVELS = isRandom;}

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
        return db.getLastRewardsGroup();
    }

    @Override
    public void setRewards(List<Reward> rewards) {
    	for(Reward reward : rewards) {
    		if(reward.getName().equals(Config.REWARD_NAME_WIN)) {
    			Config.REWARD_WIN = reward.getReward();
    		} else if(reward.getName().equals(Config.REWARD_NAME_DEATH)) {
    			Config.REWARD_DEATH = reward.getReward();
    		} else if(reward.getName().equals(Config.REWARD_NAME_HURT)) {
    			Config.REWARD_HURT = reward.getReward();
    		} else if(reward.getName().equals(Config.REWARD_NAME_KILL)) {
    			Config.REWARD_KILL = reward.getReward();
    		} else if(reward.getName().equals(Config.REWARD_NAME_ELAPSED_FRAME)) {
    			Config.REWARD_ELAPSED_FRAME = reward.getReward();
    		} else if(reward.getName().equals(Config.REWARD_NAME_MOVE_RIGHT)) {
    			Config.REWARD_MOVE_RIGHT = reward.getReward();
    		} else if(reward.getName().equals(Config.REWARD_NAME_MOVE_LEFT)) {
    			Config.REWARD_MOVE_LEFT = reward.getReward();
    		} else if(reward.getName().equals(Config.REWARD_NAME_MOVE_UP)) {
    			Config.REWARD_MOVE_UP = reward.getReward();
    		} else if(reward.getName().equals(Config.REWARD_NAME_MOVE_DOWN)) {
    			Config.REWARD_MOVE_DOWN = reward.getReward();
    		}
    	}
    }

    @Override
    public List<Reward> getRewards() {
    	List<Reward> rewards = new ArrayList<>();
        rewards.add(new Reward(Config.REWARD_NAME_WIN, Config.REWARD_WIN));
        rewards.add(new Reward(Config.REWARD_NAME_DEATH, Config.REWARD_DEATH));
        rewards.add(new Reward(Config.REWARD_NAME_HURT, Config.REWARD_HURT));
        rewards.add(new Reward(Config.REWARD_NAME_KILL, Config.REWARD_KILL));
        rewards.add(new Reward(Config.REWARD_NAME_ELAPSED_FRAME, Config.REWARD_ELAPSED_FRAME));
        rewards.add(new Reward(Config.REWARD_NAME_MOVE_RIGHT, Config.REWARD_MOVE_RIGHT));
        rewards.add(new Reward(Config.REWARD_NAME_MOVE_LEFT, Config.REWARD_MOVE_LEFT));
        rewards.add(new Reward(Config.REWARD_NAME_MOVE_UP, Config.REWARD_MOVE_UP));
        rewards.add(new Reward(Config.REWARD_NAME_MOVE_DOWN, Config.REWARD_MOVE_DOWN));

    	return rewards;
    }


    public void exportToPath(String path){
        new ExportManager(path);
    }

    @Override
    public void setVisionField(ArrayList<Zone> visionField) {
        Config.VISIONFIELD = visionField;
    }

    public ArrayList<Zone> getVisionField() {
        return Config.VISIONFIELD;
    }

    public void setMarioMul(long l){
        Config.MARIO_MUL = l;
    }

    public long getMarioMul() {
        return Config.MARIO_MUL;
    }

    public void setEnvironmentMul(long l) {
        Config.ENVIRONMENT_MUL = l;
    }

    public long getEnvironmentMul() {
        return Config.ENVIRONMENT_MUL;
    }

    public void setEnemyMul(long l) {
        Config.ENEMY_MUL = l;
    }

    public long getEnemyMul() {
        return Config.ENEMY_MUL;
    }
}
