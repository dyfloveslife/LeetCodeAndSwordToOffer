package LeetCodeSolution.DataStructure._03_Tree._106_ConstructBinaryTreeFromInorderAndPostorderTraversal;

import java.util.HashMap;

/*
 * 从中序与后序遍历序列构造二叉树
 *
 * 题目描述：
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 思路：
 * 0. 和前序遍历+中序遍历一样，需要格外注意的是索引的位置
 *   中序： |4    2    8    5     9|    1    |6    10    3    7|
 *           ↑                    ↑     ↑    ↑                ↑
 *          inL              pivot-1  pivot  pivot+1          inR
 *
 *   后序： |4    8    9    5    2|    |10    6    7    3|      1
 *           ↑                   ↑      ↑               ↑       ↑
 *         postL   postR-inR+pivot-1  postR-inR+pivot postR-1  postR
 *
 * 1. 需要主要的是，先求后序遍历中的 10 的位置，然后再推导出 2 的位置；
 * 2. 10 的位置确定方式：
 *         假设 10 的位置为 x，则存在 postR-1-x=inR-(pivot+1)，然后化简即可。
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

    HashMap<Integer, Integer> map = new HashMap<>();
    int[] postArray;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0
                || inorder.length != postorder.length) {
            return null;
        }

        int inLen = inorder.length;
        int postLen = postorder.length;

        postArray = postorder;
        // 将中序遍历的每个元素与其索引用 map 存储起来，便于查找
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }

        return process(0, inLen - 1, 0, postLen - 1);
    }

    private TreeNode process(int inL, int inR, int postL, int postR) {
        if (inL > inR || postL > postR) {
            return null;
        }

        // 找到根节点对应的值，并创建根节点
        int pivot = postArray[postR];
        TreeNode root = new TreeNode(pivot);

        // 在中序遍历序列中找到 pivot 所对应的位置
        int pivotIndex = map.get(pivot);

        root.left = process(inL, pivotIndex - 1, postL, postR - inR + pivotIndex - 1);
        root.right = process(pivotIndex + 1, inR, postR - inR + pivotIndex, postR - 1);
        return root;
    }
}
