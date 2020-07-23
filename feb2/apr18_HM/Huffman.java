package apr18;
import java.util.*;
public class Huffman {
	static HashMap<Character, String> encoder=new HashMap<>();
	static HashMap<String, Character> decoder=new HashMap<>();
	
	private static class Node implements Comparable<Node>{
		char data;
		int freq;
		Node left;
		Node right;
		Node(char data,int freq){
			this.data=data;
			this.freq=freq;
		}
		
		public int compareTo(Node other){
			return this.freq-other.freq;
		}
	}
	private static void traverse(Node n,String asf){
		if(n.left==null && n.right==null){
			encoder.put(n.data, asf);
			decoder.put(asf, n.data);
			return;
		}
		traverse(n.left,asf+"0");
		traverse(n.right,asf+"1");
	}
	Huffman(String feeder){
		//Prepare frequency Map
		HashMap<Character,Integer> fqmap=new HashMap<>();
		for(int i=0;i<feeder.length();i++){
			char c=feeder.charAt(i);
			if(fqmap.containsKey(c))
				fqmap.put(c, fqmap.get(c)+1);
			else
				fqmap.put(c, 1);
		}
		//Convert freqmap to priority queue
		PriorityQueue<Node> pq=new PriorityQueue<>();
		ArrayList<Character> keys=new ArrayList<>(fqmap.keySet());
		for(int i=0;i<keys.size();i++){
			Node n=new Node(keys.get(i), fqmap.get(keys.get(i)));
			pq.add(n);
		}
		
		//Making tree
		while(pq.size()>1){
			Node n1=pq.remove();
			Node n2=pq.remove();
			Node n3=new Node('$', n1.freq+n2.freq);
			n3.left=n2;
			n3.right=n1;
			pq.add(n3);
		}
		Node root=pq.remove();
		
		//Tree traversal
		traverse(root,"");
		
	}
	
	 public static String encode(String str){
		String ans="";
		for(int i=0;i<str.length();i++){
			char c=str.charAt(i);
			ans+= encoder.get(c);
		}
		return ans;
	}
	static String decode(String str){
		String ans="";
		for(int i=0;i<str.length();i++){
			String sub=str.substring(0, i);
		}
		return ans;
	}
	public static void main(String[] args) {
		Huffman h=new Huffman("aaaaaaaaaaaaaaaabbbbbbccccdd");
		String code=encode("abcd");
		System.out.println(encoder);
		System.out.println(code);
	}

}
