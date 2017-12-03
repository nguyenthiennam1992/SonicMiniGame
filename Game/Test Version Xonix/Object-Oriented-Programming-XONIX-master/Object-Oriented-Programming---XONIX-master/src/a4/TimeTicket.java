package a4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Random;

public class TimeTicket extends GameObject implements ISelectable {
	private int time;

	private Random rand = new Random();
	private boolean isSelected = false;
	public TimeTicket() {
		setWidth(10);
		setHeight(10);
		time = 100;//switch back to 5
		setRandLocation();
		
	}
	public int getTime() {
		return this.time;
	}
	private void setRandLocation() {
		/*
		 * Sets a random heading within the boundaries
		 */
		int x = Math.abs(rand.nextInt(450) +20);
		//add the 20 to keep it within the boundaries
		int y = Math.abs(rand.nextInt(450)+20);
		// Make random generation
		super.setX((float) x);
		super.setY((float) y);
	}
	/*
	public String toString() {

		String s = "Time Ticket: " + "Loc=[" + getX() + ","
				+ getY() + "] color= [" + getColor().getRed() + ","
				+ getColor().getGreen() + "," + getColor().getBlue()
				+ "]  width=" + this.getWidth() + " height=" + this.getHeight()
				+ " time=" + this.getTime();
		return s;

	}
*/
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		AffineTransform saveAT = g.getTransform() ;
		g.transform(getTranslation()); // This time translation is applied LAST
		g.transform(getRotation());
		g.transform(getScale());
		g.setColor(Color.RED);
		// calculate the local-space location of the lower-left corner of the bounding box
		// for the circle, since that's where the object will be drawn from in local space
		Point boxCorner = new Point (-getWidth(), -getWidth());

		if(isSelected())
		{
		g.setColor(Color.YELLOW);
		//g.fillRect((int)getX()-getWidth()/2, (int)getY()-getHeight()/2, getWidth(), getHeight());
		g.fillRect (boxCorner.x, boxCorner.y, getWidth()*2, getWidth()*2) ;
		
		}else{
		g.drawRect (boxCorner.x, boxCorner.y, getWidth()*2, getWidth()*2) ;
			
		g.setColor(Color.BLUE);
		//g.drawRect((int)(this.getX()-(getWidth()/2)), (int)(this.getY()-(getHeight()/2)), getWidth(), getHeight());
		//g.fillRect((int)(this.getX()-(getWidth()/2)), (int)(this.getY()-(getHeight()/2)), getWidth(), getHeight());
		}
		g.setTransform (saveAT) ;
		}


	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		if(otherObject instanceof Sweeper)
		{
			this.setDeath(true);
		}
		
	}
	@Override
	public void setSelected(boolean yesNo) {
		// TODO Auto-generated method stub
		isSelected = yesNo;
		setDeath(yesNo);
	}
	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return isSelected;
	}
	@Override
	public boolean contains(Point.Float p) {
		// TODO Auto-generated method stub
		int px = (int) p.getX()+getWidth()/2; // mouse location
		int py = (int) p.getY()+getHeight()/2;
		int xLoc = (int) this.getXTran(); // shape location
		int yLoc = (int) this.getYTran();
		if ( (px >= xLoc) && (px <= xLoc+this.getWidth()) && (py >= yLoc) && (py <= yLoc+this.getHeight()) )
		return true; 
		else return false;
	}



}