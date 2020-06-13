package LeetCodeSolution.DataStructure._03_Tree._112_PathSum;

/*
 * 路径总和
 *
 * 题目描述：
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 思路：
 * 1. 注意审题，它让求的是从根节点到叶节点的路径总和；
 * 2. 因此可以使用递归的方式，如果当前节点的左右孩子为空，并且当前的目标和已经是 sum 了，则返回 true；
 * 3. 如果不满足上式的话，则递归来到下一层；
 * 4. 需要注意的是，来到下一层的时候，由于已经使用过了当前节点，因此需要减去当前节点的值。
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

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        // 如果来到了叶节点，并且此时的路径和已经等于 sum 了，则返回 true
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
