import java.awt.*;
import java.util.Random;

/**
 * Created by Alex Backer on 12/2/2016.
 */
public class MonsterBall extends GameObject
        implements IColorable, IMoveable
{
    private int speed;
    private Random rand;
    private int heading;
    private int radius;
    private int headingList[] = {
            0, 90, 180, 270
    };
    public MonsterBall()
    {
        rand = new Random();
        setRandColor();
        setRandRadius();
        setRandLocation();
        setRandSpeed();
        setRandHeading();
    }

    private void setRandLocation()
    {
        int x = Math.abs(rand.nextInt(496));
        int y = Math.abs(rand.nextInt(496));
        setLocation(new java.awt.geom.Point2D.Float(x, y));
    }

    public double getSize()
    {
        return (double)(radius * 2);
    }

    private void setRandRadius()
    {
        radius = Math.abs(rand.nextInt(10));
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

//    public void setHeading(int heading2)
//    {
//        for(int i = 0; i <= 3; i++)
//            if(headingList[i] == heading2)
//                heading = heading2;
//
//    }

    public void setRandColor()
    {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        color = Color.getHSBColor(r, g, b);
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

//    public java.awt.geom.Point2D.Float getLocation()
//    {
//        return getLocation();
//    }


}
