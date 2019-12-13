package SwordToOfferSolution._55_01_TreeDepth;

/*
 * 求二叉树的深度
 * 思路：
 * 即求左子树或右子树的深度，在加 1 即可。
 */
public class Solution {
    class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }
}
