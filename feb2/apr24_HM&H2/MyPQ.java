package apr24;
import java.util.*;
public class MyPQ {
//Heap Usage
	private ArrayList<Integer> list = new ArrayList<>();
	private void upheapify(int ci){
		if(ci==0)
			return;
		int pi=(ci-1)/2;
		if(list.get(pi)>list.get(ci)){
			swap(ci,pi);
			upheapify(pi);
		}
	}
	private void downheapify(int pi){
		int mind=pi;
		int lci=2*pi+1;
		int rci=2*pi+2;
		if(lci<list.size() && list.get(lci)<list.get(mind))
			mind=lci;
		if(rci<list.size() && list.get(rci)<list.get(mind))
			mind=rci;
		if(mind!=pi){
			swap(mind,pi);
			downheapify(mind);
		}
	}
	private void swap(int i,int j){
		int ith=list.get(i);
		int jth=list.get(j);
		list.set(i, jth);
		list.set(j, ith);
	}
	public int size(){
		return list.size();
	}
	public int peek(){
		return list.get(0);
	}
	public void add(int data){
		list.add(data);
		upheapify(list.size()-1);
	}
	public int remove(){
		//swap with last element
		swap(0,list.size()-1);
		//remove
		int ans=list.remove(list.size()-1);
		//downheapify
		downheapify(0);
		return ans;
	}
	public MyPQ(int[] content) {
		for(int i=0;i<content.length;i++)
			list.add(content[i]);
		for(int i=(list.size()/2)-1;i>=0;i--)//i=list.size()-1 bhi likh skte h kyuki last node k niche kuchh h hi ni
			downheapify(i);
	}
	public static void main(String[] args) {
		int[] content={10,80,50,70,40,60,20,90,30};
		MyPQ pq=new MyPQ(content);
//		pq.add(10);
//		pq.add(80);
//		pq.add(50);
//		pq.add(70);
//		pq.add(40);
//		pq.add(60);
//		pq.add(20);
//		pq.add(90);
//		pq.add(30);
		while(pq.size()>0){
			int val=pq.remove();
			System.out.print(val+" ");
		}
		System.out.println();
	}

}
