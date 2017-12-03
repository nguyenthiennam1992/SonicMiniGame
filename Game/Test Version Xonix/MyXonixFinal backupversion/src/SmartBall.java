/**
 * Created by Alex Backer on 12/13/2016.
 */
public class SmartBall extends SuperBall implements IStrategy {
    private ChaseCar chaseCarStrategy = new ChaseCar();
    private Normal normalStrategy = new Normal();
    public void setHeading(int heading2) {
        super.setHeading((int)heading2);
    }
    public SmartBall()
    {
        super.setWidth(10);
        setStrategy(chaseCarStrategy);
    }
    public void setStrategy(IStrategy s)
    {
        curStrategy =s;
    }
    // hien thi di lieu cua banh
    public String toString()
    {
        String s = (new StringBuilder("Ball: Loc={")).append(getLocation().x).append(",").append(getLocation().y).append("] " +
                "color= [").append(getColor().getRed()).append(",").append(getColor().getGreen()).append(",").append(getColor().getBlue()).append("] " +
                "speed=").append(getSpeed()).append(" heading=").append(getHeading()).append(" radius=").append(getRadius()).toString();
        return s;
    }
    @Override
    public void UseStrategy(SmartBall object, float playerX, float playerY) {
        curStrategy.UseStrategy(this,playerX,playerY);
    }


    @Override
    public void setX(float x) {
        super.setX(x);
    }



    @Override
    public void setY(float y) {
        super.setY(y);
    }
}
