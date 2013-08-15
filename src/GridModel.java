import java.util.ArrayList;


public class GridModel {
	
	public final static String GRID_PROP = "Grid";
	
	private int rows;
	private int columns;
	
	private GridPanel gridPanel;
	
	private ArrayList<ArrayList<Cell>> grid;
	
	public GridModel() {
		this(10,10);
	}
	
	public GridModel(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		
		initGridList();
	}
	
	//Init arraylist and set all points as EMPTY
	private void initGridList() {
		grid = new ArrayList<ArrayList<Cell>>();
		for (int y = 0; y < rows; y++) {
			grid.add(new ArrayList<Cell>());
			for (int x = 0; x < columns; x++) {
				grid.get(y).add(Cell.EMPTY);
			}
		}
	}
	
	public void setCell(int x, int y, Cell cellType) {
		if (!(this.grid.get(y).get(x).equals(cellType))) {
			this.grid.get(y).set(x, cellType);
			gridPanel.propertyChange(x, y, cellType);
		}
	}
	
	public Cell getCell(int x, int y) {
		return this.grid.get(y).get(x);
	}
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public void setGridPanel(GridPanel gridPanel) {
		this.gridPanel = gridPanel;
	}
	
	public String toString() {
		String ret = "";
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				ret += (grid.get(y).get(x) + " ");
			}
			ret += "\n";
		}
		return ret;
	}
	
	public static void main(String[] args) {
		GridModel gm = new GridModel();
		
		System.out.println(gm);
		
		
	}
	

}
