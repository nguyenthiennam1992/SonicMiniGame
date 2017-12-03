package a4;

import java.awt.Graphics2D;
import java.util.Random;

public abstract class MoveableObject extends GameObject implements IMoveable{
	
	private double heading;
	private int speed;
	private Random rand = new Random();
	
	
	/*
	 public void move() {
		 double xDelta = Math.cos(Math.toRadians(90 - heading)) * speed;
			double yDelta = Math.sin(Math.toRadians(90 - heading)) * speed;
			
			if (getX()+xDelta <= 10) {
				xDelta *= -1;
				setRandHeading();
			}
			if (getX()+xDelta >= 1000) {
				
				xDelta *= -1;
				setRandHeading();
			}
			if ((getY()+yDelta) <= 10)
			{
				yDelta *= -1;
				setRandHeading();
			}
			if (getY()+yDelta >= 700) 
			{
				yDelta *= -1;
				setRandHeading();
				
			}
			super.setX(getX() + (float) xDelta);
			super.setY(getY() + (float) yDelta);
	    }
	    */
	public void setRandLocation() {
		/*
		 * Sets a random heading within the boundaries
		 */
		this.getTranslation().setToTranslation(rand.nextInt(200)+10, rand.nextInt(200)+10);
		this.getRotation().setToRotation(45);
	    this.getScale().setToScale(.8,-.8);
	}
	 public void move(double height,double mS_SEC2) {
		 
		 double xDelta = Math.cos(Math.toRadians(90 - heading)) * speed*((double)mS_SEC2/10);
		 double yDelta = Math.sin(Math.toRadians(90 - heading)) * speed*((double)mS_SEC2/10);
		 
			
			if (this.getTranslation().getTranslateX()+xDelta<= 15) {
				xDelta *=-1;	
				this.setRandHeading();
			}
			if (this.getTranslation().getTranslateX()+xDelta >= 480 ) {		
				xDelta *=-1;
				this.setRandHeading();
			}
			if (this.getTranslation().getTranslateY()+yDelta <= 15)
			{
				yDelta *= -1;
				
				this.setRandHeading();
			}
			if (this.getTranslation().getTranslateY()+yDelta >= 480) 
			{
				yDelta *= -1;	
				
				this.setRandHeading();
			}
			this.getTranslation().setToTranslation(this.getTranslation().getTranslateX()+xDelta,this.getTranslation().getTranslateY()+yDelta);
			//super.setX(getX() + (float) xDelta);
			//super.setY(getY() + (float) yDelta);
			
	    }
	 public void bounce(double height , double mS_SEC2)
	 {
		 this.setRandHeading();
		 double xDelta = Math.cos(Math.toRadians(90 - heading)) * speed*((double)mS_SEC2/10);
		 double yDelta = Math.sin(Math.toRadians(90 - heading)) * speed*((double)mS_SEC2/10); 
		 xDelta *=-1;
		 yDelta *=-1;
		 super.setX(getX() + (float) xDelta);
			super.setY(getY() + (float) yDelta);
	 }
		public void setRandHeading() {
			// TODO Auto-generated method stub
			int x = rand.nextInt(269);
			setHeading(x);
		}

	@Override
	public float getX() {
		// TODO Auto-generated method stub
		return super.getX();
	}

	@Override
	public float getY() {
		// TODO Auto-generated method stub
		return super.getY();
	}

	@Override
	public void setHeading(int heading) {
		// TODO Auto-generated method stub
		this.heading=heading;
	}
	public int getHeading()
	{
		return (int)heading;
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub
		super.setX(x);
	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub
		super.setY(y);
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		 g.transform(this.getTranslation());
	        g.transform(this.getScale());
	        g.transform(this.getRotation());
	}
	public void setSpeed(int speed)
	{
		this.speed=speed;
	}
	public int getSpeed()
	{
		return speed;
	}





}
