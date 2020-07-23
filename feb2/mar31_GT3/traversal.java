package mar31;

import java.util.*;

public class traversal {

	private static class Node {
		int data;
		ArrayList<Node> children;

		Node(int data) {
			this.data = data;
			this.children = new ArrayList<>();
		}
	}

	private static Node construct(ArrayList<Integer> dlist) {
		Node root = null;
		ArrayList<Node> nlist = new ArrayList<>();

		for (int i = 0; i < dlist.size(); i++) {
			if (dlist.get(i) == -1)
				nlist.remove(nlist.size() - 1);
			else {
				Node nn = new Node(dlist.get(i));
				if (nlist.size() == 0)
					root = nn;
				else {
					Node ln = nlist.get(nlist.size() - 1);
					ln.children.add(nn);
				}
				nlist.add(nn);
			}
		}
		return root;
	}

	public static void display(Node root) {
		String s = root.data + " -> ";
		for (int i = 0; i < root.children.size(); i++)
			s += root.children.get(i).data + " , ";
		System.out.println(s + ".");
		for (int i = 0; i < root.children.size(); i++)
			display(root.children.get(i));
	}

	public static void levelOrder(Node root) {// Breadth First Traversal
		LinkedList<Node> queue = new LinkedList<>();// linkedlist as a queue...enqueue = aL ,dequeue =rF,front = gF
		queue.addLast(root);
		while (queue.size() > 0) {
			Node fr = queue.removeFirst();
			System.out.print(fr.data + " ");
			for (int i = 0; i < fr.children.size(); i++)
				queue.addLast(fr.children.get(i));
		}
	}

	public static void levelOrderLinewiseT1(Node root) {// Technique 1
		LinkedList<Node> cq = new LinkedList<>();// linkedlist as a queue....enqueue = aL ,dequeue =rF,front = gF
		LinkedList<Node> nq = new LinkedList<>();
		cq.addLast(root);
		while (cq.size() > 0) {
			Node fr = cq.removeFirst();
			System.out.print(fr.data + " ");
			for (int i = 0; i < fr.children.size(); i++)
				nq.addLast(fr.children.get(i));
			if (cq.size() == 0) {
				cq = nq;
				nq = new LinkedList<>();
				System.out.println();
			}
		}
	}

	public static void levelOrderLinewiseZZ(Node root) {
		LinkedList<Node> cq = new LinkedList<>();// linkedlist as a queue...enqueue = aL ,dequeue =rF,front = gF
		LinkedList<Node> ns = new LinkedList<>();// linkedlist as a stack...push = aF ,pop =rF,top = gF
		cq.addLast(root);
		boolean ltr = true;// ltr=left to right
		while (cq.size() > 0) {
			Node fr = cq.removeFirst();
			System.out.print(fr.data + " ");
			if (ltr) {
				for (int i = 0; i < fr.children.size(); i++)
					ns.addFirst(fr.children.get(i));
			} else {
				for (int i = fr.children.size() - 1; i >= 0; i--)
					ns.addFirst(fr.children.get(i));
			}
			if (cq.size() == 0) {
				cq = ns;
				ltr = !ltr;
				ns = new LinkedList<>();
				System.out.println();
			}
		}
	}

	public static void preOrder(Node root){//iteratively
		LinkedList<Node> stack=new LinkedList<>();
		stack.addFirst(root);
		while(stack.size()>0){
			Node top=stack.removeFirst();
			for(int i=top.children.size()-1;i>=0;i--)
				stack.addFirst(top.children.get(i));
			System.out.print(top.data+" ");
		}
	}
	public static void postOrder(Node root){
		//not correct
		LinkedList<Node> stack=new LinkedList<>();
		stack.addFirst(root);
		Node top=stack.getFirst();
		while(stack.size()>0){
			for(int i=top.children.size()-1;i>=0;i--)
				stack.addFirst(top.children.get(i));
			top=stack.getFirst();
			if(top.children.size()==0){
				System.out.print(top.data+" ");
				stack.removeFirst();
			}
		}
	}
	private static class EulerHelper{
		public Node node;
		public int state;
		public EulerHelper(Node node,int state) {
			this.node=node;
			this.state=state;
		}
	}
	public static void eulerIterative(Node root){
		LinkedList<EulerHelper> stack=new LinkedList<>();
		EulerHelper rh=new EulerHelper(root, 0);
		stack.addFirst(rh);
		while(stack.size()>0){
			EulerHelper th=stack.getFirst();
			if(th.state==0){
				System.out.print(th.node.data+"pre ,");
			}else if(th.state >=1 && th.state<=th.node.children.size()){
				EulerHelper ch=new EulerHelper(th.node.children.get(th.state-1),0);
				stack.addFirst(ch);
				if(th.state>=2)
					System.out.print(th.node.data+"in"+(th.state-1)+" ,");
			}else if(th.state == th.node.children.size()+1){
				System.out.print(th.node.data+"post ,");
			}else{
				stack.removeFirst();
			}
			th.state++;
		}
	}
	public static void main(String[] args) {
		ArrayList<Integer> dlist = new ArrayList<>();
		dlist.add(10);
		dlist.add(20);
		dlist.add(50);
		dlist.add(-1);
		dlist.add(60);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(30);
		dlist.add(70);
		dlist.add(-1);
		dlist.add(80);
		dlist.add(110);
		dlist.add(-1);
		dlist.add(120);
		// dlist.add(-10);
		// dlist.add(-1);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(90);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(40);
		dlist.add(100);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(-1);
		Node root = construct(dlist);
//      display(root);
//      levelOrder(root);
//      levelOrderLinewiseT1(root);
//		levelOrderLinewiseZZ(root);
//		preOrder(root);
//		postOrder(root);
		eulerIterative(root);
		

	}

}
