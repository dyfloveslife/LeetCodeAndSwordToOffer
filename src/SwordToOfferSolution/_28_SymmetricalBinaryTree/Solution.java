package SwordToOfferSolution._28_SymmetricalBinaryTree;

/*
 * 对称的二叉树
 *
 * 题目描述：
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 思路：
 * 1. 二叉树要满足对称，则需要以下是三个条件，对于当前任意两个对称的节点：
 *    1.1) 这两个对称的节点的值相同；
 *    1.2) 左节点的左孩子等于右节点的右孩子，即 root1.left == root2.right；
 *    1.3) 左节点的右孩子等于右节点的左孩子，即 root1.right == root2.left。
 *             root1                          root2
 *             /     \                       /     \
 *     root1.left  root1.right      root2.left  root2.right
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

    public boolean isSymmetrical(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root.left, root.right);
    }

    public boolean process(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return process(root1.left, root2.right) && process(root1.right, root2.left);
    }
}
