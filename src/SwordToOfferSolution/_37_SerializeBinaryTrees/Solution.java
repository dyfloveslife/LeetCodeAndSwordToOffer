package SwordToOfferSolution._37_SerializeBinaryTrees;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 序列化二叉树
 *
 * 题目描述：
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 思路：
 * 序列化：
 * 1. 先序遍历二叉树，如果当前节点不为空，则采用 “节点_” 的形式记录；如果当前节点为空，则采用 “#_” 记录；
 *  1.1 用 “#” 占据空位置的目的就是防止二叉树节点有相同值的情况下造成的歧义。
 *  1.2 用 “_” 的目的是为了区分每个节点的值，如节点 12 和 3，节点 1 和 23。
 * 反序列化：
 * 1. 将字符串用 “_” 进行分割，保存到数组中；
 * 2. 将数组中的每个值都入队；
 * 3. 采用先序遍历进行反序列化。
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

    // 序列化：使用先序遍历，将二叉树序列化成字符串
    public String serialize(TreeNode root) {
        StringBuilder sb = mySerialize(root, new StringBuilder());
        return sb.toString();
    }

    public StringBuilder mySerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#_");
            return null;
        }

        sb.append(root.val).append("_");
        mySerialize(root.left, sb);
        mySerialize(root.right, sb);
        return sb;
    }

    // 反序列化：将字符串逐个添加到队列中
    public TreeNode deserialize(String str) {
        String[] values = str.split("_");
        Queue<String> queue = new LinkedList<>();
        for (String value : values) {
            queue.offer(value);
        }
        return myDeserialize(queue);
    }

    // 反序列化：每次从队列中弹出一个值进行判断
    // 使用先序遍历
    public TreeNode myDeserialize(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = myDeserialize(queue);
        root.right = myDeserialize(queue);
        return root;
    }
}
