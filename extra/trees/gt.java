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


    static Node construct(int[] arr){
        Node root = null;
        Stack<Node> st = new Stack<>();
        for(int i = 0; i < arr.length; i++){
            int data = arr[i];
            if(data == -1){
                st.pop();
            }else{
                Node nn = new Node(data);
                if(st.size() == 0)
                    root = nn;
                else{
                   Node top = st.peek();
                   top.children.add(nn); 
                }
                st.push(nn);
            }
        }
        return root;
    }

    static void display(Node root){
        System.out.print(root.data + " -> ");
        for(Node child : root.children)
            System.out.print(child.data + ", ");
        System.out.println(".");

        for(Node child : root.children)
            display(child);
        
    }

    static boolean find(Node root, int data){
        if(root.data == data)
            return true;
        
        for(Node child : root.children){
            boolean rres = find(child, data);
            if(rres)
                return true;
        }

        return false;
    }

    static int max(Node root){
        int max = root.data;
        for(Node child : root.children){
            int rres = max(child);
            max = Math.max(max, rres);
        }
        return max;
    }

    static int min(Node root){
        int min = root.data;
        for(Node child : root.children){
            int rres = min(child);
            min = Math.min(min, rres);
        }
        return min;
    }

    static int size(Node root){
        int count = 0;
        for(Node child : root.children){
            int rres = size(child);
            count += rres;
        }
        return count + 1;
    }

    static int height(Node root){
        int height = 0;
        for(Node child : root.children){
            int rres = height(child);
            height = Math.max(height, rres);
        }
        return height + 1;
    }

    static ArrayList<Node> node2rootPath(Node root, int val){
        ArrayList<Node> mres = new ArrayList<>();

        if(root.data == val){
            mres.add(root);
            return mres;
        }

        for(Node child : root.children){
            ArrayList<Node> rres = node2rootPath(child, val);
            if(rres.size() != 0){
                rres.add(root);
                return rres;
            }
        }

        return mres;
    }

    static int lca(Node root, int val1, int val2){
        if(find(root, val1) == false || find(root, val2) == false)
            return -1;

        ArrayList<Node> l1 = node2rootPath(root, val1);
        ArrayList<Node> l2 = node2rootPath(root, val2);
        int i = l1.size() - 1;
        int j = l2.size() - 1;
        while(i >= 0 && j >= 0 && l1.get(i).data == l2.get(j).data){
            i--;
            j--;
        }

        return l1.get(i + 1).data;
    }

    static void levelOrder(Node root){
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);
        while(q.size() > 0){
            Node rem = q.removeFirst();

            System.out.print(rem.data + " ");

            for(Node child : rem.children)
                q.addLast(child);
        }
        System.out.println();
    }

    static void levelOrderLinewise1(Node root){
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);
        q.addLast(null);
        while(q.size() > 1){
            Node rem = q.removeFirst();

            if(rem == null){
                System.out.println();
                q.addLast(null);
            }
            else{
                System.out.print(rem.data + " ");
                for(Node child : rem.children)
                    q.addLast(child);
            }
        }
        System.out.println();
    }

    static void levelOrderLinewise2(Node root){
        LinkedList<Node> cq = new LinkedList<>();
        LinkedList<Node> nq = new LinkedList<>();
        cq.addLast(root);
        while(cq.size() > 0){
            Node rem = cq.removeFirst();

            System.out.print(rem.data + " ");

            for(Node child : rem.children)
                nq.addLast(child);

            if(cq.size() == 0){
                cq = nq;
                nq = new LinkedList<>();
                System.out.println();
            }
        }
    }

    static void levelOrderLinewise3(Node root){
        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);
        while(q.size() > 0){
            int size = q.size();
            while(size-- > 0){
                Node rem = q.removeFirst();

                System.out.print(rem.data + " ");

                for(Node child : rem.children)
                    q.addLast(child);
            }
            System.out.println();
        }
    }

    static void levelOrderZigzag(Node root){
        boolean flag = true;
        LinkedList<Node> st1 = new LinkedList<>();
        LinkedList<Node> st2 = new LinkedList<>();
        st1.addFirst(root);
        while(st1.size() > 0){
            Node rem = st1.removeFirst();

            System.out.print(rem.data + " ");

            if(flag){
                for(int i = rem.children.size() - 1; i >= 0; i--)
                    st2.addFirst(rem.children.get(i));
            }else{
                for(Node child : rem.children)
                    st2.addFirst(child);
            }

            if(st1.size() == 0){
                flag = !flag;
                st1 = st2;
                st2 = new LinkedList<>();
                System.out.println();
            }
        }
    }

    static class HeapMover{
        int pred = -1;
        int succ = -1;
        int prev = -1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int floor = Integer.MIN_VALUE;
        int ceil = Integer.MAX_VALUE;
        boolean find = false;
    }

    static void multisolver(Node root, int data, HeapMover hm){

        if(hm.find && hm.prev == data)
            hm.succ = root.data;

        if(hm.find == false && root.data == data){
            hm.pred = hm.prev;
            hm.find = true;
        }


        if(root.data < data && root.data > hm.floor)
            hm.floor = root.data;

        if(root.data > data && root.data < hm.ceil)
            hm.ceil = root.data;

        hm.max = Math.max(hm.max, root.data);
        hm.min = Math.min(hm.min, root.data);

        hm.prev = root.data;
        for(Node child : root.children)
            multisolver(child, data, hm);

    }

    static int kthLargest(Node root, int k, HeapMover hm){
        int res = Integer.MAX_VALUE;
        while(k-- > 0){
            hm.floor = Integer.MIN_VALUE;
            multisolver(root, res, hm);
            res = hm.floor;
        }
        return res;
    }

    static int kthSmallest(Node root, int k, HeapMover hm){
        int res = Integer.MIN_VALUE;
        while(k-- > 0){
            hm.ceil = Integer.MAX_VALUE;
            multisolver(root, res, hm);
            res = hm.ceil;
        }
        return res;
    }

    static Node linearize(Node root){
        if(root.children.size() == 0){
            return root;
        }

        Node lastTail = linearize(root.children.get(root.children.size() - 1));

        for(int i = root.children.size() - 2; i >= 0; i--){
            Node slTail = linearize(root.children.get(i));

            Node last = root.children.get(i + 1);

            slTail.children.add(last);
            root.children.remove(last);
        }

        return lastTail;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
        Node root = construct(arr);
        // display(root);
        // System.out.println(find(root, 75));
        // System.out.println(max(root));
        // System.out.println(min(root));
        // System.out.println(size(root));
        // System.out.println(height(root));
        // ArrayList<Node> res = node2rootPath(root, 140);
        // for(Node n : res)
        //     System.out.print(n.data + " ");
        // System.out.println();

        // System.out.println(lca(root, 120, 110));
        // levelOrder(root);
        // levelOrderLinewise1(root);
        // levelOrderLinewise2(root);
        // levelOrderLinewise3(root);
        // levelOrderZigzag(root);
        // HeapMover hm = new HeapMover();
        // multisolver(root, 80, hm);
        // System.out.println(hm.pred + " " + hm.succ + " " + hm.find);
        // System.out.println(hm.floor + " " + hm.ceil);
        // System.out.println(hm.max + " " + hm.min);
        // System.out.println(kthLargest(root, 5, hm));
        // System.out.println(kthSmallest(root, 4, hm));
        linearize(root);
        display(root);
    }
}