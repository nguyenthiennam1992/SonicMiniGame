package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class TickCommand extends AbstractAction {
	private GameWorld gw;
	
	public TickCommand()
	{
		super("Tick");
	}
	 public void setTarget(GameWorld gw) {
	        this.gw = gw;
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		gw.tick(20);
	}

}