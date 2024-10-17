package SwordToOfferSolution._32_02_PrintTreesInLines;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 分行从上到下打印二叉树
 *
 * 题目描述：
 * 从上到下按层打印二叉树，同一层的结点按从左到右的顺序打印，每一层打印到一行。
 *
 * 思路：
 * 1. 该题和上一道题的整体思路是一样的，也是使用 BFS 实现；
 * 2. 只不过在遍历当前队列中的元素之前，需要在每一层创建一个 ArrayList；
 * 3. 最后再将每个 ArrayList 添加到最终的结果 list 中进行返回。
 */
public class Solution {
    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(int val) {
            this.val = val;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(list);
        }
        return ans;
    }
}