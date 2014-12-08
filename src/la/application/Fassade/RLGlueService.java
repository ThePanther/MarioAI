package la.application.Fassade;

import la.common.Reward;
import la.common.RewardsGroup;
import la.common.Zone;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Alex on 31.10.2014.
 */
public interface RLGlueService {

    /********************************
     * Mario AI Starter
     ********************************/

    public void setVisualisation(boolean isVisual);
    public void setRandomLevels(boolean isRandom);
    public void setFreezPolicy(boolean isFreezPolicy);
    public void setExploration(boolean isExploration);
    public void setStartMode(int mode);
    public void setDifficult(int difficult);
    public void setAgent(String agentName);
    public void setEpisodes(int episodes);
    public void setLevelSeed(int seed);
    public void setFPS(int fps);

    public String[] getAllMarioModes();
    public Integer[] getAllDifficulties();
    public String[] getAllAgents();

    public RewardsGroup saveRewards(List<Reward> rewards);

    public int getMarioStartState();
    public int getDifficulty();
    public String getAgentName();

    /**
     * Startet das Spiel mit einem bestimmten Agenten
     *
     */
    public void startAgent();

    /**
     * Startet das Spiel ohne Agenten
     * man kann selber spielen
     *
     */
    public void playMario();

    /**
     * loescht Tabellen aus der Datenbank
     */
    public void resetDB();

    /********************************
     * setRewards
     ********************************/

    /**
     * liefert die Aktuelle Rewardsgroup
     *
     * @return Rewardsgroup
     */
    public RewardsGroup getCurrentReward();

    /**
     * speichert Rewards in DB
     *
     * @param rewards
     */
    public void setRewards(List<Reward> rewards);

    public List<Reward> getRewards();

    /********************************
     * Statistiks
     ********************************/

    public void exportToPath(String path);


    /********************************
     * Stateviewer
     ********************************/

    // TODO: StateViewer Interfaces

    /********************************
     * VisionField
     ********************************/

    public void setVisionField(ArrayList<Zone> visionField);

    public ArrayList<Zone> getVisionField();

    public void setMarioMul(long l);

    public long getMarioMul();

    public void setEnvironmentMul(long l);

    public long getEnvironmentMul();

    public void setEnemyMul(long l);

    public long getEnemyMul();

    public boolean isVisual();
    public boolean isLevelRandom();
    public boolean isExplorationFreezed();
    public boolean isPolicyFreezed();
}
