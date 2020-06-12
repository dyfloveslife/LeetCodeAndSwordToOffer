package LeetCodeSolution.DataStructure._03_Tree._110_BalancedBinaryTree;

/*
 * 平衡二叉树
 *
 * 题目描述：
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 一棵高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过 1。
 *
 * 思路：
 * 1. 计算当前节点的左右子树的最大高度，相减之后，如果大于 1，则说明不是平衡二叉树；
 * 2. 如果小于 1，则递归的去判断当前节点的左右子树是否满足平衡二叉树。
 */
public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftDepth = maxDepth1(root.left);
        int rightDepth = maxDepth1(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }

        return isBalanced1(root.left) && isBalanced1(root.right);
    }

    private int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth1(root.left), maxDepth1(root.right)) + 1;
    }

    // 当然，也可以在找最大深度的过程中，判断左右子树的高度差
    private boolean isBalanced = true;

    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }

        maxDepth2(root);
        return isBalanced;
    }

    private int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = maxDepth2(root.left);
        int rightDepth = maxDepth2(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            isBalanced = false;
        }

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
