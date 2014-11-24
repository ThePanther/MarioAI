package la.application.configManagement;

import la.rlGlue.QLearningAgent;
import la.rlGlue.SARSAAgent;

public class Config {

    private final static String[] MODES = {"Small","Large","Fire","Invincible"};
    private final static Integer[] DIFFICULTIES = {0,1,2,3};
	private final static String[] AGENTS = {SARSAAgent.NAME, QLearningAgent.NAME};

	public static String AGENT = SARSAAgent.NAME;

	public static boolean VISUALIZATION = true;
	public static boolean FREEZE_EXPLORATION = false;
	public static boolean FREEZE_POLICY = false;

	public static int FPS = 1000;
	public static int EDISODES = 1;
	public static int MARIO_STARTMODE = 2; // 0 = small, 1 = large, 2 = fire
	public static int LEVEL_SEED = 0; // TODO
	public static int DIFFICULTY = 0; // TODO

	public static double REWARD_WIN = 10.0;
	public static double REWARD_DEATH = -10.0;
	public static double REWARD_HURT = -9.0;
	public static double REWARD_KILL = 3.8;
	public static double REWARD_ELAPSED_FRAME = -3.2;
	public static double REWARD_MOVE_RIGHT = 3.0;
	public static double REWARD_MOVE_LEFT = -2.4;
	public static double REWARD_MOVE_UP = 3.4;
	public static double REWARD_MOVE_DOWN = -0.9;

    public static String[] getAllMarioModes(){ return MODES;}
    public static Integer[] getAllDifficulties(){ return DIFFICULTIES;}
    public static String[] getAllAgents(){ return AGENTS;}

}
