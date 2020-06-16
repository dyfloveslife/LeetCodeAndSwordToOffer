package LeetCodeSolution.DataStructure._03_Tree._671_SecondMinimumNodeInABinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 二叉树中第二小的节点
 *
 * 题目描述：
 * 给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。
 * 如果一个节点有两个子节点的话，那么这个节点的值不大于它的子节点的值。
 * 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。
 * 如果第二小的值不存在的话，输出 -1。
 *
 * 思路：
 * 1. 这里使用 BFS 的方式，将当前出队的节点与根节点进行比较；
 * 2. 不断地求 secondMin 与当前出队的节点值的大小。
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

    public int findSecondMinimunValue(TreeNode root) {
        if (root == null) {
            return -1;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        Integer secondMin = null;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            // 由于 root 节点是整棵树的最小的节点，
            // 因此，每当我出队一个节点的时候，如果它和根节点不相等，
            // 则说明出队的这个节点有可能是第二小的节点，那么我就通过不断更新 secondMin 来得到树种第二小的节点的值
            if (node.val != root.val) {
                secondMin = (secondMin == null) ? node.val : Math.min(secondMin, node.val);
            }
        }
        return secondMin == null ? -1 : secondMin;
    }
}
