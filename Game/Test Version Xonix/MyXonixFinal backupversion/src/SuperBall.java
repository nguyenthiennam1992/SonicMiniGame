import java.awt.*;
import java.util.Random;

/**
 * Created by Alex Backer on 12/13/2016.
 */
public class SuperBall extends  MovableObjects implements IColorable,IMoveable {
    protected IStrategy curStrategy=null;
    private Random rand = new Random();
    private int radius;
    private int headingList[] = { 0, 90, 180, 270 };
    public SuperBall() {
		/*
		 * Default values
		 */
        setRandColor();
        setRandRadius();

        setSpeed(1);
        setRandHeading();
    }
    public int getRadius() {
        return radius;
    }
    private void setRandRadius() {
        radius = Math.abs(rand.nextInt(10));
    }
    @Override
    public void move() {

    }

    @Override
    public void setColor(Color color) {

    }

    @Override
    public void setRandColor() {

    }
    public void setHeading(int heading2) {
        super.setHeading((int)heading2);
    }
}
