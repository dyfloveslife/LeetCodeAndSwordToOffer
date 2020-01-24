package SwordToOfferSolution._55_02_BalancedBinaryTree;

/*
 * 平衡二叉树
 *
 * 题目描述：
 * 输入一棵二叉树的根结点，判断该树是不是平衡二叉树。
 * 如果某二叉树中任意结点的左右子树的深度相差不超过 1，那么它就是一棵平衡二叉树。
 *
 * 思路 1：
 * 后序遍历二叉树节点，同时统计当前节点的深度；
 *
 * 思路 2：
 * 1. 由于递归可以访问当前节点三次，所以使用递归；
 * 2. 先判断当前节点的左子树是否平衡，并将高度记录下来，右子树也做相同的操作；
 * 3. 然后当前节点将收集到的信息进行判断，从而判断整棵树是否是平衡二叉树。
 *
 * 树型 DP
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
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            isBalanced = false;
        }

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

    // ※ 思路二
    public static class ReturnData {
        boolean isB;
        int h;

        ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }

    // 主方法
    public static boolean isB(TreeNode head) {
        return process(head).isB;
    }

    public static ReturnData process(TreeNode head) {
        if (head == null) {
            return new ReturnData(true, 0);
        }

        ReturnData leftData = process(head.left);
        if (!leftData.isB) {
            return new ReturnData(false, 0);
        }
        ReturnData rightData = process(head.right);
        if (!rightData.isB) {
            return new ReturnData(false, 0);
        }
        if (Math.abs(leftData.h - rightData.h) > 1) {
            return new ReturnData(false, 0);
        }
        return new ReturnData(true, Math.max(leftData.h, rightData.h) + 1);
    }
}
