package LeetCodeSolution.DataStructure._03_Tree._230_KthSmallestElementInABST;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 二叉搜索树中第 K 小的元素
 *
 * 题目描述：
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * 思路：
 * 1. 由于是二叉搜索树，因此采用中序遍历就是有序的，然后再顺便求第 k 个最小的元素即可。
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

    // 之前的写法是先进行中序遍历，生成中序遍历序列，然后在这个序列中找第 k 小的数，
    // 其实不需要这么做，完全可以一边中序遍历，一边找第 k 小的数
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        List<Integer> list = inorder(root);
        for (int i = 0; i < list.size(); i++) {
            if (i == (k - 1)) {
                return list.get(i);
            }
        }
        return -1;
    }

    private List<Integer> inorder(TreeNode root) {
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

    // 直接在中序遍历的过程中进行处理即可
    public int kthSmallest1(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        int count = 0;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                count++;
                if (count == k) {
                    return root.val;
                }
                root = root.right;
            }
        }
        return -1;
    }
}
