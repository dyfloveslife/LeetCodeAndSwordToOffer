package SwordToOfferSolution._26_SubstructureInTree;

/*
 * 树的子结构
 *
 * 题目描述：
 * 输入两棵二叉树 A 和 B，判断 B 是不是 A 的子结构。
 *
 * 思路：
 * 1. 既然是需要判断 B 树是否是 A 树的子结构，则需要在 A 树中找到 B 树的根节点，用 B 树的根节点与 A 树的根节点比较：
 *    1.1) 如果不相等：
 *         则需要再次将 B 树的根节点与 A 树根节点的左右子树进行比较，此过程可以使用递归进行操作。
 *         但需要注意的是，如果 B 树本来就是空，则它就不是 A 树的子结构。
 *    1.2) 如果相等：
 *         则就需要一一比较两个子结构所对应的每个节点是否是相同的。这里也可以使用递归操作。
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

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 题目规定：如果 B 树为空的话，则它不是任意一棵树的子结构
        if (A == null || B == null) {
            return false;
        }

        if (isPartSame(A, B)) {
            return true;
        } else {
            return isSubStructure(A.left, B) || isSubStructure(A.right, B);
        }
    }

    // 现在 B 树的根节点已经在 A 树中找到了，
    // 下一步就是需要对每个节点进行一一的比较了
    public boolean isPartSame(TreeNode A, TreeNode B) {
        // 注意：整个 B 树是 A 树中某个子树的一部分，
        // 因此，如果当前 B 树节点为 null，则说明比到头了
        // 由于 B 树有可能比 A 树的某棵子树大，因此如果 B 树的某个节点已经为空了，则说明比较完了
        if (B == null) {
            return true;
        }
        // 如果 A 树中的某个子树的节点为空，则说明 B 树与该部分的节点不匹配
        if (A == null) {
            return false;
        }

        if (A.val == B.val) {
            return isPartSame(A.left, B.left) && isPartSame(A.right, B.right);
        } else {
            return false;
        }
    }
}
