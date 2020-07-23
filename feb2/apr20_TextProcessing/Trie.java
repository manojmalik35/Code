package apr20;
import java.util.*;
public class Trie {
//Word Matching
	static class Node{
		char data;
		HashMap<Character,Node> cmap=new HashMap<>();
		boolean eow;
		Node(char data,boolean eow){
			this.data=data;
			this.eow=eow;
		}
	}
	
	public static void addWord(Node root,String word){
		if(word.length()==0){
			root.eow=true;
			return;
		}
		char c=word.charAt(0);
		String row=word.substring(1);
		if(root.cmap.containsKey(c))
			addWord(root.cmap.get(c), row);
		else{
			Node nn=new Node(c, false);
			root.cmap.put(c, nn);
			addWord(root.cmap.get(c), row);
		}
	}
	public static void removeWord(Node root,String word){
		if(word.length()==0){
			root.eow=false;
			return;
		}
		char ch=word.charAt(0);
		String row=word.substring(1);
		Node child=root.cmap.get(ch);
		removeWord(child, row);
		
		if(child.eow==false && child.cmap.size() == 0)
			root.cmap.remove(ch);
	}
	public static void displayWords(Node root,String wsf){
		if(root.eow)
			System.out.print(wsf+" , ");
		ArrayList<Character> keys=new ArrayList<>(root.cmap.keySet());
		for(int i=0;i<keys.size();i++){
			Character key=keys.get(i);
			displayWords(root.cmap.get(key), wsf+key);
		}
	}
	public static boolean searchWord(Node root,String word){
		if(word.length()==0)
			return root.eow;

		char ch=word.charAt(0);
		String row=word.substring(1);
		if(root.cmap.containsKey(ch)){
			boolean res=searchWord(root.cmap.get(ch), row);
			return res;
		}else
			return false;
	}
	public static void displayTree(Node root){
		String s=root.data+"["+root.eow+"]"+" -> ";
		ArrayList<Character> keys=new ArrayList<>(root.cmap.keySet());
		for(int i=0;i<keys.size();i++){
			Character key=keys.get(i);
			s+= key+" , ";
		}
		System.out.println(s+".");
		for(int i=0;i<keys.size();i++)
			displayTree(root.cmap.get(keys.get(i)));
	}
	public static void main(String[] args) {
		Node root=new Node('$', false);
		addWord(root, "are");
		addWord(root, "art");
		addWord(root, "as");
		addWord(root, "ask");
		addWord(root, "ant");
		addWord(root, "an");
		addWord(root, "and");
		addWord(root, "seen");
		addWord(root, "sea");
		addWord(root, "see");
//		displayWords(root, "");
//		System.out.println();
//		displayTree(root);
		System.out.println(searchWord(root, "as"));
		removeWord(root, "as");
		System.out.println(searchWord(root, "as"));
	}

}
