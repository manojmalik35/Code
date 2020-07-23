package mar11;

public class crossword {

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

	public static void cw(char[][] board, String[] words, int cwi) {
		if (cwi == words.length) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++)
					System.out.print(board[i][j] + " ");
				System.out.println();
			}
			return;
		}

		if(debuggable)
			System.out.println(cwi+words[cwi]+" na");
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (canPlaceTheWordH(board, i, j, words[cwi])) {
					boolean[] reset = new boolean[words[cwi].length()];
					placeTheWordH(board, i, j, words[cwi], reset);
					if(debuggable)
						System.out.println(i+""+j+"h"+" ea");
					cw(board, words, cwi + 1);
					if(debuggable)
						System.out.println(i+""+j+"h"+" eb");
					unplaceTheWordH(board, i, j, words[cwi], reset);
				}  if (canPlaceTheWordV(board, i, j, words[cwi])) {
					boolean[] reset = new boolean[words[cwi].length()];
					placeTheWordV(board, i, j, words[cwi], reset);
					if(debuggable)
						System.out.println(i+""+j+"v"+" ea");
					cw(board, words, cwi + 1);
					if(debuggable)
						System.out.println(i+""+j+"v"+" eb");
					unplaceTheWordV(board, i, j, words[cwi], reset);
				}
			}
		}
		if(debuggable)
			System.out.println(cwi+words[cwi]+" nb");
	}

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
		cw(board, words, 0);

	}

}
