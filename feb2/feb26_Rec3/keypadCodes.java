package feb26;
import java.util.*;
public class keypadCodes {

	static String[] codes={".;","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz"};
	
	//gkpc(682) = pwd, pwe, pwf, ...........
	//gkpc(82) = wd, we, wf, xd, xe, xf
	//gkpc(682) = p gkpc(82) + q gkpc(82) + r gkpc(82) + s gkpc(82) 
	private static ArrayList<String> gkpc(String s){
		if(s.length()==0){
			ArrayList<String> bres=new ArrayList<>();
			bres.add("");
			return bres;
		}
		char ch=s.charAt(0);
		int r=ch-'0';
		String rest=s.substring(1,s.length());
		ArrayList<String> rres=gkpc(rest);
		ArrayList<String> mres=new ArrayList<>();
		String st=codes[r];
		for(int i=0;i<st.length();i++){
			for(int j=0;j<rres.size();j++)
				mres.add(st.charAt(i)+rres.get(j));
		}
		return mres;
		
	}
	public static void main(String[] args) {
		ArrayList<String> list=gkpc("682");
		System.out.println(list);

	}

}
