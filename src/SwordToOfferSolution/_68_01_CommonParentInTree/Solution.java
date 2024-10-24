package SwordToOfferSolution._68_01_CommonParentInTree;

/*
 * 二叉搜索树的最近公共祖先
 *
 * 题目描述：
 * 在二叉搜索树中（左边小于根，右边大于根），输入两个结点，求它们的最低公共祖先。
 *
 * 思路：
 *  1. 对于二叉搜索树，如果当前节点的值比两个输入的节点大，则继续在当前节点的左侧寻找最低公共祖先；
 *  2. 如果当前节点的值比两个输入的节点小，则在当前节点的右侧寻找最低公共祖先。
 */
public class Solution {
    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 递归
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    // 迭代
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }

        return null;
    }
}