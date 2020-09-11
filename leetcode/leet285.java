public class leet285 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {//lintcode 448
        TreeNode curr = root, succ = null;
        while(curr != null){
            if(curr.val < p.val)
                curr = curr.right;
            else if(curr.val > p.val){
                succ = curr;
                curr = curr.left;
            }else{

                if(curr.right != null){
                    succ = curr.right;
                    while(succ.left != null) succ = succ.left;
                }
                break;
            }
        }

        return succ;
    }
}