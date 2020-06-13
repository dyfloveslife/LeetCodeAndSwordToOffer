package LeetCodeSolution.DataStructure._03_Tree._124_BinaryTreeMaximumPathSum;

/*
 * 二叉树中的最大路径和
 *
 * 题目描述：
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 思路：
 * 1. 思路与之前的方法相同，也是利用递归的返回值，来计算结果；
 * 2. 不过该题需要注意的是：由于求的是最大路径和，并且节点还含有负数，因此在递归返回的时候，如果返回值是负数，则舍弃；
 * 3. 每次在递归返回的时候，需要加上当前节点的值。
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

    // 注意，这里不能设置为 0，
    // 因为有可能二叉树中的节点都是负数，那么在最后返回的时候，显然不能用 0 作为返回值
    private int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);

        return res;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 舍弃负数，如果 dfs 的返回值是负数，则将会重新计算路径和
        int leftSize = Math.max(0, dfs(root.left));
        int rightSize = Math.max(0, dfs(root.right));

        res = Math.max(res, leftSize + rightSize + root.val);

        return Math.max(leftSize, rightSize) + root.val;
    }
}
