package test;

import org.junit.jupiter.api.Test;

import model.Cell;

/**
 * Tests the Cell class
 * @author Lilly Purrington
 *
 */

public class CellTest {

	@Test
	public void copyTest() {
		Cell cell = new Cell(4,3,false);
		Cell cell2 = cell.copy();
		
		assert(cell != cell2);
		assert(cell.equals(cell2));
	}
}
