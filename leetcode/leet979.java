public class leet979 {

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

    int totalCoins = 0;

    int distributeCoins_(TreeNode root) {
        if (root == null)
            return 0;

        int leftDefOrGain = distributeCoins_(root.left);// left deficit or gain
        int rightDefOrGain = distributeCoins_(root.right);

        totalCoins += Math.abs(leftDefOrGain) + Math.abs(rightDefOrGain);

        return root.val - 1 + leftDefOrGain + rightDefOrGain;
    }

    public int distributeCoins(TreeNode root) {
        if (distributeCoins_(root) != 0)
            return -1;
        return totalCoins;
    }
}