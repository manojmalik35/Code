package feb22;

public class rotateRectangleby90 {

	public static void main(String[] args) {
		int[][] mat = { { 11, 12, 13, 14 ,15},
				  		{ 21, 22, 23, 24 ,25},
				  		{ 31, 32, 33, 34 ,35} };
		int rmax=mat.length;
		int cmax=mat[0].length;
		int [][] mat2=new int[cmax][rmax];
		int rmax2=mat2.length;
		int cmax2=mat2[0].length;

		for(int r=0;r<rmax;r++){
			for(int c=0;c<cmax;c++){
				mat2[c][r]=mat[r][c];
			}
		}
		
		//reversing the rows
				for(int r=0;r<rmax2;r++){
					int left=0;
					int right=cmax2-1;
					while(left<=right){
						int temp=mat2[r][left];
						mat2[r][left]=mat2[r][right];
						mat2[r][right]=temp;
						left++;
						right--;
					}
				}

		for(int i=0;i<cmax;i++){
			for(int j=0;j<rmax;j++)
				System.out.print(mat2[i][j]+" ");
			System.out.println();
		}
	}

}
