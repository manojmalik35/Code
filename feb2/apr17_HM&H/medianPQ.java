package apr17;
import java.util.*;
public class medianPQ {
	PriorityQueue<Integer> left=new PriorityQueue<>(Collections.reverseOrder());
	PriorityQueue<Integer> right=new PriorityQueue<>();
	
	void add(int val){
		if(left.size()>0 && val<left.peek())
			left.add(val);
		else
			right.add(val);
		handleBalance();
	}
	private void handleBalance(){
		if(left.size()-right.size()==2)
			right.add(left.remove());
		else if(right.size()-left.size()==2)
			left.add(right.remove());
	}
	void remove(){
		if(left.size()>=right.size())
			left.remove();
		else
			right.remove();
	}
	
	int peek(){
		if(left.size()>=right.size())
			return left.peek();
		else
			return right.peek();
	}
	int size(){
		return left.size()+right.size();
	}
	public static void main(String[] args) {
		medianPQ pq=new medianPQ();
		pq.add(10); pq.add(20); pq.add(5); pq.add(50); pq.add(30);
		System.out.println(pq.peek());
		pq.remove();
		pq.add(80);
		System.out.println(pq.peek());
		pq.add(100);
		pq.add(110);
		System.out.println(pq.peek());
		while(pq.size()>0){
			int val=pq.peek();
			System.out.println(val);
			pq.remove();
		}
	}

}
