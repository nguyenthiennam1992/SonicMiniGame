package a4;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

/** This interface defines the services (methods) provided
* by an object which is “Selectable” on the screen
*/
public interface ISelectable {
// a way to mark an object as “selected” or not
public void setSelected(boolean yesNo);
// a way to test whether an object is selected
public boolean isSelected();
// a way to determine if a mouse point is “in” an object
public boolean contains(Float transPoint);
// a way to “draw” the object that knows about drawing
// different ways depending on “isSelected”
public void draw(Graphics2D g);
}