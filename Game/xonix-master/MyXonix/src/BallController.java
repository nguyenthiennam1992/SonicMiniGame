import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/*
*  Lop dieu khien banh
*
*/
/**
 * Created by Alex Backer on 12/3/2016.
 */


public class BallController {
    private Map map;

    public class HeroMoveAction extends AbstractAction {
        int dx;
        int dy;

        HeroMoveAction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            map.moveHero(dx, dy);
        }
    }

    /**
     *  Dieu chinh vi tri nguoi choi bang ban phim
     *
     * @param jpMain
     */
    public void registerHeroMovements(JPanel jpMain,int movementType) {
        if(movementType == 1)
        {
            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "heroMoveUp");
            jpMain.getActionMap().put("heroMoveUp", new HeroMoveAction(0, -1));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "heroMoveDown");
            jpMain.getActionMap().put("heroMoveDown", new HeroMoveAction(0, 1));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "heroMoveRight");
            jpMain.getActionMap().put("heroMoveRight", new HeroMoveAction(1, 0));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "heroMoveLeft");
            jpMain.getActionMap().put("heroMoveLeft", new HeroMoveAction(-1, 0));
        }
        if(movementType == 2)
        {
            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0), "heroMoveUp");
            jpMain.getActionMap().put("heroMoveUp", new HeroMoveAction(0, -1));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0), "heroMoveDown");
            jpMain.getActionMap().put("heroMoveDown", new HeroMoveAction(0, 1));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0), "heroMoveRight");
            jpMain.getActionMap().put("heroMoveRight", new HeroMoveAction(1, 0));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0), "heroMoveLeft");
            jpMain.getActionMap().put("heroMoveLeft", new HeroMoveAction(-1, 0));
        }
        if (movementType < 1 || movementType > 2)
        {
            System.out.println("Chi co the la 1 hoac 2, He thong se chon 1 lam gia tri mac dinh");
            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "heroMoveUp");
            jpMain.getActionMap().put("heroMoveUp", new HeroMoveAction(0, -1));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "heroMoveDown");
            jpMain.getActionMap().put("heroMoveDown", new HeroMoveAction(0, 1));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "heroMoveRight");
            jpMain.getActionMap().put("heroMoveRight", new HeroMoveAction(1, 0));

            jpMain.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "heroMoveLeft");
            jpMain.getActionMap().put("heroMoveLeft", new HeroMoveAction(-1, 0));

        }

    }

    public void setMap(Map map) {
        this.map = map;
    }
}
