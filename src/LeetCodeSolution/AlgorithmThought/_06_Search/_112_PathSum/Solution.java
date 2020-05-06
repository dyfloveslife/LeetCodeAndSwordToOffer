package LeetCodeSolution.AlgorithmThought._06_Search._112_PathSum;

/*
 * 路径总和
 *
 * 题目描述：
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 思路：
 * 1. 使用 DFS，每遍历到一个节点的时候，用 target 减去该节点的值，再根据以下三个条件判断是否返回 true；
 * 2. 如果剩下的 target 为 0，并且当前节点是叶节点，那么就说明我找到了一个从根节点开始到叶节点结束的路径了；
 * 3. 如果不满足上面的条件，那么就递归去找左右子树是否满足这个条件。
 */
public class Solution {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode (int val) {
            this.val = val;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        return dfs(root, sum);
    }

    private boolean dfs(TreeNode root, int target) {
        if (root == null) {
            return false;
        }

        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            return true;
        }
        // 这里用 || 是因为：只要存在这么一条路径就可以，因此选左选右都行
        return dfs(root.left, target) || dfs(root.right, target);
    }
}
