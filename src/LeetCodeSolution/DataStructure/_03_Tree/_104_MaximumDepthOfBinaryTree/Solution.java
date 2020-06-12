package LeetCodeSolution.DataStructure._03_Tree._104_MaximumDepthOfBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 二叉树的最大深度
 *
 * 题目描述：
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 思路一：递归
 * 1. 当前二叉树的最大深度 = max(左子树的最大深度, 右子树的最大深度) + 1，这个 1 就是当前节点；
 *
 * 思路二：迭代
 * 1. 迭代的话就使用层序遍历，每当遍历到一层的时候，就将最大深度加 1。
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

    // 递归
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 迭代
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res++;
        }
        return res;
    }
}
