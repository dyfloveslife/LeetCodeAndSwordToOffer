package LeetCodeSolution.DataStructure._03_Tree._298_BinaryTreeLongestConsecutiveSequence;

/*
 * 二叉树最长连续序列
 *
 * 题目描述：
 * 给你一棵指定的二叉树，请你计算它最长连续序列路径的长度。
 * 该路径，可以是从某个初始结点到树中任意结点，通过「父 - 子」关系连接而产生的任意路径。
 * 这个最长连续的路径，必须从父结点到子结点，反过来是不可以的。
 *
 * 思路：
 * 1. 在递归的时候比较左右节点与当前节点的值，如果左节点或右节点减一之后与当前节点相等，则当前最长连续路径的长度加 1；
 * 2. “如果左节点或右节点减一之后与当前节点相等” 说明左右节点比当前节点大一个数，此时就需要将长度加 1。
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

    private int res = 0;

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        process(root, 1);
        return res;
    }

    private void process(TreeNode root, int sum) {
        if (root == null) {
            return;
        }

        res = Math.max(res, sum);

        if (root.left != null) {
            process(root.left, root.left.val - 1 == root.val ? sum + 1 : 1);
        }
        if (root.right != null) {
            process(root.right, root.right.val - 1 == root.val ? sum + 1 : 1);
        }
    }
}
