import java.awt.*;
/*
* Tinh trang cua tung o
*/
/**
 * Created by Alex Backer on 12/1/2016.
 */
// doi mau nen cua map o MapPanel
public enum TileState {
    EARTH('+', new Color(128, 128, 128)),
    WATER('O', new Color(66, 133, 244)),
    HERO('H', new Color(234, 67, 53)),
    ENEMY('E', Color.RED),
    PATH('x', Color.BLACK);


    final char symbol;
    final Color color;

    private TileState(char symbol, Color color) {
        this.symbol = symbol;
        this.color = color;
    }
}
