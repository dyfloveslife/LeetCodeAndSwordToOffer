package SwordToOfferSolution._32_02_PrintTreesInLines;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 分行从上到下打印二叉树
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

    public ArrayList<ArrayList<Integer>> printTreesInLines(TreeNode root) {
        // 存放结果
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return res;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 存放每行列表
            ArrayList<Integer> list = new ArrayList<>();
            int count = queue.size();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                // 对应某节点只有一个孩子或没有孩子的情况
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            // 每遍历完一行，就将该行加入到 res 中
            if (list.size() != 0) {
                res.add(list);
            }
        }
        return res;
    }
}
