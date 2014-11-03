package la.rlGlue.common;

import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public enum MarioAction {
    NONE(0),
    SPEED(1),
    JUMP(2),
    JUMP_SPEED(3),
    RIGHT(4),
    RIGHT_SPEED(5),
    RIGHT_JUMP(6),
    RIGHT_SPEED_JUMP(7),
    LEFT(8),
	LEFT_SPEED(9),
    LEFT_JUMP(10),
	LEFT_SPEED_JUMP(11);

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
            case NONE:
                break;
            case SPEED:
                keys[Mario.KEY_SPEED] = true;
                break;
            case JUMP:
                keys[Mario.KEY_JUMP] = true;
                break;
            case JUMP_SPEED:
                keys[Mario.KEY_JUMP] = true;
                keys[Mario.KEY_SPEED] = true;
                break;
            case RIGHT:
                keys[Mario.KEY_RIGHT] = true;
                break;
            case RIGHT_SPEED:
                keys[Mario.KEY_RIGHT] = true;
                keys[Mario.KEY_SPEED] = true;
                break;
            case RIGHT_JUMP:
                keys[Mario.KEY_RIGHT] = true;
                keys[Mario.KEY_JUMP] = true;
                break;
            case RIGHT_SPEED_JUMP:
                keys[Mario.KEY_RIGHT] = true;
                keys[Mario.KEY_SPEED] = true;
                keys[Mario.KEY_JUMP] = true;
                break;
            case LEFT:
                keys[Mario.KEY_LEFT] = true;
                break;
            case LEFT_SPEED:
                keys[Mario.KEY_RIGHT] = true;
                keys[Mario.KEY_SPEED] = true;
                break;
            case LEFT_JUMP:
                keys[Mario.KEY_RIGHT] = true;
                keys[Mario.KEY_JUMP] = true;
                break;
            case LEFT_SPEED_JUMP:
                keys[Mario.KEY_RIGHT] = true;
                keys[Mario.KEY_SPEED] = true;
                keys[Mario.KEY_JUMP] = true;
                break;
            default:
        }
		return keys;
	}
}