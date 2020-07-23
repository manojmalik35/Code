package NTrees;
public class bttobst{

    static class Node{
        int data;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
        }
    }

    static Node construct2(int[] pre, int[] in, int psi, int pei, int isi, int iei){
        if(psi > pei || isi > iei)
            return null;

        Node root = new Node(pre[psi]);

        int i = isi;
        while(i <= iei && in[i] != pre[psi])
            i++;
        int lhs = i - isi;
        root.left = construct2(pre, in, psi + 1, psi + lhs, isi, i - 1);
        root.right = construct2(pre, in, psi + lhs + 1, pei, i + 1, iei);

        return root;
    }

    static void display(Node root){
        if(root == null)
            return;

        if(root.left == null)
            System.out.print(".");
        else
            System.out.print(root.left.data);

        System.out.print(" -> " + root.data + " <- ");

        if(root.right == null)
            System.out.println(".");
        else
            System.out.println(root.right.data);
        
        display(root.left);
        display(root.right);
    }

    static class Pair {
		Node head;
		Node tail;

		Pair(Node head, Node tail){
			this.head = head;
			this.tail = tail;
		}
	}

    static void displayDLL(Pair pair) {
		Node temp;
		for (temp = pair.head; temp != null; temp = temp.right) {
			System.out.print(temp.data + " -> ");
		}
		System.out.println(".");

		for (temp = pair.tail; temp != null; temp = temp.left) {
			System.out.print(temp.data + " -> ");
		}
		System.out.println(".");
    }
    
    static Pair createDLL(Node root){
        if(root == null){
            return new Pair(null, null);
        }

        Pair lp = createDLL(root.left);
        Pair rp = createDLL(root.right);

        if(lp.tail != null)
            lp.tail.right = root;

        root.left = lp.tail;
        root.right = rp.head;

        if(rp.head != null)
            rp.head.left = root;

        Pair mp = new Pair(lp.head, rp.tail);
        if(mp.head == null && mp.tail == null){
            mp.head = root;
            mp.tail = root;
        }else if(mp.tail == null)
            mp.tail = root;
        else if(mp.head == null)
            mp.head = root;

        return mp;
    }

    static void merge2sortedDLLs(Pair d1, Pair d2, Pair res){

        Node li = d1.head;
        Node ri = d2.head;
        while(li != null && ri != null){
            Node rm = null;//node to be removed

            if(li.data < ri.data){
                rm = li;
                li = li.right;
                rm.right = null;
                li.left = null;
            }else{
                rm = ri;
                ri = ri.right;
                rm.right = null;
                ri.left = null;
            }

            if(res.head == null)
                res.head = res.tail = rm;
            else{
                res.tail.right = rm;
                rm.left = res.tail;
                res.tail = rm;
            }
        }

        if(li != null){
            res.tail.right = li;
            li.left = res.tail;
            res.tail = li;
        }else{
            res.tail.right = ri;
            ri.left = res.tail;
            res.tail = ri;
        }
    }

    static void mergeSort(Pair d){
        
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 8, 9, 5, 10, 11, 3, 6, 12, 7};
        int[] in = {8, 4, 9, 2, 10, 5, 11, 1, 12, 6, 3, 7};
        Node root = construct2(pre, in, 0, pre.length - 1, 0, in.length - 1);
        // display(root);
        Pair p = createDLL(root);
        displayDLL(p);

    }
}