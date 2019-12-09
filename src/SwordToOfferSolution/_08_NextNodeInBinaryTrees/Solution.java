package SwordToOfferSolution._08_NextNodeInBinaryTrees;

/*
 * 二叉树的下一个结点
 */
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

public class Solution {
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
