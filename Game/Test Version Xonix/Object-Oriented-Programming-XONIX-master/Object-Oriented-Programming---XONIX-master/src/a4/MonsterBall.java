package a4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;


public class MonsterBall extends SuperBall {
	private boolean isSelected;

	public MonsterBall()
	{
		super.setWidth(25);
		super.setHeight(25);
		super.setRandLocation();
		
		
	}
	@Override
	public void setStrategy(IStrategy s) {
		// TODO Auto-generated method stub
		//Normal Strategy to stay within the boundaries
		
		super.setStrategy(s);
	}


	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		AffineTransform saveAT = g.getTransform() ;
		g.transform(getTranslation()); // This time translation is applied LAST
		g.transform(getRotation());
		g.transform(getScale());
		g.setColor(color);
		// calculate the local-space location of the lower-left corner of the bounding box
		// for the circle, since that's where the object will be drawn from in local space
		Point boxCorner = new Point (-getWidth(), -getWidth());
		g.fillOval (boxCorner.x, boxCorner.y, getWidth()*2, getWidth()*2) ;
		
		/*
		g.setColor(color);
		g.drawOval((int)(getX()-(getWidth()/2)), (int)(getY()-(getHeight()/2)), getWidth(), getHeight());
		g.fillOval((int)(getX()-(getWidth()/2)), (int)(getY()-(getHeight()/2)), getWidth(), getHeight());
		*/
		g.setTransform (saveAT);
	}





	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		if(otherObject instanceof Car)
		{
			super.setRandColor();
		}
		if(otherObject instanceof Sweeper)
		{
			System.out.println("I hit a sweeper");
			this.setDeath(true);
		}
		if(otherObject instanceof FieldSquares)
		{
			this.setRandHeading();
		}
	}


}
