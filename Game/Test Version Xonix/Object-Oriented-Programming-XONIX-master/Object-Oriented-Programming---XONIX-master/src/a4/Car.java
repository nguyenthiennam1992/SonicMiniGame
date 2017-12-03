package a4;

import java.awt.Color;//do we need this here??
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.lang.Math;
import java.util.Random;//Random

public class Car extends MoveableObject implements ISteerable,
		IMoveable {
	// private Point2D.Float location = new Point2D.Float(247.5f,2.5f); // Has
	// to be on the 50...1 --*--100
	private Random rand = new Random();
	private Flame [] flames;
	
	private Flame f2;
	private float XCOORD = 247.5f;
	private float YCOORD = 2.5f;
	private int radius;
	private int headingList[] = { 0, 90, 180, 270 };
	private boolean isSelected;
	private float oldX;
	private float oldY;
	public Car() {
		setHeight(5);
		setWidth(5);
		//setX();
		//setY();
		this.getTranslation().setToTranslation(247.5,2.5);
		this.getScale().setToScale(-.8,.8);
		setRandColor();
		setRadius(radius);
		setSpeed(1);
		setHeading(0);

	}

	public void setX()
	{
		setX(XCOORD);
	}
	public void setY()
	{
		setY(YCOORD);
	}
	public String toString() {
		String s = "Car: " + "Loc=[" + getX() + "," + getY()
				+ "]" + " color= [" + getColor().getRed() + ","
				+ getColor().getGreen() + "," + getColor().getBlue()
				+ "] speed=" + this.getSpeed() + " heading="
				+ this.getHeading() + " radius=" + this.getRadius();
		return s;
	}


	public int getRadius() {
		return radius;
	}

	public int getSpeed() {
		// TODO Auto-generated method stub
		return super.getSpeed();
	}



	public void setHeading(int heading) {
		for (int i = 0; i <= 3; i++) {
			if (headingList[i] == heading) {
				super.setHeading(heading);
			}

		}

	}

	public void setRandColor() {
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		setColor(Color.getHSBColor(r, g, b));

	}
	public void move(double mS_SEC2) {	 
		 double xDelta = Math.cos(Math.toRadians(90 - getHeading())) * getSpeed()*((double)mS_SEC2/10);
		 double yDelta = Math.sin(Math.toRadians(90 - getHeading())) * getSpeed()*((double)mS_SEC2/10);
			if (this.getTranslation().getTranslateX()+xDelta < 2.5) {				
					//this.setTranX(2.5f);					
					this.getTranslation().setToTranslation(2.5f, this.getYTran());
					//setSpeed(0);
					yDelta =0;
					xDelta =0;
			}
			if (this.getTranslation().getTranslateX()+xDelta > 497.5 ) {		
				//setSpeed(0);
				yDelta =0;
				//this.translate(497.5f,this.getYTran());
				this.getTranslation().setToTranslation(497.5f, this.getYTran());
			}
			if ((this.getTranslation().getTranslateY()+yDelta) < 2.5f)
			{
				//this.setY(2.5f);
				this.getTranslation().setToTranslation(this.getXTran(), 2.5f);
				//setSpeed(0);
				//yDelta =0;
				//xDelta =0;			
			}
			if (this.getTranslation().getTranslateY()+yDelta > 497.5) 
			{
				this.setY(497.5f);
				this.getTranslation().setToTranslation(this.getXTran(), 497.5f);
				//setSpeed(0);
				//yDelta =0;
				//xDelta =0;
			}
			this.getTranslation().setToTranslation(this.getTranslation().getTranslateX()+xDelta,this.getTranslation().getTranslateY()+yDelta);			
	    }
	public float getOldX()
	{
		return oldX;
	}
	public float getOldY()
	{
		return oldY;
	}
	private void setRadius(int radius2) {
		// TODO Auto-generated method stub
		radius2 = Math.abs(rand.nextInt() % 10) + 1;
		this.radius = radius2;
	}

	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color;
	}


	@Override
	public void draw(Graphics2D g) {
		//Cars are rectangles,
		AffineTransform saveAT = g.getTransform() ;
		g.transform(getTranslation()); // This time translation is applied LAST
		g.transform(getRotation());
		g.transform(getScale());	
		g.setColor(Color.PINK);
		Point boxCorner = new Point (-getWidth(), -getWidth());	
		g.fillRect (boxCorner.x, boxCorner.y, getWidth()*2, getWidth()*2) ;
		g.setColor(Color.RED);
		//x1 y1 x2 y2
		if(this.getHeading()==0)
		{
			g.drawLine(0, 0, 0, 10);
		}else if( this.getHeading() ==180)
		{
			g.drawLine(0, 0, 0, -10);
		}else if(this.getHeading() ==90)
		{
			g.drawLine(-10, 0, 0, 0);
		}else if( this.getHeading() ==270)
		{
			g.drawLine(+10, 0, 0, 0);
		}	
		//g.drawRect((int)(this.getXTran()-(getWidth()/2)), (int)(this.getY()-(getHeight()/2)), getWidth()+1, getHeight());
		//g.fillRect((int)(this.getX()-(getWidth()/2)), (int)(this.getY()-(getHeight()/2)), getWidth()+1, getHeight());
		g.setTransform (saveAT) ;
	}

	@Override
	public void handleCollision(ICollider object) {
		// TODO Auto-generated method stub

			
		//object.handleCollision(object);
		if(object instanceof Fireball||object instanceof SuperBall|| object instanceof Flame|| object instanceof Sweeper)
		{
			this.getTranslation().setToTranslation(XCOORD,YCOORD);
			
			setSpeed(0);
		}
		if(object instanceof TimeTicket)
		{
			//Do nothing!!!
			
		}
		if(object instanceof MonsterBall)
		{
			this.getTranslation().setToTranslation(XCOORD,YCOORD);
			setSpeed(0);
		}
		
	
	}

	


}