public class leet99{

    /**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
    public:
        TreeNode* x = NULL;
        TreeNode* y = NULL;
        TreeNode* prev = NULL;
        bool recoverBST(TreeNode* root){
    
            if(root == NULL)
                return false;
    
            bool res = false;
    
            res = res || recoverBST(root->left);
    
            if(prev != NULL && root->val < prev->val){
                y = root;
                if(x == NULL)
                    x = prev;
                else
                    return true;
            }
    
            prev = root;
            res = res || recoverBST(root->right);
            return res;
        }
        void recoverTree(TreeNode* root) {
            x = NULL; y = NULL; prev = NULL;
            recoverBST(root);
            int temp = x->val;
            x->val = y->val;
            y->val = temp;
        }
    };
}