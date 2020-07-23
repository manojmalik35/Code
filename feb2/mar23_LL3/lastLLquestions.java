package mar23;

import javax.xml.soap.Node;

public class lastLLquestions {

	public class linkedlist {

		private class Node {
			public int data;
			public Node next;
		}

		public Node head;
		public Node tail;
		public int size;
		Node tempLeft;

		// constructor (not necessary)
		linkedlist() {
			head = tail = null;
			size = 0;
		}
		private void handleAddIfSize0(int data) {
			// space allocation
			Node nn = new Node();

			// data and next
			nn.data = data;
			nn.next = null;

			// summaries
			head = tail = nn;
			size = 1;
		}

		public void addLast(int data) {
			if (size == 0)
				handleAddIfSize0(data);
			else {
				// space allocation
				Node nn = new Node();

				// data and next
				nn.data = data;
				nn.next = null;

				// connect
				tail.next = nn;

				// summaries
				tail = nn;
				size++;
			}
		}
		
		private void addLastNode(Node nn){
			if(size==0){
				head=tail=nn;
				size=1;
			}else{
				tail.next=nn;
				tail=nn;
				size++;
			}
		}

		public void display() {
			for (Node temp = head;temp!=null;temp=temp.next)
				System.out.print(temp.data + " ");
			System.out.println();
		}

		public int getSize() {
			return size;
		}

		public boolean isEmpty() {
			if (size == 0)
				return true;
			return false;
		}

		public void addFirst(int data) {
			if (size == 0)
				handleAddIfSize0(data);
			else {
				// space allocation
				Node nn = new Node();

				// data and next
				nn.data = data;
				nn.next = head;

				// summaries
				head = nn;
				size++;
			}
		}
		
		private void addFirstNode(Node nn){
			if (size == 0){
				head=tail=nn;
				size=1;
			}else{
				nn.next=head;
				head = nn;
				size++;
			}
		}
		
		public int getFirst() {
			if (size == 0)
				return -1;
			return head.data;
		}

		public int getLast() {
			if (size == 0)
				return -1;
			return tail.data;
		}

		private int handleRemoveWhenSize1() {
			int temp = head.data;
			head = tail = null;
			size = 0;
			return temp;
		}

		public int removeFirst() {
			if (size == 0)
				return -1;
			else if (size == 1)
				return handleRemoveWhenSize1();
			else {
				int t = head.data;
				head = head.next;
				size--;
				return t;
			}
		}
		
		private Node removeFirstNode(){
			if (size == 0)
				return null;
			else if (size == 1){
				Node temp=head;
				head=tail=null;
				size=0;
				return temp;
			}
			else {
				Node temp=head;
				head=head.next;
				size--;
				temp.next=null;
				return temp;
			}
		}
		
		private Node midNode(Node sp,Node ep){
			Node slow=sp;
			Node fast=sp;
			while(fast!=ep && fast.next!=ep){
				fast=fast.next.next;
				slow=slow.next;
			}
			return slow;
		}
		
		public int midElement(){
			Node mid=midNode(this.head,this.tail);
			return mid.data;
		}
		
		public int kthfromLast(int k){
			Node slow=head;
			Node fast=head;
			for(int i=0;i<k;i++)
				fast=fast.next;
			
			while(fast!=null){
				fast=fast.next;
				slow=slow.next;
			}
			return slow.data;
		}
		
		public void mergeTwoSortedLists(linkedlist l1,linkedlist l2){
			Node temp1=l1.head;
			Node temp2=l2.head;
			while(temp1!=null || temp2!=null){
				if(temp1==null){
					this.addLast(temp2.data);
					temp2=temp2.next;
				}else if(temp2==null){
					this.addLast(temp1.data);
					temp1=temp1.next;
				}else if(temp1.data<temp2.data){
					this.addLast(temp1.data);
					temp1=temp1.next;
				}else{
					this.addLast(temp2.data);
					temp2=temp2.next;
				}
			}
		}
		private linkedlist mergeSort(Node sp,Node ep){
			if(sp==ep){
				linkedlist temp=new linkedlist();
				temp.addLast(sp.data);
				return temp;
			}
			Node mid=midNode(sp, ep);
			linkedlist left=mergeSort(sp,mid);
			linkedlist right=mergeSort(mid.next,ep);
			linkedlist ans=new linkedlist();
			ans.mergeTwoSortedLists(left, right);
			return ans;
		}
		public linkedlist mergeSort(){
			linkedlist res=mergeSort(this.head,this.tail);
			return res;
		}
		
		private int addLinkedLists(Node oh,Node th,int os,int ts){
			if(oh==null && th==null){
				return 0;
			}
			int carry;
			int temp;
			if(os>ts){
				carry=addLinkedLists(oh.next, th, os-1, ts);
				temp=oh.data+carry;
			}
			else if(os<ts){
				carry=addLinkedLists(oh, th.next, os, ts-1);
			    temp=th.data+carry;
			}
			else{
				carry=addLinkedLists(oh.next, th.next, os-1, ts-1);
				temp=oh.data+th.data+carry;
			}
			this.addFirst(temp%10);
			carry=temp/10;
			return carry;
		}
		public void addLinkedLists(linkedlist l1,linkedlist l2){
			int c=this.addLinkedLists(l1.head,l2.head,l1.size,l2.size);
			this.addFirst(c);
		}
		
		private int mult(Node oh,Node th,Node ohead,linkedlist temp){
			if(th==null){
				return 0;
			}
			return 0;
		
				
		}
		public void mult(linkedlist l1,linkedlist l2){
//			linkedlist temp=new linkedlist();
//			mult(l1.head,l2.head,l1.head,temp);
			
		}
		
	}

	public static void main(String[] args) {
		lastLLquestions r=new lastLLquestions();
		linkedlist ll=r.new linkedlist();
		ll.addLast(10);
		ll.addLast(20);
		ll.addLast(30);
		ll.addLast(40);
		ll.addLast(50);
		ll.addLast(60);
		ll.addLast(70);
//		System.out.println(ll.midElement());
//		System.out.println(ll.kthfromLast(3));
		linkedlist ll1=r.new linkedlist();
		linkedlist ll2=r.new linkedlist();
		ll1.addLast(10);
		ll1.addLast(20);
		ll1.addLast(30);
		ll1.addLast(40);
		ll1.addLast(50);
		
		ll2.addLast(5);
		ll2.addLast(7);
		ll2.addLast(15);
		ll2.addLast(21);
		ll2.addLast(25);
		ll2.addLast(28);
		linkedlist ll3=r.new linkedlist();
//		ll3.mergeTwoSortedLists(ll1, ll2);
//		ll3.display();
//		ll3.addLast(5);
//		ll3.addLast(9);
//		ll3.addLast(4);
//		ll3.addLast(3);
//		ll3.addLast(1);
//		ll3.addLast(8);
//		ll3.addLast(2);
//		ll3.addLast(7);
//		ll3.display();
//		linkedlist ll4=ll3.mergeSort();
//		ll4.display();
		ll3.addLast(9);
		ll3.addLast(1);
		ll3.addLast(4);
		ll3.addLast(5);
		ll3.addLast(2);
		linkedlist ll4=r.new linkedlist();
		ll4.addLast(5);
		ll4.addLast(7);
		ll4.addLast(2);
		ll3.display();
		ll4.display();
		linkedlist ll5=r.new linkedlist();
		ll5.addLinkedLists(ll3, ll4);
		ll5.display();

	}
	
}
