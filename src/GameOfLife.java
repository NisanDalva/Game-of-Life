import java.util.Random;

public class GameOfLife {
	private boolean[][] board, tempBoard;
	private int boardSize, maxNumBoards;
	private int stepsCount;
	
	
	public GameOfLife(int boardSize, int maxNumBoards) {
		this.boardSize = boardSize;
		this.maxNumBoards = maxNumBoards;
		this.stepsCount = 0;
		this.board = setRandomBoard();
		this.tempBoard = new boolean[boardSize][boardSize];
	}
	
	
	public void runGame() {
		System.out.println("Start board:");
		System.out.println(toString());
		copyBoard(board, tempBoard);
		
		//show all boards step by step
		for (int i = 0; i < this.maxNumBoards; i++) {
			if (setBoard(board)) {
				copyBoard(tempBoard, board);
				System.out.println("Step " + ++stepsCount + ":");
				System.out.print(toString());
			
			} else { // if there are no change
				System.out.println("there is no change after step " + stepsCount);
				break;
			}
		}
		System.out.println("Finished");
	}
	
	/**
	 * check how board may look after n steps<p>
	 * NOTE: no use of maximum number of board variable
	 * @param n = number of steps
	 */
	public void showResultImmediately(int n) {
		copyBoard(board, tempBoard);
		int counter = 0;
		
		for (int i = 0; i < n; i++)
			if (setBoard(board)) {
				counter++;
				copyBoard(tempBoard, board);
			} else
				break; // if there are no change
		
		if(counter < n)
			System.out.println("There are no change after step " + counter);
		
		System.out.println("Board after step " + counter + ":");
		System.out.println(toString());
	}
	
	
	private boolean setBoard(boolean[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				setCell(board, i, j);
			}
		}
		return !isEqualBoard(board, tempBoard); //true if board changed
}
	
	
	private void setCell(boolean[][] board, int i, int j) {
		// check for actually board and changing the tempBoard
		int numOfNeighbors = getNeighbors(board, i, j);
		if(board[i][j]) { // if alive
			if(numOfNeighbors < 2 || numOfNeighbors >= 4) {
				tempBoard[i][j] = false; // kill cell
			}
		}else{ // if dead
			if(numOfNeighbors == 3)
				tempBoard[i][j] = true; // become alive
		}
	}
	
	
	private int getNeighbors(boolean[][] board, int i, int j) {
		int numOfNeighbors = 0;
		// check all 8 neighbors of cell 
		for(int row = -1; row <= 1; row ++) {
			for(int col = -1; col <= 1; col++) {
				
				int checkRow = row + i;
				int checkCol = col + j;
				
				/* FIX IndexOutOfBoundsException: */
				
				// for top row
				if(checkRow < 0)
					checkRow += board.length;
				// for bottom row
				if(checkRow >= board.length)
					checkRow -= board.length;
				
				// for left column
				if(checkCol < 0)
					checkCol += board[i].length;
				// for right column
				if(checkCol >= board[i].length)
					checkCol -= board[i].length;
				
				// don't check correct (i,j) cell
				if(!(checkRow == i && checkCol == j) && board[checkRow][checkCol])
					numOfNeighbors++; // increases by 1 if neighbor is alive
			}
		}
		return numOfNeighbors;
	}
	
	
	private boolean isEqualBoard (boolean[][] board1, boolean[][] board2) {
		for(int i=0; i<board1.length; i++)
			for(int j=0; j<board1[i].length; j++)
				if(board1[i][j] != board2[i][j])
					return false; // at least one cell is not equal then return false
			
		return true;
	}

	private void copyBoard (boolean fromBoard[][], boolean toBoard[][]) {
		for (int i = 0; i < toBoard.length; i++) {
			for (int j = 0; j < toBoard[i].length; j++) {
				toBoard[i][j] = fromBoard[i][j];
			}
		}
	}
	
	/**
	 * fill the board randomly by true/false<p>
	 * defined as: true - alive cell, false - dead cell
	 * @param size of board
	 * @return the random board
	 */
	private boolean[][] setRandomBoard() {
		int size = this.boardSize;
		boolean[][] randomBoard = new boolean[size][size];
		Random rand = new Random();
		
		for(int i=0; i<randomBoard.length; i++)
			for(int j=0; j<randomBoard[i].length; j++)
				randomBoard[i][j] = rand.nextBoolean();
		
		return randomBoard;
	}
	
	@Override
	public String toString () {
		StringBuilder str = new StringBuilder("");
		// 1 - alive cell, 0 - dead cell
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if(board[i][j])
					str.append("# ");
				else
					str.append("- ");
			}
			str.append("\n");
		}
		return str.toString();
	}

}
