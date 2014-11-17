package la.rlGlue.common;

/**
 * Created by Alex on 29.10.2014.
 */
public class State {
    private static final long MARIO_MUL = 1000000000000L;
    private static final long ENVIRONMENT_MUL = 1000000L;
    private static final long ENEMY_MUL = 1;

    private long marioState;
    private long environmentState;
    private long enemyState;
    private long stateId; 

    public State(long marioState, long enviromentState, long enemyState){
        int enemyFlag = 0;

        if (enemyState != 0) {
            enemyFlag = 1;
        }

        this.marioState = ((marioState*enemyFlag)+1);
        this.environmentState = enviromentState;
        this.enemyState = enemyState;

        long id = (marioState*MARIO_MUL) + (environmentState*ENVIRONMENT_MUL) + (enemyState*ENEMY_MUL);

        this.setStateId(id);
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

    public long getEnvironmentState() {
        return environmentState;
    }

    public long getEnemyState() {
        return enemyState;
    }

    public void setMarioState(long marioState) {
        this.marioState = marioState;
    }

    public void setEnvironmentState(long enviromentState) {
        this.environmentState = enviromentState;
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
