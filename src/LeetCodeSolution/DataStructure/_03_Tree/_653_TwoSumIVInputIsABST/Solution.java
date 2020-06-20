package LeetCodeSolution.DataStructure._03_Tree._653_TwoSumIVInputIsABST;

import java.util.ArrayList;
import java.util.List;

/*
 * 两数之和 Ⅳ - 输入 BST
 *
 * 题目描述：
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 思路：
 * 1. 最初的思路是采用递归的方式，分别找到两个元素值等于 target；
 * 2. 但发现这两个元素有可能分布在左右子树两侧；
 * 3. 因此，先中序遍历二叉搜索树，然后通过双指针的方式来查找目标值。
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

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum == k) {
                return true;
            } else if (sum > k) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}
