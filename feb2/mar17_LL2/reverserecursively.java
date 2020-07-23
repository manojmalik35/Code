package mar17;

public class reverserecursively {

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
		
		private Node getNodeAt(int index) {
			Node temp = head;
			for (int i = 0; i < index; i++)
				temp = temp.next;
			return temp;
		}

		public int getAt(int index) {
			if (index < 0 || index >= size) {
				System.out.println("Index out of bounds");
				return -1;
			}
			Node temp = getNodeAt(index);
			return temp.data;
		}

		public int removeLast() {
			if (size == 0)
				return -1;
			else if (size == 1)
				return handleRemoveWhenSize1();
			else {
				int t = tail.data;
				Node nm1 = getNodeAt(size - 2);
				nm1.next = null;
				tail = nm1;
				size--;
				return t;
			}
		}

		public int removeAt(int i) {
			if (i == 0)
				return removeFirst();
			else if (i == size - 1)
				return removeLast();
			else {
				Node nm1 = getNodeAt(i - 1);
				Node n = getNodeAt(i);
				int t = n.data;
				nm1.next = n.next;
				size--;
				return t;
			}
		}

		public void addAt(int data, int i) {
			if (i < 0 || i > size)
				return;
			else if (i == 0)
				addFirst(data);
			else if (i == size)
				addLast(data);
			else {
				Node nm1 = getNodeAt(i - 1);
				Node np1 = nm1.next;

				Node n = new Node();
				n.data = data;
				nm1.next = n;
				n.next = np1;
				size++;
			}
		}

		private void displayReverse(Node node) {
			if (node == null)
				return;
			displayReverse(node.next);
			System.out.print(node.data + " ");
		}

		public void displayReverse() {
			displayReverse(head);
		}

		private void rpr(Node node) {
			if (node == tail)
				return;
			rpr(node.next);
			node.next.next=node;
		}

		public void rpr() {
			rpr(head);
			Node temp = head;
			head = tail;
			tail = temp;
			tail.next=null;
		}

		private void rdr(Node right,int floor) {
			if (right == null)
				return;
			rdr(right.next,floor+1);
			if(floor>=size/2){
				int temp=right.data;
				right.data=tempLeft.data;
				tempLeft.data=temp;
			}
			tempLeft=tempLeft.next;
		}

		public void rdr() {
			tempLeft=head;
			rdr(head,0);
		}

		private boolean isPalindrome(Node node){//floor is not required bcoz if palindrome let it traverse whole list
			if(node==null)
				return true;
			boolean b=isPalindrome(node.next);
			boolean m= b==true && tempLeft.data==node.data;
			tempLeft=tempLeft.next;
			return m;
		}
		public boolean isPalindrome(){
			tempLeft=head;
			return isPalindrome(head);
		}
		private void fold(Node right,int floor){
			if(right==null)
				return;
			fold(right.next,floor+1);
			if(floor>size/2){
				right.next=tempLeft.next;
				tempLeft.next=right;
				tempLeft=tempLeft.next.next;
			}else if(floor == size/2){
				tail=right;
				tail.next=null;
			}
		}
		public void fold(){
			tempLeft=head;
			fold(head,0);
		}
		
		private void unfold(Node left){
			if(left.next==null){
				head=tail=left;
				return;
			}else if(left.next.next==null){
				head=left;
				tail=left.next;
				return;
			}
			Node right=left.next;
			unfold(left.next.next);
			left.next=head;
			tail.next=right;
			
			head=left;
			tail=right;
			tail.next=null;
			
		}
		public void unfold(){
			unfold(head);
		}
		//very important question
		public void kreverse(int k){
			linkedlist prev=null;
			while(this.size!=0){// writing "this" is not compulsory bcoz writhing nothing means this... example is after 1 line
				linkedlist curr=new linkedlist();
				for(int i=0;i<k && size!=0;i++)   //here
					curr.addFirstNode(this.removeFirstNode());
				
				if(prev==null)
					prev=curr;
				else{
					prev.tail.next=curr.head;
					prev.tail=curr.tail;
					prev.size += curr.size;
				}
			}
			this.size = prev.size;
			this.head = prev.head;
			this.tail = prev.tail;
		}
		public void oddeven(){
			linkedlist odd=new linkedlist();
			linkedlist even=new linkedlist();
			while(this.size!=0){
				Node temp=this.removeFirstNode();
				if(temp.data%2!=0)
					odd.addLastNode(temp);
				else
					even.addLastNode(temp);
			}
			if(even.size==0){
				this.size=odd.size;
				this.head=odd.head;
				this.tail=odd.tail;
			}else if(odd.size==0){
				this.size=even.size;
				this.head=even.head;
				this.tail=even.tail;
			}else{
				odd.tail.next=even.head;
				this.size=odd.size+even.size;
				this.head=odd.head;
				this.tail=even.tail;
			}
		}

		public void removeDuplicates(){
			linkedlist nl=new linkedlist();
			while(this.size>0){
				Node temp=this.removeFirstNode();
				if(nl.size==0 || temp.data!=nl.tail.data)
					nl.addLastNode(temp);
			}
			this.size=nl.size;
			this.head=nl.head;
			this.tail=nl.tail;
		}
	}

	public static void main(String[] args) {
		reverserecursively r = new reverserecursively();
		linkedlist ll = r.new linkedlist();
		ll.addLast(1);
		ll.addLast(1);
		ll.addLast(1);
		ll.addLast(2);
		ll.addLast(3);
		ll.addLast(3);
		ll.addLast(3);
		ll.addLast(4);
		ll.addLast(4);
//		ll.displayReverse();
//		ll.rpr();
//		ll.rdr();
//		ll.fold();
//		ll.display();
//		ll.unfold();
//		ll.display();
//		System.out.println(ll.isPalindrome());
//		ll.kreverse(3);
//		ll.oddeven();
		ll.removeDuplicates();
		ll.display();
	}

}
