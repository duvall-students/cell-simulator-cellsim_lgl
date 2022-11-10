package model;


public class CellBoard {

	private Cell[][] cellBoard;
	
	public CellBoard(int row, int column) {
		cellBoard = new Cell[row][column];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				cellBoard[i][j] = new Cell(i,j);
			}
		}
	}
	
	//Randomizes the board
	public void randomizeBoard() {
		
	}
	
	//Returns the board with the same cells
	public Cell[][] getBoard(){
		return cellBoard.clone();
	}
	
	public void update() {
		//We need to make a copy b/c if we used the cellBoard instead of copyOfBoard
		//it would change while we were updating which would make it not work right
		//We need to not have the same cell objects
		Cell[][] copyOfBoard = copyBoard();
		
		for (Cell[] row : cellBoard) {
			for (Cell cell : row) {
				cell.update(copyOfBoard);
			}
		}
	}
	
	//Used to make a board with the same data but different objects
	public Cell[][] copyBoard(){
		Cell[][] copyOfBoard = new Cell[cellBoard.length][cellBoard[0].length];
		for (int row = 0; row < cellBoard.length; row++) {
			for (int col = 0; col < cellBoard[0].length; col++) {
				copyOfBoard[row][col] = cellBoard[row][col].copy();
			}
		}
		return copyOfBoard;
		
	}
	
}
