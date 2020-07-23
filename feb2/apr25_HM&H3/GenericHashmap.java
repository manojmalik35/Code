package apr25;

import java.util.*;

public class GenericHashmap<K,V> {

	private class hmNode{
		K key;
		V value;
		hmNode(K key,V value){
			this.key=key;
			this.value=value;
		}
	}
	private int size;//n
	private LinkedList<hmNode>[] buckets;//N
	public GenericHashmap(){
		initBuckets(4);
		size=0;
	}
	private void initBuckets(int nob){
		buckets=new LinkedList[nob];
		for(int i=0;i<buckets.length;i++)
			buckets[i]=new LinkedList<>();
	}
	private int hashfn(K key){
		//hc+cf
		int hc=key.hashCode();
		int bi=Math.abs(hc)%buckets.length;
		return bi;
	}
	private int findWithinBucket(int bi,K key){
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
	public void put(K key,V value){
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
	public boolean containsKey(K key){
		//key -> bi
		//bi -> di
		int bi=hashfn(key);
		int di=findWithinBucket(bi, key);
		if(di==-1)
			return false;
		return true;
	}
	public V get(K key){
		int bi=hashfn(key);
		int di=findWithinBucket(bi, key);
		if(di==-1)
			return null;
		else
			return buckets[bi].get(di).value;
	}
	public V remove(K key){
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
	
	public ArrayList<K> keyset(){
		ArrayList<K> keys=new ArrayList<>();
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
		GenericHashmap<String,Integer> hm=new GenericHashmap<>();
		hm.put("India",12);
		hm.put("Pak",25);
		hm.put("China",31);
		hm.put("England",42);
		hm.put("US",59);
		hm.put("UK",85);
		hm.put("Africa",51);
		hm.put("America",67);
		hm.display();
		hm.put("Bharat",74);
		hm.put("Gujarat",74);
		hm.display();

	}

}
