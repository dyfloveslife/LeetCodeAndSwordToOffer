package LeetCodeSolution.DataStructure._03_Tree._113_PathSumII;

import java.util.ArrayList;
import java.util.List;

/*
 * 路径总和 Ⅱ
 *
 * 题目描述：
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 思路：
 * 1. 可以使用 DFS，如果目标和已经为 0，并且已经到了叶节点了，则开始收集路径上的节点；
 * 2. 否则继续递归当前节点的左右子树是否满足要求。
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

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }

        dfs(root, sum);
        return res;
    }

    private void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        path.add(root.val);
        target -= root.val;

        if (root.left == null && root.right == null && target == 0) {
            res.add(new ArrayList<>(path));
        }
        dfs(root.left, target);
        dfs(root.right, target);
        path.remove(path.size() - 1);
    }
}
