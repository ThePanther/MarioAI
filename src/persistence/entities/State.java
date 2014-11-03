package persistence.entities;

/**
 * Created by Alex on 29.10.2014.
 */
public class State {
    private static final long MARIO_MUL = 100000000000L;
    private static final long ENVIRONMENT_MUL =   100000;
    private static final long ENEMY_MUL = 1;

    private long marioState;
    private long enviromentState;
    private long enemyState;
    private long stateId; 

    public State(long marioState, long enviromentState, long enemyState){
        this.marioState = marioState;
        this.enviromentState = enviromentState;
        this.enemyState = enemyState;
        this.setStateId((marioState*MARIO_MUL) + (enviromentState*ENVIRONMENT_MUL) + (enemyState*ENEMY_MUL));
    }

    public State(long stateId) {
    	this.stateId = stateId; 
	}

	public long getStateId(){
        return stateId; 
    }
	
    public long getMarioState() {
        return marioState;
    }

    public long getEnviromentState() {
        return enviromentState;
    }

    public long getEnemyState() {
        return enemyState;
    }

    public void setMarioState(long marioState) {
        this.marioState = marioState;
    }

    public void setEnviromentState(long enviromentState) {
        this.enviromentState = enviromentState;
    }

    public void setEnemyState(long enemyState) {
        this.enemyState = enemyState;
    }

	public void setStateId(long stateId) {
		this.stateId = stateId;
	}
	
    @Override
    public String toString() {
    	return getStateId()+"";
    }

}
