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
 * 1. 建议先做 LeetCode.100 题，判断两棵树是否相等；
 * 2. 判断一棵树是否是另外一棵树的子树，需要注意是“或”的关系：
 *    2.1) 当前两棵树相等；
 *    2.2) 或者 t 是 s 的左子树；
 *    2.3) 或者 t 是 s 的右子树。
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
        // 如果 s 原本就不是一棵树，则 t 就不需要进行比较了
        if (s == null) {
            return false;
        }

        // 这里是“或”的关系
        // 注意这里是用 t 的根节点继续与 s 的左右孩子继续比较
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s, t);
    }

    // 判断是否是相同的树
    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }

        return isSubtree(s.left, t.left) && isSubtree(s.right, t.right);
    }
}
