package la.rlGlue;

import ch.idsia.benchmark.mario.engine.sprites.Sprite;

public enum ZoomLevelEnemy {
	NONE(0, Sprite.KIND_NONE),
	GOOMBA(1, Sprite.KIND_GOOMBA),
	SPIKY(2, Sprite.KIND_SPIKY),
	FIREBALL(3, Sprite.KIND_FIREBALL);

	public static final int count = 4;
	private final int value;
	private final int kind;

	private ZoomLevelEnemy(int value, int kind) {
		this.value = value;
		this.kind = kind;
	}

	public static ZoomLevelEnemy valueOf(int kind) {
		ZoomLevelEnemy zoomLevelEnemy = NONE;

		for(ZoomLevelEnemy enemy : ZoomLevelEnemy.values()) {
			if(kind == enemy.kind) {
				zoomLevelEnemy = enemy;
			}
		}

		return zoomLevelEnemy;
	}

	public int getValue() {
		return value;
	}
}