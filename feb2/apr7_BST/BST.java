package apr7;
import java.util.*;

public class BST {

	private static class Node{
		int data;
		Node left;
		Node right;
		Node(int data){
			this.data=data;
		}
	}
	public static Node construct(ArrayList<Integer> dlist,int sp,int ep){
		if(sp>ep)
			return null;
		int mid=(sp+ep)/2;
		Node root=new Node(dlist.get(mid));
		
		root.left=construct(dlist, sp, mid-1);
		root.right=construct(dlist, mid+1, ep);
		return root;
	}
	public static void display(Node root){
		if(root==null)
			return;
		if(root.left==null)
			System.out.print(".");
		else
			System.out.print(root.left.data);
		System.out.print(" -> "+root.data+" <- ");
		if(root.right==null)
			System.out.print(".");
		else
			System.out.print(root.right.data);
		System.out.println();
		display(root.left);
		display(root.right);
	}
	public static int min(Node root){
		if(root.left!=null)
			return min(root.left);
		else
			return root.data;
	}
	public static int max(Node root){//iteratively
		while(root.right!=null)
			root=root.right;
		return root.data;
	}
	public static boolean find(Node root,int data){
		if(root==null)
			return false;
		if(data<root.data)
			return find(root.left,data);
		else if(data>root.data)
			return find(root.right,data);
		else
			return true;
	}
	public static void printInRange(Node root,int lo,int hi){
		if(root==null)
			return;
		if(root.data<lo)
			printInRange(root.right, lo, hi);
		else if(root.data>hi)
			printInRange(root.left, lo, hi);
		else{
			System.out.print(root.data+" ");
			printInRange(root.left, lo, hi);
			printInRange(root.right, lo, hi);
		}
		
	}
	static int sum=0;
	public static void rwsol(Node root){//rwsol=replace with sum of larger nodes
		//bst me inorder hume sorted values deta h
		// isliye hum reverse euler chala k reverse inorder nikalenge jisse reverse sorted values milengi
		if(root==null)
			return;
		rwsol(root.right);
		
		int ord=root.data;
		root.data=sum;
		sum+=ord;
		
		rwsol(root.left);
	}
	//works for both BT and BST In BST nlogn In BT n2
	public static void PST1(Node oroot,Node root,int tar){//Pair Target Sum
		if(root==null)
			return;
		int comp=tar-root.data;
		if(comp>root.data){
			if(find(oroot,comp))
				System.out.println(root.data+" "+comp);
		}
		PST1(oroot,root.left, tar);
		PST1(oroot,root.right, tar);
	}
	private static void filler(Node root,ArrayList<Integer> list){
		if(root==null)
			return;
		filler(root.left,list);
		list.add(root.data);
		filler(root.right,list);
	}
	//space allowed 
	public static void PST2(Node root,int tar){
		ArrayList<Integer> list=new ArrayList<>();
		filler(root,list);
		
		//for BT...... Collections.sort(list);
		int li=0,ri=list.size()-1;
		while(li<ri){
			if(list.get(li)+list.get(ri)>tar)
				ri--;
			else if(list.get(li)+list.get(ri)<tar)
				li++;
			else{
				System.out.println(list.get(li)+" "+list.get(ri));
				li++;
				ri--;
			}
		}
	}
	private static class EulerHelper{
		public Node node;
		public int state=0;
		public EulerHelper(Node node) {
			this.node=node;
		}
	}
	public static Node eulerIterative(Node root){
		LinkedList<EulerHelper> stack=new LinkedList<>();
		EulerHelper rh=new EulerHelper(root);
		stack.addFirst(rh);
		while(stack.size()>0){
			EulerHelper th=stack.getFirst();
			if(th.state==0){
				if(th.node.left!=null){
					EulerHelper ch=new EulerHelper(th.node.left);
					stack.addFirst(ch);
				}
				th.state++;					
			}else if(th.state==1){
//				System.out.print(th.node.data+" ");
				if(th.node.right!=null){
					EulerHelper ch=new EulerHelper(th.node.right);
					stack.addFirst(ch);
					return ch.node;
				}
				th.state++;
			}else
				stack.removeFirst();
		}
		return null;
	}
	public static Node eulerIterativeReverse(Node root){
		LinkedList<EulerHelper> stack=new LinkedList<>();
		EulerHelper rh=new EulerHelper(root);
		stack.addFirst(rh);
		while(stack.size()>0){
			EulerHelper th=stack.getFirst();
			if(th.state==0){
				if(th.node.right!=null){
					EulerHelper ch=new EulerHelper(th.node.right);
					stack.addFirst(ch);
				}
				th.state++;					
			}else if(th.state==1){
//				System.out.print(th.node.data+" ");
				if(th.node.left!=null){
					EulerHelper ch=new EulerHelper(th.node.left);
					stack.addFirst(ch);
					return ch.node;
				}
				th.state++;
			}else
				stack.removeFirst();
		}
		return null;
	}
	public static void PST3(Node root,int tar){
		EulerHelper ls=new EulerHelper(root);
		EulerHelper rs=new EulerHelper(root);
		
		//psuedo code
//		while(left){
//			if(left.data+right.data>tar)
//				right=eulerIterativeReverse(right);
//			else if(left.data+right.data<tar)
//				left=eulerIterative(left);
//			else{
//				System.out.println(left.data+" "+right.data);
//				left=eulerIterative(left);
//				right=eulerIterativeReverse(right);
//			}
//		}
	}
	public static Node add(Node root,int data){
		if(root==null){
			root=new Node(data);
			return root;
		}
		if(data>root.data)
			root.right=add(root.right,data);
		else if(data<root.data)
			root.left=add(root.left,data);
		else{
			//do nothing
		}
		return root;
	}
	public static Node remove(Node root,int data){
		if(data>root.data)
			root.right=remove(root.right, data);
		else if(data<root.data)
			root.left=remove(root.left, data);
		else{
			if(root.left!=null && root.right!=null){
				int lmax=max(root.left);
				root.data=lmax;
				root.left=remove(root.left, lmax);
			}else
				root=root.left!=null ? root.left : root.right;
		}
		return root;
	}
	public static void main(String[] args) {
		ArrayList<Integer> dlist=new ArrayList<>();
//		dlist.add(12); dlist.add(25); dlist.add(37); dlist.add(50); dlist.add(62); dlist.add(75); dlist.add(87);
		dlist.add(10); dlist.add(20); dlist.add(30); dlist.add(50); dlist.add(60); dlist.add(70); dlist.add(80);
		Node root=construct(dlist, 0, dlist.size()-1);
//		display(root);
//		System.out.println(min(root));
//		System.out.println(max(root));
//		System.out.println(find(root,90));
//		printInRange(root, 25, 75);
//		rwsol(root);
//		display(root);
//		PST1(root,root, 100);
//		PST2(root, 100);
//		eulerIterative(root);
//		System.out.println();
//		eulerIterativeReverse(root);
		PST3(root, 100);
	}

}
