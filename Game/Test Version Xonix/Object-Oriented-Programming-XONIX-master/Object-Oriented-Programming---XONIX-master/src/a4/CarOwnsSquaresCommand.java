package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


@SuppressWarnings("serial")
public class CarOwnsSquaresCommand extends AbstractAction {
	private GameWorld gw;
	public CarOwnsSquaresCommand()
	{
		super("Car Owns Squares");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gw.iOwnTheseTiles();
		System.out.println("Car Owns ths Square now");
	}
	 public void setTarget(GameWorld gw) {
	        this.gw = gw;
	    }
}
