package SwordToOfferSolution._34_PathInTree;

import java.util.ArrayList;
import java.util.Stack;

/*
 * 二叉树中和为某一值的路径
 * 思路：
 * 先序遍历二叉树，把结点加入路径；
 * 若该结点是叶子结点，则比较当前路径和是否等于目标和；
 * 弹出结点，每一轮递归返回到父结点时，当前路径也应该回退一个结点。
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    // 保存所有的路径
    private ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    // 保存当前节点
    private ArrayList<Integer> list = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return res;
        list.add(root.val);
        target -= root.val;
        // 由于“路径”表示从根节点一直到叶节点所经过的路径，所以需要左右叶子为 null 的条件
        // 如果 if 成立，则说明找到了一条 路径和 等于 target 的路径，则将其添加到 rs 中
        if (target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        // 在返回父节点之前，在路径上删除最后一个节点，遍历完一条路径要回退
        // 能走到这一步，说明已经到了叶节点了，但路径和不等于 target，需要返回父节点
        list.remove(list.size() - 1);
        return res;
    }
}

