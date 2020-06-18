package LeetCodeSolution.DataStructure._03_Tree._538_ConvertBSTToGreaterTree;

/*
 * 把二叉搜索树转换为累加树
 *
 * 题目描述：
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 思路：
 * 1. 利用二叉搜索树的特性，左子树的值都小于根节点的值，右子树的值都大于根节点的值；
 * 2. 既然需要在原节点加上“所有大于它的节点值之和”，那么我们需要从最右侧的节点开始，通过递归的方式不断给父节点返回累加值；
 * 3. 因此，可以采用右->根->左的方式遍历二叉搜索树，在遍历的过程中不断修改当前遍历的节点即可。
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

    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }
}
