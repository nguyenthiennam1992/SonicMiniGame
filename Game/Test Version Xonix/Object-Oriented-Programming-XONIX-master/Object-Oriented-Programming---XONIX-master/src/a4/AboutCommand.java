package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AboutCommand extends AbstractAction {

	public AboutCommand()
	{
		super("About");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Verify that the user wants to quit
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame,"Daniel Flores       Csc 133        Version 2.1 ", "About Xonix Game", 0);
		
	}
	
	}


