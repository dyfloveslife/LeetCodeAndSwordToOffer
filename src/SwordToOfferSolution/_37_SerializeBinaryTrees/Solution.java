package SwordToOfferSolution._37_SerializeBinaryTrees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化二叉树
 * 思路：
 *
 * 序列化：
 * 1. 先序遍历二叉树，如果当前节点不为空，则采用 “节点_” 的形式记录；如果当前节点为空，则采用 “#_” 记录；
 *  1.1 用 “#” 占据空位置的目的就是防止二叉树节点有相同值的情况下造成的歧义。
 *  1.2 用 “_” 的目的是为了区分每个节点的值，如节点 12 和 3，节点 1 和 23。
 * 反序列化：
 * 1. 将字符串用 “_” 进行分割，保存到数组中；
 * 2. 将数组中的每个值都入队；
 * 3. 采用先序遍历进行反序列化。
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    // 序列化：将二叉树序列化成字符串
    String Serialize(TreeNode root) {
        if (root == null) return "#_";
        String res = root.val + "_";
        res += Serialize(root.left);
        res += Serialize(root.right);
        return res;
    }

    // 将字符串逐个添加到队列中
    TreeNode Deserialize(String str) {
        String[] values = str.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i != values.length; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    // 反序列化：每次从队列中弹出一个值进行判断
    private TreeNode reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) return null;

        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = reconPreOrder(queue);
        root.right = reconPreOrder(queue);
        return root;
    }
}
