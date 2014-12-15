package la.application.configManagement;

import la.common.Zone;
import la.rlGlue.QLearningAgent;
import la.rlGlue.SARSAAgent;

import java.util.ArrayList;

public class Config {

    private final static String[] MODES = {"Small","Large","Fire","Invincible"};
    private final static Integer[] DIFFICULTIES = {0,1,2,3};
	private final static String[] AGENTS = {SARSAAgent.NAME, QLearningAgent.NAME};

	public static String AGENT = SARSAAgent.NAME;

	public static double DISCOUNT_FACTOR = 1.0;

	public static boolean VISUALIZATION = false;
	public static boolean RANDOM_LEVELS = true;
	public static boolean FREEZE_EXPLORATION = false;
	public static boolean FREEZE_POLICY = false;

	public static int FPS = 1000;
	public static int EDISODES = 1000;
	public static int MARIO_STARTMODE = 2; // 0 = small, 1 = large, 2 = fire
	public static int LEVEL_SEED = 0;
	public static int DIFFICULTY = 0;

	public static int REWARD_WIN = 2500;
	public static int REWARD_DEATH = -1000;
	public static int REWARD_HURT = -850;
	public static int REWARD_KILL = 85;
	public static int REWARD_ELAPSED_FRAME = -1;
	public static int REWARD_MOVE_RIGHT = 2;
	public static int REWARD_MOVE_LEFT = -2;
	public static int REWARD_MOVE_UP = 2;
	public static int REWARD_MOVE_DOWN = 0;

	public static String REWARD_NAME_WIN = "Win";
	public static String REWARD_NAME_DEATH = "Loss";
	public static String REWARD_NAME_HURT = "Hurt";
	public static String REWARD_NAME_KILL = "Stomp Enemy";
	public static String REWARD_NAME_ELAPSED_FRAME = "per Frame";
	public static String REWARD_NAME_MOVE_RIGHT = "Move Right";
	public static String REWARD_NAME_MOVE_LEFT = "Move Left";
	public static String REWARD_NAME_MOVE_UP = "Move Up";
	public static String REWARD_NAME_MOVE_DOWN = "Move Down";

	public static ArrayList<Zone> VISIONFIELD = new ArrayList<>();

	public static long MARIO_MUL = 1000000000000L;
	public static long ENVIRONMENT_MUL = 1000000L;
	public static long ENEMY_MUL = 1;


    public static String[] getAllMarioModes(){ return MODES;}
    public static Integer[] getAllDifficulties(){ return DIFFICULTIES;}
    public static String[] getAllAgents(){ return AGENTS;}

}
