package SwordToOfferSolution._28_SymmetricalBinaryTree;

/*
 * 对称的二叉树
 *
 * 题目描述：
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 思路：
 * 1. 可以使用前序遍历和前序对称遍历的输出是否相同来解决；
 * 2. 对于二叉树节点值都相同的情况，可以将 null 值也算进比较的范围里。
 *
 *             root1                        root2
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

    boolean isSymmetrical(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricalCore(root.left, root.right);
    }

    private boolean isSymmetricalCore(TreeNode root1, TreeNode root2) {
        // 如果两个根节点都是空
        if (root1 == null && root2 == null) {
            return true;
        }
        // 其中一个节点不为空
        if (root1 == null || root2 == null) {
            return false;
        }
        // 不满足对称的条件，则返回 false
        if (root1.val != root2.val) {
            return false;
        }
        // 递归检查对称位置
        return isSymmetricalCore(root1.left, root2.right)
                && isSymmetricalCore(root1.right, root2.left);
    }
}
