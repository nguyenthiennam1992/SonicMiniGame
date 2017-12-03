package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


public class CarHitTicketCommand extends AbstractAction {

	private GameWorld gw;
	public CarHitTicketCommand()
	{
		super("Car Hit Ticket");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gw.carHitATimeTicket();
	
	}
	 public void setTarget(GameWorld gw) {
	        this.gw = gw;
	    }
}
