import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridPanel extends JPanel{

	private final int CELL_SIZE = 64;
	private final float STROKE = 2f;
	private final int rows;
	private final int columns;
	
	private Map<Point2D.Double, Rectangle> recList;
	
	private GridModel model;

	public GridPanel(GridModel model) {
		this.model = model;
		this.rows = model.getRows();
		this.columns = model.getColumns();
		this.recList = new HashMap<Point2D.Double, Rectangle>();

		setPreferredSize(new Dimension(columns * CELL_SIZE, rows * CELL_SIZE));
		addMouseListener(new MListener());

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//set g2 ready for grid drawing
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.GRAY);
		g2.setStroke(new BasicStroke(STROKE));

		// draw verticle lines
		for (int i = 0; i < columns + 1; i++) {
			g2.draw(new Line2D.Double(i * CELL_SIZE, 0, i * CELL_SIZE, rows
					* CELL_SIZE));
		}
		// draw horizontal lines
		for (int i = 0; i < rows + 1; i++) {
			g2.draw(new Line2D.Double(0, i * CELL_SIZE, columns * CELL_SIZE, i
					* CELL_SIZE));
		}
		// set g2 ready for rectangle drawing
		GradientPaint colorToWhite;
		int gradXStart;
		int gradYStart;

		// draw all rectangles in recList
		if (!recList.isEmpty()) {
			for (Point2D.Double key : recList.keySet()) {

				// calculate gradient fill
				gradXStart = (int) key.getX();
				gradYStart = (int) key.getY();
				
				Color actualColor = recList.get(key).getColor();
				
				colorToWhite = new GradientPaint(gradXStart, gradYStart,
						actualColor, gradXStart + CELL_SIZE, gradYStart
								+ CELL_SIZE, Color.WHITE);
				g2.setPaint(colorToWhite);
				
				g2.draw(recList.get(key));
				g2.fill(recList.get(key));
			}
		}
	}

	private void drawCell(int x, int y, Cell cellType) {
		System.out.println("x: " + x + " y: " + y);

		int recX = calculateRecX(x);
		int recY = calculateRecY(y);
		
		System.out.println("recX: " + recX + "---" + "recY: " + recY);

		// do not draw rect outside grid
		if ((recX > (columns * CELL_SIZE)) || (recY > rows * CELL_SIZE)
				|| (recX < 0) || (recY < 0)) {
			return;
		}
		
		//create coordinate, also used as key in map
		Point2D.Double coordinate = new Point2D.Double(recX, recY);
		
		//if rect already exist, remove. If not, insert
		if (recList.get(coordinate) != null) {
			recList.remove(coordinate);
		}
		else {
			Rectangle rec = new Rectangle(coordinate.getX(), coordinate.getY(), CELL_SIZE
					- (2 * STROKE), CELL_SIZE - (2 * STROKE), Color.BLUE);
			recList.put(coordinate, rec);
		}
		repaint();
	}

	private int calculateRecX(int x) {
		return (((int) x / CELL_SIZE) * CELL_SIZE) + (int) STROKE;
	}

	private int calculateRecY(int y) {
		return (((int) y / CELL_SIZE) * CELL_SIZE) + (int) STROKE;
	}

	private class MListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			drawCell(e.getX(), e.getY(), Cell.EMPTY);
		}
	}
	
	//repaint grid, for better performance I should draw only the cell that has changed
	public void propertyChange(int indexX, int indexY, Cell cellType) {
		//The drawCell requires pixel coordinates
		int x = (indexX * CELL_SIZE);
		int y = (indexY * CELL_SIZE);
		
		drawCell(x,y, cellType);
		
	}
}
