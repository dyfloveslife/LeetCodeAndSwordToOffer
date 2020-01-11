package SwordToOfferSolution._55_01_TreeDepth;

/*
 * 求二叉树的深度
 *
 * 题目描述：
 * 输入一棵二叉树的根结点，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 思路：
 * 1. 即求左子树或右子树的深度，在加 1 即可。
 * 2. 如果当前节点既有左子树，又有右子树，则当前节点的深度就是左右子树深度的最大值再加 1，加的这个 1 就是当前节点。
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

        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
