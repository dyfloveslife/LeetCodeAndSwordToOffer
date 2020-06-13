package LeetCodeSolution.DataStructure._03_Tree._226_InvertBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 翻转二叉树
 *
 * 题目描述：
 * 翻转一棵二叉树。
 *
 * 思路一：
 * 1. 使用递归的话，需要确定递归终止的条件，也就是如果当前节点为 null 的话，则递归会返回；
 * 2. 交换的顺序是：从根节点开始，交换它的左右子树，然后递归的去交换左右的孩子。
 * 3. 这里的交换顺序需要想明白，这和二叉树的镜像不同；
 * 4. 注意：对于递归的理解，我们仅考虑当前是如何解决的就好，不用考虑整体是怎么做的，让递归过程去跑整体。
 *
 * 思路二：
 * 1. 使用迭代的方式，也就是 BFS + 队列的形式；
 * 2. 每次将需要交换的节点入队即可。
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    // 迭代，BFS
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return root;
    }
}
