import java.awt.*;
import java.util.Random;

/**
 * Created by Alex Backer on 12/2/2016.
 */
public class FieldSquares extends  GameObject implements  IColorable {
    //da bi di qua
    private boolean owned;
    //gan bi di qua
    private boolean almostOwned;
    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public boolean isAlmostOwned() {
        return almostOwned;
    }

    public void setAlmostOwned(boolean almostOwned) {
        this.almostOwned = almostOwned;
    }
    public FieldSquares() {
        setWidth(5);
        setHeight(5);
        setOwned(false);
        setAlmostOwned(false);
    }

    // mot mot o nen co mau co dinh
    public void setRandColor()
    {

    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}
