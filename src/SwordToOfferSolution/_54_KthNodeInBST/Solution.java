package SwordToOfferSolution._54_KthNodeInBST;

import java.util.Stack;

/*
 * 二叉查找树（搜索树）的第 K 大结点
 *
 * 题目描述：
 * 给定一棵二叉搜索树，按照节点数值从小到大，请找出其中的第 k 大的结点。
 *
 * 思路：
 * 0. 这里书中的描述和 LeetCode 中描述的不同，注意区分；
 * 1. 中序遍历一棵二叉搜索树也可得到从小到大的顺序；
 * 2. 但这里的要求是输出从小到大第 k 个数，使用左根右即可；
 * 3. 如果是从大到小的第 k 个数，则需要从右到根再到左进行。
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

    // 从大到小的第 k 个数，也就是第 k 大的节点
    // 所以使用右-根-左，根据 k 的值直接返回第 k 大的节点
    private int res = 0;
    private int cnt = 0;

    // 递归实现
    public int kthLargest1(TreeNode root, int k) {
        dfs(root, k);
        return res;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        dfs(root.right, k);
        if (++cnt == k) {
            res = root.val;
            return;
        }
        dfs(root.left, k);
    }

    // 非递归实现
    public int kthLargest2(TreeNode root, int k) {
        if (root == null || k < 1) {
            return -1;
        }

        int num = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.right;
            } else {
                root = stack.pop();
                if (++num == k) {
                    return root.val;
                }
                root = root.left;
            }
        }
        return -1;
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
        while (curNode != null || !stack.isEmpty()) {
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
