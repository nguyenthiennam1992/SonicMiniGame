package a4;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Flame extends MoveableObject  {
	private Point top, bottomLeft, bottomRight ;

	public Flame ()
	{
		this.setHeight(4);
		this.setWidth(2);
		top = new Point(0,2);
		bottomLeft = new Point (-1, -2);
		bottomRight = new Point (1, -2);
		

	}

	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		AffineTransform saveAT = g2d.getTransform() ;
		// Append this shape's transforms to the graphics object's transform. Note the
		// ORDER: Translation will be done FIRST, then Scaling, and lastly Rotation
		g2d.transform(getRotation());
		g2d.transform(getScale());
		g2d.transform(getTranslation());
		
		// draw this object in the defined color
		;
		g2d.drawLine(top.x, top.y, bottomLeft.x, bottomLeft.y);
		g2d.drawLine(bottomLeft.x,bottomLeft.y,bottomRight.x,bottomRight.y);
		g2d.drawLine(bottomRight.x,bottomRight.y,top.x,top.y);
		
		// restore the old graphics transform (remove this shape's transform)
		g2d.setTransform (saveAT) ;
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		// TODO Auto-generated method stub
		System.out.println("i hit a flame :(");
	}

	
	
}
