package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class DownCommand extends AbstractAction{
private GameWorld gw;

	public synchronized static DownCommand getInstance() {
		// TODO Auto-generated method stub

		DownCommand	down = new DownCommand();
		
		return down;
	}
	public DownCommand(){
		super("DOWN");
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gw.setHeading(180);
		gw.notifyObservers();
		//System.out.println("Car is going South");
	}
	public void SetTarget(GameWorld gw) {
		this.gw = gw;
		
	}
	

}
