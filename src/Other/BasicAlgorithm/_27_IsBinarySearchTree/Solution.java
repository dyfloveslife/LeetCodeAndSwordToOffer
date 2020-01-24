package Other.BasicAlgorithm._27_IsBinarySearchTree;

/*
 * 判断一棵树是否是二叉搜索树
 *
 * 思路：
 * 如果该树中序遍历的节点是依次升序的，则该树为二叉搜索树
 * 这里使用到了 morris 遍历
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

    // Morris Traversal
    public boolean isBST(TreeNode head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        TreeNode pre = null;
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.val > cur1.val) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }
}
