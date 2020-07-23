import java.util.*;
public class gt{
    
    static class Node{
        int data;
        ArrayList<Node> children;

        Node(int data){
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    static Node construct(int[] dlist){
        Node root = null;
        LinkedList<Node> nlist = new LinkedList<>();//link list as a stack
        //we can use arraylist as a stack and stack itself
        for(int i = 0; i < dlist.length; i++){
            if(dlist[i] == -1)
                nlist.removeFirst();
            else{
                Node nn = new Node(dlist[i]);
                if(nlist.size() == 0)
                    root = nn;
                else{
                    Node top = nlist.getFirst();
                    top.children.add(nn);
                }
                nlist.addFirst(nn);
            }
        }
        return root;
    }

    static void display(Node root){
        System.out.print(root.data + " -> ");
        for(Node child : root.children)
            System.out.print(child.data +", ");
        System.out.println(".");
        for(Node child : root.children)
            display(child);
    }

    static boolean find(Node root, int val){
        if(root.data == val)
            return true;
        for(Node child : root.children){
            boolean rres = find(child, val);
            if(rres)
                return true;
        }
        return false;
    }

    static int gtmax(Node root){
        int mymax = Integer.MIN_VALUE;   
        if(root.data > mymax)
            mymax = root.data;
        for(Node child : root.children){
            int rres = gtmax(child);
            if(rres > mymax)
                mymax = rres;
        }
        return mymax;
    }

    static int gtmin(Node root){
        int mymin = Integer.MAX_VALUE;   
        if(root.data < mymin)
            mymin = root.data;
        for(Node child : root.children)
        {
            int rres = gtmax(child);
            if(rres < mymin)
                mymin = rres;
        }
        return mymin;
    }

    public static void main(String[] args) {
        int[] dlist = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110,
                       -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
        Node root = construct(dlist);
        // display(root);
        System.out.println(find(root, 190));
        System.out.println(gtmax(root));
        System.out.println(gtmin(root));
    }
}