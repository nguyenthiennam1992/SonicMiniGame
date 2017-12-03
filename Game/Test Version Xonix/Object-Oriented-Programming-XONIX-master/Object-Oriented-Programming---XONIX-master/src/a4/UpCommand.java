package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class UpCommand extends AbstractAction{
private GameWorld gw;

	public synchronized static UpCommand getInstance() {
		// TODO Auto-generated method stub
		UpCommand upCommand = new UpCommand();
		
		return upCommand;
	}
	public void setTarget(GameWorld gw)
	{
		this.gw=gw;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gw.setHeading(0);
		gw.notifyObservers();
		//System.out.println("Car is going north");
	}

	

}
