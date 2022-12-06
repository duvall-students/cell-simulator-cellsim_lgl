package controller;

import java.awt.Point;

import View.UI;
import model.Cell;
import model.CellBoard;

/* 
 * @Author Luke Freudenthal
 * Logic of the program
 */

public class CellController {

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
	
	// Saves the current board and stores it in Cell[][]
	public void saveBoard() {
		savedBoard = board.copyBoard();
	}
	
	// If a savedBoard exists, it will load it into the board
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
	
	// Step methods for calling the board update
	public void step() {
		board.update();
	}
	
	// Does a step for the view 
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
	
	// Getter for testing saveBoard
	public Cell[][] getSavedBoard() {
		return savedBoard;
	}

}
