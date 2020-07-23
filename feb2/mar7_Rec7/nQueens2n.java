package mar7;

public class nQueens2n{

	public static boolean isQueenSafe(boolean[][] board, int i, int j) {
		// In the case of box choice, only north, northwest, northeast, and west directions are enough because
		// we are placing queens box after box so there will be no queen in east and south directions
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

	public static void nqueens2np(boolean[][] board, int qpsf, int cb, String asf) {
		if (cb == board.length*board.length) {
			if (qpsf == board.length){
				System.out.println(asf);
			}
			return;
		}
		nqueens2np(board, qpsf, cb+1, asf);// if the box says no to the queen
		int i=cb/board.length;
		int j=cb%board.length;
		if(board[i][j]==false&&isQueenSafe(board, i, j)){
			board[i][j]=true;
			nqueens2np(board, qpsf+1, cb + 1, asf + i+j + "_");//if the box says yes to the queen
			board[i][j]=false;
		}
	}
	public static void main(String[] args) {
		boolean[][] board=new boolean[4][4];
		nqueens2np(board, 0, 0, "");

	}

}
