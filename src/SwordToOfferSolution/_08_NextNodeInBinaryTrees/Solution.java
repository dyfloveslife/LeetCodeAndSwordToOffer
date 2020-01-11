package SwordToOfferSolution._08_NextNodeInBinaryTrees;

/*
 * 二叉树的下一个结点
 *
 * 题目描述：
 * 给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
 * 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
 */

public class Solution {
    class TreeNode<Integer> {
        int val = 0;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    private static TreeNode<Integer> getNextNode(TreeNode<Integer> node) {
        if (node == null) {
            return null;
        }
        else if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }
        while (node.parent != null) {
            if (node.parent.left == node) {
                return node.parent;
            }
            node = node.parent;
        }
        return null;
    }
}
