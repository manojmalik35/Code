package mar9;

public class sudoku {

	public static boolean isthenoValid(int[][] board, int i, int j, int n) {
		// column
		for (int ii = 0; ii < board.length; ii++) {
			if (board[ii][j] == n)
				return false;
		}
		// row
		for (int jj = 0; jj < board.length; jj++) {
			if (board[i][jj] == n)
				return false;
		}
		//submatrix
		for(int ii=i/3*3;ii<i/3*3+3;ii++){
			for(int jj=j/3*3;jj<j/3*3+3;jj++){
				if(board[ii][jj]==n)
					return false;
			}
		}
		return true;
	}

	public static void sudoku(int[][] board, int bno) {
		if (bno == board.length * board.length) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++)
					System.out.print(board[i][j] + " ");
				System.out.println();
			}

			return;
		}
			int i = bno / board.length;
			int j = bno % board.length;
			if (board[i][j] == 0){
				for (int m = 1; m < 10; m++) {
					if (isthenoValid(board, i, j, m)) {
						board[i][j] = m;
						sudoku(board, bno+1);
						board[i][j] = 0;
					}
				}
			}
			else
				sudoku(board,bno+1);
	}

	public static void main(String[] args) {
		int[][] arr = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		sudoku(arr, 0);

	}

}
