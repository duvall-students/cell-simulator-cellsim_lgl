package controller;

import View.UI;
import model.Cell;
import model.CellBoard;

public class CellController {
	
	// Model
	private CellBoard board;
	private Cell cell;
	
	private int rows;
	private int cols;
	
	// View
	private UI display;
	
	// Controller
	private Cell[][] savedBoard;
	private boolean runStatus;

	public CellController(int rows, int cols, UI display) {
		board = new CellBoard(rows, cols);
		this.display = display;
	}
	
	public void newBoard() {
		board.randomizeBoard();
//		display.redraw();
	}
	
	public void startSimulation() {
		board.update();
	}
	
	public void playSimulation() {
		runStatus = true;
	}
	
	public void pauseSimulation() {
		runStatus = false;
	}
	
	public void step() {
		
	}
	
	public void doOneStep() {
		
	}
	
	public void saveSimulation() {
		savedBoard = board.copyBoard();
	}
	
	public void loadSimulation() {
		board.setBoard(savedBoard);
	}

}
