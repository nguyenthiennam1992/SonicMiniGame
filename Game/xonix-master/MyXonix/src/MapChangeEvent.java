import java.awt.*;
/**
 * Created by Alex Backer on 12/2/2016.
 */
public class MapChangeEvent extends Rectangle {
    private Map map;

    public MapChangeEvent(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
