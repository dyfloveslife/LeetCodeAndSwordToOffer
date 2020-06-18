package LeetCodeSolution.DataStructure._03_Tree._108_ConvertSortedArrayToBinarySearchTree;

/*
 * 将有序数组转换为二叉搜索树
 *
 * 题目描述：
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 思路：
 * 1. 直接采用分治的思想，处理完当前数组的根节点以后，再分别处理左右两部分的子数组。
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

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        int middle = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[middle]);
        root.left = process(nums, left, middle - 1);
        root.right = process(nums, middle + 1, right);
        return root;
    }
}
