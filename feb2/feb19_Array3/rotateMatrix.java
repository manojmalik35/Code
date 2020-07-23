package feb19;

public class rotateMatrix {

	public static void main(String[] args) {
		int[][] mat={
				{1,2,3,4},
				{5,6,7,8},
				{9,10,11,12},
				{13,14,15,16}
		};
		
		int rmax=mat.length;
		int cmax=mat[0].length;
		if(rmax!=cmax){
			System.out.println("Invalid");
			return;
		}
		
		//transpose
		for(int r=0;r<rmax;r++){
			for(int c=r;c<cmax;c++){
				int temp=mat[r][c];
				mat[r][c]=mat[c][r];
				mat[c][r]=temp;
			}
		}
		
		//reversing the rows
//		for(int r=0;r<rmax;r++){
//			int left=0;
//			int right=cmax-1;
//			while(left<=right){
//				int temp=mat[r][left];
//				mat[r][left]=mat[r][right];
//				mat[r][right]=temp;
//				left++;
//				right--;
//			}
//		}
		
		//printing result
		for(int r=0;r<rmax;r++){
			for(int c=0;c<cmax;c++){
				System.out.print(mat[r][c]+" ");
			}
			System.out.println();
		}
		
		

	}

}
