package a4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Fireball extends SmartBall implements IStrategy,ISelectable {
private SmartBall myBall ;
private Flame [] flames;
private double flameOffset = 0 ; // current flame distance from fireball
private double flameIncrement = +0.05 ; // change in flame distance each tick
private double maxFlameOffset = 0.5 ; // max distance before reversing
private int quickTime;
private boolean collided;
public Fireball ()
{
	super.setSpeed(1);
	super.setRandHeading();
	setWidth(25);
	setHeight(25);
	myBall=new SmartBall();
	//myBall.scale(2.5,1.5);
	myBall.getScale().setToScale(2.5, 1.5);
	flames = new Flame[4];
	
	//Flame f0 = new Flame(); f0.translate(0, 5); f0.scale (5, 5);
	Flame f0 =new Flame(); f0.getTranslation().setToTranslation(0, 5);f0.getScale().setToScale(5, 5);
	flames[0] = f0 ; 
	Flame f1 = new Flame(); f1.getTranslation().setToTranslation(0, 5);f1.getRotation().setToRotation(-90);;f1.getScale().setToScale(5, 5);
	flames[1] = f1 ; 
	Flame f2 = new Flame(); f2.getTranslation().setToTranslation(0, 5);f2.getRotation().setToRotation(180);f2.getScale().setToScale(5, 5);
	flames[2] = f2 ; 
	Flame f3 = new Flame(); f3.getTranslation().setToTranslation(0, 5);f3.getRotation().setToRotation(90);f3.getScale().setToScale(5, 5);
	flames[3] = f3;
	
}


public void update()
{
	//this.getTranslation().translate(1, 1);
	//this.getRotation().rotate(Math.toRadians(1));
	flameOffset += flameIncrement ;
	for (Flame f:flames) {
	//f.getTranslation.translate(0, flameOffset); // this is why flames are TRANSLATED 1st
	f.getTranslation().setToTranslation(0, flameOffset);
	}
	// reverse direction of flame movement for next time if we've hit the max
	if (Math.abs(flameOffset) >= maxFlameOffset) {
	flameIncrement *= -1 ;
	}
	
	
	
}

	public void draw (Graphics2D g2d) {
		// save the current graphics transform for later restoration
		AffineTransform saveAT = g2d.getTransform() ;
		// append this shape's transforms to the graphics object's transform
		g2d.transform(getTranslation());
		g2d.transform(getRotation());
		
		g2d.transform(getScale());
		// draw this shape's components, modified by the updated g2d transformation
		
		
		if(isSelected())
		{
			g2d.setColor(Color.BLACK);
			myBall.draw(g2d);
			g2d.setColor(Color.BLACK);
			for (Flame f : flames) {
			f.draw(g2d);
			}

		}else {
		
		//
			g2d.setColor(Color.RED);
			myBall.draw(g2d);
			g2d.setColor(Color.YELLOW);
			for (Flame f : flames) {
				f.draw(g2d);
				}

		}
		
		// restore the old graphics transform (remove this shape's transform)
		g2d.setTransform (saveAT);
}
@Override
public void handleCollision(ICollider otherObject) {
	// TODO Auto-generated method stub
	if(otherObject instanceof Sweeper)
	{
	//	System.out.println("I hit a sweeper");
		this.setDeath(true);
	}
	if(otherObject instanceof Car)
	{
		this.maxFlameOffset=100;
		this.flameIncrement =10;
		collided =true;
		
	}
	
}


public void move(int height, double mS_SEC2, float x, float y) {
	// TODO Auto-generated method stub
	//myBall.move(height,mS_SEC2,x,y);
	if(collided)
	{
		maxFlameOffset=100;
		quickTime +=1;
		if(quickTime == 10)
		{
			this.setDeath(true);
		}
	}
	this.update();
	//myBall.move(myBall.getHeight(), mS_SEC2, x+90,y+90);
	
	//this.setHeading(myBall.getHeading());
	this.move(height, mS_SEC2);
	
}
public boolean contains(Point.Float p) {
	// TODO Auto-generated method stub
	int px = (int) p.getX()+getWidth()/2; // mouse location
	int py = (int) p.getY();
	//System.out.println(px + "   "+ py);
	int xLoc = (int) this.getXTran(); // shape location
	int yLoc = (int) this.getYTran();
	if ( (px >= xLoc) && (px <= xLoc+this.getWidth()) && (py >= yLoc) && (py <= yLoc+this.getHeight()) )
	return true; 
	else return false;
}

}
