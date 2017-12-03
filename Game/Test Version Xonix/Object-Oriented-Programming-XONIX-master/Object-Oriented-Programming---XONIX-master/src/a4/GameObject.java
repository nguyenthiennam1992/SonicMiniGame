package a4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.File;

public abstract class GameObject implements IDrawable,ICollider {
	protected Color color;
	private float x ,y ;
	private Point location;
	private int width,height;
	private int size;
	private boolean isDead =false;
	private AffineTransform myTranslation =new AffineTransform();;
	private AffineTransform myRotation=new AffineTransform(); ;
	private AffineTransform myScale=new AffineTransform(); ;
	public Color getColor() {
		return color;
	}
	public void setDeath(boolean isDead)
	{
		this.isDead=isDead;
	}
	public boolean getDeath()
	{
		return isDead;
	}
	public float getX() {
		return x;
		//return (float) myTranslation.getTranslateX();
	}
	public float getXTran() {
		
		return (float) myTranslation.getTranslateX();
	}
	public float getYTran()
	{
		
	return (float) myTranslation.getTranslateY();
		
	}
	

	
	
	public void setX(float x) {
		if(x >=2.5 || x< 450.5)
		{
			this.x = x;
		}	
	}

	public float getY() {
		return y;
		//return (float) myTranslation.getTranslateY();
	}

	public void setY(float y) {
		if(y >=2.5 || y< 450.5)
		{
		this.y = y;	
		}	
	}
	
	
	
	public void setLocation(Point location)
	{
		this.location=location;
	}
	public void setHeight(int height)
	{
		this.height =height;
	}
	public void setWidth(int width)
	{
		this.width=width;
	}
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public Point getLocation()
	{
		return location;
	}
	public int getSize()
	{
		 return size= (int) ((int) Math.sqrt((this.width / 2 * this.width / 2) + (this.height / 2 * this.height / 2)));
	}

	public boolean collidesWith(ICollider obj)
	{
	GameObject tmp = (GameObject) obj;

/*
	int objSizeTemp = this.getSize()/2;
	int thisSize = this.getSize()/2;
	
	int thisCenterX =(int) x+ size/2;//+ width/2;
	int thisCenterY =(int) y+ size/2;//+ height/2;
	
	int objX =(int) tmp.getX()+tmp.getSize()/2;//+ tmp.getWidth()/2;
	int objY =(int) tmp.getY()+tmp.getSize()/2;//+ tmp.getHeight()/2;
	*/
	//Different Strategy
		
	float objX1=  (this.getXTran()-this.getWidth()/2);
	float objX2= (this.getXTran()+this.getWidth()/2);
	float objY1= (this.getYTran()-this.getHeight()/2);
	float objY2= (this.getYTran()+this.getHeight()/2);
	
	float tmpX1= (tmp.getXTran()-tmp.getWidth()/2);
	float tmpX2= (tmp.getXTran()+tmp.getWidth()/2);
	float tmpY1= (tmp.getYTran()-tmp.getHeight()/2);
	float tmpY2= (tmp.getYTran()+tmp.getHeight()/2);
	
	if(objX1 <= tmpX2 && objX2 >= tmpX1 && objY1 <= tmpY2 && objY2 >= tmpY1 )
	{
		return true;
	}	
	
	return false ;
	}

	    public AffineTransform getTranslation() {
	        return myTranslation;
	    }

	    public AffineTransform getRotation() {
	        return myRotation;
	    }

	    public AffineTransform getScale() {
	        return myScale;
	    }


	
}