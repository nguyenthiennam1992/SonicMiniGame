import java.util.Scanner;
/**
 * Created by Alex Backer on 12/1/2016.
 */
public class Starter {
    public static void main(String[] args) {
        Frame mapFrame = new Frame();
        int movementType = 2;
        String a ;

        //stategy
        // di chuyen bang mui ten
        ContextMovements context = new ContextMovements(new ArrowMovements());
        mapFrame.setMapFrame(new Map(15, 15),context.executeStrategy());

        // di chuyen bang ban phim
//        mapFrame = new Frame();
//        context = new ContextMovements(new KeyWordMovements());
//        mapFrame.setMapFrame(new Map(15, 15),context.executeStrategy());
//
//        Scanner reader = new Scanner(System.in);
//        a = reader.next();

      //  mapFrame.getContentPane().setBackground(Color.CYAN);
    }
}
