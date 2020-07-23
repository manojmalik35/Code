package apr25;

import java.util.*;
public class GenericPQ<T extends Comparable<T>> {

	private ArrayList<T> list = new ArrayList<>();
	private void upheapify(int ci){
		if(ci==0)
			return;
		int pi=(ci-1)/2;
		if(list.get(ci).compareTo(list.get(pi))<0){
			swap(ci,pi);
			upheapify(pi);
		}
	}
	private void downheapify(int pi){
		int mind=pi;
		int lci=2*pi+1;
		int rci=2*pi+2;
		if(lci<list.size() && list.get(lci).compareTo(list.get(mind))<0)
			mind=lci;
		if(rci<list.size() && list.get(rci).compareTo(list.get(mind))<0)
			mind=rci;
		if(mind!=pi){
			swap(mind,pi);
			downheapify(mind);
		}
	}
	private void swap(int i,int j){
		T ith=list.get(i);
		T jth=list.get(j);
		list.set(i, jth);
		list.set(j, ith);
	}
	public int size(){
		return list.size();
	}
	public T peek(){
		return list.get(0);
	}
	public void add(T data){
		list.add(data);
		upheapify(list.size()-1);
	}
	public T remove(){
		//swap with last element
		swap(0,list.size()-1);
		//remove
		T ans=list.remove(list.size()-1);
		//downheapify
		downheapify(0);
		return ans;
	}
//	public GenericPQ(T[] content) {
//		for(int i=0;i<content.length;i++)
//			list.add(content[i]);
//		for(int i=(list.size()/2)-1;i>=0;i--)//i=list.size()-1 bhi likh skte h kyuki last node k niche kuchh h hi ni
//			downheapify(i);
//	}
	private static class Car implements Comparable<Car>{
		int speed;
		int price;
		String name;
		Car(int speed,int price,String name){
			this.speed=speed;
			this.price=price;
			this.name=name;
		}
		void display(){
			System.out.println("["+this.speed+","+this.price+","+this.name+"]");
		}
		public int compareTo(Car o){
			return this.speed-o.speed;
		}
	}
	public static void main(String[] args) {
//		String[] content={"10","100","50","70","500","60","110","90","30"};
		GenericPQ<Car> pq=new GenericPQ<>();
		Car c1=new Car(100,50000,"A");
		Car c2=new Car(600,10000,"B");
		Car c3=new Car(300,80000,"C");
		Car c4=new Car(400,90000,"D");
		Car c5=new Car(50,60000,"E");
		pq.add(c1);
		pq.add(c2);
		pq.add(c3);
		pq.add(c4);
		pq.add(c5);
		while(pq.size()>0){
			Car val=pq.remove();
			val.display();
		}
		System.out.println();

	}

}
