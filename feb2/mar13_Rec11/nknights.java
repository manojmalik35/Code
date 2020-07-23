package mar13;

public class nknights {

	static int counter=0;
	public static boolean isKnightSafe(int[][] board, int i, int j) {
		int n=board.length;
		if(i-2 >=0 && j-1 >=0 && board[i-2][j-1]!=0)
			return false;
		if(i-1 >=0 && j-2 >=0 && board[i-1][j-2]!=0)
			return false;
		if(i+1 <n && j-2 >=0 && board[i+1][j-2]!=0)
			return false;
		if(i+2 <n && j-1 >=0 && board[i+2][j-1]!=0)
			return false;
		if(i+2 <n && j+1 <n && board[i+2][j+1]!=0)
			return false;
		if(i+1 <n && j+2 <n && board[i+1][j+2]!=0)
			return false;
		if(i-1 >=0 && j+2 <n && board[i-1][j+2]!=0)
			return false;
		if(i-2 >=0 && j+1 <n && board[i-2][j+1]!=0)
			return false;
		return true;
	}
	public static void nkc_b(int[][] board, int kpsf, int bno) { //nknights combination using boxes on levels
		if (bno == board.length * board.length) {
			if (kpsf == board.length) {
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
		nkc_b(board, kpsf, bno + 1);// if the box says no to the queen
		int i = bno / board.length;
		int j = bno % board.length;
		if (board[i][j] == 0 && isKnightSafe(board, i, j)) {
			board[i][j] = 1;
			nkc_b(board, kpsf + 1, bno + 1);// if the box says yes to the queen
			board[i][j] = 0;
		}
	}
	public static void main(String[] args) {
		int[][] board=new int[3][3];
		nkc_b(board, 0, 0);

	}

}
