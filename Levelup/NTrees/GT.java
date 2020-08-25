import java.util.*;

public class n_aryTree {

    static class Node {
        int data;
        ArrayList<Node> children;

        Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    static Node construct(int[] arr) {
        Stack<Node> st = new Stack<>();
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] != -1)
                st.push(new Node(arr[i]));
            else{
                Node n = st.pop();
                st.peek().children.add(n);
            }
        }

        return st.peek();
    }

    static void display(Node root){
        StringBuilder sb = new StringBuilder();
        sb.append(root.data + " -> ");
        for(Node child : root.children)
            sb.append(child.data + ", ");
        System.out.println(sb + ".");
        for(Node child : root.children)
            display(child);
    }

    static Node lca = null;
    static boolean LCA(Node root, int a, int b){

        boolean selfDone = root.data == a || root.data == b;
        int count = 0;
        for(Node child : root.children)
            if(LCA(child, a, b)) count++;

        if((selfDone && count == 1) || count == 2){
            lca = root;
            return true;
        }

        return selfDone || count != 0;
    }    

    public static void main(String[] args) {
        int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 100, -1, 110, -1, -1, 90, -1, -1, 40, 120, 140, -1,
                150, -1, -1, -1, -1 };
        Node root = construct(arr);
        // display(root);
        LCA(root, 90, 60);
        System.out.println(lca.data);
    }
}