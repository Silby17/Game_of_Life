/***************************************
 * Yossi Silberhaft - 210028924
 * Binyamin Greenberg - 200220671
 **************************************/
package logic;

import java.util.Random;

public class Board {
	int[][] board;
	
	public Board(int width, int hight){
		board = new int[hight][width];
	}
	
	public int getBoardCellValue(Cell cell){
		return board[cell.getY()][cell.getX()];
	}
	
	public int getBoardCellValue(int y, int x){
		return board[y][x];
	}
	
	public void setBoardCell(int i, Cell cell){
		board[cell.getY()][cell.getX()] = i;
	}
	
	public void switchBoardCell(Cell cell){
		board[cell.getY()][cell.getX()] ^= 1;
	}
	
	public void switchBoardTwoXTwo(TwoXTwo twoXTwo){
		for (Cell c: twoXTwo.getListOfCells()){
			this.switchBoardCell(c);
		}
	}
	
	public int getBoardTwoXTwoValue(TwoXTwo twoXTwo){
		int sum = 0;
		for (Cell c: twoXTwo.getListOfCells()){
			sum += this.getBoardCellValue(c);
		}
		return sum;
	}

	public void rotateTwoXTwo(TwoXTwo twoXTwo) {
		Cell[] tempCells = twoXTwo.getListOfCells();
		this.switchTwoCells(tempCells[0], tempCells[2]);
		this.switchTwoCells(tempCells[1], tempCells[3]);
		
	}
	private void switchTwoCells(Cell a, Cell b){
		int temp = this.getBoardCellValue(a);
		setBoardCell(getBoardCellValue(b), a);
		setBoardCell(temp,b);
	}
	
	public void randomizeBoard(){
		Random generator = new Random();

		for (int i = 0 ; i < board[0].length ; i++){
			for (int j = 0 ; j < board.length ; j++){
				board[i][j] = generator.nextInt( 2 );
			}
		}
	}
	
	public void printBoard(){
		for (int i = 0 ; i < board[0].length ; i++){
			for (int j = 0 ; j < board.length ; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
	}
}