package apr1;
import java.util.*;

public class binaryTree {

	private static class Node{
		int data;
		Node left;
		Node right;
		Node(int data){
			this.data=data;
		}
	}
	public static Node construct(ArrayList<Integer> dlist){
		ArrayList<Node> nlist=new ArrayList<>();
		Node root=null;
		for(int i=0;i<dlist.size();i++){
			if(dlist.get(i)!=-1){
				Node nn=new Node(dlist.get(i));
				if(nlist.size()==0)
					root=nn;
				else{
					Node top=nlist.get(nlist.size()-1);
					if(top.left==null)
						top.left=nn;
					else
						top.right=nn;
				}
				nlist.add(nn);
			}else
				nlist.remove(nlist.size()-1);
		}
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
	public static int size(Node root){
		if(root==null)
			return 0;
		int sl=size(root.left);
		int sr=size(root.right);
		return sl+sr+1;
	}
	public static int sum(Node root){
		if(root==null)
			return 0;
		int sl=sum(root.left);
		int sr=sum(root.right);
		return sl+sr+root.data;
	}
	public static int max(Node root){
		if(root==null)
			return Integer.MIN_VALUE;
		int max=root.data;
		int ml=max(root.left);
		max=Math.max(ml, max);
		int mr=max(root.right);
		max=Math.max(mr, max);
		return max;
	}
	public static int height(Node root){
		if(root==null)
			return 0;//-1 for edges
		int lh=height(root.left);
		int rh=height(root.right);
		return Math.max(lh, rh) + 1;
	}
	public static boolean find(Node root,int data){
		if(root==null)
			return false;
		if(root.data==data)
			return true;
		boolean lr=find(root.left,data);
		if(lr)
			return true;
		boolean rr=find(root.right,data);
		if(rr)
			return true;
		return false;
	}
	public static ArrayList<Integer> node2rootPath(Node root,int data){
		if(root==null)
			return null;
		if(root.data==data){
			ArrayList<Integer> br=new ArrayList<>();
			br.add(root.data);
			return br;
		}
		ArrayList<Integer> lr=node2rootPath(root.left, data);
		if(lr!=null){
			lr.add(root.data);
			return lr;
		}
		ArrayList<Integer> rr=node2rootPath(root.right, data);
		if(rr!=null){
			rr.add(root.data);
			return rr;
		}
		return null;
	}
	public static ArrayList<Node> node2rootPath2(Node root,int data){
		if(root==null)
			return null;
		if(root.data==data){
			ArrayList<Node> br=new ArrayList<>();
			br.add(root);
			return br;
		}
		ArrayList<Node> lr=node2rootPath2(root.left, data);
		if(lr!=null){
			lr.add(root);
			return lr;
		}
		ArrayList<Node> rr=node2rootPath2(root.right, data);
		if(rr!=null){
			rr.add(root);
			return rr;
		}
		return null;
	}
	public static void printkdown(Node root,Node prohibitor,int k){
		if(root==null || root==prohibitor)
			return;
		if(k==0)
			System.out.println(root.data);
		printkdown(root.left, prohibitor,k-1);
		printkdown(root.right, prohibitor,k-1);
	}
	public static void printkfar(Node root,int data,int k){
		ArrayList<Node> n2rp=node2rootPath2(root, data);
		for(int i=0;i<=k && i<n2rp.size();i++){
			printkdown(n2rp.get(i), i==0 ? null : n2rp.get(i-1), k-i);
		}
	}
	//my method
	public static Node removeLeaves(Node root){
		if(root==null)
			return null;
		if(root.left==null && root.right==null){
			return root;
		}
		Node l=removeLeaves(root.left);
		if(l!=null)
			root.left=null;
		Node r=removeLeaves(root.right);
		if(r!=null)
			root.right=null;
		return null;
	}
	//sir method
	//faith is== left vla sare leaves remove kr k naya root return karega
	public static Node removeLeaves2(Node root){
		if(root==null)
			return null;
		if(root.left==null && root.right==null){
			return null;
		}
		root.left=removeLeaves2(root.left);
		root.right=removeLeaves2(root.right);
		return root;
	}
	public static void root2leafpathinrange(Node root,int lo,int hi,ArrayList<Integer> list,int sum){
		if(root==null)
			return;
		if(root.left==null && root.right==null){
			list.add(root.data);
			sum+=root.data;
			if(sum>=lo && sum<=hi)
				System.out.println(list);
			list.remove(list.size()-1);
			return;
		}
		list.add(root.data);
		root2leafpathinrange(root.left, lo, hi, list, sum+root.data);
		root2leafpathinrange(root.right, lo, hi, list, sum+root.data);
		list.remove(list.size()-1);
	}
	public static void printSingleChild(Node root){
		if(root==null)
			return;
		if(root.left!=null && root.right==null)
			System.out.println(root.left.data);
		if(root.left==null && root.right!=null)
			System.out.println(root.right.data);
		printSingleChild(root.left);
		printSingleChild(root.right);
	}
	public static void main(String[] args) {
		ArrayList<Integer> dlist = new ArrayList<>();
		dlist.add(50);
		dlist.add(25);
		dlist.add(12);
		dlist.add(-1);
		dlist.add(37);
		dlist.add(30);
		dlist.add(28);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(40);
		dlist.add(45);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(75);
		dlist.add(62);
		dlist.add(60);
		dlist.add(-1);
		dlist.add(70);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(87);
		dlist.add(-1);
		dlist.add(-1);
		dlist.add(-1);
		Node root = construct(dlist);
		display(root);
//		System.out.println(size(root));
//		System.out.println(sum(root));
//		System.out.println(max(root));
//		System.out.println(height(root));
//		System.out.println(find(root, 80));
//		ArrayList<Integer> ntr=node2rootPath(root,70);
//		System.out.println(ntr);
//		printkdown(root, null, 3);
//		printkfar(root, 25, 3);
		System.out.println("=======================");
		Node n=removeLeaves2(root);
		display(n);
//		printSingleChild(root);
//		ArrayList<Integer> list=new ArrayList<>();
//		root2leafpathinrange(root, 100, 250, list,0);
//		printSingleChild(root);
	}

}
