import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * Created by Alex Backer on 12/2/2016.
 */
public class Map {
    private Tile[][] tiles;
    private List<Tile> path;
    private Tile hero;
    private List<MapChangeListener> changeListeners;

    /**
     * @param width     chieu ngang cua map
     * @param height    chieu doc cua map
     * //@param movementType
     */
    public Map(int width, int height) {
        tiles = new Tile[width + 2][height + 2];

        width = tiles.length;
        height = tiles[0].length;

        /**
         * Fill earth and water
         */
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                TileState tileState =
                        ((x == 0 || x == width - 1) && y >= 0) ||
                        ((y == 0 || y == height - 1) && x >= 0) ? TileState.WATER : TileState.EARTH;

                tiles[x][y] = new Tile(x, y, tileState);
            }
        }

        /**
         *  Dat nguoi choi o vi tri goc trai ten cung
         */
        hero = new Tile(0, 0, TileState.HERO);

        /**
         * Khoi tao duong di
         */
        path = new ArrayList<Tile>();
    }

    @Override
    public String toString() {
        String result = "";

        for (int y = 0; y < getRows(); y++) {
            for (int x = 0; x < getCols(); x++) {
                result += tiles[x][y].state.symbol + " ";
            }
            result += "\r\n";
        }

        return result;
    }

    /**
     * Xoa vung nho hon cua map khi di qia
     */
    public void cut() {
        List<Tile> border = getPath();
        Set<Tile> borderSet = new HashSet<Tile>(border);
        Set<Tile> usedPoints = new HashSet<Tile>(borderSet);
        List<Set<Tile>> areas = new ArrayList<Set<Tile>>();
        int biggestAreaIndex = -1;

        /**
         * Di qua bien gioi
         */
        for (Point point : border) {
            final int x = point.x;
            final int y = point.y;

            /**
             * Kiem tra vi tri di cua nguoi choi
             */
            for (int[] dxdy : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1} }) {
                Set<Tile> area = new HashSet<Tile>();
                fill(getTile(x + dxdy[0], y + dxdy[1]), usedPoints, area);
                if (!area.isEmpty()) {
                    areas.add(area);
                    usedPoints.addAll(area);

                    if (biggestAreaIndex < 0 || areas.get(biggestAreaIndex).size() < area.size()) {
                        biggestAreaIndex = areas.size() - 1;
                    }
                }
            }
        }

        Set<Tile> toFill = new HashSet<Tile>(borderSet);
        if (!areas.isEmpty()) {
            areas.remove(biggestAreaIndex);
            for (Set<Tile> area : areas) {
                toFill.addAll(area);
            }
        }

        for (Tile tile : toFill) {
            tile.state = TileState.WATER;
            fireChange(tile);
        }

        getPath().clear();
    }

    /**
     * @param point     cac diem se do day lai
     * @param filled    cac diem da bi danh sau den
     * @param result    ket qua
     */
    private void fill(Tile point, Set<Tile> filled, Set<Tile> result) {
        if (point != null && point.state == TileState.EARTH && !filled.contains(point) && !result.contains(point)) {
            result.add(point);
            fill(getTile(point.x - 1, point.y), filled, result);
            fill(getTile(point.x + 1, point.y), filled, result);
            fill(getTile(point.x, point.y - 1), filled, result);
            fill(getTile(point.x, point.y + 1), filled, result);
        }
    }

    /**
     * @return  tong so dong
     */
    public int getCols() {
        return tiles.length;
    }

    /**
     * @return  tong so cot
     */
    public int getRows() {
        return tiles[0].length;
    }

    /**
     * <p>Looks for existing tile in a map. Will return null if coordinates are invalid.</p>
     *
     * <p>Never throws IndexOutOfBoundsException.</p>
     *
     * @param x x-coordinate of desired tile
     * @param y y-coordinate of desired tile
     * @return  map tile, null if coordinates point out of map bounds
     */
    public Tile getTile(int x, int y) {
        return x >= 0 && x < getCols() &&
               y >= 0 && y < getRows() ? tiles[x][y] : null;
    }

    /**
     * @return  current path of hero
     */
    public List<Tile> getPath() {
        return path;
    }

    /**
     * Set up path of hero. Used in tests only
     *
     * @param path
     * @deprecated  to be used in tests only
     */
    public void setPath(List<Tile> path) {
        this.path = path;
    }

    /**
     * Di chuyen nguoi choi
     *
     * @param dx
     * @param dy
     */
    public void moveHero(int dx, int dy) {
        int oldX = hero.x;
        int oldY = hero.y;
        int newX = oldX + dx;
        int newY = oldY + dy;

        if (newX >= 0 && newY >= 0 &&
            newX < getCols() && newY < getRows()) {
            if (tiles[oldX][oldY].state == TileState.EARTH) {
                tiles[oldX][oldY].state = TileState.PATH;
                path.add(tiles[oldX][oldY]);
            }
            hero.x = newX;
            hero.y = newY;

            fireChange(getTile(oldX, oldY));
            fireChange(getTile(newX, newY));

            if (tiles[newX][newY].state == TileState.WATER && !path.isEmpty()) {
                cut();
            }
        }
    }

    /**
     * @return tile of hero along with it's current position
     */
    public Tile getHero() {
        return hero;
    }

    /**
     * Notify all map change listeners about some changes in specified tile
     *
     * @param tile
     */
    private void fireChange(Tile tile) {
        fireChange(new MapChangeEvent(tile.x, tile.y, 1, 1));
    }

    /**
     * Notify all map change listeners with specified map change event
     *
     * @param e
     */
    private void fireChange(MapChangeEvent e) {
        if (changeListeners == null) {
            return;
        }

        for (int i = changeListeners.size() - 1; i >= 0; i--) {
            changeListeners.get(i).MapChanged(e);
        }
    }

    /**
     * Register specified map change listener to listen events from this map object
     *
     * @param l
     */
    public void addChangeListener(MapChangeListener l) {
        if (changeListeners == null) {
            changeListeners = new ArrayList<MapChangeListener>();
        }

        changeListeners.add(l);
    }

    /**
     * Un-register specified map change listener
     *
     * @param l
     */
    public void removeChangeListener(MapChangeListener l) {
        if (changeListeners == null) {
            return;
        }

        changeListeners.remove(l);
    }
}
