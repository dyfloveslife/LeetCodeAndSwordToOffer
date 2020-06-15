package LeetCodeSolution.DataStructure._03_Tree._404_SumOfLeftLeaves;

/*
 * 左叶子之和
 *
 * 题目描述：
 * 计算给定二叉树的所有左叶子之和。
 *
 * 思路：
 * 1. 既然需要求的是所有的左叶子之和，那么我们可以先从求所有节点之和开始，慢慢的过度到求左叶子之和；
 * 2. 所有节点之和 -> 所有叶子节点之和 -> 所有左叶子之和。
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

    // 求二叉树中所有节点之和
    public int allNodeSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftSum = allNodeSum(root.left);
        int rightSum = allNodeSum(root.right);

        return leftSum + rightSum + root.val;
    }

    // 求所有叶子节点之和
    public int allLeavesNodeSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leaves = 0;
        // 叶子节点
        if (root.left == null && root.right == null) {
            leaves += root.val;
        }
        int leftSum = allLeavesNodeSum(root.left);
        int rightSum = allLeavesNodeSum(root.right);

        return leftSum + rightSum + leaves;
    }

    // 再求所有左叶子之和，需要通过一个布尔值，来确定它是左叶子还是右叶子
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return dfs(root, false);
    }

    private int dfs(TreeNode root, boolean flag) {
        if (root == null) {
            return 0;
        }

        int leaves = 0;
        if (flag && root.left == null && root.right == null) {
            leaves += root.val;
        }

        int leftSum = dfs(root.left, true);
        int rightSum = dfs(root.right, false);

        return leaves + leftSum + rightSum;
    }

    // 或者也可以这样做，判断当前节点的左孩子是不是叶子节点，
    // 如果是的话，则就用左孩子的值再加上递归处理右孩子
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (isLeaf(root.left)) {
            return root.left.val + sumOfLeftLeaves1(root.right);
        }

        return sumOfLeftLeaves1(root.left) + sumOfLeftLeaves1(root.right);
    }

    private boolean isLeaf(TreeNode node) {
        if (node == null) {
            return false;
        }

        return node.left == null && node.right == null;
    }
}