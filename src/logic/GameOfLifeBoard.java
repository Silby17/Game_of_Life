/***************************************
 * Yossi Silberhaft - 210028924
 * Binyamin Greenberg - 200220671
 **************************************/
package logic;
import java.util.ArrayList;


public class GameOfLifeBoard {
	public final static String WRAPAROUND = "wrap";
	private final int columns;
	private final int rows;
	private Board board2;
	private ArrayList<TwoXTwo> evenTwoXTwoWraped;
	private ArrayList<TwoXTwo> evenTwoXTwo;
	private ArrayList<TwoXTwo> oddTwoXTwo;
	public int cycleCounter = 1;


	public GameOfLifeBoard(int width, int height) {	
		this.columns = width;
		this.rows = height;
		this.board2 = createEmptyBoard();
		oddTwoXTwo = createOddTwoXTwo(this.columns, this.rows);
		evenTwoXTwo = createEvenTwoXTwo(this.columns, this.rows);
		evenTwoXTwoWraped = createEvenTwoXTwoWraping(this.columns, this.rows);
	}

	public void resetBoard() {
		this.board2 = createEmptyBoard();
		cycleCounter = 1;
	}


	private Board createEmptyBoard() {
		return new Board(columns, rows);
	}

	public boolean getCellAt(int row, int column) {
		if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
			return false;
		}
		return this.board2.getBoardCellValue(new Cell(row, column)) == 1;
	}


	public void changeCellAt(int row, int column) {
		if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
			this.board2.switchBoardCell(new Cell(row, column));
		}
	}


	public boolean containsLiveCell() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				if (this.board2.getBoardCellValue(i, j) == 1) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Calculates the population for the next round.
	 */
	 public void nextRound(boolean wraped) {
		 System.out.println("counter "+ cycleCounter);

		 ArrayList<TwoXTwo> listOfTwoXTwo;
		 if (cycleCounter%2 == 0){
			 listOfTwoXTwo = wraped ? evenTwoXTwoWraped : evenTwoXTwo;
		 } else{
			 listOfTwoXTwo = oddTwoXTwo;
		 }
		 for (TwoXTwo t: listOfTwoXTwo){
			 t.updateTwoXTwo(this.board2);
		 }
		 cycleCounter++;
	 }

	public void Randomize()
	{
		this.board2.randomizeBoard();
	}


	private ArrayList<TwoXTwo> createOddTwoXTwo(int width, int hight){
		ArrayList<TwoXTwo> listOfTwoXTwo = new ArrayList<TwoXTwo>();
		for (int i = 0 ; i < width ; i+=2){
			for (int j = 0 ; j < hight ; j+=2){
				Cell upLeft = new Cell(i,j);
				Cell upRight = new Cell(i+1, j);
				Cell downRight = new Cell(i+1, j+1);
				Cell downLeft = new Cell(i, j+1);
				listOfTwoXTwo.add(new TwoXTwo(upLeft, upRight, downLeft, downRight));
			}
		}
		return listOfTwoXTwo;
	}


	//the only difference between this method and the 
	//one above is the initial and end value in loop
	//can be refactured
	private ArrayList<TwoXTwo> createEvenTwoXTwo(int width, int hight){
		ArrayList<TwoXTwo> listOfTwoXTwo = new ArrayList<TwoXTwo>();
		for (int i = 1 ; i < width -1 ; i+=2){
			for (int j = 1 ; j < hight -1; j+=2){
				Cell upLeft = new Cell(i,j);
				Cell upRight = new Cell(i+1, j);
				Cell downRight = new Cell(i+1, j+1);
				Cell downLeft = new Cell(i, j+1);
				listOfTwoXTwo.add(new TwoXTwo(upLeft, upRight, downLeft, downRight));
			}
		}
		return listOfTwoXTwo;
	}


	private ArrayList<TwoXTwo> createEvenTwoXTwoWraping(int width, int hight){
		ArrayList<TwoXTwo> listOfTwoXTwo = new ArrayList<TwoXTwo>();

		//add all the twoXtwo in the middle of the board
		listOfTwoXTwo.addAll(createEvenTwoXTwo(width, hight));

		//add the corner wrap around block
		Cell upLeft = new Cell(width-1, hight-1);
		Cell upRight = new Cell(0,hight-1);
		Cell downRight = new Cell(0, 0);
		Cell downLeft = new Cell(width-1, 0);
		listOfTwoXTwo.add(new TwoXTwo(upLeft, upRight, downLeft, downRight));

		//add the bottom top wrap blocks
		for (int i = 1 ; i < width -1 ; i+=2){
			upLeft = new Cell(i,hight-1);
			upRight = new Cell(i+1, hight-1);
			downRight = new Cell(i+1, 0);
			downLeft = new Cell(i, 0);
			listOfTwoXTwo.add(new TwoXTwo(upLeft, upRight, downLeft, downRight));
		}
		//add the left right wrap blocks
		for(int i = 1 ; i < hight - 1 ; i +=2){
			upLeft = new Cell(0,i);
			upRight = new Cell(width-1, i);
			downRight = new Cell(width-1, i+1);
			downLeft = new Cell(0, i+1);
			listOfTwoXTwo.add(new TwoXTwo(upLeft, upRight, downLeft, downRight));
		}
		return listOfTwoXTwo;
	}
}