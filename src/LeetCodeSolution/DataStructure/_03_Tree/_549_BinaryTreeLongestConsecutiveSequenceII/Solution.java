package LeetCodeSolution.DataStructure._03_Tree._549_BinaryTreeLongestConsecutiveSequenceII;

/*
 * 二叉树中最长的连续序列 Ⅱ
 *
 * 题目描述：
 *
 * 给定一个二叉树，你需要找出二叉树中最长的连续序列路径的长度。
 * 请注意，该路径可以是递增的或者是递减。
 * 例如，[1,2,3,4] 和 [4,3,2,1] 都被认为是合法的，而路径 [1,2,4,3] 则不合法。
 * 另一方面，路径可以是 子-父-子 顺序，并不一定是 父-子 顺序。
 *
 * 思路：
 * 1. 由于允许递增和递减，因此需要分别计算递增时的最大长度和递减时的最大长度；
 * 2. 需要注意的是，在来到右子树的时候，需要同时更新递增的最大长度和递减的最大长度。
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

    public int longestConsecutive(TreeNode root) {
        if (root == null) {
            return 0;
        }

        process(root);
        return res;
    }

    private int[] process(TreeNode root) {
        int[] arr = new int[2];
        // arr[0] 表示 递增 序列路径长度
        // arr[1] 表示 递减 序列路径长度
        arr[0] = 1;
        arr[1] = 1;

        if (root == null) {
            return arr;
        }
        int[] leftSum = process(root.left);
        int[] rightSum = process(root.right);

        if (root.left != null) {
            // 递增的
            if (root.left.val - 1 == root.val) {
                arr[0] = leftSum[0] + 1;
            }
            // 递减的
            if (root.left.val + 1 == root.val) {
                arr[1] = leftSum[1] + 1;
            }
        }
        // 注意此时需要更新递增或递减序列的长度
        if (root.right != null) {
            // 递增的
            if (root.right.val - 1 == root.val) {
                arr[0] = Math.max(arr[0], rightSum[0] + 1);
            }
            // 递减的
            if (root.right.val + 1 == root.val) {
                arr[1] = Math.max(arr[1], rightSum[1] + 1);
            }
        }
        // 这里的减 1 操作是为了减去重复的节点，
        // 如果当前节点即在递减路径中，又在递增路径中，那么当前节点就计算了 2 次，所以需要减 1
        res = Math.max(res, arr[0] + arr[1] - 1);
        return arr;
    }
}
