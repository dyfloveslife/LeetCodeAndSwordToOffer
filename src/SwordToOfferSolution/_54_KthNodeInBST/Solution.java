package SwordToOfferSolution._54_KthNodeInBST;

import java.util.Stack;

/*
 * 二叉查找树的第 K 个结点
 *
 * 思路：
 * 中序遍历直接梭~
 * 感觉非递归比较清晰
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
