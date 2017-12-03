package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class IncreaseSpeedCommand extends AbstractAction{
private GameWorld gw;

	public synchronized static IncreaseSpeedCommand getInstance() {
		// TODO Auto-generated method stub
		IncreaseSpeedCommand increaseCommand = new IncreaseSpeedCommand();
		
		return increaseCommand;
	}
	public IncreaseSpeedCommand()
	{
		super("i");
	}
	public void setTarget(GameWorld gw)
	{
		this.gw=gw;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gw.incSpeed(1);
	//System.out.println("Adjusting Speed");
	}
}

	