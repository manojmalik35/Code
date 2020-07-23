package feb22;

public class diagonalTraversal4 {

	public static void main(String[] args) {
		int[][] mat = { { 11, 12, 13, 14 }, 
					    { 15, 16, 17, 18 }, 
					    { 19, 20, 21, 22 }, 
					    { 23, 24, 25, 26 } };
		int rmax = mat.length;
		int cmax = mat[0].length;

		for (int gap = -rmax + 1; gap < rmax; gap++) {
			int i=0;
			int j=0;
			j=gap<0?0:i+gap;
			i=gap<0?j-gap:0;
//			if (gap < 0) {
//				j = 0;
//				i = j -gap;
//			}else{
//				i=0;
//				j=i+gap;
//			}
				int count = 1;
				while (count <= rmax - Math.abs(gap)) {
					System.out.print(mat[i][j] + " ");
					i++;
					j++;
					count++;
				}
				System.out.println();
		}
	}

}
