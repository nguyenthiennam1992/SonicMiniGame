package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;



@SuppressWarnings("serial")
public class AddMonsterBallCommand extends AbstractAction{

	private GameWorld gw;
	public AddMonsterBallCommand()
	{
		super("b");
		
	}
	 public void setTarget(GameWorld gw) {
	        this.gw = gw;
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
		gw.addMonsterBall();
		gw.notifyObservers();
		
		
	}




}
