package mar15;
import java.util.*;
public class wordbreak {

	public static boolean isTheWordInVocab(ArrayList<String> vocab,String word){
		for(int i=0;i<vocab.size();i++){
			if(vocab.get(i).compareTo(word)==0)
				return true;
		}
		return false;
	}
	public static void mango(ArrayList<String> vocab,String q,String a){
		if(q.length()==0){
			System.out.println(a);
			return;
		}
		
		for(int d=0;d<q.length();d++){
			String s=q.substring(0, d+1);
			String roq=q.substring(d+1);
			if(isTheWordInVocab(vocab, s))
				mango(vocab,roq,a+s+"-");
		}
	}
	
	public static void main(String[] args) {
		ArrayList<String> vocab=new ArrayList<>();
		vocab.add("man"); vocab.add("go"); vocab.add("mango"); vocab.add("ice"); vocab.add("cream"); vocab.add("icecream"); vocab.add("air");
		vocab.add("plane"); vocab.add("airplane");
		mango(vocab,"gomanicecreamairplane","");
	}

}
