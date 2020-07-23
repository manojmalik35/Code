package mar7;

public class nqueens {

	static int counter = 0;

	public static boolean isQueenSafe(boolean[][] board, int i, int j) {
			//n
			for(int ii=i-1;ii>=0;ii--){
				if(board[ii][j])
					return false;
			}
			//s
			for(int ii=i+1;ii<board.length;ii++){
				if(board[ii][j])
					return false;
			}
			//e
			for(int jj=j+1;jj<board.length;jj++){
				if(board[i][jj])
					return false;
			}
			//w
			for(int jj=j-1;jj>=0;jj--){
				if(board[i][jj])
					return false;
			}
			//ne
			for(int ii=i-1,jj=j+1;ii>=0&&jj<board.length;ii--,jj++){
				if(board[ii][jj])
					return false;
			}
			//nw
			for(int ii=i-1,jj=j-1;ii>=0&&jj>=0;ii--,jj--){
				if(board[ii][jj])
					return false;
			}
			//se
			for(int ii=i+1,jj=j+1;ii<board.length&&jj<board.length;ii++,jj++){
				if(board[ii][jj])
					return false;
			}
			//sw
			for(int ii=i+1,jj=j-1;ii<board.length&&jj>=0;ii++,jj--){
				if(board[ii][jj])
					return false;
			}
			return true;
	}

	public static boolean isBoardSafe(boolean[][] board) {
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board.length;j++){
				if(board[i][j]){
					if(isQueenSafe(board, i, j)==false)
						return false;
				}
			}
		}
		return true;
	}

	public static void nqueens(boolean[][] board, int qpsf, int lqwpiwb, String asf) {//(reactive)
		// lqwpiwb=last queen was placed in which row
		// lqwpiwb=last queen was placed in which column
		if (qpsf == board.length) {
			if (isBoardSafe(board) == true) {
				counter++;
				System.out.println(counter + " " + asf);
			}
			return;
		}

		for (int b = lqwpiwb + 1; b < board.length * board.length; b++) {
			int i = b / board.length;
			int j = b % board.length;
			if (board[i][j] == false) {
				board[i][j] = true;
				nqueens(board, qpsf + 1, b, asf + i + j + "_");
				board[i][j] = false;
			}
		}

		// for(int r=lqwpiwr;r<board.length;r++){
		// for(int c=(r==lqwpiwr?lqwpiwc+1:0);c<board[0].length;c++){
		// if(board[r][c]==false){
		// board[r][c]=true;
		// nqueens(board, qpsf+1,r,c, asf+r+""+c+"_");
		// board[r][c]=false;
		// }
		// }
		// }

	}

	public static void nqueensp(boolean[][] board, int qpsf, int lqwpiwb, String asf) {//p=proactive
		if (qpsf == board.length) {
				counter++;
				System.out.println(counter + " " + asf);
			return;
		}

		for (int b = 0; b < board.length * board.length; b++) {
			int i = b / board.length;
			int j = b % board.length;
			if (board[i][j] == false&& isQueenSafe(board, i, j)) {
				board[i][j] = true;
				nqueensp(board, qpsf + 1, b, asf + i + j + "_");
				board[i][j] = false;
			}
		}
	}
	public static void main(String[] args) {
		boolean[][] board = new boolean[4][4];
//		nqueens(board, 0, -1, "");
		nqueensp(board, 0,0, "");

	}

}
