package SwordToOfferSolution._07_ConstructBinaryTree;

import java.util.HashMap;
import java.util.Map;

/*
 * 重建二叉树
 *
 * 题目描述：
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列 {1,2, 4, 7, 3, 5, 6, 8} 和中序遍历序列 {4, 7, 2, 1, 5, 3, 8, 6}，则重建出如下图所示的二叉树并输出它的头结点。
 *         1
 *       /   \
 *      2     3
 *     /     / \
 *    4     5   6
 *     \        /
 *      7      8
 *
 * 思路：
 * 1. 注意特殊情况，即边界条件、前序和中序数组的长度是否相同、以及数组中元素的个数是否大于 1；
 * 2. 建树的核心参数，这里给出了前序遍历数组、前序中的左右边界，中序遍历数组、中序中的左右边界；
 * 3. 难点在于给定的根节点确定后，如果确定左右子树的数量，需要扣一些边界条件。
 * 4. 一定要画图，这样便于确定边界：
 *    先序： 1    |2    4    5    8    9|    |3    6    10    7|
 *           ↑    ↑                    ↑     ↑                ↑
 *          preL preL+1     pivot-inL+preL  pivot-inL+preL+1  preR
 *
 *    中序： |4    2    8    5    9|    1    |6    10    3    7|
 *           ↑                    ↑     ↑    ↑                ↑
 *          inL              pivot-1  pivot  pivot+1          inR
 */
class Solution {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    Map<Integer, Integer> map = new HashMap<>();
    int[] preArray;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length, inLen = inorder.length;

        if (preLen != inLen) {
            return null;
        }

        preArray = preorder;

        // 将中序遍历数组中的每个数放进 map 的 key 中，
        // 将每个数字对应的索引放进 value 中
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }

        return buildTreeCore(0, preLen - 1, 0, inLen - 1);
    }

    public TreeNode buildTreeCore(int preL, int preR, int inL, int inR) {
        if (preL > preR || inL > inR) {
            return null;
        }

        // 通过 preL 找到先序遍历的第一个数
        int pivot = preArray[preL];
        TreeNode root = new TreeNode(pivot);

        // 通过在先序遍历中第一个节点的值，寻找其在中序遍历中的索引
        int pivotIndex = map.get(pivot);

        root.left = buildTreeCore(preL + 1, pivotIndex - inL + preL, inL, pivotIndex - 1);
        root.right = buildTreeCore(pivotIndex - inL + preL + 1, preR, pivotIndex + 1, inR);
        return root;
    }
}