package a4;

import java.awt.Color;
import java.util.Random;//Random
import java.lang.Math;

public abstract class SuperBall extends MoveableObject implements IColorable, IMoveable {
	protected IStrategy curStrategy=null;
	private Random rand = new Random();
	private int radius;
	private int headingList[] = { 0, 90, 180, 270 };
	// Setting up the Object
	public SuperBall() {
		/*
		 * Default values
		 */
		setRandColor();
		setRandRadius();
		
		setSpeed(1);
		setRandHeading();
	}


	private void setRandRadius() {
		// make random size within a range
		radius = Math.abs(rand.nextInt(10));
	}



	public int getHeading() {
		return super.getHeading();

	}

	public int getSpeed() {
		return super.getSpeed();
	}

	public int getRadius() {
		return radius;
	}

	public void setRandSpeed() {
		//speed = Math.abs((rand.nextInt() % 100));
		super.setSpeed(Math.abs((rand.nextInt() % 1)+1));
	}

	public void setRandHeading() {
		// TODO Auto-generated method stub
		int x = rand.nextInt(4);
		setHeading(headingList[x]);

	}

	public void setHeading(int heading2) {			
		super.setHeading((int)heading2);
	}

	public void setRandColor() {
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		color = Color.getHSBColor(r, g, b);
	}
	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color;
	}
	 public void setStrategy(IStrategy s)
	 {
		 this.curStrategy=s;
	 }

}
