package apr1;

import java.util.*;
public class Iteratively {

	static String[] codes={".;","abc","def","ghi","jk","lmno"};
	private static class kpcNode{
		String ques;
		String ans;
		public kpcNode(String ques,String ans) {
			this.ques=ques;
			this.ans=ans;
		}
	}
	
	public static void kpcBFT(String ques){
		LinkedList<kpcNode> queue=new LinkedList<>();
		kpcNode rn=new kpcNode(ques,"");
		queue.addLast(rn);
		while(queue.size()>0){
			kpcNode top=queue.getFirst();
			queue.removeFirst();
			if(top.ques.length()>0){
				String code=codes[top.ques.charAt(0)-'0'];
				for(int i=0;i<code.length();i++){
					kpcNode cn=new kpcNode(top.ques.substring(1), top.ans+code.charAt(i));
					queue.addLast(cn);
				}
			}else
				System.out.print(top.ans+" ");
			
		}
	}
	public static void main(String[] args) {
		
		kpcBFT("543");
	}

}
