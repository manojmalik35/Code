import java.util.*;
public class leet662 {

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

    class pair {
        TreeNode node;
        int index;

        pair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<pair> que = new ArrayDeque<>();
        que.add(new pair(root, 0));
        int width = 0;
        while(que.size() > 0){
            int sz = que.size();
            int fi = que.peek().index;
            int li = 0;
            while(sz-- > 0){
                pair p = que.remove();

                li = p.index;
                if(p.node.left != null)
                    que.add(new pair(p.node.left, 2 * p.index));
                if(p.node.right != null)
                    que.add(new pair(p.node.right, 2 * p.index + 1));
            }
            width = Math.max(width, li - fi + 1);
        }

        return width;
    }

    private int widthOfBinaryTree(TreeNode node, int level, int index, List<Integer> startIndex) {
        if (node == null) return 0;
        if (startIndex.size() == level) {
            startIndex.add(index);
        }
        
        int curWidth = index - startIndex.get(level) + 1;
        int leftWidth = widthOfBinaryTree(node.left, level + 1, index * 2, startIndex);
        int rightWidth = widthOfBinaryTree(node.right, level + 1, index * 2 + 1, startIndex);
        return Math.max(curWidth, Math.max(leftWidth, rightWidth));
    }
    
    public int widthOfBinaryTreeII(TreeNode root) {
        return widthOfBinaryTree(root, 0, 0, new ArrayList<>());
    }
}