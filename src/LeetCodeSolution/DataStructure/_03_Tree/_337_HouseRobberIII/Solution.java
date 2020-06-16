package LeetCodeSolution.DataStructure._03_Tree._337_HouseRobberIII;

/*
 * 打家劫舍 Ⅲ
 *
 * 题目描述：
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 思路：
 * 1. 其实就是二叉树的间隔遍历；
 * 2. 但需要注意的是，如果选择偷了某个节点后，那么它的父节点以及它的孩子是不能再偷的；
 * 3. 如果选择偷根节点，那么根节点的两个孩子不能再偷；
 * 4. 如果选择不偷根节点，那么就可以偷两个孩子节点，然后再选择较大的那个即可，当然可以不偷两个孩子节点；
 * 5. 当然也可以使用递归（后序遍历）的方式，让子节点将信息返回给父节点。
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

    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 选择偷根节点
        int v1 = root.val;
        // 左孩子不为空，我先不偷左孩子，而是选择偷左孩子的左右孩子
        if (root.left != null) {
            v1 += rob(root.left.left) + rob(root.left.right);
        }
        // 右孩子不为空，我先不偷右孩子，而是选择偷右孩子的左右孩子
        if (root.right != null) {
            v1 += rob(root.right.left) + rob(root.right.right);
        }

        // 选择不偷根节点，
        // 那么我即偷左孩子，又偷右孩子
        int v2 = rob(root.left) + rob(root.right);

        // 最后取两者最大
        return Math.max(v1, v2);
    }
}
