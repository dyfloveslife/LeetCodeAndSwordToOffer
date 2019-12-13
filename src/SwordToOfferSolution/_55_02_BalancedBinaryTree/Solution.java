package SwordToOfferSolution._55_02_BalancedBinaryTree;

/*
 * 判断一棵树是不是平衡二叉树
 *
 * 思路：
 * 后序遍历二叉树节点，同时统计当前节点的深度。
 *
 */
public class Solution {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 方法一：
    // 在后序遍历到当前节点的时候，该节点的左右子树就已经遍历了
    // 从底往上遍历，每个节点只需要遍历一次
    private boolean isBalanced = true;
    public boolean isBalanced(TreeNode root) {
        getDepth(root);
        return isBalanced;
    }

    private int getDepth(TreeNode root) {
        if (root == null || !isBalanced) {
            return 0;
        }
        // 左
        int leftDepth = getDepth(root.left);
        // 右
        int rightDepth = getDepth(root.right);
        // 根
        if (Math.abs(leftDepth - rightDepth) > 1) {
            isBalanced = false;
        }

        //return leftDepth > rightDepth ? leftDepth + 1 : rightDepth + 1;
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 方法二：
    // 在求二叉树深度的基础上，求出左右子树的深度，
    // 缺点是一个节点会被重复遍历多次
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }

        int leftDepth = getTreeDepth(root.left);
        int rightDepth = getTreeDepth(root.right);

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        return isBalanced2(root.left) && isBalanced2(root.right);
    }

    private int getTreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(getTreeDepth(root.left), getTreeDepth(root.right)) + 1;
    }
}
