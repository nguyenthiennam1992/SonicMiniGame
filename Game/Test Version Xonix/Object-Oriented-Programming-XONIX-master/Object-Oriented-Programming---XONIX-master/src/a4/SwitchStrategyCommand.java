package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;




public class SwitchStrategyCommand extends AbstractAction {
	private GameWorld gw;
	private static SwitchStrategyCommand switchStrategy = null;
	public SwitchStrategyCommand()
	{
		super("Switch Strategy");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//gw.changeSmartBallStrategy();
		
	}
	 public  void setTarget(GameWorld gw) {
	        this.gw = gw;
	    }
	public synchronized static SwitchStrategyCommand getInstance() {
		// TODO Auto-generated method stub

		switchStrategy = new SwitchStrategyCommand();
			
		return switchStrategy;
	}
}
