package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class PauseCommand extends AbstractAction {
	private GameWorld gw;
	private Game g;
	private boolean prev;
	public PauseCommand()
	{
		super("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

		gw.togglePause(gw);
		
		
	}
	public void setTarget(GameWorld gw) {
        this.gw = gw;
    }

}
