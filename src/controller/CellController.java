package controller;

import View.UI;
import model.Cell;
import model.CellBoard;

public class CellController {
	
	// Model
	private CellBoard board;
	
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
		//display.redraw();
	}
	
	public void startSimulation() {
		while(runStatus) {
			step();
		}
	}
	
	public void playSimulation() {
		runStatus = true;
	}
	
	public void pauseSimulation() {
		runStatus = false;
	}
	
	public void step() {
		board.update();
		//display.redraw();
	}
	
	public void doOneStep() {
		step();
		//display.redraw();
	}
	
	public void saveSimulation() {
		savedBoard = board.copyBoard();
	}
	
	public void loadSimulation() {
		board.setBoard(savedBoard);
	}

}
