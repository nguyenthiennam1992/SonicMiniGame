package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;



public class AddSmartBallCommand extends AbstractAction{

	private GameWorld gw;
	public AddSmartBallCommand()
	{
		super("Add SmartBall");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		gw.addSmartBall();
	
	//	System.out.println("SmartBall added");
		
	}
    public void setTarget(GameWorld gw) {
        this.gw = gw;
    }

}
