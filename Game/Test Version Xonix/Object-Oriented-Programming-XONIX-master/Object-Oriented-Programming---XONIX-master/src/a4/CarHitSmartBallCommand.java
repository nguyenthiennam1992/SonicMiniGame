package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CarHitSmartBallCommand extends AbstractAction {
	private GameWorld gw;
	public CarHitSmartBallCommand()
	{
		super("car Hit SmartBall");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gw.carHitSmartBall();
		
	}
	 public void setTarget(GameWorld gw) {
	        this.gw = gw;
	    }
}
