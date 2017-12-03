import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
/**
 * Created by Alex Backer on 12/3/2016.
 */
public class Frame extends JFrame {

    public static  int movementTypeStatic = 0;
    JPanel jpMain = new JPanel();

    MapPanel jpField = new MapPanel(movementTypeStatic);

    JPanel jpToolbar = new JPanel();


    public Frame() {
        // ten cua tro choi
        setTitle("MyXonix");
        // chinh kich thuoc
        setResizable(false);

        /**
         * Initialize main panel
         */
//        jpMain.setLayout(new BorderLayout());
//        jpMain.add(jpToolbar, BorderLayout.PAGE_START);
//        jpMain.add(jpField, BorderLayout.CENTER);

        /**
         * Initialize tool bar
         */
        jpToolbar.setLayout(new BorderLayout());

        /**
         * Put main panel to the content pane
         */
        getContentPane().add(jpMain);
        setBackground( Color.GREEN );
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    /**
     *
     *
     * @param map
     */
    // dieu chinh ten + kich thyoc cua map
    public void setMapFrame(Map map,int movementType) {
        setTitle("MyXonix " + (map.getCols() - 2) + "x" + (map.getRows() - 2));
        jpMain.setLayout(new BorderLayout());
        jpField = new MapPanel(movementType);
        jpField.setMap(map);
        jpMain.add(jpField, BorderLayout.CENTER);
        /**
         * Cho khe vao giua cac o
         */
        int w = getPreferredSize().width;
        int h = getPreferredSize().height;

        int sW = getToolkit().getScreenSize().width;
        int sH = getToolkit().getScreenSize().height;

        setBounds((sW - w) / 2, (sH - h) / 2, w, h);
    }
}
