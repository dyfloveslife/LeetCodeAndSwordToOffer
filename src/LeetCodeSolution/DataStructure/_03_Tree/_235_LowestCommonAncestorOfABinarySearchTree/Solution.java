package LeetCodeSolution.DataStructure._03_Tree._235_LowestCommonAncestorOfABinarySearchTree;

/*
 * 二叉搜索树的最近公共祖先
 *
 * 题目描述：
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 思路：
 * 1. 同样利用二叉搜索树的性质；
 * 2. 如果当前节点比 p 和 q 都大，则说明当前节点需要来到它的左子树；
 * 3. 如果当前节点比 p 和 q 都小，则说明当前节点需要来到它的右子树；
 * 4. 否则，当前节点就是 p 和 q 的最近公共祖先。
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        while (root != null) {
            if (root.val > p.val && root.val > q.val) {
                root = root.left;
            } else if (root.val < p.val && root.val < q.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}
