package model;

public class Cell {

	private int row;
	private int column;
	
	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
	}


	//Updates the cell according to the algorithm
	public void update(Cell[][] cellBoard) {
		
	}


	//Returns a copy of the cell
	public Cell copy() {
		return new Cell(row, column);
	}
	
}
