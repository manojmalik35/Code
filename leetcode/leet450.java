public class leet450 {

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

    int max(TreeNode root){
        while(root.right != null)
            root = root.right;

        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;

        if(key < root.val)
            root.left = deleteNode(root.left, key);
        else if(key > root.val)
            root.right = deleteNode(root.right, key);
        else{
            
            if(root.left == null || root.right == null)
                return root.left == null ? root.right : root.left;

            int lmax = max(root.left);
            root.val = lmax;
            root.left = deleteNode(root.left, lmax);
        }

        return root;
    }
}