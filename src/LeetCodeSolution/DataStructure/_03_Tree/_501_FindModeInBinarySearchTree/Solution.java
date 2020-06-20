package LeetCodeSolution.DataStructure._03_Tree._501_FindModeInBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/*
 * 二叉搜索树中的众数
 *
 * 题目描述：
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 假定 BST 有如下定义：
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 *
 * 提示：如果众数超过 1 个，不需考虑输出顺序
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 * 思路：
 * 1. 还是在使用中序遍历的时候做一些操作，如果当前遍历到的节点与 pre 相等，则让 curCount 加 1；
 * 2. 具体看代码。
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

    private int curCount = 1;
    private int maxCount = 1;
    private TreeNode pre = null;

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int[] res = new int[list.size()];
        int index = 0;
        for (int i : list) {
            res[index++] = i;
        }
        return res;
    }

    private void inorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        if (pre != null) {
            if (pre.val == root.val) {
                curCount++;
            } else {
                curCount = 1;
            }
        }
        if (curCount > maxCount) {
            maxCount = curCount;
            list.clear();
            list.add(root.val);
        } else if (curCount == maxCount) {
            list.add(root.val);
        }
        pre = root;
        inorder(root.right, list);
    }
}
