package mar2;

public class pss {
	
	public static void pss(String ques,String asf){
		if(ques.length()==0){
			System.out.println(asf);
			return;
		}
		char ch=ques.charAt(0);
		String roq=ques.substring(1);
		
		pss(roq, asf+ch);
		pss(roq, asf+'-');
	}

	public static void main(String[] args) {
		pss("abc","");

	}

}
