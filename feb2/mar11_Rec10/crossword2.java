package mar11;

public class crossword2 {
	
	static boolean debuggable=false;
	public static boolean canPlaceTheWordH(char[][] board, int i, int j, String word) {
		if (j + word.length() > board.length)
			return false;
		if (j > 0 && board[i][j - 1] != '+')
			return false;
		if (j + word.length() < board.length && board[i][j + word.length()] != '+')
			return false;
		for (int c = 0; c < word.length(); c++) {
			if (board[i][j + c] != '-' && board[i][j + c] != word.charAt(c))
				return false;
		}
		return true;
	}

	public static boolean canPlaceTheWordV(char[][] board, int i, int j, String word) {
		if (i + word.length() > board.length)
			return false;
		if (i > 0 && board[i - 1][j] != '+')
			return false;
		if (i + word.length() < board.length && board[i + word.length()][j] != '+')
			return false;
		for (int r = 0; r < word.length(); r++) {
			if (board[i + r][j] != '-' && board[i + r][j] != word.charAt(r))
				return false;
		}
		return true;
	}

	public static void placeTheWordH(char[][] board, int i, int j, String word, boolean[] reset) {
		for (int c = 0; c < word.length(); c++) {
			if (board[i][j + c] == '-') {
				reset[c] = true;
				board[i][j + c] = word.charAt(c);
			}
		}
	}

	public static void placeTheWordV(char[][] board, int i, int j, String word, boolean[] reset) {
		for (int r = 0; r < word.length(); r++) {
			if (board[i + r][j] == '-') {
				reset[r] = true;
				board[i + r][j] = word.charAt(r);
			}
		}
	}

	public static void unplaceTheWordH(char[][] board, int i, int j, String word, boolean[] reset) {
		for (int c = 0; c < word.length(); c++) {
			if (reset[c]) {
				board[i][j + c] = '-';
				reset[c] = false;
			}
		}
	}

	public static void unplaceTheWordV(char[][] board, int i, int j, String word, boolean[] reset) {
		for (int r = 0; r < word.length(); r++) {
			if (reset[r]) {
				board[i + r][j] = '-';
				reset[r] = false;
			}
		}
	}

	public static void cw2(char[][] board,String[] words,int bno,boolean[] waused){
		if(bno==board.length*board.length){
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++)
					System.out.print(board[i][j] + " ");
				System.out.println();
			}
			return;
		}
		
		int i=bno/board.length;
		int j=bno%board.length;
		int cc=0;
		if(board[i][j]=='+')
			cw2(board, words, bno+1, waused); //no action call
		else{
			for(int w=0;w<words.length;w++){
				if(canPlaceTheWordH(board, i, j, words[w])){
					boolean[] reset=new boolean[words[w].length()];
					placeTheWordH(board, i, j, words[w], reset);
					waused[w]=true;
					cc++;
					cw2(board, words, bno+1, waused);
					waused[w]=false;
					unplaceTheWordH(board, i, j, words[w], reset);
				}
				if(canPlaceTheWordV(board, i, j, words[w])){
					boolean[] reset=new boolean[words[w].length()];
					placeTheWordV(board, i, j, words[w], reset);
					waused[w]=true;
					cc++;
					cw2(board, words, bno+1, waused);
					waused[w]=false;
					unplaceTheWordV(board, i, j, words[w], reset);
				}
			}
			
			if(cc==0 && board[i][j]!='-')
				cw2(board, words, bno+1, waused); //no action call
			
		}
	}
	/*Logic
	 * when there is + then we have no choice other than no action call
	 * when there is - then we have no choice other than take action and try to place a word 
	 * because we cannot leave a - because it will not be filled later by others
	 * when there is a character then we have two choices - either make a no action call or try to place a word
	 * */
	public static void main(String[] args) {
		char[][] board = { { '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
				{ '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
				{ '+', '-', '+', '+', '+', '+', '+', '+', '+', '+' },
				{ '+', '-', '-', '-', '-', '-', '+', '+', '+', '+' },
				{ '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
				{ '+', '-', '+', '+', '+', '-', '+', '+', '+', '+' },
				{ '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
				{ '+', '+', '-', '-', '-', '-', '-', '-', '+', '+' },
				{ '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' },
				{ '+', '+', '+', '+', '+', '-', '+', '+', '+', '+' } };
		String[] words = { "LONDON", "DELHI", "ICELAND", "ANKARA" };
		boolean[] w=new boolean[words.length];
		cw2(board, words, 0,w);

	}

}
