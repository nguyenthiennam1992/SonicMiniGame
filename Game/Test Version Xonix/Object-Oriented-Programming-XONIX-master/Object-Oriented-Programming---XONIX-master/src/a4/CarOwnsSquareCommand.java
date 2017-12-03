package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CarOwnsSquareCommand extends AbstractAction {
	private GameWorld gw;
	public CarOwnsSquareCommand()
	{
		super("Car Owns Square");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gw.iOwnThisOneTile();
		
	}
	 public void setTarget(GameWorld gw) {
	        this.gw = gw;
	    }
}
