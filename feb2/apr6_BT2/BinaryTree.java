package apr6;

import java.util.*;
public class BinaryTree {

	private static class Node{
		int data;
		Node left;
		Node right;
		Node(int data){
			this.data=data;
		}
	}
	public static Node construct1(ArrayList<Integer> pre,ArrayList<Integer> in,int plo,int phi,int ilo,int ihi){
		if(plo>phi || ilo>ihi)
			return null;
		Node root=new Node(pre.get(plo));
		int lhs=0;
		while(in.get(ilo+lhs) != pre.get(plo))
			lhs++;
		
		root.left=construct1(pre, in, plo+1, plo+lhs, ilo, ilo+lhs-1);
		root.right=construct1(pre, in, plo+lhs+1, phi, ilo+lhs+1, ihi);
		return root;
	}
	public static Node construct2(ArrayList<Integer> post,ArrayList<Integer> in,int plo,int phi,int ilo,int ihi){
		if(plo>phi || ilo>ihi)
			return null;
		Node root=new Node(post.get(phi));
		int lhs=0;
		while(in.get(ilo+lhs) != post.get(phi))
			lhs++;
		
		root.left=construct2(post, in, plo, plo+lhs-1, ilo, ilo+lhs-1);
		root.right=construct2(post, in, plo+lhs, phi-1, ilo+lhs+1, ihi);
		return root;
	}
	public static Node construct3(ArrayList<Integer> da,ArrayList<Integer> pa){
		Node root=null;
		Node[] narr=new Node[101];
		for(int i=0;i<da.size();i++)
			narr[da.get(i)]=new Node(da.get(i));
		for(int i=0;i<da.size();i++){
			int data=da.get(i);
			int pdata=pa.get(i);
			if(pdata==-1)
				root=narr[data];
			else{
				Node child=narr[data];
				Node parent=narr[pdata];
				if(parent.left==null)
					parent.left=child;
				else
					parent.right=child;
			}
		}
		return root;
	}
	//Bonus
	public static Node construct4(ArrayList<Integer> pre,ArrayList<Integer> post,int prlo,int prhi,int polo,int pohi){
		if(prlo==prhi || polo==pohi){
			Node bn=new Node(pre.get(prlo));
			return bn;
		}
		Node root=new Node(pre.get(prlo));
		int lhs=0;
		while(pre.get(prlo+1) != post.get(polo+lhs))
			lhs++;
		
		root.left=construct4(pre, post, prlo+1, prlo+lhs+1, polo, polo+lhs);
		root.right=construct4(pre, post, prlo+lhs+2, prhi, polo+lhs+1, pohi-1);
		return root;
	}
	public static int height(Node root){
		if(root==null)
			return 0;//-1 for edges
		int lh=height(root.left);
		int rh=height(root.right);
		return Math.max(lh, rh) + 1;
	}
	//O(n2)
	public static int diameter(Node root){
		if(root==null)
			return 0;
		int ld=diameter(root.left);
		int rd=diameter(root.right);
		
		int lh=height(root.left);
		int rh=height(root.right);
		int f3=lh+ rh+1;
		int f1=ld;
		int f2=rd;
		return Math.max(f1, Math.max(f2, f3));
	}
	private static class diaPair{
		int ht=0;
		int dia=0;
	}
	//O(n)
	public static diaPair diameter2(Node root){
		if(root==null){
			diaPair bp=new diaPair();
			return bp;
		}
		
		diaPair ld=diameter2(root.left);
		diaPair rd=diameter2(root.right);
		diaPair md=new diaPair();
		md.ht=Math.max(ld.ht, rd.ht)+1;
		md.dia=Math.max(ld.ht+rd.ht+1, Math.max(ld.dia, rd.dia));
		return md;
	}
	//Balanced tree means for all nodes abs(lh-rh)<=1
	public static boolean isBalanced(Node root){
		if(root==null)
			return true;
		
		boolean lb=isBalanced(root.left);
		if(lb==false)
			return false;
		boolean rb=isBalanced(root.right);
		if(rb==false)
			return false;
		int lh=height(root.left);
		int rh=height(root.right);
		int gap=lh-rh;
		if(gap<-1 || gap>1)
			return false;
		return true;
	}
	private static class balPair{
		int height;
		boolean isBal;
	}
	public static balPair isBalanced2(Node root){
		if(root==null){
			balPair bp=new balPair();
			bp.height=0;
			bp.isBal=true;
			return bp;
		}
		balPair lb=isBalanced2(root.left);
		balPair rb=isBalanced2(root.right);
		int gap=lb.height-rb.height;
		
		balPair mp=new balPair();
		mp.height=Math.max(lb.height, rb.height)+1;
		mp.isBal= lb.isBal && rb.isBal && gap>=-1 && gap<=1;
		return mp;
	}
	//BST= for all nodes node.data must be larger than left subtree and smaller than the right subtree
	public static boolean isBST(Node root){
		return true;
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
	private static class BSTPair{
		int max;
		int min;
		boolean isBST;
		int lbstroot;
		int lbstsize;
	}
	//extremely important question just like kreverse of the linked list
	public static BSTPair isAndLargestBST(Node root){
		if(root==null){
			BSTPair bp=new BSTPair();
			bp.min=Integer.MAX_VALUE;
			bp.max=Integer.MIN_VALUE;
			bp.isBST=true;
			bp.lbstroot=-1;
			bp.lbstsize=0;
			return bp;
		}
		BSTPair lp=isAndLargestBST(root.left);
		BSTPair rp=isAndLargestBST(root.right);
		BSTPair mp=new BSTPair();
		
		mp.max=Math.max(root.data, Math.max(lp.max, rp.max));
		mp.min=Math.min(root.data, Math.min(lp.min, rp.min));
		mp.isBST= lp.isBST && rp.isBST && root.data>lp.max && root.data<rp.min;
		
		if(mp.isBST){
			mp.lbstroot=root.data;
			mp.lbstsize=lp.lbstsize+rp.lbstsize+1;
		}else{
			if(lp.lbstsize>rp.lbstsize){
				mp.lbstroot=lp.lbstroot;
				mp.lbstsize=lp.lbstsize;
			}else{
				mp.lbstroot=rp.lbstroot;
				mp.lbstsize=rp.lbstsize;
			}
		}
		return mp;
	}
	public static void main(String[] args) {
		ArrayList<Integer> pre=new ArrayList<>();
		ArrayList<Integer> in=new ArrayList<>();
		ArrayList<Integer> post=new ArrayList<>();
//		pre.add(50); pre.add(25); pre.add(12); pre.add(37); /*pre.add(40);*/ pre.add(75); pre.add(62); pre.add(87);
//		in.add(12); in.add(25); in.add(37); in.add(40); in.add(50); in.add(62); in.add(75); in.add(87);
		pre.add(50); pre.add(25); pre.add(12); pre.add(37); pre.add(30); pre.add(40); pre.add(75); pre.add(62); pre.add(60); 
		pre.add(70); pre.add(87); pre.add(90);
		in.add(12); in.add(25); in.add(30); in.add(37); in.add(40); in.add(50); in.add(60); in.add(62); in.add(70); in.add(75);
		in.add(87); in.add(90);
		post.add(12); /*post.add(40);*/ post.add(37); post.add(25); post.add(62); post.add(87); post.add(75); post.add(50);
		ArrayList<Integer> da=new ArrayList<>();
		ArrayList<Integer> pa=new ArrayList<>();
		da.add(12); da.add(25); da.add(50); da.add(75); da.add(37); da.add(87); da.add(40); da.add(80);
		pa.add(25); pa.add(50); pa.add(-1); pa.add(50); pa.add(25); pa.add(75); pa.add(37); pa.add(87);
		Node root=construct1(pre, in,0,pre.size()-1,0,in.size()-1);
//		Node root=construct2(post, in,0,post.size()-1,0,in.size()-1);
//		Node root=construct3(da, pa);
//		Node root=construct4(pre, post, 0, pre.size()-1, 0, post.size()-1);
//		display(root);
//		System.out.println(diameter(root));
//		diaPair d=diameter2(root);
//		System.out.println(d.dia);
//		System.out.println(isBalanced(root));
//		balPair b=isBalanced2(root);
//		System.out.println(b.isBal);
		BSTPair bst=isAndLargestBST(root);
		System.out.println(bst.isBST +" "+bst.lbstroot+" "+bst.lbstsize);
	}

}
