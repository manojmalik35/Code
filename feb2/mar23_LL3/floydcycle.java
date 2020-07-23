package mar23;

public class floydcycle {

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
	
	public static void removeLoop(Node slow,Node fast){
		Node temp=fast;
		while(slow!=fast){
			slow=slow.next;
			temp=fast;
			fast=fast.next;
		}
//		System.out.println(slow.data);//intersection point
		temp.next=null;
	}
	public static boolean dar(Node node){//dar= detect and remove
		Node slow=node;
		Node fast=node;
		while(fast!=null && fast.next!=null){
			slow=slow.next;
			fast=fast.next.next;
			if(slow==fast){
				removeLoop(node,fast);
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		floydcycle f=new floydcycle();
		Node n1=f.new Node(10);
		Node n2=f.new Node(20);
		Node n3=f.new Node(30);
		Node n4=f.new Node(40);
		Node n5=f.new Node(50);
		Node n6=f.new Node(60);
		Node n7=f.new Node(70);
		Node n8=f.new Node(80);
		Node n9=f.new Node(90);
		Node n10=f.new Node(100);
		
		n1.next=n2;
		n2.next=n3;
		n3.next=n4;
		n4.next=n5;
		n5.next=n6;
		n6.next=n7;
		n7.next=n8;
		n8.next=n9;
		n9.next=n10;
		n10.next=n5;
		
		System.out.println(dar(n1));
		display(n1);
		
		

	}

}
