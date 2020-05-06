package LeetCodeSolution.AlgorithmThought._06_Search._113_PathSumII;

import java.util.ArrayList;
import java.util.List;

/*
 * 路径总和Ⅱ
 *
 * 题目描述：
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 思路：
 * 1. 先序遍历二叉树，把结点加入路径；
 * 2. 在先序遍历中，记录从根节点到当前节点的路径：
 *    如果当前路径是从根节点到叶节点形成的路径并且各节点值的和等于目标值 sum，
 *    则将此路径加入到结果集中。
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

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

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

        if (target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }
        dfs(root.left, target);
        dfs(root.right, target);
        path.remove(path.size() - 1);
    }
}
