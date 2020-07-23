package mar16;

public class linkedlist {

		private class Node{
			public int data;
			public Node next;
		}
		public Node head;
		public Node tail;
		public int size;
		
		//constructor (not necessary)
		linkedlist(){
			head = tail = null;
			size=0;
		}
		private void handleAddIfSize0(int data){
			//space allocation
			Node nn=new Node();
			
			//data and next
			nn.data=data;
			nn.next=null;
			
			//summaries
			head = tail = nn;
			size=1;
		}
		public void addLast(int data){
			if(size==0)
				handleAddIfSize0(data);
			else{
				//space allocation
				Node nn=new Node();
				
				//data and next
				nn.data=data;
				nn.next=null;
				
				//connect
				tail.next=nn;
				
				//summaries
				tail=nn;
				size++;
			}
		}
		public void display(){
			Node temp=head;
			for(int i=0;i<size;i++){
				System.out.print(temp.data+" ");
				temp=temp.next;
			}
			System.out.println();
		}

		public int getSize(){
			return size;
		}
		public boolean isEmpty(){
			if(size==0)
				return true;
			return false;
		}
		public void addFirst(int data){
			if(size==0)
				handleAddIfSize0(data);
			else{
				//space allocation
				Node nn=new Node();
				
				//data and next
				nn.data=data;
				nn.next=head;
				
				//summaries
				head = nn;
				size++;
			}
		}
		public int getFirst(){
			if(size==0)
				return -1;
			return head.data;
		}
		public int getLast(){
			if(size==0)
				return -1;
			return tail.data;
		}
		private int handleRemoveWhenSize1(){
			int temp=head.data;
			head=tail=null;
			size=0;
			return temp;
		}
		public int removeFirst(){
			if(size==0)
				return -1;
			else if(size==1)
				return handleRemoveWhenSize1();
			else{
				int t=head.data;
				head=head.next;
				size--;
				return t;
			}
		}
		private Node getNodeAt(int index){
			Node temp=head;
			for(int i=0;i<index;i++)
				temp=temp.next;
			return temp;
		}
		public int getAt(int index){
			if(index<0 || index>=size){
				System.out.println("Index out of bounds");
				return -1;
			}
			Node temp=getNodeAt(index);
			return temp.data;
		}
		public int removeLast(){
			if(size==0)
				return -1;
			else if(size==1)
				return handleRemoveWhenSize1();
			else{
				int t=tail.data;
				Node nm1=getNodeAt(size-2);
				nm1.next=null;
				tail = nm1;
				size--;
				return t;
			}
		}
		public int removeAt(int i){
			if(i==0)
				return removeFirst();
			else if(i==size-1)
				return removeLast();
			else{
				Node nm1=getNodeAt(i-1);
				Node n= getNodeAt(i);
				int t=n.data;
				nm1.next = n.next;
				size--;
				return t;
			}
		}
		public void addAt(int data,int i){
			if(i<0 || i>size)
				return;
			else if(i==0)
				addFirst(data);
			else if(i==size)
				addLast(data);
			else{
				Node nm1=getNodeAt(i-1);
				Node np1=nm1.next;
				
				Node n=new Node();
				n.data=data;
				nm1.next=n;
				n.next=np1;
				size++;
			}
		}
		
	public static void main(String[] args) {
		linkedlist ll=new linkedlist();
		ll.addLast(10);
		ll.addLast(20);
		ll.addLast(30);
		ll.display();
		ll.addFirst(40);
		ll.addFirst(50);
		ll.display();
		ll.addAt(100, 3);
		ll.display();
		ll.removeFirst();
		ll.removeFirst();
		ll.display();
		ll.removeLast();
		ll.display();
		ll.addLast(200);
		ll.display();
		ll.removeAt(2);
		ll.display();

	}

}
