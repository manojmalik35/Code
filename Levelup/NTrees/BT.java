import java.util.*;
public class BT {
    
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

    static int burningTree(Node root, int tar, ArrayList<ArrayList<Integer>> ans){
        if(root == null) return -1;

        if(root.data == tar){
            kDown(root, null, 0, ans);
            return 1;
        }

        int ld = burningTree(root.left, tar, ans);
        if(ld != -1){
            kDown(root, root.left, ld, ans);
            return ld + 1;
        }

        int rd = burningTree(root.right, tar, ans);
        if(rd != -1){
            kDown(root, root.right, rd, ans);
            return rd + 1;
        }

        return -1;
    }

    static void kDown(Node node, Node blocker, int k, ArrayList<ArrayList<Integer>> ans){
        if(node == null || node == blocker) return;
        
        if(k >= ans.size())
            ans.add(new ArrayList<>());
        ans.get(k).add(node.data);

        kDown(node.left, blocker, k + 1, ans);
        kDown(node.right, blocker, k + 1, ans);

    }

    // https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/
    static ArrayList<ArrayList<Integer>> burningTree(Node root, int target){
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target, ans);
        return ans;
    }

    // https://www.geeksforgeeks.org/convert-a-binary-tree-to-a-circular-doubly-link-list/
    Node bTreeToClistII(Node root)
    {
        if(root == null)
            return null;

        Node ltail = bTreeToClistII(root.left);
        Node rtail = bTreeToClistII(root.right);

        Node lhead = null, rhead = null;
        if(ltail != null)
            lhead = ltail.right;
        else{
            ltail = root;
            lhead = root;
        }

        if(rtail != null)
            rhead = rtail.right;
        else{
            rtail = root;
            rhead = root;
        }

        ltail.right = root;
        root.left = ltail;
        root.right = rhead;
        rhead.left = root;

        lhead.left = rtail;
        rtail.right = lhead;
        return rtail;
    }

    static Node head = null;
    static Node previous = null;
    void bTreeToClist_(Node root){
        if(root == null) return;

        bTreeToClist_(root.left);

        if(head == null) head = root;
        else{
            root.left = previous;
            previous.right = root;
        }
        previous = root;

        bTreeToClist_(root.right);
    }

    Node bTreeToClist(Node root)
    {
        bTreeToClist_(root);
        head.left = previous;
        previous.right = head;
        return head;
        // return bTreeToClistII(root).right;
    }

    static class Res{
        Node pre;
        Node succ;
    }
    // https://www.geeksforgeeks.org/inorder-predecessor-successor-given-key-bst/
    public static void findPreSuc(Node root, Res p, Res s, int key)
    {
        Node curr = root, pred = null, succ = null;
        while(curr != null){
            if(curr.data < key){
                pred = curr;
                curr = curr.right;
            }else if(curr.data > key){
                succ = curr;
                curr = curr.left;
            }else{
                
                if(curr.left != null){
                    pred = curr.left;
                    while(pred.right != null) pred = pred.right;
                }

                if(curr.right != null){
                    succ = curr.right;
                    while(succ.left != null) succ = succ.left;
                }

                break;
            }
        }

        p.pre = pred;
        s.succ = succ;
    }

    static class LOHelper{
        int lrange;
        int rrange;
        Node parent;
        boolean isLeft;

        LOHelper(int lrange, int rrange, Node parent, boolean isLeft){
            this.lrange = lrange;
            this.rrange = rrange;
            this.parent = parent;
            this.isLeft = isLeft;
        }
    }

    // https://practice.geeksforgeeks.org/problems/convert-level-order-traversal-to-bst/1
    static Node constructBSTFromLevelOrder(int[] lev){
         Queue<LOHelper> que = new LinkedList<>();
         Node root = new Node(lev[0]);
         que.add(new LOHelper(-(int)1e9, root.data, root, true));
         que.add(new LOHelper(root.data, (int)1e9, root, false));
         int idx = 1;
         while(que.size() > 0 && idx < lev.length){
             LOHelper l = que.remove();

             int ele = lev[idx];
             if(ele > l.lrange && ele < l.rrange){
                 Node nn = new Node(ele);
                 if(l.isLeft) l.parent.left = nn;
                 else l.parent.right = nn;
                 idx++;

                 que.add(new LOHelper(l.lrange, ele, nn, true));
                 que.add(new LOHelper(ele, l.rrange, nn, false));
             }
         }

         return root;
    }

    static class LOHelp{
        Node lrange;
        Node rrange;
        boolean isLeft;

        LOHelp(Node lrange, Node rrange, boolean isLeft){
            this.lrange = lrange;
            this.rrange = rrange;
            this.isLeft = isLeft;
        }
    }

    static Node constructBSTFromLevelOrderII(int[] lev){
        Queue<LOHelp> que = new LinkedList<>();
        Node root = new Node(lev[0]);
        que.add(new LOHelp(new Node(-(int)1e9), root, true));
        que.add(new LOHelp(root, new Node((int)1e9), false));
        int idx = 1;
        while(que.size() > 0 && idx < lev.length){
            LOHelp l = que.remove();

            int ele = lev[idx];
            if(ele > l.lrange.data && ele < l.rrange.data){
                Node nn = new Node(ele);
                if(l.isLeft) l.rrange.left = nn;
                else l.lrange.right = nn;
                idx++;

                que.add(new LOHelp(l.lrange, nn, true));
                que.add(new LOHelp(nn, l.rrange, false));
            }
        }

        return root;
   }

    // https://www.geeksforgeeks.org/connect-leaves-doubly-linked-list/
    static Node leafHead = null, prev = null;
    static Node connectLeavesAsDLL1(Node root){
        if(root == null) return null;

        if(root.left == null && root.right == null){
            if(leafHead == null)
                leafHead = root;
            else{
                prev.right = root;
                root.left = prev;
            }
            prev = root;
            return null;
        }

        root.left = connectLeavesAsDLL1(root.left);
        root.right = connectLeavesAsDLL1(root.right);
        return root;
   }

   //upr vale link me leaves remove b krne h pr niche jo question me isme ni krne
   static void connectLeavesAsDLL(Node root){
        if(root == null) return;

        if(root.left == null && root.right == null){
            if(leafHead == null)
                leafHead = root;
            else{
                prev.right = root;
                root.left = prev;
            }
            prev = root;
            return;
        }

        connectLeavesAsDLL(root.left);
        connectLeavesAsDLL(root.right);
   }

    public static void main(String[] args) {
        int[] pre = {12, 13, 10, 14, 21, 24, 15, 22, 23};
        int[] in = {13, 12, 21, 14, 24, 10, 22, 15, 23};
        Node root = construct2(pre, in, 0, pre.length - 1, 0, in.length - 1);
        // System.out.println(burningTree(root, 14));

        // int[] lev = {7, 4, 12, 3, 6, 8, 1, 5, 10};
        // Node root = constructBSTFromLevelOrder(lev);
        // Node root = constructBSTFromLevelOrderII(lev);
        // display(root);
        connectLeavesAsDLL(root);
        Node itr = leafHead;
        while(itr != null){
            System.out.print(itr.data + " ");
            itr = itr.right;
        }
    }
}