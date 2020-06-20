package LeetCodeSolution.DataStructure._03_Tree._530_MinimumAbsoluteDifferenceInBST;

/*
 * 二叉搜索树的最小绝对差
 *
 * 题目描述：
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 * 思路：
 * 1. 根据二叉搜索树中序遍历的特性，输出的结果是升序的；
 * 2. 因此可以在中序遍历的时候用当前遍历到的根节点的值减去左节点的值，然后每次更新最小值即可；
 * 3. 但需要注意的是，由于每次需要用到前一个节点的值，因此需要使用一个节点记录前一个节点引用。
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

    private int res = 0;
    private TreeNode pre = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        if (pre != null) {
            // 注意使用 root 减去 pre，
            // 因为 root 比 pre 大
            res = Math.min(res, root.val - pre.val);
        }
        // 需要移动 pre
        pre = root;
        inorder(root.right);
    }
}
