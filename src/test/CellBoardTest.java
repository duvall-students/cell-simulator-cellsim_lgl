package test;

import org.junit.jupiter.api.Test;

import model.Cell;
import model.CellBoard;

/**
 * Tests the cellBoard class 
 * @author Lilly Purrington
 *
 */

public class CellBoardTest {

	//This test checks that the getBoard function returns the same cell objects
	//while copy board is different objects 
	@Test
	public void testCopy() {
		CellBoard cellBoard = new CellBoard(3,3);
		assert(cellBoard.getBoard()[0][0]==cellBoard.getBoard()[0][0]);
		assert(cellBoard.copyBoard()[0][0]!=cellBoard.getBoard()[0][0]);
	}
	
	@Test
	//This makes a stable system
	public void stableSystemTest() {
		CellBoard cellBoard = new CellBoard(4,4);
		cellBoard.getBoard()[1][1].setBacteria(true);
		cellBoard.getBoard()[1][2].setBacteria(true);
		cellBoard.getBoard()[2][1].setBacteria(true);
		cellBoard.getBoard()[2][2].setBacteria(true);
		
		for (int i = 0; i < 100; i++) {
			cellBoard.update();
			assert(cellBoard.getBoard()[1][1].hasBacteria());
			assert(cellBoard.getBoard()[1][2].hasBacteria());
			assert(cellBoard.getBoard()[2][1].hasBacteria());
			assert(cellBoard.getBoard()[2][2].hasBacteria());
		}
	}
	
	@Test
	//This makes a dynamically stable system
	public void dynamicallyStableSystemTest() {
		CellBoard cellBoard = new CellBoard(5,5);
		cellBoard.getBoard()[1][2].setBacteria(true);
		cellBoard.getBoard()[2][2].setBacteria(true);
		cellBoard.getBoard()[3][2].setBacteria(true);
		cellBoard.getBoard()[1][1].setBacteria(false);
		cellBoard.getBoard()[1][3].setBacteria(false);
		cellBoard.getBoard()[2][1].setBacteria(false);
		cellBoard.getBoard()[3][1].setBacteria(false);
		cellBoard.getBoard()[2][3].setBacteria(false);
		cellBoard.getBoard()[3][3].setBacteria(false);

		
		for (int i = 0; i < 100; i++) {

			cellBoard.update();
			
			if (i % 2 == 0) {
				assert(cellBoard.getBoard()[2][1].hasBacteria());
				assert(cellBoard.getBoard()[2][2].hasBacteria());
				assert(cellBoard.getBoard()[2][3].hasBacteria());
			}else {
				assert(cellBoard.getBoard()[1][2].hasBacteria());
				assert(cellBoard.getBoard()[2][2].hasBacteria());
				assert(cellBoard.getBoard()[3][2].hasBacteria());
				
			}
			
		}

	}
	
	@Test
	public void randomizeTest() {
		
		boolean different = false;
		for (int i = 0; i < 10 && !different; i++) {
			CellBoard cellBoard = new CellBoard(10,10);
			Cell[][] firstBoard = cellBoard.copyBoard();
			cellBoard.randomizeBoard();
			Cell[][] secondBoard = cellBoard.getBoard();
			
			for (int x = 1; x < firstBoard.length-1 && !different; x++) {
				for (int y = 1; y < firstBoard[0].length-1 && !different; y++) {
					different = firstBoard[x][y].hasBacteria() != secondBoard[x][y].hasBacteria();
				}
			}
		}
		assert(different);
		
	}

}
