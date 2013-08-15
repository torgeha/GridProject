import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class Rectangle extends Rectangle2D.Double{
	
	private Color color;
	
	public Rectangle (double x, double y, double width, double height, Color color) {
		super(x,y,width,height);
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
	}
	
}
