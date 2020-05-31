package SwordToOfferSolution._27_MirrorOfBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 求二叉树的镜像
 *
 * 题目描述：
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 思路：
 * 1. 前序遍历树中的每个节点，如果遍历到的节点有子节点，则交换两个子节点；
 * 2. 当交换完所有非叶节点的左右子节点的时候，就可以得到二叉树的镜像。
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
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        // 将当前节点的左右子树交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // 递归得交换当前节点的左子树
        if (root.left != null) {
            mirrorTree(root.left);
        }

        // 递归得交换当前节点的右子树
        if (root.right != null) {
            mirrorTree(root.right);
        }
        return root;
    }

    // 迭代
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 每次从队列中拿出一个节点，并交换该节点的左右孩子
                TreeNode node = queue.poll();
                TreeNode left = node.left;
                node.left = node.right;
                node.left = left;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        // 处理完之后返回根节点
        return root;
    }
}
