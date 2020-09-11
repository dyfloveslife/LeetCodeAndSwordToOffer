package LeetCodeSolution.DataStructure._03_Tree._257_BinaryTreePaths;

import java.util.ArrayList;
import java.util.List;

/*
 * 二叉树的所有路径
 *
 * 题目描述：
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 思路：
 * 1. 该题可以使用 dfs 的方式，从根节点一直递归到某个叶子节点，然后再返回；
 * 2. 在递归的过程中，如果当前节点为 null，则返回 return；
 * 3. 如果当前节点不为 null，说明它是一个正常的节点，那么我们就将这个节点放进 path 中，这个 path 表示的是一个字符串，也就是从根节点到某个叶节点的路径，这点需要注意；
 * 4. 如果当前节点的左右孩子都为 null，那么说明当前节点就是叶节点，此时就说明，递归函数已经帮我找到了一条从根节点到当前叶节点的路径，那么我现在将这条路径加入到 ans 中即可；
 * 5. 否则的话，说明当前节点不是叶节点，那么我还要递归的去处理左右孩子；
 * 6. 但是，在递归的处理左右孩子之前，需要往 path 中添加一个 “->” 符号即可。
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

    List<String> ans = new ArrayList<>();

    // 方式一
    public List<String> binaryTreePaths1(TreeNode root) {
        if (root == null) {
            return ans;
        }
        dfs1(root, "");
        return ans;
    }

    private void dfs1(TreeNode root, String path) {
        if (root == null) {
            return;
        }
        path += root.val;
        if (root.left == null && root.right == null) {
            ans.add(path);
        } else {
            path += "->";
            dfs1(root.left, path);
            dfs1(root.right, path);
        }
    }

    // 方式二
    List<String> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<String> binaryTreePaths2(TreeNode root) {
        if (root == null) {
            return res;
        }
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i != path.size() - 1) {
                    sb.append("->");
                }
            }
            res.add(sb.toString());
        }
        dfs(root.left);
        dfs(root.right);
        path.remove(path.size() - 1);
    }
}
