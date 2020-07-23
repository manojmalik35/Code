package feb12;

public class printWaveMatrix {

	public static void main(String[] args) {
		int[][] mat={{11,12,13,14},
					 {21,22,23,24},
					 {31,32,33,34},
					 {41,42,43,44}};
		int row=mat.length;
		int col=mat[0].length;
		for(int c=0;c<col;c++){
			if(c%2==0){
			for(int r=0;r<row;r++)
				System.out.print(mat[r][c]+" ");
			}else {
				for(int r=row-1;r>=0;r--)
					System.out.print(mat[r][c]+" ");
			}
			System.out.println();
		}
	}

}
