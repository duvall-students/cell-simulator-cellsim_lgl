package test;

import org.junit.jupiter.api.Test;

import model.CellBoard;

public class CellBoardTest {

	//This test checks that the getBoard function returns the same cell objects
	//while copy board is different objects 
	@Test
	public void testCopy() {
		CellBoard cellBoard = new CellBoard(3,3);
		assert(cellBoard.getBoard()[0][0]==cellBoard.getBoard()[0][0]);
		assert(cellBoard.copyBoard()[0][0]!=cellBoard.getBoard()[0][0]);
	}
}
