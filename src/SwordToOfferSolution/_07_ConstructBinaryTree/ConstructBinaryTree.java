package SwordToOfferSolution._07_ConstructBinaryTree;

import java.util.HashMap;

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
 * 3. 难点在于给定的根节点确定后，如果确定左右子树的数量，需要扣一些边界条件；
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

    // 递归+哈希表
    static HashMap<Integer, Integer> map = new HashMap<>();
    static int[] pos;
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length < 1 || inorder.length < 1
                || preorder.length != inorder.length) {
            return null;
        }
        pos = preorder;

        // 使用 map 每个数在中序遍历中的位置，便于查找
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        // 前序遍历数组的索引区间，以及中序遍历数组的索引区间
        return dfs(0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode dfs(int pl, int pr, int il, int ir) {
        if (pl > pr) {
            return null;
        }

        // 找到根节点
        TreeNode root = new TreeNode(pos[pl]);
        // 在中序遍历里找出根节点的位置
        int i = map.get(root.val);

        // 左子树在前序遍历中的区间
        // 起始位置：pl + 1
        // 长度：pl + 1 + i - il - 1，其中 i 是根节点在中序遍历中的位置，i-il 表示长度
        // 左子树在中序遍历中的区间，就是从 il 到 i-1 的位置
        // 起始位置：il
        // 长度：i - 1
        root.left = dfs(pl + 1, pl + 1 + i - il - 1, il, i - 1);
        // 右子树在前序遍历中的区间
        // 起始位置：pl + 1 + i - il - 1 + 1，即左子树的末尾再加一，就是起始位置
        // 长度：pr
        // 右子树在中序遍历中的区间
        // 起始位置：i + 1
        // 长度：ir
        root.right = dfs(pl + 1 + i - il - 1 + 1, pr, i + 1, ir);
        return root;
    }

    public static TreeNode constructBinaryTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length < 1 || inorder.length < 1
                || preorder.length != inorder.length) {
            return null;
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    // 这里的形参已经是数组的索引了
    public static TreeNode buildTree(int[] preorder, int pre_left, int pre_right, int[] inorder, int in_left, int in_right) {
        if (pre_left > pre_right || in_left > in_right) {
            return null;
        }

        // 每次将前序遍历的左边界 pre_left 作为树的根，pre_left 在每次递归的时候是变化的
        TreeNode root = new TreeNode(preorder[pre_left]);

        for (int i = in_left; i <= in_right; i++) {
            // 如果前序遍历得到的值与中序遍历得到的值相等，则开始递归左右子树
            if (inorder[i] == preorder[pre_left]) {

                root.left = buildTree(preorder, pre_left + 1, pre_left + i - in_left,
                        inorder, in_left, i - 1);
                root.right = buildTree(preorder, i - in_left + pre_left + 1, pre_right,
                        inorder, i + 1, in_right);
            }
        }
        return root;
    }
}