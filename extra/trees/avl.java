import java.util.*;
public class avl{

    static class Node{
        int data;
        Node left = null;
        Node right = null;
        int ht = 1;
        int bal = 0;

        Node(int data){
            this.data = data;
        }
    }

    static int getht(Node root){
        int lh = root.left != null ? root.left.ht : 0;
        int rh = root.right != null ? root.right.ht : 0;
        return Math.max(lh, rh) + 1;
    }

    static int getBal(Node root){
        int lh = root.left != null ? root.left.ht : 0;
        int rh = root.right != null ? root.right.ht : 0;
        return lh - rh;
    }

    static Node construct(int[] sa, int lo, int hi){
        if(lo > hi)
            return null;

        int mid = (lo + hi) / 2;
        Node root = new Node(sa[mid]);
        root.left = construct(sa, lo, mid - 1);
        root.right = construct(sa, mid + 1, hi);
        root.ht = getht(root);
        root.bal = getBal(root);
        return root;
    }

    static void display(Node root){
        if(root == null)
            return;

        System.out.print(root.left == null ? "." : root.left.data);
        System.out.print(" -> " + root.data + "_" + root.ht + "_" + root.bal + " <- ");
        System.out.println(root.right == null ? "." : root.right.data);

        display(root.left);
        display(root.right);
    }

    static int max(Node root)
    {
        if(root.right == null)
            return root.data;
        else
            return max(root.right);
    }

    static Node leftRotation(Node x){
        Node y = x.right;
        Node t2 = y.left;
        y.left = x;
        x.right = t2;

        x.ht = getht(x);
        x.bal = getBal(x);
        y.ht = getht(y);
        y.bal = getBal(y);

        return y;
    }

    static Node rightRotation(Node x){
        Node y = x.left;
        Node t3 = y.right;
        y.right = x;
        x.left = t3;

        x.ht = getht(x);
        x.bal = getBal(x);
        y.ht = getht(y);
        y.bal = getBal(y);

        return y;
    }

    static Node add(Node root, int data){
        if(root == null){
            Node nn = new Node(data);
            return nn;
        }

        if(data < root.data)
            root.left = add(root.left, data);
        else if(data > root.data)
            root.right = add(root.right, data);

        root.ht = getht(root);
        root.bal = getBal(root);

        if(root.bal > 1){//L type of problem
            if(root.left.bal >= 0)//LL problem
                root = rightRotation(root);
            else{//LR problem
                root.left = leftRotation(root.left);
                root = rightRotation(root);
            }
        }else if(root.bal < -1){//R type of problem
            if(root.right.bal <= 0)//RR problem
                root = leftRotation(root);
            else{//RL problem
                root.right = rightRotation(root.right);
                root = leftRotation(root);
            }
        }   

        return root;
    }

    static Node remove(Node root, int val){
        if(root == null)
            return null;

        if(val < root.data)
            root.left = remove(root.left, val);
        else if(val > root.data)
            root.right = remove(root.right, val);
        else{
            if(root.left == null || root.right == null)
                return root.left == null ? root.right : root.left;
            else{
                int lmax = max(root.left);
                root.data = lmax;
                root.left = remove(root.left, lmax);
            }
        }

        // if(root != null){
            root.ht = getht(root);
            root.bal = getBal(root);
            if(root.bal > 1){//L type of problem
                if(root.left.bal >= 0)//LL problem
                    root = rightRotation(root);
                else{//LR problem
                    root.left = leftRotation(root.left);
                    root = rightRotation(root);
                }
            }else if(root.bal < -1){//R type of problem
                if(root.right.bal <= 0)//RR problem
                    root = leftRotation(root);
                else{//RL problem
                    root.right = rightRotation(root.right);
                    root = leftRotation(root);
                }
            }
        // }

        return root;
    }

    public static void main(String[] args) {
        int[] sa = {12, 25, 37, 50, 62, 75, 87};
        Node root = construct(sa, 0, sa.length - 1);
        // display(root);
        add(root, 30);
        add(root, 28);
        add(root, 29);
        remove(root, 12);
        remove(root, 25);
        remove(root, 37);
        display(root);
    }
}