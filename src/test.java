/**
 *    GAME OF LIFE     
 *    
 * according to Game of Life rules:
 * for alive cells:
 * Each cell with one or no neighbors dies.
 * Each cell with four or more neighbors dies.
 * Each cell with two or three neighbors survives.
 * 
 * for dead cells:
 * Each cell with three neighbors becomes alive.
 * 
*/

import java.util.Scanner;

public class test {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int boardSize, maxNumBoards;
		
		System.out.println("size of board:");
		boardSize = in.nextInt();
		
		System.out.println("max number of boards:");
		maxNumBoards = in.nextInt();
		in.close(); // stop using Scanner
		
		
		GameOfLife game = new GameOfLife(boardSize, maxNumBoards);
		game.runGame();
		//game.showResultImmediately(5);
		
	}
}
