package NTrees;
import java.util.*;
public class btree{

    static class Node{
        int data;
        Node left = null;
        Node right = null;
        Node(int data){
            this.data = data;
        }
    }

    static int idx = 0;
    static Node construct(int[] arr){
        if(idx == arr.length || arr[idx] == -1){
            idx++;
            return null;
        }
        Node root = new Node(arr[idx]);
        idx++;

        root.left = construct(arr);
        root.right = construct(arr);

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

    static List<List<Integer>> pathSumII(Node root, int tar){
        if(root == null){
            return new LinkedList<>();
        }
        
        if(root.left == null && root.right == null && tar == root.data){
            List<List<Integer>> base = new LinkedList<>();
            List<Integer> b = new LinkedList<>();
            b.add(root.data);
            base.add(b);
            return base;
        }

        List<List<Integer>> mans = new LinkedList<>();
        List<List<Integer>> lans = pathSumII(root.left, tar - root.data);
        List<List<Integer>> rans = pathSumII(root.right, tar - root.data);

        for(List<Integer> l : lans){
            l.add(0, root.data);
            mans.add(l);
        }

        for(List<Integer> r : rans){
            r.add(0, root.data);
            mans.add(r);
        }

        return mans;
    }

    static int maxLeaftoLeafSum = Integer.MIN_VALUE;//humara ans isme hoga
    //y vo style h return kuchh or impact kisi or pe jse diameter kiya tha
    static int leafToLeafMaxSum(Node root){

        if(root == null)
            return 0;

        int lm = leafToLeafMaxSum(root.left);//left se leaf tk ka max sum
        int rm = leafToLeafMaxSum(root.right);//right se leaf tk ka max sum

        if(root.left != null && root.right != null){
            maxLeaftoLeafSum = Math.max(maxLeaftoLeafSum, lm + rm + root.data);
            return Math.max(lm, rm) + root.data;
        }
        
        return (root.left == null ? rm : lm) + root.data;
    }

    static int maxNodetoNodeSum = (int)-1e8;

    static int nodeToNodeSum(Node root){

        if(root == null)
            return (int)-1e8;

        int lmax = nodeToNodeSum(root.left);
        int rmax = nodeToNodeSum(root.right);

        int lrmax = Math.max(lmax, rmax) + root.data;

        maxNodetoNodeSum = Math.max(maxNodetoNodeSum, lmax + rmax + root.data);
        maxNodetoNodeSum = Math.max(maxNodetoNodeSum, Math.max(lrmax, root.data));
        
        return Math.max(lrmax, root.data);
    }

    static Node lca_node = null;

    static boolean LCA(Node root, int data1, int data2){

        if(root == null)
            return false;

        boolean selfDone = root.data == data1 || root.data == data2;

        boolean left = LCA(root.left, data1, data2);
        if(lca_node != null) return true;

        boolean right = LCA(root.right, data1, data2);
        if(lca_node != null) return true;

        if((left && right) || (left && selfDone) || (right && selfDone)){
            lca_node = root;
            return true;
        }

        return left || right || selfDone;
    }

    static int pathSumIII(Node root, int tar, HashMap<Integer, Integer> fmap, int prefixSum){

        if(root == null)
            return 0;

        prefixSum += root.data;

        int count = fmap.getOrDefault(prefixSum - tar, 0);
        fmap.put(prefixSum, fmap.getOrDefault(prefixSum, 0) + 1);

        count += pathSumIII(root.left, tar, fmap, prefixSum);
        count += pathSumIII(root.right, tar, fmap, prefixSum);

        if(fmap.get(prefixSum) == 1)
            fmap.remove(prefixSum);
        else
            fmap.put(prefixSum, fmap.get(prefixSum) - 1);

        return count;
    }

    static Node x = null, y = null, prev = null;
    static boolean recoverBST(Node root){

        if(root == null)
            return false;

        boolean res = false;

        res = res || recoverBST(root.left);

        if(prev != null && root.data < prev.data){
            y = root;
            if(x == null)
                x = prev;
            else
                return true;
        }

        prev = root;
        res = res || recoverBST(root.right);
        return res;
    }

    static Node constructBSTFromPreOrder(int[] pre, int psi, int pei){//O(n^2)
        if(psi > pei)
            return null;

        Node root = new Node(pre[psi]);
        int x = psi + 1;
        while(x < pre.length && pre[x] < pre[psi]){
            x++;
        }

        root.left = constructBSTFromPreOrder(pre, psi + 1, x - 1);
        root.right = constructBSTFromPreOrder(pre, x, pei);
        return root;
    }

    static int index = 0;
    static Node constructBSTFromPreOrder(int[] pre, int min, int ele, int max){//O(n)

        if(ele < min || ele > max || index == pre.length)
            return null;

        Node root = new Node(pre[index]);
        index++;

        if(index < pre.length)
            root.left = constructBSTFromPreOrder(pre, min, pre[index], root.data);

        if(index < pre.length)
            root.right = constructBSTFromPreOrder(pre, root.data, pre[index], max);

        return root;
    }

    static int HeightBSTFromPreOrder(int[] pre, int min, int ele, int max){//O(n)

        if(ele < min || ele > max || index == pre.length)
            return 0;

        int node = pre[index];
        index++;

        int lh = 0, rh = 0;
        if(index < pre.length)
            lh = HeightBSTFromPreOrder(pre, min, pre[index], node);

        if(index < pre.length)
            rh = HeightBSTFromPreOrder(pre, node, pre[index], max);

        return Math.max(lh, rh) + 1;
    }

    static int leftLevel = -1;
    static void leftView(Node root, int level){//DFS

        if(root == null)
            return;

        if(level > leftLevel){
            System.out.print(root.data + " ");
            leftLevel = level;
        }

        leftView(root.left, level + 1);
        leftView(root.right, level + 1);
    }

    static void leftAndRightView(Node root){//BFS
        LinkedList<Node> que = new LinkedList<>();
        que.addLast(root);
        while(que.size() > 0){
            int size = que.size();
            for(int i = 0; i < size; i++){
                Node rem = que.removeFirst();

                if(i == 0 || i == size - 1)
                    System.out.print(rem.data + " ");

                // if(i == size - 1)//for right view
                //     System.out.print(rem.data + " ");

                if(rem.left != null)
                    que.addLast(rem.left);
                if(rem.right != null)
                    que.addLast(rem.right);
            }
            System.out.println();
        }
    }

    static int leftWidth(Node root){

        if(root == null)
            return -1;

        int lw = leftWidth(root.left);
        int rw = leftWidth(root.right);

        return Math.max(lw + 1, rw - 1);
    }

    static int rightWidth(Node root){

        if(root == null)
            return -1;

        int lw = rightWidth(root.left);
        int rw = rightWidth(root.right);

        return Math.max(lw - 1, rw + 1);
    }

    static int width(Node root, boolean isLeft){

        if(root == null)
            return -1;

        int left = width(root.left, isLeft) + (isLeft ? 1 : -1);
        int right = width(root.right, isLeft) + (isLeft ? -1 : 1);

        return Math.max(left, right);
    }

    static class verticalPair{
        Node node;
        int level;

        verticalPair(Node node, int level){
            this.node = node;
            this.level = level;
        }
    }

    static void verticalTraversal(Node root){
        int lw = leftWidth(root);
        int rw = rightWidth(root);
        ArrayList<Integer>[] arr = new ArrayList[lw + rw + 1];
        for(int i = 0; i < arr.length; i++)
            arr[i] = new ArrayList<>();

        LinkedList<verticalPair> que = new LinkedList<>();
        que.addLast(new verticalPair(root, lw));
        while(que.size() > 0){
            verticalPair rem = que.removeFirst();

            arr[rem.level].add(rem.node.data);

            if(rem.node.left != null)
                que.addLast(new verticalPair(rem.node.left, rem.level - 1));

            if(rem.node.right != null)
                que.addLast(new verticalPair(rem.node.right, rem.level + 1));
        }

        for(int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
        
    }

    static void verticalTraversal2(Node root){
        int lw = width(root, true);
        int rw = width(root, false);
        ArrayList<Integer>[] arr = new ArrayList[lw + rw + 1];
        for(int i = 0; i < arr.length; i++)
            arr[i] = new ArrayList<>();

        LinkedList<Object[]> que = new LinkedList<>();
        Object[] src = {root, lw};
        que.addLast(src);
        while(que.size() > 0){
            Object[] rem = que.removeFirst();

            int level = (int)rem[1];
            Node node = (Node)rem[0];
            arr[level].add(node.data);

            if(node.left != null)
                que.addLast(new Object[]{node.left, level - 1});

            if(node.right != null)
                que.addLast(new Object[]{node.right, level + 1});
        }

        for(int i = 0; i < arr.length; i++)
            System.out.println(arr[i]);
        
    }

    static int mg = 0;//maxGap
    static void findMaxGap(Node root, int x, int y){
        
        if(root == null)
            return;
        
        if(y - x > mg)
            mg = y - x;
        
        findMaxGap(root.left, x - 1, y + 1);
        findMaxGap(root.right, x + 1, y + 1);
    }

    static void fill(Node root, int x, int y, ArrayList<Integer>[] arr){
        
        if(root == null)
            return;
        
        int gap = y - x;
        arr[gap].add(root.data);
        
        fill(root.left, x - 1, y + 1, arr);
        fill(root.right, x + 1, y + 1, arr);
    }

	static void diagonalTraversal(Node root) {
        findMaxGap(root, 0, 0);
		ArrayList<Integer>[] arr = new ArrayList[mg + 1];
        for(int i = 0; i < arr.length; i++)
            arr[i] = new ArrayList<>();
        
        fill(root, 0, 0, arr);
        
        for(int i = 0; i < arr.length; i++){
            ArrayList<Integer> t = arr[i];
            if(t.size() > 0){
                for(int j = 0; j < t.size(); j++)
                    System.out.print(t.get(j) + " ");
                System.out.println();
            }
        }
    }

    static void verticalTraversalSum(Node root){
        int lw = width(root, true);
        int rw = width(root, false);
        int[] arr = new int[lw + rw + 1];

        LinkedList<Object[]> que = new LinkedList<>();
        que.addLast(new Object[]{root, lw});
        while(que.size() > 0){
            Object[] rem = que.removeFirst();

            Node node = (Node)rem[0];
            int x = (int)rem[1];

            arr[x] += node.data;

            if(node.left != null)
                que.addLast(new Object[]{node.left, x - 1});
            if(node.right != null)
                que.addLast(new Object[]{node.right, x + 1});
        }
        
        for(int i : arr)
            System.out.println(i);
    }

    static void diagonalTraversalSum(Node root){
        int lw = width(root, true);
        int[] arr = new int[lw + 1];

        LinkedList<Object[]> que = new LinkedList<>();
        que.addLast(new Object[]{root, lw});
        while(que.size() > 0){
            Object[] rem = que.removeFirst();

            Node node = (Node)rem[0];
            int x = (int)rem[1];

            arr[x] += node.data;

            if(node.left != null)
                que.addLast(new Object[]{node.left, x - 1});
            if(node.right != null)
                que.addLast(new Object[]{node.right, x});
        }
        
        for(int i = arr.length - 1; i >= 0; i--)
            System.out.println(arr[i]);
    }
    static void verticalTraversalSum2(Node root, Node head){//Optimized

        if(root == null)
            return;
    
        head.data += root.data;
        
        if(root.left != null && head.left == null){
            head.left = new Node(0);
            head.left.right = head;
        }

        verticalTraversalSum2(root.left, head.left);    

        if(root.right != null && head.right == null){
            head.right = new Node(0);
            head.right.left = head;            
        }
        verticalTraversalSum2(root.right, head.right);    
    }

    static void diagonalTraversalSum2(Node root, Node head){
        if(root == null)
            return;

        head.data += root.data;

        if(root.left != null && head.left == null){
            head.left = new Node(0);
            head.left.right = head;
        }

        diagonalTraversalSum2(root.left, head.left);
        diagonalTraversalSum2(root.right, head);
    }

    static Node constructLO(int[] arr){
        Node[] nodes = new Node[arr.length];
		for (int i = 0; i < nodes.length; i++) {
			if (arr[i] != -1) {
				nodes[i] = new Node(arr[i]);

				if (i > 0) {
					int pi = (i - 1) / 2;

					if (i == 2 * pi + 1) {
						nodes[pi].left = nodes[i];
					} else {
						nodes[pi].right = nodes[i];
					}
				}
			}
		}
        Node root = nodes[0];
        return root;
    }

    static int maxLen = 1;
    static void maxLengthConsecutiveSequence(Node root, int potentialVal, int currLen){

        if(root == null)
            return;

        if(root.data == potentialVal){
            currLen++;
            maxLen = Math.max(maxLen, currLen);
        }
        else
            currLen = 1;

        maxLengthConsecutiveSequence(root.left, root.data + 1, currLen);
        maxLengthConsecutiveSequence(root.right, root.data + 1, currLen);
    }

    static int maxLengthConsecutiveSequence2(Node root, int potentialVal, int currLen){

        if(root == null)
            return 0;

        if(root.data == potentialVal)
            currLen++;
        else
            currLen = 1;

        int max1 = maxLengthConsecutiveSequence2(root.left, root.data + 1, currLen);
        int max2 = maxLengthConsecutiveSequence2(root.right, root.data + 1, currLen);

        return Math.max(currLen, Math.max(max1, max2));
    }

    static int cam = 0;
    static int btc(Node root){

        if(root == null)
            return 3;

        int left = btc(root.left);
        int right = btc(root.right);

        if(left == 1 || right == 1){
            cam++;
            return 2;
        }

        if(left == 2 || right == 2)
            return 3;

        return 1;
    }

    static int binaryTreeCameras(Node root){
        if(root.left == null && root.right == null)
            return 1;
        cam = 0;
        int ans = btc(root);
        if(ans == 1)
            cam++;
        return cam;
    }

    static Node leftsRightmost(Node node, Node curr) {
		while(node.right != null && node.right != curr)
            node = node.right;
        
        return node;
    }
    
    static void morrisIn(Node root){
        Node curr = root;
        while(curr != null){
            Node next = curr.left;
            if(next == null){
                System.out.print(curr.data + " ");
                curr = curr.right;
                continue;
            }
            
            next = leftsRightmost(next, curr);
            if(next.right == null){
                next.right = curr;
                curr = curr.left;
            }else{
                next.right = null;
                System.out.print(curr.data + " ");
                curr = curr.right;
            }
        }
    }

    static void morrisPre(Node root){
        Node curr = root;
        while(curr != null){
            Node next = curr.left;
            if(next == null){
                System.out.print(curr.data + " ");
                curr = curr.right;
                continue;
            }
            
            next = leftsRightmost(next, curr);
            if(next.right == null){
                next.right = curr;
                System.out.print(curr.data + " ");
                curr = curr.left;
            }else{
                next.right = null;
                curr = curr.right;
            }
        }
    }

    static void morrisPost(Node root){
        Node curr = root;
        ArrayList<Integer> post = new ArrayList<>();
        while(curr != null){
            Node next = curr.right;
            if(next == null){
                post.add(curr.data);
                curr = curr.left;
                continue;
            }
            
            while(next.left != null && next.left != curr)
                next = next.left;
            
            if(next.left == null){
                next.left = curr;
                post.add(curr.data);
                curr = curr.right;
            }else{
                next.left = null;
                curr = curr.left;
            }
        }

        for(int i = post.size() - 1; i >= 0; i--)
            System.out.print(post.get(i) + " ");
    }

    

    public static void main(String[] args) {

        int[] pre = {8, 3, 1, 10, 6, 4, 7, 14, 13};
        int[] in = {1, 3, 8, 4, 6, 7, 10, 13, 14};
        Node root = construct2(pre, in, 0, pre.length - 1, 0, in.length - 1);
        // display(root);
        // List<List<Integer>> res = pathSumII(root, 22);
        // for(List<Integer> s : res)
        //     System.out.println(s);
        // leafToLeafMaxSum(root);
        // System.out.println(maxLeaftoLeafSum);
        // nodeToNodeSum(root);
        // System.out.println(maxNodetoNodeSum);
        // LCA(root, 5, 4);
        // System.out.println(lca_node.data);
        // HashMap<Integer, Integer> fmap = new HashMap<>();
        // fmap.put(0, 1);
        // System.out.println(pathSumIII(root, 8, fmap, 0));
        // recoverBST(root);
        // int temp = x.data;
        // x.data = y.data;
        // y.data = temp;

        // int[] pre = {8, 5, 1, 7, 10, 12};
        // Node root = constructBSTFromPreOrder(pre, 0, pre.length - 1);
        // Node root = constructBSTFromPreOrder(pre, Integer.MIN_VALUE, pre[index], Integer.MAX_VALUE);
        // display(root);
        // System.out.println(HeightBSTFromPreOrder(pre, Integer.MIN_VALUE, pre[index], Integer.MAX_VALUE));
        // leftAndRightView(root);
        // verticalTraversal2(root);
        // diagonalTraversal(root);
        // verticalTraversalSum(root);
        // diagonalTraversalSum(root);
        // Node head = new Node(0);
        // verticalTraversalSum2(root, head);
        // diagonalTraversalSum2(root, head);
        // Node itr = head;
        // while(itr.left != null)
        //     itr = itr.left;

        // while(itr != null){
        //     System.out.println(itr.data);
        //     itr = itr.right;
        // }

        //for diagonal Traversal
        // while(itr.right != null)
        //     itr = itr.right;

        // while(itr != null){
        //     System.out.println(itr.data);
        //     itr = itr.left;
        // }


        // int[] pre = {1, 2, 2, 3, 4, 5, 6, 7, 7, 8};
        // int[] in = {2, 1, 3, 2, 5, 7, 6, 8, 7, 4};
        // Node root = construct2(pre, in, 0, pre.length - 1, 0, in.length - 1);
        // display(root);
        // maxLengthConsecutiveSequence(root, root.data, 1);
        // System.out.println(maxLen);
        // System.out.println(maxLengthConsecutiveSequence2(root, root.data, 0));
        // morrisPost(root);
    }
}