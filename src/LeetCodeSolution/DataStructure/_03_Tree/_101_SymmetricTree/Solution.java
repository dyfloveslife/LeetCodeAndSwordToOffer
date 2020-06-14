package LeetCodeSolution.DataStructure._03_Tree._101_SymmetricTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 对称二叉树
 *
 * 题目描述：
 * 给定一个二叉树，检查它是否是镜像对称的，对称轴在根节点上。
 *
 * 思路：
 * 1. 递归或迭代；
 * 2. 递归直接看代码；
 * 3. 对于迭代，首先将当前节点的左右孩子都加入到队列中，然后在弹出的时候进行判断；
 * 4. 判断完之后，需要将下一层节点加入到队列中，需要注意的是加入的顺序以及需要成对的加入；
 * 5. 即 leftNode.left 与 rightNode.right 一对，leftNode.right 与 rightNode.left 一对。
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return process(root.left, root.right);
    }

    private boolean process(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }

        if (t1.val != t2.val) {
            return false;
        }
        // 分别判断 t1 的左子树和 t2 的右子树是否相等，
        // 判断 t1 的右子树和 t2 的左子树是否相等
        return process(t1.left, t2.right) && process(t1.right, t2.left);
    }

    // 迭代
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode leftNode = queue.poll();
                TreeNode rightNode = queue.poll();
                if (leftNode == null && rightNode == null) {
                    continue;
                }
                if (leftNode == null || rightNode == null) {
                    return false;
                }
                if (leftNode.val != rightNode.val) {
                    return false;
                }

                queue.offer(leftNode.left);
                queue.offer(rightNode.right);

                queue.offer(leftNode.right);
                queue.offer(rightNode.left);
            }
        }
        return true;
    }
}
