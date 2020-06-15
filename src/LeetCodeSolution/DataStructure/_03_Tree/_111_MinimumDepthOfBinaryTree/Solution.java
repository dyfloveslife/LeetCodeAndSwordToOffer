package LeetCodeSolution.DataStructure._03_Tree._111_MinimumDepthOfBinaryTree;

/*
 * 二叉树的最小深度
 *
 * 题目描述：
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 思路：
 * 1. 注意：对于二叉树 [1, 2] 来说，它的最小深度为 2；
 * 2. 因为求的是所经过的节点数量；
 * 3. 还是采用之前的求最大深度的方法，只不过该题需要多一个判断条件；
 * 4. 叶子节点的定义是：如果当前节点没有左右孩子，也就是左右孩子都为 null，那么当前节点就是叶子节点；
 * 5. 如果当前节点的左右孩子都为空，则返回 1；
 * 6. 如果当前节点的左右孩子其中有一个为空，则返回不为空的孩子节点的深度；
 * 7. 如果当前节点的左右孩子都不为空，则返回左右孩子较小深度的节点值。
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

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);

        if (leftDepth == 0 || rightDepth == 0) {
            return leftDepth + rightDepth + 1;
        }
        return Math.min(leftDepth, rightDepth) + 1;
    }
}
