package SwordToOfferSolution._32_01_PrintTreeFromTopToBottom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 从上到下打印二叉树（不分行）
 *
 * 题目描述：
 * 从上往下打印出二叉树的每个结点，同一层的结点按照从左到右的顺序打印。
 *
 * 思路：
 * 1. 从上到下遍历二叉树，就是 BFS；
 * 2. 用队列保存节点，用链表保存输出节点的值；
 * 3. 当队列不为空的时候，取队首元素；
 * 4. 如果队首元素为空则跳过，否则将队首元素的左右孩子加入到队列中。
 */
public class Solution {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return list;
    }
}
