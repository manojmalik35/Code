package apr23;
import java.util.*;
public class KMP {

	private static int[] lpsCalculator(String pat){
		int[] lps=new int[pat.length()];
		int i=1;  
		int l=0;
		while(i<pat.length()){
			if(pat.charAt(i) == pat.charAt(l)){
				lps[i]=l+1;
				i++;
				l++;
			}else{
				if(l>0)
					l=lps[l-1];
				else
					i++;
			}
		}
		return lps;
	}
	public static void kmp(String src,String pat){
		int[] lps=lpsCalculator(pat);
		int i=0;
		int j=0;
		while(i<src.length()){
			if(src.charAt(i) == pat.charAt(j)){
				i++;
				j++;
				if(j == pat.length()){
					System.out.println(i-pat.length());
					j=lps[j-1];
				}
			}else{
				if(j>0)
					j=lps[j-1];
				else
					i++;
			}
		}
	}
	public static void main(String[] args) {
		String src="aaabaabaaabaababbaabbba";
		String pat="abaab";
		kmp(src, pat);

	}

}
