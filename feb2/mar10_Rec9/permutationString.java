package mar10;

public class permutationString {

	//pqc= permutation question choice
	static int counter=0;
	public static void pqc(String ques,String asf){
		if(ques.length()==0){
			counter++;
			System.out.println(counter+" "+asf+" ");
			return;
		}
		
		for(int i=0;i<ques.length();i++){
			String sl=ques.substring(0, i);
			String sr=ques.substring(i+1);
			pqc(sl+sr,asf+ques.charAt(i));
		}
	}
	//pac= permutation answer choice
	public static void pac(String ques,String ans){
		if(ques.length()==0){
			counter++;
			System.out.println(counter+" "+ans+" ");
			return;
		}
		
		char ch=ques.charAt(0);
		String rest=ques.substring(1);
		for(int i=0;i<=ans.length();i++){
			String sl=ans.substring(0, i);
			String sr=ans.substring(i);
			pac(rest,sl+ch+sr);
		}
	}
	
	//pqcd = permutation question choice distinct
	public static void pqcd(String ques,String asf){
		if(ques.length()==0){
			counter++;
			System.out.println(counter+" "+asf+" ");
			return;
		}
		
		boolean[] aused=new boolean[256];
//		int bm=0;
		for(int i=0;i<ques.length();i++){
			char ch=ques.charAt(i);
			String sl=ques.substring(0, i);
			String sr=ques.substring(i+1);
//			if((bm&(1<<ch-97))==0){
//				bm^=(1<<ch-97);
//				pqcd(sl+sr, asf+ch);
//			}
			if(aused[ch]==false){
				aused[ch]=true;
				pqcd(sl+sr, asf+ch);
			}
		}
		
	}
	public static void main(String[] args) {
//		pqc("abc", "");
//		pac("abc","");
//		pqcd("baba", "");
		pqcd("abc","");

	}

}
