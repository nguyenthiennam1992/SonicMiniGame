import java.awt.*;
/**
 * Created by Alex Backer on 12/3/2016.
 */
public class Tile extends Point {
    TileState state;

    public Tile(int x, int y, TileState state) {
        super(x, y);
        this.state = state;
    }
}
