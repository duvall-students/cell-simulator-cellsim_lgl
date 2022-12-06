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
	
	public CellController(int rows, int cols, UI display) {
		// Initializing logic state
		numRows = rows;
		numCols = cols;
		board = new CellBoard(numRows, numCols);
		
		this.display = display;
	}
	
	public void saveSimulation() {
		savedBoard = board.copyBoard();
	}
	
	public void loadSimulation() {
		board.setBoard(savedBoard);
	}
	
	// Create new maze helper
	public void newSimulation() {
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

	public Cell getCellState(int row, int col) {
		return board.getCell(row, col);
	}
	

}
