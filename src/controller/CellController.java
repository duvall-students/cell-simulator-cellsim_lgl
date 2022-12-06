package controller;

import java.awt.Point;

import View.UI;
import model.Cell;
import model.CellBoard;

public class CellController {

	/* 
	 * Logic of the program
	 */
	
	// Controller
	private Cell[][] savedBoard;

	// The habitat to search
	private CellBoard board;
	
	// UI instance
	private UI display;
	
	private int numRows;
	private int numCols;
	
	private static final int INACTIVE_CELL = 0;
	private static final int ACTIVE_CELL = 1;
	
	public CellController(int rows, int cols, UI display) {
		// Initializing logic state
		numRows = rows;
		numCols = cols;
		
		board = new CellBoard(rows, cols);
		
		this.display = display;
	}
	
	public void saveSimulation() {
		savedBoard = board.copyBoard();
	}
	
	public void loadSimulation() {
		board.setBoard(savedBoard);
	}
	
	// Create new maze helper
	public void newBoard() {
		board = new CellBoard(numRows, numCols);
		display.redraw();
	}
	
	// Search method
	public void startSimulation() {
		board = new CellBoard(numRows, numCols);
	}
	
	public void step() {
		board.update();
	}
	
	/*
	 * Does a step in the search regardless of pause status
	 */
	public void doOneStep(double elapsedTime){
		if(board != null) step();
		display.redraw();
	}

	public int getCellState(int row, int col) {
		if(board.getCell(row, col).hasBacteria()) {
			return ACTIVE_CELL;
		} else {
			return INACTIVE_CELL;
		}
	}
	

}
