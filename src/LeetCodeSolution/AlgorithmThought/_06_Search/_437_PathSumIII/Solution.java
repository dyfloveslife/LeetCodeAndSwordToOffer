package LeetCodeSolution.AlgorithmThought._06_Search._437_PathSumIII;

/*
 * 路径总和 Ⅲ
 *
 * 题目描述：
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 思路：
 * 1. 使用双重递归；
 * 2. 第一个递归 dfs 用于不断用 sum 减去当前节点的值，然后一直到 sum 为 0 为止；
 * 3. 第二个递归 pathSum 表示分别处理当前节点的左右孩子。
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

    int res = 0;

    public int pahtSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        dfs(root, sum);
        pahtSum(root.left, sum);
        pahtSum(root.right, sum);
        return res;
    }

    private void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        target -= root.val;
        if (target == 0) {
            res++;
        }
        dfs(root.left, target);
        dfs(root.right, target);
    }
}
