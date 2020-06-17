package LeetCodeSolution.DataStructure._03_Tree._669_TrimABinarySearchTree;

/*
 * 修剪二叉搜索树
 *
 * 题目描述：
 * 给定一个二叉搜索树，同时给定最小边界 L 和最大边界 R。
 * 通过修剪二叉搜索树，使得所有节点的值在 [L, R] 中 (R>=L)。
 * 你可能需要改变树的根节点，所以结果应当返回修剪好的二叉搜索树的新的根节点。
 *
 * 思路：
 * 1. 首先要知道二叉搜索树的特点，对于每一个节点，它左子树的左右节点都是比它小的，而它右子树的左右节点都是比它大的；
 * 2. 现在开始从根节点开始修建；
 * 3. 如果根节点的值比 L 小，则说明根节点的左子树都是比 L 小的，则需要将左子树剪掉，因此需要直接返回右子树的修剪结果；
 * 4. 如果根节点的值比 R 大，则说明根节点的右子树都是比 R 大的，则需要将右子树剪掉，因此需要直接返回左子树的修剪结果；
 * 5. 如果根节点没问题，则递归地修改左右子节点；
 * 6. 如果根节点为空，则无需修改，直接返回空即可。
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

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }

        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }
}
