package apr25;
import java.util.*;
public class hashmap {
//Assume key is int and value is String
	private static class hmNode{
		Integer key;
		String value;
		hmNode(int key,String value){
			this.key=key;
			this.value=value;
		}
	}
	private int size;//n
	private LinkedList<hmNode>[] buckets;//N
	public hashmap(){
		initBuckets(4);
		size=0;
	}
	private void initBuckets(int nob){
		buckets=new LinkedList[nob];
		for(int i=0;i<buckets.length;i++)
			buckets[i]=new LinkedList<>();
	}
	private int hashfn(int key){
		//hc+cf
		int hc=key;
		int bi=Math.abs(hc)%buckets.length;
		return bi;
	}
	private int findWithinBucket(int bi,int key){
		for(int di=0;di<buckets[bi].size();di++){
			hmNode node=buckets[bi].get(di);
			if(node.key.equals(key))
				return di;
		}
		return -1;
	}
	private void rehash(){
		LinkedList<hmNode>[] obuckets=buckets;
		initBuckets(buckets.length*2);
		size=0;
		for(int i=0;i<obuckets.length;i++){
			for(int j=0;j<obuckets[i].size();j++)
				put(obuckets[i].get(j).key,obuckets[i].get(j).value);
		}
	}
	public void put(Integer key,String value){
		int bi=hashfn(key);
		int di=findWithinBucket(bi, key);
		if(di!=-1)//updation
			buckets[bi].get(di).value=value;
		else{//insertion
			hmNode nn=new hmNode(key, value);
			buckets[bi].add(nn);
			size++;
			double lemda=size*1.0/buckets.length;
			if(lemda>2.0)
				rehash();
		}
	}
	public boolean containsKey(Integer key){
		//key -> bi
		//bi -> di
		int bi=hashfn(key);
		int di=findWithinBucket(bi, key);
		if(di==-1)
			return false;
		return true;
	}
	public String get(Integer key){
		int bi=hashfn(key);
		int di=findWithinBucket(bi, key);
		if(di==-1)
			return null;
		else
			return buckets[bi].get(di).value;
	}
	public String remove(Integer key){
		int bi=hashfn(key);
		int di=findWithinBucket(bi, key);
		if(di==-1)
			return null;
		else{
			hmNode nn=buckets[bi].remove(di);
			size--;
			return nn.value;
		}
	}
	
	public ArrayList<Integer> keyset(){
		ArrayList<Integer> keys=new ArrayList<>();
		for(int i=0;i<buckets.length;i++){
			for(int j=0;j<buckets[i].size();j++)
				keys.add(buckets[i].get(j).key);
		}
		return keys;
	}
	public void display(){
		System.out.println("===================");
		for(int i=0;i<buckets.length;i++){
			System.out.print("bucket"+i+" -> ");
			for(int j=0;j<buckets[i].size();j++)
				System.out.print("["+buckets[i].get(j).key+"="+buckets[i].get(j).value+"] ");
			System.out.println();
		}
		System.out.println("===================");
	}
	public static void main(String[] args) {
		hashmap hm=new hashmap();
		hm.put(12, "India");
		hm.put(25, "Pak");
		hm.put(31, "China");
		hm.put(42, "England");
		hm.put(59, "US");
		hm.put(85, "UK");
		hm.put(51, "Africa");
		hm.put(67, "America");
		hm.display();
		hm.put(74, "Bharat");
		hm.put(74, "Gujarat");
		hm.display();
//		System.out.println(hm.containsKey(6));
//		System.out.println(hm.get(4));
//		String s=hm.remove(2);
//		ArrayList<Integer> keys=hm.keyset();
//		System.out.println(keys);

	}

}
