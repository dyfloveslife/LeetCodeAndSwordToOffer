package LeetCodeSolution.DataStructure._03_Tree._94_BinaryTreeInorderTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 二叉树的中序遍历
 *
 * 题目描述：
 * 给定一个二叉树，返回它的中序遍历。
 *
 * 思路：
 * 1. 中序遍历的顺序是：左->根->右；
 * 2. 它与前序遍历和后序遍历的方式是不同的；
 * 3. 既然先遍历左孩子，那么我就一直来到二叉树的最左孩子的位置，然后再开始收集结果。
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

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
