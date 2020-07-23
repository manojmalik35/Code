package apr8;

import java.util.*;

public class AVL {

	private static class Node{
		int data;
		Node left;
		Node right;
		int ht;
		int bal;
		Node(int data){
			this.data=data;
			this.ht=1;
			this.bal=0;
		}
	}
	public static Node construct(ArrayList<Integer> dlist,int sp,int ep){
		if(sp>ep)
			return null;
		int mid=(sp+ep)/2;
		Node root=new Node(dlist.get(mid));
		
		root.left=construct(dlist, sp, mid-1);
		root.right=construct(dlist, mid+1, ep);
		sethnb(root);
		return root;
	}
	public static void display(Node root){
		if(root==null)
			return;
		if(root.left==null)
			System.out.print(".");
		else
			System.out.print(root.left.data);
		System.out.print(" -> "+root.data+"["+root.ht+","+root.bal+"]"+" <- ");
		if(root.right==null)
			System.out.print(".");
		else
			System.out.print(root.right.data);
		System.out.println();
		display(root.left);
		display(root.right);
	}
	private static void sethnb(Node root){
		int lh=root.left==null ? 0 : root.left.ht;
		int rh=root.right==null ? 0 : root.right.ht;
		root.ht=Math.max(lh, rh)+1;
		root.bal=lh-rh;
	}
	public static int max(Node root){//iteratively
		while(root.right!=null)
			root=root.right;
		return root.data;
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
		sethnb(root);
		root=handleAVL(root);
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
		if(root==null)
			return null;
		sethnb(root);
		root=handleAVL(root);
		return root;
	}
	private static Node handleAVL(Node root){
		if(root.bal>1){// left problem
			if(root.left.bal>0)//ll problem
				root=rightRotation(root);//r@z
			else{//lr problem
				root.left=leftRotation(root.left);//l@y
				root=rightRotation(root);//r@z
			}
		}else if(root.bal<-1){//right problem
			if(root.right.bal<0)//rr problem
				root=leftRotation(root);//l@z
			else{//rl problem
				root.right=rightRotation(root.right);//r@y
				root=leftRotation(root);//l@z
			}
		}
		return root;
	}
	private static Node leftRotation(Node z){
		Node y=z.right;
		Node t2=y.left;
		y.left=z;
		z.right=t2;
		sethnb(z);
		sethnb(y);
		return y;
	}
	private static Node rightRotation(Node z){
		Node y=z.left;
		Node t3=y.right;
		y.right=z;
		z.left=t3;
		sethnb(z);
		sethnb(y);
		return y;
	}
	public static void main(String[] args) {
		ArrayList<Integer> dlist=new ArrayList<>();
		dlist.add(20); dlist.add(30); dlist.add(40); dlist.add(50); dlist.add(60); dlist.add(70); dlist.add(80);
		Node root=construct(dlist, 0, dlist.size()-1);
		add(root,5);
		add(root,-5);
		add(root,25);
		display(root);
	}
}
