package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class HullDrawCommand extends AbstractAction {
	private GameWorld gw;

	public HullDrawCommand(){
		super("Curve");
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		gw.setDrawHull();
		gw.notifyObservers();
		//System.out.println("Car is going South");
	}
	public void setTarget(GameWorld gw) {
		this.gw = gw;
		
	}
	
}
