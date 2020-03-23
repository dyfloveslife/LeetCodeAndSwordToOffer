package LeetCodeSolution.AlgorithmThought._06_Search._257_BinaryTreePaths;

import java.util.ArrayList;
import java.util.List;

/*
 * 二叉树的所有路径
 *
 * 题目描述：
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 思路：
 * 1. DFS，由于要返回每次访问过的路径，所以需要维护一个 path 来存储走过的节点；
 * 2. 如果遇到叶节点，则将当前的路径添加到答案集合进行返回；
 * 3. 需要在搜索完一条路径后进行回溯，转而再去搜索其它的路径。
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

    private List<String> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return res;
        }

        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }

        path.add(root.val);
        // 来到了叶节点，我要开始遍历当前已经走过的节点了
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            // path 中已经存好了某条路径，现在将其一个一个的添加到 sb 中
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i != path.size() - 1) {
                    sb.append("->");
                }
            }
            // 将其中的一组路径加入到结果集中
            res.add(sb.toString());
        }
        dfs(root.left);
        dfs(root.right);
        // 回溯
        path.remove(path.size() - 1);
    }
}
