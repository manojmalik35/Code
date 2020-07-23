package mar24;

import java.util.*;

public class GenericTree {

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

	private static int max(Node root) {
		int max = root.data;
		for (int i = 0; i < root.children.size(); i++) {
			int nm = max(root.children.get(i));
			max = Math.max(max, nm);
		}
		return max;
	}

	private static int size(Node root) {
		int count = 0;
		for (int i = 0; i < root.children.size(); i++) {
			int nc = size(root.children.get(i));
			count += nc;
		}
		count += 1;
		return count;
	}

	private static boolean find(Node root, int dtf) {
		if (root.data == dtf)
			return true;
		for (int i = 0; i < root.children.size(); i++) {
			boolean nm = find(root.children.get(i), dtf);
			if (nm)
				return true;
		}
		return false;
	}

	private static int height(Node root) {
		int count = 0; // for edges height count=-1
		for (int i = 0; i < root.children.size(); i++) {
			int c = height(root.children.get(i));
			count = Math.max(count, c);
		}
		count += 1;
		return count;
	}

	private static ArrayList<Integer> node2root(Node root, int data) {
		if (root.data == data) {
			ArrayList<Integer> br = new ArrayList<>();
			br.add(root.data);
			return br;
		}
		for (int i = 0; i < root.children.size(); i++) {
			ArrayList<Integer> ntcp = node2root(root.children.get(i), data);
			if (ntcp != null) {
				ntcp.add(root.data);
				return ntcp;
			}
		}
		return null;
	}

	private static int lca(Node root, int d1, int d2) {
		ArrayList<Integer> a1 = node2root(root, d1);
		ArrayList<Integer> a2 = node2root(root, d2);
		int temp = 0;
		// for(int i=a1.size()-1,j=a2.size()-1;i>=0 && j>=0;i--,j--){
		// if(a1.get(i)==a2.get(j))
		// temp=a1.get(i);
		// else
		// return temp;
		// }
		// return temp;
		int i = a1.size() - 1;
		int j = a2.size() - 1;
		while (i >= 0 && j >= 0 && a1.get(i) == a2.get(j)) {
			temp = a1.get(i);
			i--;
			j--;
		}
		return temp;
	}

	private static int distance(Node root, int d1, int d2) {
		ArrayList<Integer> a1 = node2root(root, d1);
		ArrayList<Integer> a2 = node2root(root, d2);
		// int d=1;
		// for(int i=a1.size()-1,j=a2.size()-1;i>=0 || j>=0;i--,j--){
		// if(i<0||j<0)
		// d++;
		// else if(a1.get(i)!=a2.get(j))
		// d+=2;
		// }
		// return d;
		int i = a1.size() - 1;
		int j = a2.size() - 1;
		while (i >= 0 && j >= 0 && a1.get(i) == a2.get(j)) {
			i--;
			j--;
		}
		return (i + 1) + (j + 1) + 1;
	}

	private static void mirror(Node root) {
		for (int i = 0; i < root.children.size(); i++)
			mirror(root.children.get(i));
		// for(int i=0;i<root.children.size()/2;i++){
		// Node n=root.children.get(i);
		// Node n2=root.children.get(root.children.size()-i-1);
		// root.children.set(i,n2);
		// root.children.set(root.children.size()-i-1,n);
		// }
		int left = 0;
		int right = root.children.size() - 1;
		while (left < right) {
			Node n1 = root.children.get(left);
			Node n2 = root.children.get(right);
			root.children.set(left, n2);
			root.children.set(right, n1);
			left++;
			right--;
		}
	}

	//not complete
	private static void removeleaves(Node root) {
		for (int i = 0; i < root.children.size(); i++)
			removeleaves(root.children.get(i));
		Node temp = root;
		if (root.children.size() == 0) {

		}
	}

	// linearize1 nhi krna h kyuki iski complexity (nh) hai pr mne ese hi kr rkha hai
	private static Node getTail(Node root) {
		Node temp = root;
		while (temp.children.size() > 0) {
			temp = temp.children.get(0);
		}
		return temp;
	}
	// linearize1 nhi krna h kyuki iski complexity (nh) hai pr mne ese hi kr rkha hai
	public static void linearize1(Node root) {
		for (int i = 0; i < root.children.size(); i++)
			linearize1(root.children.get(i));

		while (root.children.size() > 1) {
			Node temp = root.children.get(root.children.size() - 1);
			root.children.remove(root.children.size() - 1);

			Node sl = root.children.get(root.children.size() - 1);
			Node slt = getTail(sl);
			slt.children.add(temp);
		}
	}

