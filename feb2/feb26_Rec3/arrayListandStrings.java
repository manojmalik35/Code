package feb26;
import java.util.*;
public class arrayListandStrings {

	public static void main(String[] args) {
		ArrayList<Integer> list=new ArrayList<>();
		list.add(10);
		list.add(20);
		list.add(30);
		list.add(40);
		System.out.println(list);
		System.out.println(list.size());
		System.out.println(list.get(2));
		list.set(1,300);
		System.out.println(list);
		
		String s="hello";
		System.out.println(s.charAt(0));
		System.out.println(s.length());
		System.out.println(s.substring(1,3));

	}

}
