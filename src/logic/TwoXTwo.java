/***************************************
 * Yossi Silberhaft - 210028924
 * Binyamin Greenberg - 200220671
 **************************************/
package logic;


public class TwoXTwo {
	// array holding the cells of a TwoXTwo
	Cell[] cells = new Cell[4];
	
	public TwoXTwo(Cell upLeft, Cell upRight, Cell downLeft, Cell downRight) {
		cells[0] = upLeft;
		cells[1] = upRight;
		cells[2] = downRight;
		cells[3] = downLeft;
	}
	
	public void updateTwoXTwo(Board board){
		int numOfLiveCells = board.getBoardTwoXTwoValue(this);
		//if 2 alive in block don't change
		if (numOfLiveCells == 2){
			return;
		} 
		//else if 3 switch and rotate
		else if (numOfLiveCells == 3){
			board.switchBoardTwoXTwo(this);
			board.rotateTwoXTwo(this);
		} 
		//else if 4,1 or 0 then switch the cell 
		else { 
			board.switchBoardTwoXTwo(this);
		}
	}

	public Cell[] getListOfCells(){
		return cells;
	}
}