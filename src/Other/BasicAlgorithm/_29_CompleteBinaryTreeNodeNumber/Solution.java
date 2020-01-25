package Other.BasicAlgorithm._29_CompleteBinaryTreeNodeNumber;

/*
 * 求完全二叉树节点的个数
 *
 * 要求：
 * 时间复杂度低于 O(N)
 *
 * 思路：
 * 1. 深度为 h 的满二叉树的节点个数为 2^n - 1；
 * 2. 先遍历当前节点左子树的左边界，求出高度 a，然后再遍历当前节点的右子树的左子树，求出高度 b；
 *    如果 a=b，则说明当前节点的左子树是满的，也就是说，如果当前节点右子树的左子树的高度等于 a，则说明当前节点的左子树是满的。
 * 3. 如果当前节点右子树的左子树的高度不等于 a，则说明当前节点的右子树是满的。
 * 4. O((logN)^2)
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

    // 主函数
    public int completeBinaryTreeNodeNumber(TreeNode head) {
        if (head == null) {
            return 0;
        }

        return bs(head, 1, mostLeftLevel(head, 1));
    }

    // level 表示当前节点所在的层数
    // h 表示完全二叉树的高度
    // 返回值是以 node 为根节点的节点个数
    public int bs(TreeNode node, int level, int h) {
        // 如果当前节点 node 所处的层数为树的高度，也就是最后一层，
        // 即叶节点，则节点数只有 1 个
        if (level == h) {
            return 1;
        }

        // 求当前节点 node 的右孩子的左边界到达了哪层
        // 是不是等于完全二叉树的高度
        if (mostLeftLevel(node.right, level + 1) == h) {
            // 当前节点左子树的节点数量(包括当前节点， 即 + 1) + 当前节点右子树的节点总数
            return (1 << (h - level) - 1 + 1) + bs(node.right, level + 1, h);
            // 当前节点右子树的左边界没达到完全二叉树的高度时
            // 则当前节点的右子树是满的
        } else {
            // 当前节点右子树上的节点总数 + 当前节点左树的节点数
            return (1 << (h - level - 1)) + bs(node.left, level + 1, h);
        }
    }

    // 求完全二叉树的高度
    public int mostLeftLevel(TreeNode node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }
}
