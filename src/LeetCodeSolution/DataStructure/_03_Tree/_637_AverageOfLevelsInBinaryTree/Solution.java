package LeetCodeSolution.DataStructure._03_Tree._637_AverageOfLevelsInBinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 二叉树的层平均值
 *
 * 题目描述：
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组.
 *
 * 思路：
 * 1. 直接使用 BFS 即可；
 * 2. 需要注意的是：由于总和可能会超过 int 的最大值，因此可以使用 double 类型的 sum 存储总和。
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

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            // 用 double 类型存储每次的总和，如果用 int 存储的话，它们的和有可能溢出
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(sum / size);
        }
        return res;
    }
}
