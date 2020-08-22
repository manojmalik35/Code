import java.util.Stack;

public class leet230 {

    public class TreeNode {
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

    int ans = -1;
    int kth = 0;

    boolean kthSmallest_(TreeNode root) {
        if (root == null) return false;

        if (kthSmallest_(root.left)) return true;

        if (--kth == 0) {
            ans = root.val;
            return true;
        }

        if (kthSmallest_(root.right)) return true;

        return false;
    }

    public int kthSmallest(TreeNode root, int k) {
        kth = k;
        kthSmallest_(root);
        return ans;
    }

    void pushAllLeft(TreeNode node, Stack<TreeNode> st) {
        while (node != null) {
            st.push(node);
            node = node.left;
        }
    }

    public int kthSmallestII(TreeNode root, int k) {
        Stack<TreeNode> st = new Stack<>();
        pushAllLeft(root, st);
        while(--k != 0){
            TreeNode rv = st.pop();
            pushAllLeft(rv.right, st);
        }

        return st.peek().val;
    }
}