	// linearize2 complexity - n
	public static Node linearize2(Node root) {
		if (root.children.size() == 0) {
			return root;
		}

		Node ol = root.children.get(root.children.size() - 1);
		Node olt = linearize2(ol);
		while (root.children.size() > 1) {
			Node sl = root.children.get(root.children.size() - 2);
			Node slt = linearize2(sl);

			Node last = root.children.get(root.children.size() - 1);
			root.children.remove(root.children.size() - 1);
			slt.children.add(last);
		}
		return olt;
	}

	// Bonus question
	public static int[] countNodesSubtreeSum(Node root) {
		// print the number of nodes for which the subtree sum is greater than the parent subtree sum
		// tree contains both positive and negative integers
		int[] ans = new int[2];// count and sum
		for(int i=0;i<root.children.size();i++){
			int[] rs=countNodesSubtreeSum(root.children.get(i));
		}
		return ans;
	}
	
	public static boolean areSimilarStructured(Node root1,Node root2){
		if(root1.children.size()==root2.children.size()){
			for(int i=0;i<root1.children.size();i++){
				boolean rs=areSimilarStructured(root1.children.get(i), root2.children.get(i));
				if(rs==false)
					return false;
			}
		}else
			return false;
		return true;
	}
	
	public static boolean areMirrorShaped(Node root1,Node root2){
		if(root1.children.size()==root2.children.size()){
			int left=0;
			int right=root2.children.size()-1;
			while(left!=root1.children.size() && right>0){
				boolean rs=areMirrorShaped(root1.children.get(left), root2.children.get(right));
				if(rs==false)
					return false;
				left++;
				right--;
			}
		}else
			return false;
		return true;
	}
	
	public static boolean isSymmetric(Node root){
		return areMirrorShaped(root, root);
	}
	
	public static int secondLargestElement(Node root){
		return 0;
	}
	//is done in future as kth largest
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
		// dlist.add(130);
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
		display(root);
		ArrayList<Integer> dlist1 = new ArrayList<>();
		dlist1.add(1);
		dlist1.add(2);
		dlist1.add(5);
		dlist1.add(-1);
		dlist1.add(6);
		dlist1.add(-1);
		dlist1.add(-1);
		dlist1.add(3);
		dlist1.add(7);
		dlist1.add(-1);
		dlist1.add(8);
		dlist1.add(11);
		dlist1.add(-1);
		dlist1.add(12);
		// dlist1.add(13);
		// dlist1.add(-1);
		dlist1.add(-1);
		dlist1.add(-1);
		dlist1.add(9);
		dlist1.add(-1);
		dlist1.add(-1);
		dlist1.add(4);
		dlist1.add(10);
		dlist1.add(-1);
		dlist1.add(-1);
		dlist1.add(-1);
		//for areMirror
//		dlist1.add(1);
//		dlist1.add(4);
//		dlist1.add(10);
//		dlist1.add(-1);
//		dlist1.add(-1);
//		dlist1.add(3);
//		dlist1.add(9);
//		dlist1.add(-1);
//		dlist1.add(8);
//		dlist1.add(12);
//		dlist1.add(-1);
//		dlist1.add(11);
//		dlist1.add(-1);
//		dlist1.add(-1);
//		   dlist1.add(13);
//		   dlist1.add(-1);
//		dlist1.add(7);
//		dlist1.add(-1);
//		dlist1.add(-1);
//		dlist1.add(2);
//		dlist1.add(6);
//		dlist1.add(-1);
//		dlist1.add(5);
//		dlist1.add(-1);
//		dlist1.add(-1);
//		dlist1.add(-1);
		//for isSymmetric
//		dlist1.add(1);
//		dlist1.add(2);
//		dlist1.add(5);
//		dlist1.add(-1);
//		dlist1.add(6);
//		dlist1.add(-1);
//		dlist1.add(-1);
//		dlist1.add(3);
//		dlist1.add(7);
//		dlist1.add(-1);
//		dlist1.add(8);
//		dlist1.add(-1);
//		dlist1.add(-1);
//		dlist1.add(4);
//		// dlist1.add(13);
//		// dlist1.add(-1);
//		dlist1.add(9);
//		dlist1.add(-1);
//		dlist1.add(10);
//		dlist1.add(-1);
//		dlist1.add(-1);
//		dlist1.add(-1);
		Node root2=construct(dlist1);
		System.out.println("=========================");
//		display(root2);
		// System.out.println(max(root));
		// System.out.println(size(root));
		// System.out.println(find(root,120));
		// System.out.println(height(root));
		// ArrayList<Integer> path=node2root(root, 120);
		// System.out.println(path);
		// System.out.println(lca(root, 70, 80));
		// System.out.println(distance(root, 70, 100));
		// mirror(root);
		// removeleaves(root);
		System.out.println("=========================");
		// linearize1(root);
//		linearize2(root);
//		display(root);
//		System.out.println(areSimilarStructured(root, root2));
//		System.out.println(areMirrorShaped(root, root2));
		System.out.println(isSymmetric(root2));

	}

}
