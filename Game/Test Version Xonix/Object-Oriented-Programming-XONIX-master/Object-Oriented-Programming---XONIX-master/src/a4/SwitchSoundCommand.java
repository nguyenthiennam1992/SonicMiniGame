package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class SwitchSoundCommand extends AbstractAction {
GameWorld gw;
	public SwitchSoundCommand()
	{
		super("Sound");
	}
	public void setTarget(GameWorld gw)
	{
		this.gw = gw;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Verify that the user wants to quit
		gw.setSound(!gw.getSound());
	}
	
	public synchronized static SwitchSoundCommand getInstance()
	{
		SwitchSoundCommand	switchSound = new SwitchSoundCommand();
		return switchSound;
		
	}

	}