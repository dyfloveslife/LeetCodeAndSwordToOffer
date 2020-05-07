package LeetCodeSolution.DataStructure._03_Tree._572_SubtreeOfAnotherTree;

/*
 * 另一个树的子树
 *
 * 题目描述：
 * 给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。
 * s 的一个子树包括 s 的一个节点和这个节点的所有子孙。
 * s 也可以看做它自身的一棵子树。
 * 需要格外注意：该题与“树的子结构”的判断条件有些不同。
 *
 * 思路：
 * 1. 这里使用的其实是双重递归；
 * 2. t 如果是 s 的子树，要么 t 等于 s，要么 t 是 s 的左/右子树。
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

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }

        if (isPartSame(s, t)) {
            return true;
        } else {
            // 如果 s 的根节点和 t 的根节点不相等，则 t 的根节点再与 s 的左右孩子进行比对
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }

    public boolean isPartSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }

        if (s.val == t.val) {
            return isPartSame(s.left, t.left) && isPartSame(s.right, t.right);
        } else {
            return false;
        }
    }
}
