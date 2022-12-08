package controller;

import java.awt.Point;

import View.UI;
import model.Cell;
import model.CellBoard;

public class CellController {

	/* 
	 * Logic of the program
	 */

	// Model
	private CellBoard board;
	
	// View
	private UI display;

	// Controller
	private Cell[][] savedBoard;
	
	private int numRows;
	private int numCols;
	
	private static final int INACTIVE_CELL = 0;
	private static final int ACTIVE_CELL = 1;
	
	public CellController(int rows, int cols, UI display) {
		// Initializing logic state
		numRows = rows;
		numCols = cols;
		
		board = new CellBoard(numRows, numCols);
		
		this.display = display;
	}
	
	public void saveBoard() {
		savedBoard = board.copyBoard();
	}
	
	public void loadBoard() {
		if(savedBoard != null) {
			board.setBoard(savedBoard);
			display.redraw();	
			savedBoard = board.copyBoard();
		}
	}
	
	// Creates a new board
	public void newBoard() {
		board = new CellBoard(numRows, numCols);
		display.redraw();
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

	// Returns a 1 (Active cell) or 0 (Inactive cell) for the view
	public int getCellState(int row, int col) {
		if(board.getCell(row, col).hasBacteria()) {
			return ACTIVE_CELL;
		} else {
			return INACTIVE_CELL;
		}
	}
	

}
