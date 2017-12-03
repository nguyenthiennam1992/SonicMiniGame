import javax.swing.*;
import java.awt.*;
/**
 * Created by Alex Backer on 12/2/2016.
 */
public class MapPanel extends JPanel implements MapChangeListener {
    final static Dimension TILE_SIZE = new Dimension(25, 25);
    final static Insets TILE_INSETS = new Insets(1, 1, 1, 1);

    Map map;
    BallController ballController;

    JLabel[][] labels;

    // doi mau cua panel
    public MapPanel(int movementType) {
       this.ballController = new BallController();

        setLayout(new GridBagLayout());
        setBackground(Color.white);
        /**
         * Set up hero movement actions
         */
       ballController.registerHeroMovements(this,movementType);

    }

    public void setMap(Map map) {
        if (this.map != null) {
            removeAll();
            this.map.removeChangeListener(this);
        }
        ballController.setMap(map);
        this.map = map;
        this.map.addChangeListener(this);

        /**
         * Prepare tiles
         */
        labels = new JLabel[map.getCols()][map.getRows()];
        for (int i = 0; i < map.getCols(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                JLabel l = new JLabel();
                labels[i][j] = l;
                l.setPreferredSize(TILE_SIZE);
                l.setMinimumSize(TILE_SIZE);
                l.setMaximumSize(TILE_SIZE);
                l.setOpaque(true);
                add(l, new GridBagConstraints(i, j, 1, 1, 0.0, 0.0,
                        GridBagConstraints.CENTER, GridBagConstraints.BOTH, TILE_INSETS, 0, 0));
            }
        }

        MapChanged(new MapChangeEvent(0, 0, map.getCols(), map.getRows()));
    }

    /**
     * Thay doi UI sau moi lan di chuyen
     *
     * @param e
     */
    @Override
    public void MapChanged(MapChangeEvent e) {
        for (int i = e.x; i < e.x + e.width; i++) {
            for (int j = e.y; j < e.y + e.height; j++) {
                Tile t = map.getTile(i, j);
                JLabel l = labels[i][j];
                if (map.getHero().x == i && map.getHero().y == j) {
                    l.setBackground(map.getHero().state.color);
                } else {
                    l.setBackground(t.state.color);
                }
            }
        }
    }
}
