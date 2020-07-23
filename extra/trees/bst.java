import java.util.*;
public class bst{

    static class Node{
        int data;
        Node left = null;
        Node right = null;

        Node(int data){
            this.data = data;
        }
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

    static Node construct(int[] sa, int lo, int hi){
        if(lo > hi)
            return null;

        int mid = (lo + hi) / 2;
        Node root = new Node(sa[mid]);
        root.left = construct(sa, lo, mid - 1);
        root.right = construct(sa, mid + 1, hi);
        return root;
    }

    static boolean find(Node root, int data){
        if(root == null)
            return false;

        if(root.data == data)
            return true;
        else if(data < root.data)
            return find(root.left, data);
        else
            return find(root.right, data);
    }

    static int max(Node root){
        if(root.right == null)
            return root.data;

        return max(root.right);
    }

    static int min(Node root){
        if(root.left == null)
            return root.data;

        return min(root.left);
    }

    static Node add(Node root, int val){
        if(root == null){
            Node nn = new Node(val);
            return nn;
        }


        if(val < root.data)
            root.left = add(root.left, val);
        else if(val > root.data)
            root.right = add(root.right, val);

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
            if(root.left == null && root.right == null)//leaf
                return null;
            else if(root.left != null && root.right != null){//two children
                root.data = max(root.left);
                remove(root.left, root.data);
            }
            else if(root.left != null || root.right != null)//single child
                return root.left != null ? root.left : root.right;
        }

        return root;
    }

    static int gSum = 0;
    static void transformSOGT(Node root){//sum of greater tree
        if(root == null)
            return;

        transformSOGT(root.right);
        int temp = root.data;
        root.data = gSum;
        gSum += temp;
        transformSOGT(root.left);
    }

    static int lca(Node root, int val1, int val2){
        if(root == null)
            return 0;

        if(root.data < val1 && root.data < val2)
            return lca(root.right, val1, val2);
        else if(root.data > val1 && root.data > val2)
            return lca(root.left, val1, val2);
        else
            return root.data;
    }

    static void targetSumPair1(Node node, int tar, Node root){
        if(node == null)
            return;

        int rm = tar - node.data;
        if(rm > node.data && find(root, rm))
            System.out.println(node.data + " " + rm);

        targetSumPair1(node.left, tar, root);
        targetSumPair1(node.right, tar, root);
    }

    static void filler(Node root, ArrayList<Integer> list){
        if(root == null)
            return;

        filler(root.left, list);
        list.add(root.data);
        filler(root.right, list);
    }

    static void targetSumPair2(Node root, int tar){
        ArrayList<Integer> sorted = new ArrayList<>();
        filler(root, sorted);
        int i = 0, j = sorted.size() - 1;
        while(i < j){
            if(sorted.get(i) + sorted.get(j) < tar)
                i++;
            else if(sorted.get(i) + sorted.get(j) > tar)
                j--;
            else{
                System.out.println(sorted.get(i) + " " + sorted.get(j));
                i++;
                j--;
            }
        }
    }

    static class Euler{
        Node node = null;
        int state = 0;

        Euler(Node node){
            this.node = node;
        }
    }

    static void targetSum3(Node root, int tar){

        Stack<Euler> sst = new Stack<>();
        sst.push(new Euler(root));

        Stack<Euler> rst = new Stack<>();
        rst.push(new Euler(root));

        int c1 = 0, c2 = 0;
        boolean f1 = true, f2 = true;
        while(true){
        
            while(f1 && sst.size() > 0){
                Euler rem = sst.peek();

                if(rem.node == null){
                    sst.pop();
                    continue;
                }

                if(rem.state == 0){
                    rem.state++;
                    sst.push(new Euler(rem.node.left));
                }else if(rem.state == 1){
                    c1 = rem.node.data;
                    rem.state++;
                    sst.push(new Euler(rem.node.right));
                    break;
                }else
                    sst.pop();
            }

            while(f2 && rst.size() > 0){
                Euler rem = rst.peek();

                if(rem.node == null){
                    rst.pop();
                    continue;
                }
                if(rem.state == 0){
                    rem.state++;
                    rst.push(new Euler(rem.node.right));
                }else if(rem.state == 1){
                    c2 = rem.node.data;
                    rem.state++;
                    rst.push(new Euler(rem.node.left));
                    break;
                }else
                    rst.pop();
            }

            if(c1 > c2)
                break;

            if(c1 + c2 < tar){
                f2 = false;
                f1 = true;
            }else if(c1 + c2 > tar){
                f1 = false;
                f2 = true;
            }else{
                System.out.println(c1 + " " + c2);
                f1 = true;
                f2 = true;
            }
        }
    }

    static void intersection2BSTs(Node root1, Node root2){
        
        int v1 = 0, v2 = 0;
        boolean m1 = true, m2 = true;
        Stack<Euler> st1 = new Stack<>();
        Stack<Euler> st2 = new Stack<>();
        st1.push(new Euler(root1));
        st2.push(new Euler(root2));

        while(true){

            while(m1 && st1.size() > 0){
                Euler rem = st1.peek();

                if(rem.node == null){
                    st1.pop();
                    continue;
                }

                if(rem.state == 0){
                    rem.state++;
                    st1.push(new Euler(rem.node.left));
                }else if(rem.state == 1){
                    v1 = rem.node.data;
                    rem.state++;
                    st1.push(new Euler(rem.node.right));
                    break;
                }else
                    st1.pop();

            }

            while(m2 && st2.size() > 0){
                Euler rem = st2.peek();

                if(rem.node == null){
                    st2.pop();
                    continue;
                }

                if(rem.state == 0){
                    rem.state++;
                    st2.push(new Euler(rem.node.left));
                }else if(rem.state == 1){
                    v2 = rem.node.data;
                    rem.state++;
                    st2.push(new Euler(rem.node.right));
                    break;
                }else
                    st2.pop();
            }

            if(st1.size() == 0 || st2.size() == 0)
                break;

            if(v1 < v2){
                m1 = true;
                m2 = false;
            }else if(v1 > v2){
                m1 = false;
                m2 = true;
            }else{
                System.out.println(v1);
                m1 = true;
                m2 = true;
            }

        }
    }

    public static void main(String[] args) {
        // int[] sa = {12, 25, 37, 50, 62, 75, 87};
        // Node root = construct(sa, 0, sa.length - 1);
        // display(root);
        // System.out.println(find(root, 87));
        // System.out.println(max(root));
        // System.out.println(min(root));
        // add(root, 30); add(root, 40); add(root, 60); add(root, 70); add(root, 80); add(root, 90);
        // remove(root, 75);
        // transformSOGT(root);
        // display(root);
        int[] sa = {10, 20, 30, 50, 60, 70, 80};
        Node root = construct(sa, 0, sa.length - 1);
        // add(root, 25); add(root, 35); add(root, 55); add(root, 65);
        // targetSumPair1(root, 125, root);
        // targetSumPair2(root, 125);
        // targetSum3(root, 125);
        int[] sa1 = {10, 25, 36, 40, 65, 70, 90};
        Node root1 = construct(sa1, 0, sa1.length - 1);
        intersection2BSTs(root, root1);
        

    }
}