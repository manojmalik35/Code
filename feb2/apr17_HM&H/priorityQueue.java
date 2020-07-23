package apr17;
import java.util.*;
public class priorityQueue {

	static void demo(){
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		pq.add(10);
		pq.add(2);
		pq.add(12);
		pq.add(18);
		pq.add(1);
		pq.add(33);
		
		while(pq.size()>0){
			int val=pq.peek();
			pq.remove();
			System.out.print(val+" ");
		}
	}
	public static void printksmallest1(int[] arr,int k){ //SC=O(n) ,TC=nlogn
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		for(int i=0;i<arr.length;i++){
			pq.add(arr[i]);
		}
		for(int i=0;i<k;i++){
			int val=pq.peek();
			pq.remove();
			System.out.print(val+" ");
		}
	}
	public static void printksmallest2(int[] arr,int k){//SC=O(k), TC=nlogk
		PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
		for(int i=0;i<arr.length;i++){
			if(i<k)
				pq.add(arr[i]);
			else{
				int val=pq.peek();
				if(val>arr[i]){
					pq.remove();
					pq.add(arr[i]);
				}
			}
		}
		while(pq.size()>0){
			System.out.println(pq.peek());
			pq.remove();
		}
	}
	public static void printSortedOfkSorted(int[] arr,int k){
		PriorityQueue<Integer> pq=new PriorityQueue<>();
		for(int i=0;i<=k;i++)
			pq.add(arr[i]);
		for(int i=k+1;i<arr.length;i++){
			System.out.print(pq.peek()+" ");
			pq.remove();
			pq.add(arr[i]);
		}
		while(pq.size()>0){
			System.out.print(pq.peek()+" ");
			pq.remove();
		}
	}
	private static class helper implements Comparable<helper>{
		int lno;//list no.
		int dno;//data no.
		int data;
		public helper(int lno,int dno,int data){
			this.lno=lno;
			this.dno=dno;
			this.data=data;
		}
		// +ve , 0 ,-ve
		public int compareTo(helper other){
			return this.data-other.data;
		}
	}
	public static void mergekSorted(ArrayList<ArrayList<Integer>> list){//space =k ,time=nlogk
		//k=no of lists and tnoe=total no of elements
		PriorityQueue<helper> pq=new PriorityQueue<>();
		for(int i=0;i<list.size();i++){
			helper h=new helper(i,0,list.get(i).get(0));
			pq.add(h);
		}
		while(pq.size()>0){
			helper h=pq.peek();
			System.out.print(h.data+" ");
			pq.remove();
			
			h.dno++;
			if(h.dno<list.get(h.lno).size()){
				h.data=list.get(h.lno).get(h.dno);
				pq.add(h);
			}
		}
	}
	public static void main(String[] args) {
		
//		demo();
		int[] arr={10,15,2,17,7,18,22,9,3};
//		int[] arr={7,15,5,22,18,20,27,35,40,30};
		printksmallest2(arr, 4);
//		printSortedOfkSorted(arr, 2);
//		ArrayList<ArrayList<Integer>> list=new ArrayList<>();
//		list.add(new ArrayList<>(Arrays.asList(5,15,25,35,40)));
//		list.add(new ArrayList<>(Arrays.asList(11,14,22,28)));
//		list.add(new ArrayList<>(Arrays.asList(2,7,17)));
//		list.add(new ArrayList<>(Arrays.asList(9,12,18,24,36)));	
//		mergekSorted(list);
	}

}

