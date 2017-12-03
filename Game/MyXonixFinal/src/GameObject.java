import java.awt.*;

/**
 * Created by Alex Backer on 12/2/2016.
 */
public abstract class GameObject
{
    protected Color color;
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


}
