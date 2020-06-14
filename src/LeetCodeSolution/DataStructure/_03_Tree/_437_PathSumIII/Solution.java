package LeetCodeSolution.DataStructure._03_Tree._437_PathSumIII;

/* 路径总和 Ⅲ
 *
 * 题目描述：
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 思路：
 * 1. 注意：路径不一定以根节点开头，也不一定以叶节点结尾，但是要求连续；
 * 2. 这里使用到的是双重递归，先序遍历每个节点，然后再以每个节点为起始点递归寻找满足条件的路径。
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

    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        dfs(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);

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
