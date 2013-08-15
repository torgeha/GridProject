
public enum Cell {
	
	EMPTY(0), 
	WALL(1),
	STARTPOINT(2),
	ENDPOINT(3),
	PATH(4);
	
	private int cellType;
	
	private Cell(int cellType) {
		this.cellType = cellType;
	}
	
	public int getCellType() {
		return cellType;
	}
	
}
