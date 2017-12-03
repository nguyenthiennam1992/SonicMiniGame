import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.Random;

/**
 * Created by Alex Backer on 12/2/2016.
 */
public abstract class GameObject
{
    private float x ,y ;
    protected Color color;
    private Random rand = new Random();

    private int speed;
    // chieu ngang
    private int width;
    // chieu cao
    private int height;
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    private java.awt.geom.Point2D.Float location;

    public GameObject()
    {
        location = new java.awt.geom.Point2D.Float();
    }

    public Color getColor()
    {
        return color;
    }

    public java.awt.geom.Point2D.Float getLocation()
    {
        return location;
    }

    public void setLocation(java.awt.geom.Point2D.Float location)
    {
        this.location = location;
    }


    public float getX() {
        return x;

    }
    public void setX(float x) {
        if(x >=2.5 || x< 450.5)
        {
            this.x = x;
        }
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        if(y >=2.5 || y< 450.5)
        {
            this.y = y;
        }
    }
}
