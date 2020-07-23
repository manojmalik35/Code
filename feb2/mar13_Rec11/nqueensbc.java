package mar13;

public class nqueensbc {

	static int counter = 0;
	static boolean debuggable=false;
	public static boolean isQueenSafe(int[][] board, int i, int j) {
		// n
		for (int ii = i - 1; ii >= 0; ii--) {
			if (board[ii][j] != 0)
				return false;
		}
		// s
		for (int ii = i + 1; ii < board.length; ii++) {
			if (board[ii][j] != 0)
				return false;
		}
		// e
		for (int jj = j + 1; jj < board.length; jj++) {
			if (board[i][jj] != 0)
				return false;
		}
		// w
		for (int jj = j - 1; jj >= 0; jj--) {
			if (board[i][jj] != 0)
				return false;
		}
		// ne
		for (int ii = i - 1, jj = j + 1; ii >= 0 && jj < board.length; ii--, jj++) {
			if (board[ii][jj] != 0)
				return false;
		}
		// nw
		for (int ii = i - 1, jj = j - 1; ii >= 0 && jj >= 0; ii--, jj--) {
			if (board[ii][jj] != 0)
				return false;
		}
		// se
		for (int ii = i + 1, jj = j + 1; ii < board.length && jj < board.length; ii++, jj++) {
			if (board[ii][jj] != 0)
				return false;
		}
		// sw
		for (int ii = i + 1, jj = j - 1; ii < board.length && jj >= 0; ii++, jj--) {
			if (board[ii][jj] != 0)
				return false;
		}
		return true;
	}

	public static void nqc_b(int[][] board, int qpsf, int bno) { //nqueens combination using boxes on levels
		if (bno == board.length * board.length) {
			if (qpsf == board.length) {
				counter++;
				System.out.println("***********" + counter + "***********");
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board.length; j++) {
						System.out.print(board[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("***********" + counter + "***********");
			}
			return;
		}
		nqc_b(board, qpsf, bno + 1);// if the box says no to the queen
		int i = bno / board.length;
		int j = bno % board.length;
		if (board[i][j] == 0 && isQueenSafe(board, i, j)) {
			board[i][j] = 1;
			nqc_b(board, qpsf + 1, bno + 1);// if the box says yes to the queen
			board[i][j] = 0;
		}
	}

	public static void nqp_b(int[][] board, boolean[] qpsf, int bno) { //nqueens permutation using boxes on levels
		if (bno == board.length * board.length) {
			int qc = 0;
			for (int i = 0; i < qpsf.length; i++) {
				if (qpsf[i] == true)
					qc++;
			}
			if (qc == board.length) {
				counter++;
				System.out.println("***********" + counter + "***********");
				for (int i = 0; i < board.length; i++) {
					for (int j = 0; j < board.length; j++) {
						System.out.print(board[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println("***********" + counter + "***********");
			}
			return;
		}
		if(debuggable)
			System.out.println(bno+"nc");
		nqp_b(board, qpsf, bno + 1);// if the box says no to the queen
		if(debuggable)
			System.out.println(bno+"nc");
		int i = bno / board.length;
		int j = bno % board.length;
		if (board[i][j] == 0 && isQueenSafe(board, i, j)) {
			if(debuggable)
				System.out.println(i+""+j+"node a");
			for (int m = 0; m < qpsf.length; m++) {
				qpsf[m] = true;
				board[i][j] = m+1;
				if(debuggable)
					System.out.println(i+""+j+" "+m+"yc");
				nqp_b(board, qpsf, bno + 1);// if the box says yes to the queen m
				if(debuggable)
					System.out.println(i+""+j+" "+m+"yc");
				board[i][j] = 0;
				qpsf[m] = false;
			}
			if(debuggable)
				System.out.println(i+""+j+"node b");
		}
	}
	public static void nqp_q(int[][] board,int qpsf){ //nqueens permutation using queens on levels
		if(qpsf==board.length+1){
			counter++;
			System.out.println("***********" + counter + "***********");
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board.length;j++)
					System.out.print(board[i][j]+" ");
				System.out.println();
			}
			System.out.println("***********" + counter + "***********");
		}
		
		for(int b=0;b<board.length*board.length;b++){
			int i=b/board.length;
			int j=b%board.length;
			if(board[i][j]==0 && isQueenSafe(board, i, j)){
				board[i][j]=qpsf;
				nqp_q(board, qpsf+1);
				board[i][j]=0;
			}
		}
	}

	public static void nqc_q(int[][] board,int qpsf,int lqwpiwb){ //nqueens combination using queens on levels
		if(qpsf==board.length+1){
			counter++;
			System.out.println("***********" + counter + "***********");
			for(int i=0;i<board.length;i++){
				for(int j=0;j<board.length;j++)
					System.out.print(board[i][j]+" ");
				System.out.println();
			}
			System.out.println("***********" + counter + "***********");
		}
		
		for(int b=lqwpiwb+1;b<board.length*board.length;b++){
			int i=b/board.length;
			int j=b%board.length;
			if(board[i][j]==0 && isQueenSafe(board, i, j)){
				board[i][j]=qpsf;
				nqc_q(board, qpsf+1,b);
				board[i][j]=0;
			}
		}
	}

	public static void main(String[] args) {
		int[][] board = new int[4][4];
		boolean[] qpsf = new boolean[board.length];
//		 nqc_b(board,0, 0);
		nqp_b(board, qpsf, 0);
//		nqp_q(board, 1);
//		nqc_q(board, 1,-1);

	}

}
