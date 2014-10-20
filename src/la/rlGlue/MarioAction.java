package la.rlGlue;

import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public enum MarioAction {
	RIGHT(0),
	LEFT(1),
	RIGHT_SPEED(2),
	LEFT_SPEED(3),
	RIGHT_SPEED_JUMP(4),
	LEFT_SPEED_JUMP(5),
	RIGHT_JUMP(6),
	LEFT_JUMP(7),
	JUMP(8),
	NONE(9);
    //TODO: SPEED/Shoot
    //TODO: JUMP_SPEED

	private final int value;

	private MarioAction(int value) {
		this.value = value;
	}

	public static MarioAction valueOf(int value) {
		MarioAction marioAction = NONE;

		for(MarioAction action : MarioAction.values()) {
			if(value == action.value) {
				marioAction = action;
			}
		}

		return marioAction;
	}

	public static boolean[] getKeysOfAction(MarioAction action) {
		boolean[] keys = new boolean[Environment.numberOfKeys];

		switch(action) {
        case RIGHT:
        	keys[Mario.KEY_RIGHT] = true;
        	break;
        case LEFT:
        	keys[Mario.KEY_LEFT] = true;
        	break;
        case RIGHT_SPEED:
        	keys[Mario.KEY_RIGHT] = true;
        	keys[Mario.KEY_SPEED] = true;
        	break;
        case LEFT_SPEED:
        	keys[Mario.KEY_RIGHT] = true;
        	keys[Mario.KEY_SPEED] = true;
        	break;
        case RIGHT_SPEED_JUMP:
        	keys[Mario.KEY_RIGHT] = true;
        	keys[Mario.KEY_SPEED] = true;
        	keys[Mario.KEY_JUMP] = true;
        	break;
        case LEFT_SPEED_JUMP:
        	keys[Mario.KEY_RIGHT] = true;
        	keys[Mario.KEY_SPEED] = true;
        	keys[Mario.KEY_JUMP] = true;
        	break;
        case RIGHT_JUMP:
        	keys[Mario.KEY_RIGHT] = true;
        	keys[Mario.KEY_JUMP] = true;
        	break;
        case LEFT_JUMP:
        	keys[Mario.KEY_RIGHT] = true;
        	keys[Mario.KEY_JUMP] = true;
        	break;
        case JUMP:
        	keys[Mario.KEY_JUMP] = true;
        	break;
        default:
        }

		return keys;
	}
}