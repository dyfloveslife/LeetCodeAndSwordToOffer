package LeetCodeSolution.DataStructure._03_Tree._617_MergeTwoBinaryTrees;

/*
 * 合并二叉树
 *
 * 题目描述：
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 思路：
 * 1. 一开始将问题想复杂了；
 * 2. 其实很简单，每次将两个节点的值相加，然后用这个值去创建新的节即可；
 * 3. 然后再分别递归处理左右节点。
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

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }

        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t2.right, t2.right);

        return root;
    }
}
