package feb26;
import java.util.*;
public class stringSubsequence {
	
	//subsequence with ascii code
	
	
	//subsequence
	//gss(abc) = ---,--c,-b-,-bc,a--,a-c,ab-,abc
	//gss(bc) =  --, -c, b-, bc
	//gss(abc) = -gss(bc) + agss(bc)
	private static ArrayList<String> gss(String s){
		if(s.length()==0){
			ArrayList<String> bres=new ArrayList<>();
			bres.add("");
			return bres;
		}
		
		char ch=s.charAt(0);
		String ros=s.substring(1,s.length());
		ArrayList<String> rres=gss(ros);
		ArrayList<String> mres=new ArrayList<>();
		
		for(int i=0;i<rres.size();i++){
			mres.add('-'+rres.get(i));
			mres.add(ch+rres.get(i));
		}
		return mres;
	}
	public static void main(String[] args) {
		String s="abc";
		ArrayList<String> list=gss(s);
		System.out.println(list);

	}

}
