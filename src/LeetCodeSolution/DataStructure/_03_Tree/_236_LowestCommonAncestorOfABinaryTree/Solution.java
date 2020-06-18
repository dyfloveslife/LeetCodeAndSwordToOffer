package LeetCodeSolution.DataStructure._03_Tree._236_LowestCommonAncestorOfABinaryTree;

/*
 * 二叉树的最近公共祖先
 *
 * 题目描述：
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 思路：
 * 1. https://github.com/dyfloveslife/LeetCodeAndSwordToOffer/blob/master/src/SwordToOfferSolution/_68_02_CommonParentInTree/Solution.java
 * 2. 剑指 offer 做过这道题，现在重新思考一下解题方法；
 * 3. 由于不是二叉搜索树，因此不能通过比较节点值来确定最近公共祖先；
 * 4. 先从根节点开始判断，如果根节点就是 p 节点或者就是 q 节点，则说明 p 和 q 的最近公共祖先就是根节点；
 * 5. 否则递归找到左右节点，如果左节点为空，则返回右节点即可，如果右节点为空，则返回左节点即可；
 * 6. 其实就是后序遍历的思想，每当遍历完左右孩子的时候，就把信息返回给父节点；
 * 7. 找到了 p 或 q 节点，就向上返回，如果没有找到 p 或 q 节点，则向上返回 null。
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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);
        if (leftNode == null) {
            return rightNode;
        }
        if (rightNode == null) {
            return leftNode;
        }
        return root;
    }
}
