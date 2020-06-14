package LeetCodeSolution.DataStructure._03_Tree._100_SameTree;

/*
 * 相同的树
 *
 * 题目描述：
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 思路：
 * 1. 判断两棵树是否相同，则需要满足：
 *    1.1) 当前两棵树的根节点相同；
 *    1.2) 并且，p 的左子树和 q 的右子树相同；
 *    1.3) 并且，p 的右子树和 q 的右子树相同。
 * 2. 注意，以上是“并且”的关系。
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

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }

        // 注意是“并且”的关系
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
