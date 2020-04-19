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
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        if (root.left != null) {
            mirrorTree(root.left);
        }
        if (root.right != null) {
            mirrorTree(root.right);
        }
        return root;
    }
}
