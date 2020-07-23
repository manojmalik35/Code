package mar15;

public class encodings {

	public static boolean isTheChoiceValid(String word){
		if(word.charAt(0)=='0')
			return false;
		int n=Integer.parseInt(word);
		if(n==0)
			return false;
		if(n>26)
			return false;
		return true;
	}
	public static void encodings(String q,String a){
		if(q.length()==0){
			System.out.println(a);
			return;
		}
		
		for(int d=0;d<q.length();d++){
			String s=q.substring(0,d+1);
			String roq=q.substring(d+1);
			if(isTheChoiceValid(s)){
				char c=(char)(Integer.parseInt(s)+'a'-1);
				encodings(roq,a+c);
			}
		}
	}
	public static void main(String[] args) {
		encodings("1123", "");

	}

}
