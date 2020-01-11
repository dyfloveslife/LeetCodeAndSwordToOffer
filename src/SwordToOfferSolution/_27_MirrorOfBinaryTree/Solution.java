package SwordToOfferSolution._27_MirrorOfBinaryTree;

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
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public void mirrorOfBinaryTree(TreeNode root) {
        if (root == null) {
            return;
        }
        // 说明已经到了叶节点了
        if (root.left == null && root.right == null) {
            return;
        }

        // 交换操作
        TreeNode tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
        if (root.left != null) {
            mirrorOfBinaryTree(root.left);
        }
        if (root.right != null) {
            mirrorOfBinaryTree(root.right);
        }
    }
}
