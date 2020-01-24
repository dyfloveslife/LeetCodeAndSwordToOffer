package Other.BasicAlgorithm._28_IsCompleteBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断一棵树是否是完全二叉树
 *
 * 思路：
 * 1. 按层遍历二叉树，如果当前节点有右孩子而没有左孩子，则一定不是二叉树；
 * 2. 如果当前节点左右孩子不齐全，即只有左孩子而没有右孩子，或者当前节点没有左右孩子的话，
 *    则当前节点后面的所有节点都必须是叶节点。
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

    public boolean isCBT(TreeNode head) {
        if (head == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        // isLeaf 表示后面所有的节点是否都是叶节点
        boolean isLeaf = false;
        TreeNode left = null;
        TreeNode right = null;
        // 将 head 入队，当队列慢时，不会发生异常，而是返回 false
        // 而 add 方法会产生异常
        queue.offer(head);
        while (!queue.isEmpty()) {
            // 出队
            head = queue.poll();
            left = head.left;
            right = head.right;
            // left == null && right != null 表示情况 1
            // isLeaf && (left != null || right != null) 表示如果开启了叶节点这一阶段，
            // 则必须满足后面的节点的左右孩子必须都是空
            if ((isLeaf && (left != null || right != null)) || (left == null && right != null)) {
                return false;
            }
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
            // 左右两个孩子不齐全的话，开启后面都是叶节点的阶段
            if (left == null || right == null) {
                isLeaf = true;
            }
        }
        return true;
    }
}
