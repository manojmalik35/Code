import java.util.*;
public class bt{

    static class Node{
        int data;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
    }

    static Node construct(int[] arr){
        Stack<Node> st = new Stack<>();
        Node root = null;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == -1){
                st.pop();
            }else{
                Node nn = new Node(arr[i]);
                if(st.size() == 0)
                    root = nn;
                else{
                    Node top = st.peek();
                    if(top.left == null)
                        top.left = nn;
                    else
                        top.right = nn;
                }
                st.push(nn);
            }
        }
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

    static int size(Node root){
        if(root == null)
            return 0;
    
        int ls = size(root.left);
        int rs = size(root.right);
        return ls + rs + 1;
    }

    static int sum(Node root){
        if(root == null)
            return 0;

        int ls = sum(root.left);
        int rs = sum(root.right);
        return ls + rs + root.data;
    }

    static int max(Node root){
        if(root == null)
            return Integer.MIN_VALUE;

        int lm = max(root.left);
        int rm = max(root.right);
        return Math.max(root.data, Math.max(lm, rm));
    }

    static int height(Node root){
        if(root == null)
            return 0;

        int lh = height(root.left);
        int rh = height(root.right);
        return Math.max(lh, rh) + 1;
    }

    static boolean find(Node root, int val){
        if(root == null)
            return false;

        if(root.data == val)
            return true;

        boolean lr = find(root.left, val);
        if(lr)
            return true;

        boolean rr = find(root.right, val);
        if(rr)
            return true;
        
        return false;
    }

    static ArrayList<Node> node2rootPath(Node root, int val){
        if(root == null)
            return null;

        if(root.data == val){
            ArrayList<Node> bres = new ArrayList<>();
            bres.add(root);
            return bres;
        }

        ArrayList<Node> lres = node2rootPath(root.left, val);
        if(lres != null){
            lres.add(root);
            return lres;
        }

        ArrayList<Node> rres = node2rootPath(root.right, val);
        if(rres != null){
            rres.add(root);
            return rres;
        }

        return null;
    }

    static Node removeLeaves(Node root){
        if(root == null)
            return null;

        if(root.left == null && root.right == null)
            return null;

        root.left = removeLeaves(root.left);
        root.right = removeLeaves(root.right);
        return root;
    }

    static void printSingleChild(Node root){
        if(root == null)
            return;

        if(root.left != null && root.right == null)
            System.out.print(root.left.data + " ");
        
        if(root.left == null && root.right != null)
            System.out.print(root.right.data + " ");

        printSingleChild(root.left);
        printSingleChild(root.right);
    }

    static void root2leafsuminRange(Node root, int lo, int hi, int ssf, String psf){
        if(root == null)
            return;

        if(root.left == null && root.right == null){
            ssf += root.data;
            psf += root.data;
            if(ssf >= lo && ssf <= hi)
                System.out.println(psf);
            return;
        }

        root2leafsuminRange(root.left, lo, hi, ssf + root.data, psf + root.data + " ");
        root2leafsuminRange(root.right, lo, hi, ssf + root.data, psf + root.data + " ");
    }

    static void transformLD(Node root){//left duplicates
        if(root == null)
            return;

        Node nn = new Node(root.data);
        nn.left = root.left;
        root.left = nn;

        transformLD(root.left.left);
        transformLD(root.right);
    }

    static Node reverseTransformLD(Node root){
        if(root == null)
            return null;
        root.left = reverseTransformLD(root.left.left);
        root.right = reverseTransformLD(root.right);
        return root;
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

    static Node construct3(int[] post, int[] in, int psi, int pei, int isi, int iei){
        if(psi > pei || isi > iei)
            return null;

        Node root = new Node(post[pei]);

        int i = iei;
        while(i >= isi && in[i] != post[pei])
            i--;
        int rhs = iei - i;
        root.left = construct3(post, in, psi, pei - rhs - 1, isi, i - 1);
        root.right = construct3(post, in, pei - rhs, pei - 1, i + 1, iei);
        return root;
    }

    static class Pair{
        Node node = null;
        int state;

        Pair(Node node, int state){
            this.node = node;
            this.state = state;
        }
    }    

    static void DFTraversals(Node root){
        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 0));
        while(st.size() > 0){
            Pair t = st.peek();

            if(t.node == null){
                st.pop();
                continue;
            }

            if(t.state == 0){
                pre.add(t.node.data);
                t.state++;
                st.push(new Pair(t.node.left, 0));
            }else if(t.state == 1){
                in.add(t.node.data);
                t.state++;
                st.push(new Pair(t.node.right, 0));
            }else{
                post.add(t.node.data);
                st.pop();
            }
        }

        System.out.println(pre);
        System.out.println(in);
        System.out.println(post);

    }

    static int diameter1(Node root){//O(n^2)
        if(root == null)
            return 0;

        int lh = height(root.left);
        int rh = height(root.right);
        int factor = lh + rh + 1;

        int ld = diameter1(root.left);
        int rd = diameter1(root.right);

        return Math.max(factor, Math.max(ld, rd));
    }

    static class DiaPair{
        int ht;
        int dia;
    }

    static DiaPair diameter2(Node root){
        if(root == null){
            DiaPair bp = new DiaPair();
            bp.ht = 0;
            bp.dia = 0;
            return bp;
        }

        DiaPair lp = diameter2(root.left);
        DiaPair rp = diameter2(root.right);
        DiaPair mp = new DiaPair();

        mp.ht = Math.max(lp.ht, rp.ht) + 1;
        mp.dia = Math.max(lp.ht + rp.ht + 1, Math.max(lp.dia, rp.dia));
        return mp;
    }

    static int dia = 0;
    static int diameter3Height(Node root){//return and impact
        if(root == null)
            return 0;

        int lh = diameter3Height(root.left);
        int rh = diameter3Height(root.right);

        dia = Math.max(dia, lh + rh + 1);

        return Math.max(lh, rh) + 1;
    }

    static class BalPair{
        int ht;
        boolean isBal;
    }

    static BalPair isBalanced(Node root){
        if(root == null){
            BalPair bp = new BalPair();
            bp.ht = 0;
            bp.isBal = true;
            return bp;
        }

        BalPair mp = new BalPair();
        BalPair lp = isBalanced(root.left);
        BalPair rp = isBalanced(root.right);

        mp.isBal = lp.isBal && rp.isBal && Math.abs(lp.ht - rp.ht) <= 1;
        mp.ht = Math.max(lp.ht, rp.ht) + 1;
        return mp;
    }

    static boolean isBal = true;
    static int isBalancedHeight(Node root){//return something and impact on another thing
        if(root == null)
            return 0;

        int lh = isBalancedHeight(root.left);
        int rh = isBalancedHeight(root.right);

        if(Math.abs(lh - rh) > 1)
            isBal = false;

        return Math.max(lh, rh) + 1;
    }

    static class BSTPair{
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        boolean isBST;
        Node lbstroot = null;
        int lbstsize = 0;
    }
    static BSTPair isBST(Node root){
        if(root == null){
            BSTPair bp = new BSTPair();
            bp.isBST = true;
            return bp;
        }
        BSTPair lp = isBST(root.left);
        BSTPair rp = isBST(root.right);

        BSTPair mp = new BSTPair();
        mp.max = Math.max(root.data, Math.max(lp.max, rp.max));
        mp.min = Math.min(root.data, Math.min(lp.min, rp.min));
        mp.isBST = lp.isBST && rp.isBST && root.data >= lp.max && root.data <= rp.min;
        return mp;
    }

    static BSTPair largestBST(Node root){
        if(root == null){
            BSTPair bp = new BSTPair();
            bp.isBST = true;
            return bp;
        }

        BSTPair lp = largestBST(root.left);
        BSTPair rp = largestBST(root.right);

        BSTPair mp = new BSTPair();
        mp.max = Math.max(root.data, Math.max(lp.max, rp.max));
        mp.min = Math.min(root.data, Math.min(lp.min, rp.min));
        mp.isBST = lp.isBST && rp.isBST && root.data >= lp.max && root.data <= rp.min;
        if(mp.isBST){
            mp.lbstroot = root;
            mp.lbstsize = lp.lbstsize + rp.lbstsize + 1;
        }else{
            if(lp.lbstsize > rp.lbstsize){
                mp.lbstsize = lp.lbstsize;
                mp.lbstroot = lp.lbstroot;
            }else{
                mp.lbstsize = rp.lbstsize;
                mp.lbstroot = rp.lbstroot;
            }
        }
        return mp;
    }

    static void printkdown(Node troot, int k){
        if(troot == null)
            return;

        if(k == 0){
            System.out.print(troot.data + " ");
            return;
        }

        printkdown(troot.left, k - 1);
        printkdown(troot.right, k - 1);
    }

    static void printkdown2(Node troot, int k, Node stopper){
        if(troot == null || troot == stopper || k < 0)
            return;

        if(k == 0){
            System.out.print(troot.data + " ");
            return;
        }

        printkdown2(troot.left, k - 1, stopper);
        printkdown2(troot.right, k - 1, stopper);
    }

    static void printkfar(Node root, int val, int k){
        ArrayList<Node> ntr = node2rootPath(root, val);

        Node stopper = null;
        for(int i = 0; i < ntr.size(); i++){
            Node troot = ntr.get(i);
            printkdown2(troot, k - i, stopper);
            stopper = troot;
        }
    }

    public static void main(String[] args) {
        // int[] arr = {50, 25, 12, -1, 37, 30, -1, 40, -1, -1, -1, 75, 62, 60, -1, 70, -1, -1, 87, -1, -1, -1};    
        // Node root = construct(arr);
        // display(root);
        // System.out.println(size(root));
        // System.out.println(sum(root));
        // System.out.println(max(root));
        // System.out.println(height(root));
        // System.out.println(find(root, 350));
        // ArrayList<Node> res = node2rootPath(root, 70);
        // for(Node n : res)
        //     System.out.print(n.data + " ");
        // System.out.println();
        // removeLeaves(root);
        // display(root);
        // printSingleChild(root);
        // root2leafsuminRange(root, 150, 250, 0, "");
        // transformLD(root);
        // reverseTransformLD(root);
        // display(root);

        int[] pre = {50, 25, 12, 37, 30, 51, 75, 62, 60, 70, 87, 80, 90};
        int[] in = {12, 25, 30, 37, 51, 50, 60, 62, 70, 75, 80, 87, 90};
        int[] post = {12, 30, 37, 25, 70, 62, 87, 75, 50};
        // int[] pre = {50, 25, 12, 87, 91, 105};//for isBalanced
        // int[] in = {12, 25, 50, 87, 91, 105};
        Node root = construct2(pre, in, 0, pre.length - 1, 0, in.length - 1);
        // Node root = construct3(post, in, 0, post.length - 1, 0, in.length - 1);
        // display(root);
        // DFTraversals(root);
        // System.out.println(diameter1(root));
        // DiaPair dp = diameter2(root);
        // System.out.println(dp.ht + " " + dp.dia);
        // diameter3Height(root); System.out.println(dia);
        // BalPair bp = isBalanced(root);
        // System.out.println(bp.ht + " " + bp.isBal);
        // isBalancedHeight(root); System.out.println(isBal);
        // BSTPair bp = isBST(root);
        // System.out.println(bp.isBST);
        // BSTPair bp = largestBST(root);
        // System.out.println(bp.lbstroot.data + " " + bp.lbstsize);
        // printkdown(root, 3);
        printkfar(root, 62, 1);
        
    }
}