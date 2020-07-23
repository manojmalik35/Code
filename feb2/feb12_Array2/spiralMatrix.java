package feb12;

public class spiralMatrix {

	public static void main(String[] args) {
		int[][] mat = { { 11, 12, 13, 14 ,15},
					  { 21, 22, 23, 24 ,25},
					  { 31, 32, 33, 34 ,35}}; 
		int rmin = 0;
		int rmax = mat.length - 1;
		int cmin = 0;
		int cmax = mat[0].length - 1;
		int tn = mat.length * mat[0].length;
		while (tn > 0) {
			// Left Wall
			for (int r = rmin; r <= rmax&&tn>0; r++) {
				System.out.print(mat[r][cmin] + " ");
				tn--;
			}
			cmin++;

			// Bottom Wall
			for (int c = cmin; c <= cmax&&tn>0; c++) {
				System.out.print(mat[rmax][c] + " ");
				tn--;
			}
			rmax--;

			// Right Wall
			for (int r = rmax; r >= rmin&&tn>0; r--) {
				System.out.print(mat[r][cmax] + " ");
				tn--;
			}
			cmax--;

			// Top Wall
			for (int c = cmax; c >= cmin&&tn>0; c--) {
				System.out.print(mat[rmin][c] + " ");
				tn--;
			}
			rmin++;
		}
	}
}