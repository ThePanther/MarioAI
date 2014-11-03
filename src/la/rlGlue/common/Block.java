package la.rlGlue.common;

/**
 * Created by Benjamin on 18.10.2014.
 */
public class Block {
    private int x = 0;
    private int y = 0;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
