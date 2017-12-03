import javax.swing.*;

/**
 * Created by Alex Backer on 12/3/2016.
 */
public class ContextMovements {
    private  IMovement movement;
    public  ContextMovements(IMovement movement)
    {
        this.movement = movement;
    }

    /*
    *  tra ve kieu di chuyen
    * */
    public int executeStrategy(){
        return movement.movementType();
    }
}
