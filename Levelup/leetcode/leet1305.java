import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.LinkedList;

public class leet1305 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    static class Pair {
        TreeNode node;
        int state;

        Pair(TreeNode node) {
            this.node = node;
        }
    }

    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        Stack<Pair> st1 = new Stack<>();
        if(root1 != null)
            st1.push(new Pair(root1));
        Stack<Pair> st2 = new Stack<>();
        if(root2 != null)
            st2.push(new Pair(root2));

        boolean itr1 = true, itr2 = true;
        int tree1Data = (int)1e8, tree2Data = (int)1e8;
        while (true) {

            while (itr1 && st1.size() > 0) {
                Pair t = st1.peek();

                if (t.state == 0) {
                    if (t.node.left != null)
                        st1.push(new Pair(t.node.left));
                } else if (t.state == 1) {
                    itr1 = false;
                    tree1Data = t.node.val;
                    if (t.node.right != null)
                        st1.push(new Pair(t.node.right));
                } else{
                    st1.pop();
                    tree1Data = (int)1e8;
                }

                t.state++;
            }

            while (itr2 && st2.size() > 0) {
                Pair t = st2.peek();

                if (t.state == 0) {
                    if (t.node.left != null)
                        st2.push(new Pair(t.node.left));
                } else if (t.state == 1) {
                    itr2 = false;
                    tree2Data = t.node.val;
                    if (t.node.right != null)
                        st2.push(new Pair(t.node.right));
                } else{
                    st2.pop();
                    tree2Data = (int)1e8;
                }
                t.state++;
            }

            if (st1.size() == 0 && st2.size() == 0)
                break;

            if (tree1Data < tree2Data) {
                ans.add(tree1Data);
                itr1 = true;
            } else if (tree1Data > tree2Data) {
                ans.add(tree2Data);
                itr2 = true;
            } else {
                ans.add(tree1Data);
                ans.add(tree2Data);
                itr1 = true;
                itr2 = true;
            }
        }

        return ans;
    }

    void inorder(TreeNode root, List<Integer> list){
        if(root == null) return;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    List<Integer> merge2SortedLists(List<Integer> l1, List<Integer> l2){
        int i = 0, j = 0;
        List<Integer> ans = new ArrayList<>();
        while(i < l1.size() && j < l2.size()){
            int ele1 = l1.get(i);
            int ele2 = l2.get(j);
            if(ele1 < ele2){
                ans.add(ele1);
                i++;
            }else if(ele2 < ele1){
                ans.add(ele2);
                j++;
            }else{
                ans.add(ele1);
                ans.add(ele2);
                i++; j++;
            }
        }

        while(i < l1.size())
            ans.add(l1.get(i++));

        while(j < l2.size())
            ans.add(l2.get(j++));

        return ans;
    }

    public List<Integer> getAllElements2(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        inorder(root1, l1);
        inorder(root2, l2);
        return merge2SortedLists(l1, l2);
    }

    void inorder(TreeNode root, LinkedList<Integer> list){
        if(root == null) return;

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    void merge(TreeNode node, LinkedList<Integer> tree1, List<Integer> merged){
        if(node == null) return;

        merge(node.left, tree1, merged);

        while(tree1.size() > 0 && tree1.getFirst() < node.val)
            merged.add(tree1.removeFirst());

        merged.add(node.val);
        merge(node.right, tree1, merged);
    }

    public List<Integer> getAllElements3(TreeNode root1, TreeNode root2) {
        LinkedList<Integer> tree1 = new LinkedList<>();
        List<Integer> merged = new LinkedList<>();

        inorder(root1, tree1);

        merge(root2, tree1, merged);
        merged.addAll(tree1);
        return merged;
    }
}
