package model;

import java.util.Random;

/**
 * Class that handles individual cells 
 * @author Lilly Purrington
 *
 */

public class Cell {

	private int row;
	private int column;
	private boolean isEdge;
	private boolean hasBacteria;
	//The position of all the neighbors relative to the cell
	private int[][] neighbors = {{-1,-1},{0,-1},{+1,-1},{+1,0},{+1,+1},{0,+1},{-1,+1},{-1,0}};
	
	public static final int BACTERIA_CHANCE = 40/100;
	private static final int LONELY_BACTERIA = 1;
	private static final int OVERCROWDED_BACTERIA = 4;
	private static final int JUST_RIGHT_BACTERIA = 3;
	
	
	public Cell(int row, int column, boolean isEdge) {
		this.row = row;
		this.column = column;
		this.isEdge = isEdge;
		hasBacteria = new Random().nextInt() < BACTERIA_CHANCE && !isEdge;
	}


	//Updates the cell according to the algorithm
	public void update(Cell[][] cellBoard) {
		if (!isEdge) {
			int bacteriaNeighbors = 0;
			for (int[] neighbor : neighbors) {
				if (cellBoard[row+neighbor[0]][column+neighbor[1]].hasBacteria()) {
					bacteriaNeighbors++;
				}
			}
			
			if (bacteriaNeighbors <= LONELY_BACTERIA || bacteriaNeighbors >= OVERCROWDED_BACTERIA) {
				hasBacteria = false;
			}else if (bacteriaNeighbors == JUST_RIGHT_BACTERIA) {
				hasBacteria = true;
			}
		}
	}


	
	
	//Returns a copy of the cell
	public Cell copy() {
		Cell cell = new Cell(row,column,isEdge);
		cell.setBacteria(hasBacteria);
		return cell;
		
	}
	
	public void setBacteria(boolean hasBacteria) {
			this.hasBacteria = hasBacteria && !isEdge;
	}
	
	//If the cell is an edge or not
	public boolean isEdge() {
		return isEdge;
	}
	
	public boolean hasBacteria() {
		return hasBacteria;
	}
	
	public String toString() {
		return "row: " + row + " column: " + column + " edge: " + isEdge + " bacteria: " + hasBacteria;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	
	public boolean equals(Cell otherCell) {
		return (row == otherCell.getRow() && column == otherCell.getColumn() && isEdge == otherCell.isEdge() && hasBacteria == otherCell.hasBacteria());
	}
	
}
