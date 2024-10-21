package SwordToOfferSolution._37_SerializeBinaryTrees;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 序列化二叉树
 *
 * 题目描述：
 * 请实现两个函数，分别用于序列化和反序列化二叉树。
 *
 * 思路一：递归+先序遍历
 * 0. 序列化后的字符串其实是二叉树的层序遍历结果，但是通常使用的先序、中序、后序、层序遍历记录二叉树的信息不完整，
 *    也就是可能对应多种二叉树的结果。题目要求是可逆的，因此序列化的字符串需要携带完整的二叉树信息，需要拥有单独
 *    表示二叉树的能力；
 * 序列化：
 * 1. 先序遍历二叉树，如果当前节点不为空，则采用 “节点_” 的形式记录；如果当前节点为空，则采用 “#_” 记录；
 *  1.1 用 “#” 占据空位置的目的就是防止二叉树节点有相同值的情况下造成的歧义。
 *  1.2 用 “_” 的目的是为了区分每个节点的值，如节点 12 和 3，节点 1 和 23。
 * 反序列化：
 * 1. 将字符串用 “_” 进行分割，保存到数组中；
 * 2. 将数组中的每个值都入队（或者将队列用数组和索引代替）；
 * 3. 采用先序遍历进行反序列化。
 *
 * 思路二：
 * 1. 使用层次遍历，然后需要使用队列。
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
    public String serialize1(TreeNode root) {
        if (root == null) {
            return "null";
        }

        StringBuilder sb = mySerialize(root, new StringBuilder());
        return sb.toString();
    }

    private StringBuilder mySerialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#_");
            return sb;
        }

        sb.append(root.val).append("_");
        mySerialize(root.left, sb);
        mySerialize(root.right, sb);
        return sb;
    }

    // 反序列化：将字符串逐个添加到队列中
    int index = 0;

    public TreeNode deserialize1(String str) {
        String[] values = str.split("_");
        return myDeserialize(values);
    }

    // 反序列化：每次从队列中弹出一个值进行判断
    // 使用先序遍历
    private TreeNode myDeserialize(String[] values) {
        if (values[index].equals("#")) {
            index++;
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(values[index++]));
        root.left = myDeserialize(values);
        root.right = myDeserialize(values);
        return root;
    }

    public String serialize2(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                res.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                res.append("null,");
            }
        }
        // 删除最后一个逗号
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    public TreeNode deserialize2(String data) {
        if (data.equals("[]")) {
            return null;
        }

        // [1, data.length()-1)，左闭右开
        String[] values = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.left);
            }
            i++;
            if (!values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
}
