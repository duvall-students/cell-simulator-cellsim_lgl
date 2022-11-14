package model;

public class Cell {

	private int row;
	private int column;
	private boolean isEdge;
	
	public Cell(int row, int column, boolean isEdge) {
		this.row = row;
		this.column = column;
		this.isEdge = isEdge;
	}


	//Updates the cell according to the algorithm
	public void update(Cell[][] cellBoard) {
		
	}


	//Returns a copy of the cell
	public Cell copy() {
		return new Cell(row, column, isEdge);
	}
	
	public boolean isEdge() {
		return isEdge;
	}
	
}
