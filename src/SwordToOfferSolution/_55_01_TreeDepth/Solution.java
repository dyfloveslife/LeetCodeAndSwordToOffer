package SwordToOfferSolution._55_01_TreeDepth;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 求二叉树的深度
 *
 * 题目描述：
 * 输入一棵二叉树的根结点，求该树的深度。
 * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 *
 * 思路：
 * 1. 即求左子树或右子树的深度，再加 1 即可。
 * 2. 如果当前节点既有左子树，又有右子树，则当前节点的深度就是左右子树深度的最大值再加 1，加的这个 1 就是当前节点。
 * 3. 迭代的方式：
 *      BFS + Queue
 *      DFS + Stack
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

    // BFS + Queue
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return level;
    }

    // DFS + Stack 这里使用的是系统栈
    int maxLevel = 0;

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        dfs(root, 1);
        return maxLevel;
    }

    public void dfs(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level > maxLevel) {
            maxLevel = level;
        }
        dfs(root.left, level + 1);
        dfs(root.right, level + 1);
    }

    // 递归
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
