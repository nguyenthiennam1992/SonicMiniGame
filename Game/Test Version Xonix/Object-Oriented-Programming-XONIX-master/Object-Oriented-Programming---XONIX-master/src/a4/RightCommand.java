package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class RightCommand extends AbstractAction{
private GameWorld gw;
public RightCommand(){
	super("RIGHT");
}

	public synchronized static RightCommand getInstance() {
		// TODO Auto-generated method stub
		RightCommand right = new  RightCommand();
		
		return right;
	}
	public void SetTarget(GameWorld gw)
	{
		this.gw=gw;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gw.setHeading(90);
		gw.notifyObservers();
		//System.out.println("car is going Right");
	}
	

}
