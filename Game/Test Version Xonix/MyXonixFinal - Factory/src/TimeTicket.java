import java.awt.*;
import java.util.Random;

/**
 * Created by Alex Backer on 12/2/2016.
 */
public class TimeTicket extends GameObject
{
    private int width;
    private int time;
    private int height;
    private Random rand;

    public TimeTicket()
    {
        rand = new Random();
        width = 15;
        height = 15;
        time = 5;
        color = Color.RED;
        setRandLocTimeTicket();
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getTime()
    {
        return time;
    }

    public String toString()
    {
        String s = (new StringBuilder("Time Ticket: Loc=[")).append(getLocation().x).append(",").append(getLocation().y).append("] color= [").append(getColor().getRed()).append(",").append(getColor().getGreen()).append(",").append(getColor().getBlue()).append("]  width=").append(getWidth()).append(" height=").append(getHeight()).append(" time=").append(getTime()).toString();
        return s;
    }

    private void setRandLocTimeTicket()
    {
        int x = Math.abs(rand.nextInt(496));
        int y = Math.abs(rand.nextInt(496));
        setLocation(new java.awt.geom.Point2D.Float(x, y));
    }


}