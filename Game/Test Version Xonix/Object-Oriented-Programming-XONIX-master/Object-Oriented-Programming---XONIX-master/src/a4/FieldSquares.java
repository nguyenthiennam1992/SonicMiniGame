package a4;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class FieldSquares extends GameObject implements IColorable {
private boolean owned;
private boolean almostOwned;

	public FieldSquares() {
		setWidth(5);
		setHeight(5);
		setOwned(false);
		setAlmostOwned(false);

		
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setRandColor() {
		// We dont want each tile to be different color but the same
	}

	@Override
	public void draw(Graphics2D g) {
		/*
		FieldSquares are displayed by first drawing filled rectangles and then
		overdrawing each square with an unfilled rectangle;
		 */
		AffineTransform saveAT = g.getTransform() ;
		g.transform(getTranslation()); // This time translation is applied LAST
		g.transform(getRotation());
		g.transform(getScale());
		Point boxCorner = new Point (-getWidth(), -getWidth());
		
		
		if(isAlmostOwned())
		{
	//	g.setColor(Color.BLUE);
			//g.fillRect((int)(this.getX()-((getWidth()/2)-1)), (int)(this.getY()-((getHeight()/2)-1)), getWidth(), getHeight());
		//	g.setColor(Color.WHITE);
		//	g.fillRect((int)(this.getX()-((getWidth()/2)-1)), (int)(this.getY()-((getHeight()/2)-1)), getWidth(), getHeight());
			g.setColor(Color.CYAN);
			g.drawRect(boxCorner.x, boxCorner.y, getWidth()*2, getWidth()*2) ;
			//g.draw(new Rectangle2D.Double((this.getXTran()-(getWidth()/2)),(this.getYTran()-(getHeight()/2)),getWidth(),getHeight()));
		}else {
		//g.drawRect((int)(this.getX()-(getWidth()/2)), (int)(this.getY()-(getHeight()/2)), getWidth(), getHeight());
		//g.setColor(Color.WHITE);
		//g.drawRect (boxCorner.x, boxCorner.y, getWidth()*2, getWidth()*2) ;
		//g.fillRect((int)(this.getXTran()-((getWidth()/2)-1)), (int)(this.getYTran()-((getHeight()/2)-1)), getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillRect (boxCorner.x, boxCorner.y, getWidth()*2, getWidth()*2) ;
		//g.draw(new Rectangle2D.Double((-getWidth()-(getWidth()/2)),(getWidth()-(getHeight()/2)),getWidth(),getHeight()));
		}
		g.setTransform (saveAT) ;
	}


	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		owned =true;

	}

	public boolean isAlmostOwned() {
		return almostOwned;
	}

	public void setAlmostOwned(boolean almostOwned) {
		this.almostOwned = almostOwned;
	}

	public boolean isOwned() {
		return owned;
	}

	public void setOwned(boolean owned) {
		this.owned = owned;
	}
	public boolean contains(float x,float y) {
		// TODO Auto-generated method stub
		int px = (int) x+getWidth()/2; // mouse location
		int py = (int) y+getHeight()/2;
		int xLoc = (int) this.getXTran(); // shape location
		int yLoc = (int) this.getYTran();
		if ( (px >= xLoc) && (px <= xLoc+this.getWidth()) && (py >= yLoc) && (py <= yLoc+this.getHeight()) )
		return true; 
		else return false;
	}
}