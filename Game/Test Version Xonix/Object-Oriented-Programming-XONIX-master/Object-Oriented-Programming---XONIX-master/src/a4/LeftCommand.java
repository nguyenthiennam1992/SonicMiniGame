package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class LeftCommand extends AbstractAction{
private GameWorld gw;

public LeftCommand(){
	super("LEFT");
}
//Only 1 instance spooler
	public synchronized static LeftCommand getInstance() {
		// TODO Auto-generated method stub
		LeftCommand left = new LeftCommand();
		return left;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gw.setHeading(270);
		gw.notifyObservers();
		//System.out.println("Car is going West");
	}
	public void setTarget(GameWorld gw) {
		// TODO Auto-generated method stub
		this.gw=gw;
		
	}
	

}
