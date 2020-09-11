import java.util.*;
public class leet173 {

    // Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class BSTIterator {

        LinkedList<TreeNode> stack;

        public BSTIterator(TreeNode root) {
            this.stack = new LinkedList<>();
            while (root != null) {
                this.stack.addFirst(root);
                root = root.left;
            }
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode top = this.stack.removeFirst();

            if (top.right != null) {
                this.stack.addFirst(top.right);

                TreeNode itr = this.stack.getFirst().left;
                while (itr != null) {
                    this.stack.addFirst(itr);
                    itr = itr.left;
                }
            }

            return top.val;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return this.stack.size() != 0;
        }
    }

    /**
     * Your BSTIterator object will be instantiated and called as such: BSTIterator
     * obj = new BSTIterator(root); int param_1 = obj.next(); boolean param_2 =
     * obj.hasNext();
     */
}