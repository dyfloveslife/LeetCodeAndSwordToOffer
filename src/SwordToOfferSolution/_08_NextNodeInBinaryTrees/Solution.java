package SwordToOfferSolution._08_NextNodeInBinaryTrees;

/*
 * 二叉树的下一个结点
 *
 * 题目描述：
 * 给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
 * 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
 * 或者说，如何在二叉树中找到一个节点的后继节点？
 * 后继节点指的是在中序遍历中，当前节点的下一个节点叫做当前节点的后继节点。
 *
 * 思路：
 * 1. 如果当前节点有右子树，则当前节点的后继就是其右子树中最左侧的节点；
 * 2. 如果当前节点没有右子树，可利用父指针，朝上继续找，直至找到某个节点是其父节点的左孩子，则当前节点的后继就是那个父节点；
 *
 *
 */

public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private static TreeNode getNextNode(TreeNode node) {
        if (node == null) {
            return null;
        }

        // 如果当前节点有右孩子，则在右孩子中的最左侧的节点就是当前节点的后继
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
            // 如果某个节点没有右子树
        } else {
            TreeNode parent = node.parent;
            // 当前节点等于它父亲节点的左孩子的时候，就停止 while
            // 如果当前节点的父节点是 null，则说明当前节点没有后继
            while (parent != null && parent.left != node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }
}
