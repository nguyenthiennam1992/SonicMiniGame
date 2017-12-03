package a4;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Iterator;
import java.awt.geom.AffineTransform;//a4
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MapView extends JPanel implements IObserver,MouseListener,MouseMotionListener,MouseWheelListener{
private JPanel centerPanel = new JPanel();
private GameWorldProxy gameWorldProxy;
private AffineTransform worldToND,ndToScreen,vTm,inverseVTM,theVTM;
//This means that a reference to the GameWorldProxy
//must be saved when MapView is constructed,


private double windowLeft = 0;
private double windowRight =500; //Maybe adjust to varible for the span
private double windowTop = 500;
private double windowBottom = 0;
private Point prev=new Point(0,0);
private Point2D.Float transPoint;
public MapView(GameWorldProxy gwProxy)
{
	addMouseListener(this);
	this.gameWorldProxy = gwProxy;
	//this.setBorder(new LineBorder(Color.black,1));
	//this.add(centerPanel,BorderLayout.CENTER);
	addMouseMotionListener(this);
	this.repaint();
	addMouseWheelListener(this);
}

	@Override
	public void update(IObservable o, Object arg) {
		// TODO Auto-generated method stub
		//Next Assignment
		// code here to output current map information (based on 
		 // the data in the Observable) to the console
		gameWorldProxy =(GameWorldProxy) o;
		this.repaint();
	}
	public AffineTransform buildWorldToNDXform(double WIDTH,double HEIGHT,double windowLeft,double windowBottom)
	{
		AffineTransform mapView2 =new AffineTransform();
		mapView2.setToIdentity();
		//Adjust for zoom and zoom out
		
		mapView2.scale(1.0/WIDTH, 1.0/HEIGHT);
		mapView2.translate( -windowLeft, -windowBottom);
		return mapView2;
	}
	public AffineTransform buildNDToScreenXform (double width,double height)
	{
		AffineTransform mapViewTran = new AffineTransform();
		mapViewTran.setToIdentity();
		mapViewTran.translate(0,height);
		//				wdith height( negative)
		mapViewTran.scale(width, -height);

		return mapViewTran;
		
	}
	public void paintComponent(Graphics g)
	{
		//A4!!!!! we should be a transform here
		super.paintComponent(g);
		Graphics2D g2D =(Graphics2D) g;
	//Throw up to be private
		 ObjectCollection objCollection = gameWorldProxy.getCollection();
		
		Iterator inter1 = objCollection.iterator();
		//WORK!!
		//has to be varibLES CAUSE ITS throwing off the zoom
		worldToND = buildWorldToNDXform(windowRight-windowLeft,windowTop-windowBottom,windowLeft,windowBottom);
		ndToScreen = buildNDToScreenXform (this.getSize().getWidth(),this.getSize().getHeight());
		//Combine the Transforms
		 theVTM =(AffineTransform) ndToScreen.clone();
		 theVTM.concatenate(worldToND);
		 //theVTM Now contains the mapview Trans!!!!
		 
		
		g2D.transform(theVTM);
		//ORDER OF READING
		// <-----------------------------

		while(inter1.hasNext())
		{
			GameObject gwObj =(GameObject) inter1.next();
	
			gwObj.draw(g2D);
			g2D.setTransform(g2D.getTransform());
			
		}
		//g2D.transform(theVTM);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		if(!gameWorldProxy.isPaused())
		{
		if(e.getWheelRotation()<0)
		{
			this.zoomIn();
		}else{
			this.zoomOut();
		}
		}
	}
	public void zoomIn() {
		double h = windowTop - windowBottom;
		double w = windowRight - windowLeft;
		windowLeft += w*0.05;
		windowRight -= w*0.05;
		windowTop -= h*0.05;
		windowBottom += h*0.05;
		this.repaint();
		}
	public void zoomOut() {
		double h = windowTop - windowBottom;
		double w = windowRight - windowLeft;
		windowLeft -= w*0.05;
		windowRight += w*0.05;
		windowTop += h*0.05;
		windowBottom -= h*0.05;
		this.repaint();
		}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// 2 points
		
		
		if(!gameWorldProxy.isPaused())
		{
		 setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		 float x = (float) prev.getX();
         float y =  (float) prev.getY();
         float newx = arg0.getX();
         float newy = arg0.getY();
         
         if (x < newx){
             windowLeft -= 5;
             windowRight -= 5;
             
         }
         if (x > newx){
             windowLeft +=5;
             windowRight += 5;
         }
         if (y > newy){
        	 
             windowTop -=5;
             windowBottom -=5;
         }
         if (y <newy){
             windowTop += 5;
             windowBottom += 5;
         }
		
         //Save point
    	prev= arg0.getPoint();
 }
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		try {
			inverseVTM = theVTM.createInverse(); // slide 31
		} catch (NoninvertibleTransformException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Point p =arg0.getPoint();
		transPoint = new Point2D.Float();
		inverseVTM.transform(p, transPoint);
		
		System.out.print(transPoint.x +"  , " +transPoint.y);
		if(gameWorldProxy.isPaused())
		{
		if(arg0.isControlDown())
		{
			select(arg0,true);
			
		}else {
			select(arg0,false);
		}
		if(arg0.isShiftDown())
		{
			gameWorldProxy.addSweeper(transPoint);
		}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		 int newx = e.getX();
         int newy = e.getY();
         prev = new Point(newx,newy);
		
	}
	public void select(MouseEvent e,boolean control)
	{
		if(gameWorldProxy.isPaused())
		{
		ObjectCollection objects =gameWorldProxy.getCollection();
		Iterator itr = objects.getIterator();
		itr =objects.getIterator();
		Point p =e.getPoint();
		inverseVTM.transform(p, p);
	//	System.out.print(p.x +"  , " +p.y);
		//System.out.println("click");
		while(itr.hasNext()){
			GameObject object =(GameObject)itr.next();
		if(object instanceof ISelectable)
		{
			ISelectable selectableObject = (ISelectable)object;
			
			if(selectableObject.contains(transPoint))
			{	
				((ISelectable) object).setSelected(true);			
			}
			/*

			 *  To do this, the contains() method
of each shape must apply the inverse of the shape’s local transformations to the mouse world
coordinate in order to determine whether the world coordinate lies within the shape.

			 */			
			else {
				if(!control)
				{
				((ISelectable) object).setSelected(false);
				}		
			}
		}
		}
	//	this.repaint();
		
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		 int newx = e.getX();
         int newy = e.getY();
         prev = new Point(newx,newy);
         setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

}
