package a4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.Random;


public class Sweeper extends MoveableObject {
	
	private ChasePlayerStrategy chasePlayer = new ChasePlayerStrategy();
    private NormalStrategy normalStrategy = new NormalStrategy();
	private double count;
	Point2D.Double Point1 ;
	Point2D.Double Point2 ;
	Point2D.Double Point3 ;
	private float epsilon =.001f; // epsilon (“tolerance”) = (e.g.) .001
	private Point2D.Double[] Points = new Point2D.Double[4];
	Point2D.Double Point4 ;
	private int level =5;
	private Random rand = new Random();
	private boolean drawHull;
	private int lifeTime;
	public Sweeper(boolean drawHull2,int lifeTime)
	{

		super.setSpeed(1);
		super.setRandHeading();
		setDraw(drawHull2);
		setWidth(25);
		setHeight(25);
		//These have to be random but less the 4 times a size of a gameobject
		this.setlifeTime(lifeTime/2);
		Points[0]  = new Point2D.Double(0,0);
		Points[1]  = new Point2D.Double(rand.nextInt(20)+this.getWidth(),rand.nextInt(20)+this.getWidth());//Adjust
		Points[2]  = new Point2D.Double(rand.nextInt(35)+this.getHeight(),rand.nextInt(35)+this.getHeight());
		Points[3]  = new Point2D.Double(0,rand.nextInt(70)+35);	
	}

	public Sweeper(Float transPoint,boolean drawHull2,int lifeTime) {
		// TODO Auto-generated constructor stub
		super.setSpeed(1);
		this.setlifeTime(lifeTime/2);
		super.setRandHeading();
		setWidth(25);
		this.setDraw(drawHull2);
		setHeight(25);
		//These have to be random but less the 4 times a size of a gameobject
		Points[0]  = new Point2D.Double(0,0);
		Points[1]  = new Point2D.Double(rand.nextInt(20)+this.getWidth(),rand.nextInt(20)+this.getWidth());//Adjust
		Points[2]  = new Point2D.Double(rand.nextInt(35)+this.getHeight(),rand.nextInt(35)+this.getHeight());
		Points[3]  = new Point2D.Double(0,rand.nextInt(70)+35);	
		
		
	}
	public void setDraw(boolean which)
	{
		this.drawHull=which;
	}
	public void setlifeTime(int lifeTime)
	{
		this.lifeTime=lifeTime;
	}
	public int getLifeTime()
	{
		return lifeTime;
	}
	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		//System.out.println("SWEEPT AWAY!!!");
	}
	public void draw (Graphics2D g2d) {
		// save the current graphics transform for later restoration
		AffineTransform saveAT = g2d.getTransform() ;
		// append this shape's transforms to the graphics object's transform
		g2d.transform(getTranslation());
		g2d.transform(getRotation());
		g2d.setColor(Color.CYAN);
		g2d.transform(getScale());
		drawBezierCurve(g2d,Points,0);
		// draw this shape's components, modified by the updated g2d transformation
		//Control Points should be able to turn on or OFF'
		if(this.drawHull)
		{
			g2d.setColor(Color.CYAN);
		g2d.drawLine((int)Points[0].getX(),(int)Points[0].getY(),(int)Points[1].getX(),(int)Points[1].getY());
		
		g2d.drawLine((int)Points[1].getX(),(int)Points[1].getY(),(int)Points[2].getX(),(int)Points[2].getY());
	
		g2d.drawLine((int)Points[2].getX(),(int)Points[2].getY(),(int)Points[3].getX(),(int)Points[3].getY());
		
		}
		// restore the old graphics transform (remove this shape's transform)
		g2d.setTransform (saveAT);
}
	//Q is points!
	boolean straightEnough (Point2D.Double[] Q) {
		// find length around control polygon
		//		t values ??
		double d1,d2;
		
		//*****************************************
		//d1 = lengthOf(Q0,Q1) + lengthOf(Q1,Q2) + lengthOf(Q2,Q3);
		d1 = Math.pow(Q[0].x-Q[1].x + Q[0].y-Q[1].y, 2) + Math.pow(Q[1].x-Q[2].x + Q[1].y-Q[2].y, 2)+ Math.pow(Q[2].x-Q[3].x + Q[2].y-Q[3].y, 2);
		// find distance directly between first and last control point
	//	d2 = lengthOf(Q0,Q3) ;
		d2 = Math.pow(Q[0].x-Q[3].x + Q[0].y-Q[3].y, 2);
		
		if ( Math.abs(Math.sqrt(d1+d2)) < epsilon ) // epsilon (“tolerance”) = (e.g.) .001
		return true ;
		else
		return false ;
		}
	//Needs graphics inorder to draw
	void drawBezierCurve (Graphics2D g2d, Point2D.Double[] points, int Level) {
		//We dont know these yet
		Point2D.Double[] LeftSubArray =new Point2D.Double[4];
		Point2D.Double[] RightSubArray = new Point2D.Double[4];
		
		//Straight Enough??
		if ( straightEnough (points) || (Level>2)){
		//Draw Line from 1st Control Point to last Control Point ;
			g2d.setColor(Color.RED);
		g2d.drawLine((int)points[0].x,(int) points[0].y,(int)points[3].x,(int) points[3].y);
		}else {
		subdivideCurve (points, LeftSubArray, RightSubArray) ;
		
		//Recursive but how to stop?? maybe level is a counter
		drawBezierCurve (g2d,LeftSubArray,Level+1) ;
		drawBezierCurve (g2d,RightSubArray,Level+1) ;
		}
	}
	void subdivideCurve (Point2D.Double[] Q,Point2D.Double[] R,Point2D.Double[] S) {
		//System.out.println("I subdivided");
		R[0] = Q[0] ;
		//R[1] = (Q[0]+Q[1]) / 2.0 ;
		R[1] = new Point2D.Double(Q[0].x /2.0f + Q[1].x/2.0f, Q[0].y/2.0f+Q[1].y/2.0);
		//R[2] = (R[1]/2.0) + (Q[1]+Q[2])/4.0 ;
		R[2] = new Point2D.Double(R[1].x/2.0f +Q[1].x/4.0f + Q[2].x/4.0f,R[1].y/2.0f +Q[1].y/4.0f + Q[2].y/4.0f  );
		//S[3] = Q(3) ;
		S[3] =Q[3];
		//S[2] = (Q[2]+Q[3]) / 2.0 ;
		S[2] = new Point2D.Double(Q[2].x /2.0f +Q[3].x/2.0f,Q[2].y /2.0f +Q[3].y/2.0f );
		//S[1] = (Q[1]+Q[2])/4.0 + S[2]/2.0 ;
		S[1] = new Point2D.Double(Q[1].x/4.0f + Q[2].x/4.0f +S[2].x/2.0f, Q[1].y/4.0f + Q[2].y/4.0f +S[2].y/2.0f);
		
		//R[3] = (R[2]+S[1]) / 2.0 ;
		R[3] =new Point2D.Double(R[2].x/2.0f + S[1].x/2.0f,R[2].y/2.0f+S[1].y/2.0f);
		//S[0] = R[3] ;
		S[0] =R[3];
		}
}
