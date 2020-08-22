public class leet510 {
    
    public class Node {
        int val;
        Node left;
        Node right;
        Node parent;

        Node(int x) {
            val = x;
        }
    }

    public Node inorderSuccessor(Node node){
        Node curr = node, succ = null;
        if(curr.right != null){
            succ = curr.right;
            while(succ.left != null) succ = succ.left;
            return succ;
        }

        //1st method
        // int val = curr.val;
        // while(curr.parent != null){
        //     curr = curr.parent;
        //     if(curr.val > val) return curr;
        // }

        // return succ;

        //2nd method
        Node prev = null;
        while(curr.parent != null){
            prev = curr;
            curr = curr.parent;
            if(curr.left == prev)
                return curr;
        }

        return succ;
    }
}