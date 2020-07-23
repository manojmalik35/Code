package mar23;

import mar23.floydcycle.Node;

public class intersectionpoint {

	class Node{
		public int data;
		public Node next;
		
		Node(int data){
			this.data=data;
		}
	}
	
	public static void display(Node node){
		for(Node temp=node;temp!=null;temp=temp.next)
			System.out.print(temp.data+" ");
		System.out.println();
	}
	
	private static int removeLoop(Node slow,Node fast){
		Node temp=fast;
		while(slow!=fast){
			slow=slow.next;
			temp=fast;
			fast=fast.next;
		}
		return slow.data;
	}
	public static int ip(Node oh,Node th){
		Node prev=oh;
		for(Node temp=oh;temp!=null;temp=temp.next){
			prev=temp;
		}
		prev.next=th;
		Node slow=oh;
		Node fast=oh;
		int ans=0;
		while(fast!=null && fast.next!=null){
			slow=slow.next;
			fast=fast.next.next;
			if(slow==fast){
				ans=removeLoop(oh,fast);
				break;
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		intersectionpoint f=new intersectionpoint();
		Node n1=f.new Node(10);
		Node n2=f.new Node(20);
		Node n3=f.new Node(30);
		Node n4=f.new Node(40);
		Node n5=f.new Node(50);
		Node n6=f.new Node(60);
		Node n7=f.new Node(70);
		Node n8=f.new Node(80);
		Node n9=f.new Node(90);
		
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;
		n5.next=n6;
		n6.next=n7;
		n7.next=n8;
		n8.next=n9;
		n9.next=null;
		
		Node n10=f.new Node(100);
		Node n11=f.new Node(110);
		Node n12=f.new Node(120);
		
		n10.next=n11;
		n11.next=n12;
		n12.next=n6;
		System.out.println(ip(n1,n10));

	}

}
