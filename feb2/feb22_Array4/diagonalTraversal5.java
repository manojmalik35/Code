package feb22;

public class diagonalTraversal5 {

	public static void main(String[] args) {
		int[][] mat = { { 11, 12, 13, 14 }, 
						{ 15, 16, 17, 18 },
						{ 19, 20, 21, 22 },
						{ 23, 24, 25, 26 } };
		int rmax = mat.length;
		int t=1;
		for (int gap = -rmax + 1; gap < rmax; gap++) {
			int i=0;
			int j=0;
			int total=rmax-Math.abs(gap);
			j=gap<0?0:i+gap;
			i=gap<0?j-gap:0;
			if(t<0){
				i=i+total-1;
				j=j+total-1;
			}
				int count = 1;
					while (count <= total) {
						System.out.print(mat[i][j] + " ");
						i+=t;
						j+=t;
						count++;
						}
					t=-t;
				System.out.println();
		}
	}

}
