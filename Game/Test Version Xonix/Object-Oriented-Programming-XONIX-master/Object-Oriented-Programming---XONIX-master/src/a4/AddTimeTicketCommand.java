package a4;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


@SuppressWarnings("serial")
public class AddTimeTicketCommand extends AbstractAction{

		private GameWorld gw;
		public AddTimeTicketCommand(){
			super("k");
		}
		 public void setTarget(GameWorld gw) {
		        this.gw = gw;
		    }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			gw.addTimeTicket();
	
		//	collection.add(timeTicket);
		}
}
