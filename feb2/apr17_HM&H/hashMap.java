package apr17;
import java.util.*;
public class hashMap {

	public static void main(String[] args) {
		HashMap<String, Integer> hm=new HashMap<>();
		hm.put("India", 135);// insertion
		hm.put("China", 200);
		hm.put("Nigeria", 110);
		hm.put("Sweden", 80);

		hm.put("India", 140);//updation
		System.out.println(hm.get("China"));
		System.out.println(hm.containsKey("Norway"));
		ArrayList<String> keys=new ArrayList<>(hm.keySet());
		System.out.println(keys);
		System.out.println(hm.remove("China"));
		System.out.println(hm);
	}

}
