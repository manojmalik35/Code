package may1;

public class dp3 {

	public static void lcs(String s1,String s2){//longest common subsequence
		int[][] strg=new int[s1.length()+1][s2.length()+1];
		for(int i=strg.length-2;i>=0;i--){
			for(int j=strg[0].length-2;j>=0;j--){
				if(s1.charAt(i) == s2.charAt(j))
					strg[i][j] = strg[i+1][j+1] +1;
				else
					strg[i][j] = Math.max(strg[i+1][j], strg[i][j+1]);
			}
		}
//		for(int i=0;i<strg.length;i++){
//			for(int j=0;j<strg[0].length;j++)
//				System.out.print(strg[i][j]+" ");
//			System.out.println();
//		}
		System.out.println(strg[0][0]);
//		printlcs(strg,0,0,"");
	}
	private static void printlcs(int[][] strg,int r,int c,String psf){
		//galat h
		if(r == strg.length && c == strg[0].length){
			System.out.println(psf);
			return;
		}
		int f1=strg[r+1][c];
		int f2=strg[r][c+1];
		if(f1>f2)
			printlcs(strg, r+1, c, psf);
		else if(f1<f2)
			printlcs(strg, r, c+1, psf+strg[r][c]);
		else{
			printlcs(strg, r+1, c, psf+strg[r][c]);
			printlcs(strg, r, c+1, psf+strg[r][c]);
		}
	}
	public static void lps(String s){//longest palindromic subsequence
		int[][] strg=new int[s.length()][s.length()];
		for(int gap=0;gap<s.length();gap++){
			for(int i=0,j=i+gap;j<s.length();i++,j++){
				if(i==j)
					strg[i][j] = 1;
				else if(s.charAt(j) == s.charAt(i))
						strg[i][j] = strg[i+1][j-1] + 2;
				else
						strg[i][j] = Math.max(strg[i][j-1], strg[i+1][j]);
			}
		}
		System.out.println(strg[0][s.length()-1]);
	}
	public static void cps(String s){
		int[][] strg=new int[s.length()][s.length()];
		for(int gap=0;gap<s.length();gap++){
			for(int i=0,j=i+gap;j<s.length();i++,j++){
				if(gap == 0)
					strg[i][j] = 1;
				else if(s.charAt(j) == s.charAt(i))
						strg[i][j] = strg[i][j-1] + strg[i+1][j] + 1;
				else
						strg[i][j] = strg[i][j-1] + strg[i+1][j] - strg[i+1][j-1];
			}
		}
		System.out.println(strg[0][s.length()-1]);
	}
	public static void calpst(String s){//count and longest palindromic substring
		boolean[][] strg=new boolean[s.length()][s.length()];
		int count=0;
		int length=-1;
		for(int gap=0;gap<s.length();gap++){
			for(int i=0,j=i+gap;j<s.length();i++,j++){
				if(gap == 0)
					strg[i][j] = true;
				else if(gap == 1)
					strg[i][j] = s.charAt(j) == s.charAt(i);
				else
					strg[i][j] = s.charAt(j) == s.charAt(i) && strg[i+1][j-1];
				if(strg[i][j]){
					count++;
					length = gap + 1;
				}
			}
		}
		System.out.println(count+" "+length);
	}
	public static void main(String[] args) {
//		lcs("abcd","aebd");
//		lps("bddaghdab");
//		cps("bddaghdab");
		calpst("abccbc");

	}

}
