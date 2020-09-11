package LeetCodeSolution.DataStructure._03_Tree._543_DiameterOfBinaryTree;

/*
 * 二叉树的直径
 *
 * 题目描述：
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 *
 * 思路：
 * 1. 要知道 dfs 函数的返回值的含义，当左孩子为空的时候，说明左孩子给父节点返回来的是 0；
 * 2. 当左孩子不为空的时候，我就继续让左孩子的左孩子去跑 dfs，最后给我返回来的就是 dfs 返回的结果再加上 1；
 * 3. 这个 1 就是我当前节点的值，然后再更新 res；
 * 4. 最后 return max 是为了求左右孩子中最长的路径。
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

    public int diameterOfBinaryTree(TreeNode root) {
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

        int left = dfs(root.left);
        int right = dfs(root.right);
        res = Math.max(res, left + right);

        // 注意，这里让求的是【直径长度】，所以和边有关，因此需要加 1
        return Math.max(left, right) + 1;
    }
}
