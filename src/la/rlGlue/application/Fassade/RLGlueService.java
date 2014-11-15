package la.rlGlue.application.Fassade;

import la.rlGlue.common.Reward;
import la.rlGlue.common.RewardsGroup;

/**
 * Created by Alex on 31.10.2014.
 */
public interface RLGlueService {

    /********************************
     * Mario AI Starter
     ********************************/

    /**
     * liefert alle moeglichen Mario-Startzustaende
     *
     * @return Array von Strings
     */
    public String[] getMarioStartState();

    /**
     * liefert alle moeglichen Schwierigkeitsgrade
     *
     * @return Array von Integer
     */
    public Integer[] getDifficult();

    /**
     * liefert alle vorhandenen Agentennamen
     *
     * @return Array von Strings
     */
    public String[] getAgentNames();

    /**
     * Startet das Spiel mit einem bestimmten Agenten
     *
     */
    public static void startAgent(){
        //TODO
    }

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
    public void setRewards(Reward[] rewards);

    /********************************
     * Statistiks
     ********************************/

    // TODO: Statistik Interfaces


    /********************************
     * Stateviewer
     ********************************/

    // TODO: StateViewer Interfaces
}
