package LeetCodeSolution.DataStructure._03_Tree._513_FindBottomLeftTreeValue;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 找树左下角的值
 *
 * 题目描述：
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 *
 * 思路：
 * 1. 一般情况下，当我们使用 BFS 的时候，会先将 root.left 入队，然后再将 root.right 入队，这样的话最后来到的是最右侧的节点；
 * 2. 现在反过来即可，让 root.right 先入队，然后再让 root.left 再入队。
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

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                // 注意顺序
                if (root.right != null) {
                    queue.offer(root.right);
                }
                if (root.left != null) {
                    queue.offer(root.left);
                }
            }
        }
        // 最后返回的就是最左下角的节点
        return root.val;
    }
}
