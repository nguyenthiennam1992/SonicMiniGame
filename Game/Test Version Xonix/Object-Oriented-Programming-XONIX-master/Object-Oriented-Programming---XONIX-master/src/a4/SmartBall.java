package a4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;


public class SmartBall extends SuperBall implements IStrategy,ISelectable  {
	private ChasePlayerStrategy chasePlayer = new ChasePlayerStrategy();
    private NormalStrategy normalStrategy = new NormalStrategy();
	private double count;
	private boolean isSelected = false;
	private MapView mv;
	private Point top,bottomLeft,bottomRight;
		public SmartBall()
		{
			
			super.setWidth(7);
			setStrategy(normalStrategy);
			
			
	//		setCollisionSound("boom_SmartBall.wav");
			
		}
		public void setStrategy(IStrategy s)
		{
			curStrategy =s;
		}
		public void setHeading(double heading)
		{
			super.setRandHeading();
		}
		

		//Need to add get size
		public IStrategy getStrategy() {
			// TODO Auto-generated method stub
			return curStrategy;
		}


		@Override
		public void draw(Graphics2D g) {
			// TODO Auto-generated method stub
/*
			int[] xList ={ (int) (getX()-(getWidth()/2)), (int) getX(), (int) (getX()+(getWidth()/2))};
			int[] yList ={(int) (getY()+(getHeight()/2)),(int) (getY()),(int) getY()+(getHeight()/2)};
			if(isSelected())
			{
			int[] xList1 ={ (int) (getX()-(getWidth()/2+1)), (int) getX(), (int) (getX()+(getWidth()/2+1))};
			int[] yList1 ={(int) (getY()+(getHeight()/2)-1),(int) (getY()+1),(int) getY()+(getHeight()/2)-1};
			g.setColor(Color.BLACK);
			g.fillPolygon(xList1, yList1, 3);
			}
			g.setColor(Color.RED);
			g.drawPolygon(xList, yList, 3);
		*/	
			AffineTransform saveAT = g.getTransform() ;
			g.transform(getTranslation()); // This time translation is applied LAST
			g.transform(getRotation());
			g.transform(getScale());
			
			// calculate the local-space location of the lower-left corner of the bounding box
			// for the circle, since that's where the object will be drawn from in local space
			Point boxCorner = new Point (-getWidth(), -getWidth());
			if(isSelected())
			{
				g.setColor(Color.BLACK);
				g.fillOval (0, 0, getWidth()*2, getWidth()*2) ;
			System.out.println("IM selected");
			}else {
			
			//
			g.fillOval (boxCorner.x, boxCorner.y, getWidth()*2, getWidth()*2) ;
			g.setTransform (saveAT) ;
			}
			


			//g.drawOval((int)(this.getX()-(width/2)), (int)(this.getY()-(height/2)), width, height);
		}
		@Override
		public void setX(float x) {
			// TODO Auto-generated method stub
			
			super.setX(x);
		}
		@Override
		public void setY(float y) {
			// TODO Auto-generated method stub
			
			super.setX(y);
		}

		@Override
		public void apply(SmartBall object,float playerX,float playerY) {
			// TODO Auto-generated method stub
			curStrategy.apply(this,playerX,playerY);
			
			
		}

		@Override
		public void handleCollision(ICollider otherObject) {
			// TODO Auto-generated method stub
			if(otherObject instanceof Car)
			{
				//make the ball explode! or just get bigger
				//super.setWidth(this.getWidth()*2);
			//	super.setHeight(this.getHeight()*2);					
			}
			
		}
		public void move(int height,double mS_SEC2,float playerX,float playerY)
		{
			count += (double)mS_SEC2;
			if(count >1000)//times *10
			{
				
				if(curStrategy == normalStrategy)
				{
					setStrategy(chasePlayer);
					count=0;
				}
				else
				{
					setStrategy(normalStrategy);
					count=0;
				}
			}
			curStrategy.apply(this,playerX,playerY);
			//super.move(height, mS_SEC2);
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
			int py = (int) p.getY();
			System.out.println(px + "   "+ py);
			int xLoc = (int) this.getXTran(); // shape location
			int yLoc = (int) this.getYTran();
			if ( (px >= xLoc) && (px <= xLoc+this.getWidth()) && (py >= yLoc) && (py <= yLoc+this.getHeight()) )
			return true; 
			else return false;
		}
		


		
		
}
