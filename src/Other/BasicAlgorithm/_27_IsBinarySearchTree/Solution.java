package Other.BasicAlgorithm._27_IsBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/*
 * 判断一棵树是否是二叉搜索树
 *
 * 思路一：递归
 * 1. 如果该树中序遍历的节点是依次升序的，则该树为二叉搜索树；
 * 2. 可以使用递归的方法，在中序遍历时，判断当前节点是否大于前一个节点：
 *    如果大于，则说明是二叉搜索树；
 *    如果小于等于，则说明不是二叉搜索树。
 *
 * 思路二：遍历
 * 1. 但这里使用到了 morris 遍历。
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

    // 递归：中序遍历
    // 由于树中节点的值可能会小于 Integer.MIN_VALUE=-2147483648，
    // 因此使用 long 类型
    long pre = Long.MIN_VALUE;

    public boolean isBST1(TreeNode root) {
        // 空树也算是二叉搜索树的一种
        if (root == null) {
            return true;
        }

        if (!isBST1(root.left)) {
            return false;
        }
        // 在 BST 的中序遍历中，如果前一个节点的值比当前节点的值要大或者相等，
        // 则说明构不成 BST
        if (pre >= root.val) {
            return false;
        }
        // pre 后移
        pre = root.val;
        return isBST1(root.right);
    }

    // 当然，也可以在中序遍历的过程中，将每个节点的值收集到 list 中，
    // 通过遍历 list 来判断是否是二叉搜索树
    private List<Integer> list = new ArrayList<>();

    public boolean isBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        inorder(root);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        list.add(root.val);
        inorder(root.right);
    }

    // Morris 遍历
    public boolean isBST3(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean res = true;
        TreeNode pre = null;
        TreeNode cur1 = root;
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
