import java.awt.*;
import java.util.Random;

/**
 * Created by Alex Backer on 12/2/2016.
 */
public class Car extends GameObject implements  ISteerable, IMoveable
{
    private int speed;
    private Random rand;
    private java.awt.geom.Point2D.Float origin;
    private int heading;
    private int radius;
    private int headingList[] = {
            0, 90, 180, 270
    };

    //create an object of SingleObject
    private static Car instance = new Car();

    private Car()
    {

        rand = new Random();
        origin = new java.awt.geom.Point2D.Float(247.5F, 2.5F);
        setLocation(origin);
        setCarRandColor();
        setRadius(radius);
        setSpeed(speed);
        setHeading(heading);
    }
    //Get the only object available
    public static Car getInstance(){
        return instance;
    }


    public String toString()
    {
        String s = (new StringBuilder("Car: Loc=[")).append(getLocation().x).append(",").append(getLocation().y).append("]").append(" color= [").append(getColor().getRed()).append(",").append(getColor().getGreen()).append(",").append(getColor().getBlue()).append("] speed=").append(getSpeed()).append(" heading=").append(getHeading()).append(" radius=").append(getRadius()).toString();
        return s;
    }

    public int getHeading()
    {
        return heading;
    }

    public int getRadius()
    {
        return radius;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setLocation()
    {
        setLocation(origin);
    }

    public void setHeading(int heading2)
    {
        for(int i = 0; i <= 3; i++)
            if(headingList[i] == heading2)
                heading = heading2;

    }

    public void setCarRandColor()
    {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        setCarColor(Color.getHSBColor(r, g, b));
    }

    public void move()
    {
        double xDelta = Math.cos(Math.toRadians(90 - heading)) * (double)speed;
        double yDelta = Math.sin(Math.toRadians(90 - heading)) * (double)speed;
        setLocation(new java.awt.geom.Point2D.Float(getLocation().x + (float)xDelta, getLocation().y + (float)yDelta));
        if((double)getLocation().x < 2.5D)
            setLocation(new java.awt.geom.Point2D.Float(2.5F, getLocation().y));
        if((double)getLocation().x > 497.5D)
            setLocation(new java.awt.geom.Point2D.Float(497.5F, getLocation().y));
        if((double)getLocation().y < 2.5D)
            setLocation(new java.awt.geom.Point2D.Float(getLocation().x, 2.5F));
        if((double)getLocation().y > 497.5D)
            setLocation(new java.awt.geom.Point2D.Float(getLocation().x, 497.5F));
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    private void setRadius(int radius2)
    {
        radius2 = Math.abs(rand.nextInt() % 10) + 1;
        radius = radius2;
    }

    public void setCarColor(Color color)
    {
        this.color = color;
    }


}
