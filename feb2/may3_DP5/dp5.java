package may3;

public class dp5 {
	public static void mpc(String s){//minimum palindromic cut
		int[][] strg=new int[s.length()][s.length()];
		for(int gap=1;gap<strg.length;gap++){
			for(int i=0,j=i+gap;j<strg.length;i++,j++){
				if(gap==1)
					strg[i][j] = s.charAt(j) == s.charAt(i) ? 0 : 1;
				else if(s.charAt(j) == s.charAt(i) && strg[i+1][j-1] == 0)
						strg[i][j] = 0;
				else{
					//my code
					int min=Integer.MAX_VALUE;
					for(int l=i,r=i+1;l<j;l++,r++)
						min = Math.min(min, strg[i][l] + strg[r][j]);
					strg[i][j] = min + 1;
					//sir code
//					strg[i][j] = Integer.MAX_VALUE;
//					int lo=gap; //left offset
//					int ro=1;   //right offset
//					while(lo>=1 && ro<=gap){
//				 		int lhs=strg[i][j-lo];
//				 		int rhs=strg[i+ro][j];
//				 		if(lhs+rhs < strg[i][j])
//				 			strg[i][j] = lhs + rhs;
//				 		lo--;
//						ro++;
//					}
//					strg[i][j] += 1;
				}
			}
		}
//		for(int i=0;i<strg.length;i++){
//			for(int j=0;j<strg.length;j++)
//				System.out.print(strg[i][j]+" ");
//			System.out.println();
//		}
		System.out.println(strg[0][s.length()-1]);
	}
	public static void rodCutting(int[] arr){
		int[] strg=new int[arr.length];
		for(int i=0;i<arr.length;i++){
			if(i==0 || i==1)
				strg[i] = arr[i];
			else{
				strg[i] = arr[i];
				for(int l=1,r=i-1;l<=r;l++,r--)
						strg[i] = Math.max(strg[i], strg[l]+strg[r]);
			}
		}
		System.out.println(strg[strg.length-1]);
	}
	public static void mcm(int[] arr){//matrix chain multiplication
		int[][] strg=new int[arr.length-1][arr.length-1];
		for(int gap=1;gap<strg.length;gap++){
			for(int i=0,j=i+gap;j<strg.length;i++,j++){
				if(gap==1)
					strg[i][j] = arr[i]*arr[j]*arr[j+1];
				else{
					int min=Integer.MAX_VALUE;
					for(int l=i,r=i+1;l<j;l++,r++)
						min = Math.min(min, strg[i][l] + strg[r][j]+(arr[i])*arr[j]*arr[j+1]);
					strg[i][j] = min;
				}
			}
		}
		System.out.println(strg[0][strg.length-1]);
	}
	public static void main(String[] args) {
//		mpc("abccbc");
//		int[] arr={0,3,5,6,15,10,25,12,24};
//		rodCutting(arr);
		int[] abcde={10,20,30,40,50,60};
		mcm(abcde);
	}

}
