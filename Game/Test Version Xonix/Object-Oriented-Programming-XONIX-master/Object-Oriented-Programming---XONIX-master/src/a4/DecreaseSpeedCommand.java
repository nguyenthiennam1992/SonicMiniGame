package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class DecreaseSpeedCommand extends AbstractAction{
private GameWorld gw;

	public synchronized static DecreaseSpeedCommand getInstance() {
		// TODO Auto-generated method stub
		DecreaseSpeedCommand decreaseSpeedCommand = new DecreaseSpeedCommand();
		
		return decreaseSpeedCommand;
	}
	public DecreaseSpeedCommand()
	{
		super("l");
	}
	public void setTarget(GameWorld gw)
	{
		this.gw=gw;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gw.incSpeed(-1);
	//	System.out.println("Decreased Speed");
	}
}

	