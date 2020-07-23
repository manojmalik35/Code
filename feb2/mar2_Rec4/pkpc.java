package mar2;

public class pkpc {

	static String[] codes={".;","abc","def","ghi","jkl","mno","pqrs","tuv","wx","yz"};
	
	public static void pkpc(String ques,String asf){
		if(ques.length()==0){
			System.out.print(asf+" ");
			return;
		}
		char ch=ques.charAt(0);
		int l=ch-'0';
		String roq=ques.substring(1);
		for(int i=0;i<codes[l].length();i++){
			pkpc(roq,asf+codes[l].charAt(i));
		}
		
	}
	
	public static void main(String[] args) {
		pkpc("682","");

	}

}
