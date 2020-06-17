package LeetCodeSolution.DataStructure._03_Tree._144_BinaryTreePreorderTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 二叉树的前序遍历
 *
 * 题目描述：
 * 给定一个二叉树，返回它的前序遍历。
 *
 * 思路：
 * 1. 如果使用迭代的方式，可以使用栈；
 * 2. 前序遍历的顺序是：根->左->右；
 * 3. 先将右子树入栈，再将左子树入栈。
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

    public List<Integer> preorderTreaversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);
            // 先让右孩子入栈的原因是：我在遍历完根节点的时候，接下来需要遍历左子树，
            // 根据栈的出入规则，最先出来的应该是左子树
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return res;
    }
}
