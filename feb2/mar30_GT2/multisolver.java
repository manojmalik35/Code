package mar30;

import java.util.*;

public class multisolver {

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
	public static void multisolver(Node root,int depth,heapmover hm,int data){
		hm.sz++;
		hm.height= depth>hm.height ? depth : hm.height;
		hm.max= root.data>hm.max ? root.data : hm.max;
		hm.min= root.data<hm.min ? root.data : hm.min;
		if(root.data>data && root.data<hm.ceil)
			hm.ceil=root.data;
		if(root.data<data && root.data>hm.floor)
			hm.floor=root.data;
		for(int i=0;i<root.children.size();i++)
			multisolver(root.children.get(i), depth+1, hm,data);
	}
	
	public static void ceilFloor(Node root,int data,heapmover hm){
		if(root.data>data && root.data<hm.ceil)
			hm.ceil=root.data;
		if(root.data<data && root.data>hm.floor)
			hm.floor=root.data;
		for(int i=0;i<root.children.size();i++)
			ceilFloor(root.children.get(i), data, hm);
	}
	public static void predSucc(Node root,int data,heapmover hm){
		if(hm.state==0){
			if(root.data!=data)
				hm.pred=root.data;
			else
				hm.state++;
		}else if(hm.state==1){
			hm.succ=root.data;
			hm.state++;
		}
		for(int i=0;i<root.children.size();i++)
			predSucc(root.children.get(i), data, hm);
	}
	public static void predSucc2(Node root,int data,heapmover hm){
		hm.prev=hm.curr;
		hm.curr=root.data;
		if(hm.curr==data)
			hm.pred=hm.prev;
		else if(hm.prev==data)
			hm.succ=hm.curr;
		for(int i=0;i<root.children.size();i++)
			predSucc2(root.children.get(i), data, hm);
	}
	public static int kthlargest(Node root,int k,heapmover hm){
		int res=Integer.MAX_VALUE;
		for(int i=0;i<k;i++){
			hm.floor=Integer.MIN_VALUE;
			ceilFloor(root, res, hm);
			res=hm.floor;
		}
		return res;
	}
	public static int kthsmallest(Node root,int k,heapmover hm){
		int res=Integer.MIN_VALUE;
		for(int i=0;i<k;i++){
			hm.ceil=Integer.MAX_VALUE;
			ceilFloor(root, res, hm);
			res=hm.ceil;
		}
		return res;
	}
	
	
	private static class heapmover{
		int sz=0;
		int height=0;
		int max=Integer.MIN_VALUE;
		int min=Integer.MAX_VALUE;
		int ceil=Integer.MAX_VALUE;
		int floor=Integer.MIN_VALUE;
		int pred,succ,state=0;
		int prev,curr;
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
//		 dlist.add(-10);
//		 dlist.add(-1);
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
//		display(root);
		heapmover hm=new heapmover();
//		multisolver(root, 0, hm,110);
//		System.out.println(hm.sz+" "+hm.height+" "+hm.max+" "+hm.min+" "+hm.floor+" "+hm.ceil);
//		predSucc(root, 90, hm);
//		predSucc2(root, 90, hm);
//		System.out.println(hm.pred+" "+hm.succ);
//		System.out.println(kthlargest(root, 5, hm));
//		System.out.println(kthsmallest(root, 4, hm));
		
		
	}

}
