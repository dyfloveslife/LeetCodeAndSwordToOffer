package SwordToOfferSolution._28_SymmetricalBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 对称的二叉树
 *
 * 题目描述：
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 思路一：递归
 * 1. 二叉树要满足对称，则需要以下是三个条件，对于当前任意两个对称的节点：
 *    1.1) 这两个对称的节点的值相同；
 *    1.2) 左节点的左孩子等于右节点的右孩子，即 root1.left == root2.right；
 *    1.3) 左节点的右孩子等于右节点的左孩子，即 root1.right == root2.left。
 *             root1                          root2
 *             /     \                       /     \
 *     root1.left  root1.right      root2.left  root2.right
 * 思路二：BFS
 * 1. 首先将当前节点的 left 和 right 放进队列中；
 * 2. 然后将 left 的左孩子与 right 的右孩子放进队列；
 * 3. 然后将 left 的右孩子与 right 的左孩子放进队列；
 * 4. 每次从队列中取出两个节点，并比较这两个节点的值：
 *    如果都为空，则继续循环；
 *    如果其中有一个为空，则返回 false；
 *    如果两个节点的值不相等，则返回 false。
 * 5. 最后将左节点的左孩子和右节点的右孩子放进队列，再将左节点的右孩子和右节点的左孩子放进队列。
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
    public boolean isSymmetrical(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root.left, root.right);
    }

    public boolean process(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return process(root1.left, root2.right) && process(root1.right, root2.left);
    }

    // BFS
    public boolean isSymmetrical2(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 从队列中取出两个节点，紧接着进行比较
                TreeNode left = queue.poll();
                TreeNode right = queue.poll();
                if (left == null && right == null) {
                    continue;
                }
                if (left == null || right == null) {
                    return false;
                }
                if (left.val != right.val) {
                    return false;
                }

                queue.offer(left.left);
                queue.offer(right.right);

                queue.offer(left.right);
                queue.offer(right.left);
            }
        }
        return true;
    }
}
