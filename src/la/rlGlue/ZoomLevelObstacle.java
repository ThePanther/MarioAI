package la.rlGlue;

import ch.idsia.benchmark.mario.engine.GeneralizerLevelScene;

public enum ZoomLevelObstacle {
	NONE(0, 0),
	SOFT(1, GeneralizerLevelScene.BRICK),
	COIN(2, GeneralizerLevelScene.COIN_ANIM),
	HARD(3, GeneralizerLevelScene.BORDER_CANNOT_PASS_THROUGH),
	BORDER(4, GeneralizerLevelScene.BORDER_HILL),
	DANGER(5, GeneralizerLevelScene.FLOWER_POT_OR_CANNON);

	public static final int count = 6;
	private final int value;
	private final int kind;

	private ZoomLevelObstacle(int value, int kind) {
		this.value = value;
		this.kind = kind;
	}

	public static ZoomLevelObstacle valueOf(int kind) {
		ZoomLevelObstacle zoomLevelObstacle = NONE;

		for(ZoomLevelObstacle obstacle : ZoomLevelObstacle.values()) {
			if(kind == obstacle.kind) {
				zoomLevelObstacle = obstacle;
			}
		}

		return zoomLevelObstacle;
	}

	public int getValue() {
		return value;
	}
}