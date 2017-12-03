package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class CarHitBallCommand extends AbstractAction {
	private GameWorld gw;
	public CarHitBallCommand()
	{
		super("1");
	}

	public void setTarget(GameWorld gw) {
        this.gw = gw;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		gw.crashedIntoMonsterBall();
	}
}
