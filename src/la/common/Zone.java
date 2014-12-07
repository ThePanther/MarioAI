package la.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Benjamin on 18.10.2014.
 */
@SuppressWarnings("serial")
public class Zone implements Serializable {
    private ArrayList<Block> blocks;
    private Type type;

    public Zone(ArrayList<Block> blocks, Type type) {
        this.blocks = blocks;
        this.type = type;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
