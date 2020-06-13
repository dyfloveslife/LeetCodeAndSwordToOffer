package LeetCodeSolution.DataStructure._03_Tree._687_LongestUnivaluePath;

/*
 * 最长同值路径
 *
 * 题目描述：
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。
 * 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 *
 * 思路：
 * 1. 注意理解题意：找到路径中每个节点具有相同值的最长路径；
 * 2. 对于当前节点来说，它的左孩字在给它返回最大值之后，需要再判断一下左孩子的节点值是否和当前节点值相同；
 * 3. 如果不同，则就需要将左孩子所返回的最大值置为 0，因为我需要找的是每个节点具有相同值的路径；
 * 4. 同理，对于右孩子也是一样的。
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

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root);

        return res;
    }

    private int dfs(TreeNode root) {
        // 如果当前节点的左右孩子是空的，则不需要给当前节点返回任何值
        if (root.left == null && root.right == null) {
            return 0;
        }

        int leftSize = (root.left == null) ? 0 : dfs(root.left) + 1;
        int rightSize = (root.right == null) ? 0 : dfs(root.right) + 1;

        if (leftSize > 0 && root.left.val != root.val) {
            leftSize = 0;
        }
        if (rightSize > 0 && root.right.val != root.val) {
            rightSize = 0;
        }
        res = Math.max(res, leftSize + rightSize);

        return Math.max(leftSize, rightSize);
    }
}
