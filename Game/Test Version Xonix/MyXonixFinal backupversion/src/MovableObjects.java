import java.util.Random;

/**
 * Created by Alex Backer on 12/13/2016.
 */
public class MovableObjects extends  GameObject implements  IMoveable {

    private Random rand = new Random();
    private double heading;
    private int speed;
    public void setSpeed(int speed)
    {
        this.speed=speed;
    }
    public void setRandHeading() {
        int x = rand.nextInt(269);
        setHeading(x);
    }
    public void setHeading(int heading) {
        this.heading=heading;
    }
    public int getHeading()
    {
        return (int)heading;
    }

    @Override
    public void move() {

    }
    public int getSpeed()
    {
        return speed;
    }


}
