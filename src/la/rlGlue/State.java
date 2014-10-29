package la.rlGlue;

/**
 * Created by Alex on 29.10.2014.
 */
public class State {
    private long marioMul = 100000000000L;
    private long enviromentMul = 100000;
    private long enemyMul = 1;

    private long marioState;
    private long enviromentState;
    private long enemyState;

    public State(long marioState, long enviromentState, long enemyState){
        this.marioState = marioState;
        this.enviromentState = enviromentState;
        this.enemyState = enemyState;
    }

    public long getStateId(){
        return (marioState*marioMul) + (enviromentState*enviromentMul) + (enemyState*enemyMul);
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
}
