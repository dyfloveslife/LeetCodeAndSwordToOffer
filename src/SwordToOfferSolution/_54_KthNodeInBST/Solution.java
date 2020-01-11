package SwordToOfferSolution._54_KthNodeInBST;

import java.util.Stack;

/*
 * 二叉查找树（搜索树）的第 K 个结点
 *
 * 题目描述：
 * 给定一棵二叉搜索树，请找出其中的第 k 大的结点。
 *
 * 思路：
 * 1. 中序遍历直接梭~
 * 2. 感觉非递归比较清晰
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

    // 中序遍历（非递归-用栈实现）
    public TreeNode getKthNodeInBST(TreeNode root, int k) {
        if (root == null || k < 1) {
            return null;
        }

        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;

        // 如何理解这里的 while 条件？
        // 假设 curNode 已经到了叶节点的孩子节点了，说明 curNode 为空；
        // 那么此时栈是不为空的，即还有元素，所以还需要后序的栈弹出操作
        while (curNode != null  || !stack.isEmpty()) {
            if (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            } else {
                curNode = stack.pop();
                count++;
                if (count == k) {
                    return curNode;
                }
                curNode = curNode.right;
            }
        }
        return null;
    }

    // 中序遍历（递归实现）
    int count = 0;
    public TreeNode getKthNodeBST2(TreeNode root, int k) {
        if (root != null) {
            TreeNode curNode = getKthNodeBST2(root.left, k);
            if (curNode != null) {
                return curNode;
            }
            count++;
            if (count == k) {
                return root;
            }
            curNode = getKthNodeBST2(root.right, k);
            if (curNode != null) {
                return curNode;
            }
        }
        return null;
    }
}
