package feb22;

public class diagonalTraversal2 {

	public static void main(String[] args) {
		int[][] mat={
				{11,12,13,14},
				{15,16,17,18},
				{19,20,21,22},
				{23,24,25,26}
		};
		int rmax=mat.length;
		int cmax=mat[0].length;
		
		for(int gap=0;gap<rmax;gap++){
			//int gap=0;gap>-rmax;gap--
			int j=0;
			int i=j+gap;
			//int i=j-gap;
			int count=1;
			while(count<=rmax-gap){
				//count<=rmax-Math.abs(gap)
				System.out.print(mat[i][j]+" ");
				i++;
				j++;
				count++;
			}
			System.out.println();
			
		}

	}

}
