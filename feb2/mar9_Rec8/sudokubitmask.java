package mar9;

public class sudokubitmask {

	static int[] ra=new int[9];
	static int[] ca=new int[9];
	static int[][] sbm=new int[3][3];
	public static boolean isthenoValid(int i, int j, int n) {
		int b=1<<n;
		if(((ra[i]&b)==0) && 
				((ca[j]&b)==0) && 
				((sbm[i/3][j/3]&b)==0))
			return true;
		return false;
			
	}

	public static void sudokubm(int[][] board,int bno){
		if (bno == board.length * board.length) {
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++)
					System.out.print(board[i][j] + " ");
				System.out.println();
			}

			return;
		}
		
		int i=bno/board.length;
		int j=bno%board.length;
		if (board[i][j] == 0){
			for (int k = 1; k < 10; k++) {
				if (isthenoValid(i, j, k)) {
					ra[i] ^= (1<<k);
					ca[j] ^= (1<<k);
					sbm[i/3][j/3] ^= (1<<k);
					
					board[i][j] = k;
					sudokubm(board, bno+1);
					board[i][j] = 0;
					
					ra[i] ^= (1<<k);
					ca[j] ^= (1<<k);
					sbm[i/3][j/3] ^= (1<<k);
				}
			}
		}
		else
			sudokubm(board,bno+1);
		
	}
	public static void main(String[] args) {
		int[][] arr = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, { 0, 8, 7, 0, 0, 0, 0, 3, 1 },
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, { 0, 5, 0, 0, 9, 0, 6, 0, 0 },
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		for(int i=0;i<ra.length;i++){
			for(int j=0;j<ra.length;j++){
				if(arr[i][j]!=0){
					ra[i] ^= (1<<arr[i][j]);
					ca[j] ^= (1<<arr[i][j]);
					sbm[i/3][j/3] ^= (1<<arr[i][j]);
				}
			}
		}
		sudokubm(arr, 0);

	}

}
