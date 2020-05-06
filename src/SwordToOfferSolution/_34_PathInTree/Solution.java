package SwordToOfferSolution._34_PathInTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 二叉树中和为某一值的路径
 *
 * 题目描述：
 * 输入一棵二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
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
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    private List<ArrayList<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }

        dfs(root, sum);
        return res;
    }

    public void dfs(TreeNode root, int target) {
        if (root == null) {
            return;
        }

        // 这里其实是按照先序遍历的方式，即根左右
        path.add(root.val);
        target -= root.val;
        // 由于“路径”表示从根节点一直到叶节点所经过的路径，所以需要左右叶子为 null 的条件
        // 如果 if 成立，则说明找到了一条 路径和 等于 target 的路径，则将其添加到 rs 中
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(path));
        }
        dfs(root.left, target);
        dfs(root.right, target);
        // 在返回父节点之前，在路径上删除最后一个节点，遍历完一条路径要回退，即回溯
        // 能走到这一步，说明已经到了叶节点了，但路径和不等于 target，需要返回父节点
        path.remove(path.size() - 1);
    }
}

