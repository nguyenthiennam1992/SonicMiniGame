import java.awt.*;
import java.util.Random;

/**
 * Created by Alex Backer on 12/13/2016.
 */
public class SmartBall extends GameObject
        implements IColorable, IMoveable {
    private int speed;
    private Random rand;
    private int heading;
    private int radius;
    private int headingList[] = {
            0, 90, 180, 270
    };
    private int newHeading;
    private double theta;
    //private  SmartBall ball;
   //private Car player;
    public SmartBall()
    {
        rand = new Random();
        setRandColor();
        setRandRadius();
        setRandLocation();
        setRandSpeed();
        setRandHeading();
        //player = Car.getInstance();
    }

    private void setRandLocation()
    {
        int x = Math.abs(rand.nextInt(496));
        int y = Math.abs(rand.nextInt(496));
        setLocation(new java.awt.geom.Point2D.Float(x, y));
    }
    public float getX()
    {
        return getLocation().x;
    }
    public float getY()
    {
        return getLocation().y;
    }

    public double getSize()
    {
        return (double)(radius * 2);
    }

    private void setRandRadius()
    {
        radius = Math.abs(rand.nextInt(20));
    }

    public void move()
    {
        double xDelta = Math.cos(Math.toRadians(90 - heading)) * (double)speed;
        double yDelta = Math.sin(Math.toRadians(90 - heading)) * (double)speed;
        setLocation(new java.awt.geom.Point2D.Float(getLocation().x + (float)xDelta, getLocation().y + (float)yDelta));
        if(getLocation().x < 5F)
            setLocation(new java.awt.geom.Point2D.Float(5F, getLocation().y));
        if(getLocation().x > 495F)
            setLocation(new java.awt.geom.Point2D.Float(495F, getLocation().y));
        if(getLocation().y < 5F)
            setLocation(new java.awt.geom.Point2D.Float(getLocation().x, 5F));
        if(getLocation().y > 495F)
            setLocation(new java.awt.geom.Point2D.Float(getLocation().x, 495F));
    }

    public double getHeading()
    {
        return (double)heading;
    }

    public int getSpeed()
    {
        return speed;
    }

    public int getRadius()
    {
        return radius;
    }

    private void setRandSpeed()
    {
        speed = Math.abs(rand.nextInt() % 100);
    }

    public String toString()
    {
        String s = (new StringBuilder("Ball: Loc={")).append(getLocation().x).append(",").append(getLocation().y).append("] " +
                "color= [").append(getColor().getRed()).append(",").append(getColor().getGreen()).append(",").append(getColor().getBlue()).append("] " +
                "speed=").append(getSpeed()).append(" heading=").append(getHeading()).append(" radius=").append(getRadius()).toString();
        return s;
    }

    // di random
    private void setRandHeading()
    {
        int x = rand.nextInt(4);
        heading = headingList[x];
    }
    private void setHeading(int _heading)
    {
        heading = _heading;
    }


    public void setRandColor()
    {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        color = Color.getHSBColor(r, g, b);
    }

    public void ChasePLayer(SmartBall ball,float playerX,float playerY)
    {
        theta = Math.toDegrees(Math.atan2(playerY-ball.getY(),playerX-ball.getX()));
        //System.out.println("Theta is ="+ theta);
        theta = (90-theta);
        newHeading =(int)theta;
        ball.setHeading(newHeading);

    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}
