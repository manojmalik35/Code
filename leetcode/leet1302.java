public class leet1302 {

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

    void findSum(TreeNode root, int[] sum, int depth) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            if (depth > sum[0]) {
                sum[0] = depth;
                sum[1] = root.val;
            } else if (depth == sum[0])
                sum[1] += root.val;

        }

        findSum(root.left, sum, depth + 1);
        findSum(root.right, sum, depth + 1);
    }

    public int deepestLeavesSum(TreeNode root) {
        int[] sum = { 0, root.val };
        // sum[0] = maxDepth, sum[1] = sumOfDeepestLeaves
        findSum(root, sum, 0);
        return sum[1];
    }

    // ==========================================

    int height(TreeNode root){
        if(root == null) return -1;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    
    int findSum(TreeNode root, int h){
        if(root == null) return 0;
        
        if(h == 0) return root.val;
        int lsum = findSum(root.left, h - 1);
        int rsum = findSum(root.right, h - 1);
        return lsum + rsum;
    }
    
    public int deepestLeavesSum2(TreeNode root) {
        int h = height(root);
        return findSum(root, h);
    }

    // ===========================================

    int sum = 0;
    void findSum(TreeNode root, int level, int h){
        if(root == null) return;
        
        if(level == h) sum += root.val;
        findSum(root.left, level + 1, h);
        findSum(root.right, level + 1, h);
    }
    
    public int deepestLeavesSum3(TreeNode root) {
        int h = height(root);
        findSum(root, 0, h);
        return sum;
    }
}