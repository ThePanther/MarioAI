package la.rlGlue.application.Fassade.impl;

import la.rlGlue.application.Fassade.RLGlueService;
import la.rlGlue.common.Reward;
import la.rlGlue.common.RewardsGroup;

/**
 * Created by Alex on 31.10.2014.
 */
public class Fassade implements RLGlueService {

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

    public static void startAgent(){
        // TODO: starte Agenten
    }

    public void playMario(){
        // TODO: playMario
    }

    @Override
    public void resetDB() {

    }

    @Override
    public RewardsGroup getCurrentReward() {
        return null;
    }

    @Override
    public void setRewards(Reward[] rewards) {

    }

}
