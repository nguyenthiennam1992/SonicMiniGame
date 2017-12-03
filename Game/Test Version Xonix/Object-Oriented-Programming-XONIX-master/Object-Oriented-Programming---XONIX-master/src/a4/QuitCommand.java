package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

public class QuitCommand extends AbstractAction {

	private GameWorldProxy gwProxy;
	public QuitCommand()
	{
		super("Quit");
	}
	 public void setTarget(GameWorldProxy gwProxy) {
	        this.gwProxy = gwProxy;
	    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Verify that the user wants to quit
		System.out.println ("Quit requested from " + e.getActionCommand()
				+ " " + e.getSource().getClass() );
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to Quit?", "Confirm Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
	if(result == JOptionPane.YES_OPTION)
		{
		System.exit(0);
		}
		
	}
	
	}


