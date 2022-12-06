package test;

import org.junit.jupiter.api.Test;

import View.UI;
import controller.CellController;

/*
 * @Author Luke Freudenthal
 * Attempt at creating controller test
 */

public class CellControllerTest {
	
	

	@Test
	public void boardSaveTest() {
		UI testDisplay = new UI();
		CellController testController = new CellController(5,5,testDisplay);
		testController.newBoard();
		testController.step();
		assert(testController.getSavedBoard() == null);
		testController.saveBoard();
		assert(testController.getSavedBoard() != null);
		
	}

}
