package SwordToOfferSolution._36_ConvertBinarySearchTree;

/*
 * 二叉搜索树与双向链表
 *
 * 思路：
 * 1. 将左子树构造成双链表，并返回链表头节点；
 * 2. 新增一个全局变量记录左子树的最后一个节点；
 * 3. 如果左子树链表不为空的话，将当前 root 追加到左子树链表；
 * 4. 将右子树构造成双链表，并返回链表头节点；
 * 5. 如果右子树链表不为空的话，将该链表追加到 root 节点之后；
 * 6. 根据左子树链表是否为空确定返回的节点。
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    // 用于记录左子树的最后一个节点
    private TreeNode leftLast = null;

    public TreeNode Convert(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            leftLast = root;
            return root;
        }
        // 将 左子树 转换成双向链表，并返回头节点
        TreeNode leftNode = Convert(root.left);
        // 如果左子树的头节点不为空，则将根节点加到左链表的最后
        if (leftNode != null) {
            leftLast.right = root;
            root.left = leftLast;
        }
        // 如果根节点只包含左子树的话，则根节点就是链表的最后一个节点
        leftLast = root;
        // 将 右子树 转换成双向链表，并返回头节点
        TreeNode rightNode = Convert(root.right);
        // 如果右子树的头节点不为空，则将根节点添加到右链表的开头
        if (rightNode != null) {
            rightNode.left = root;
            root.right = rightNode;
        }
        // 如果根节点有左子树，则返回左子树的头节点；否则返回根节点
        return leftNode != null ? leftNode : root;
    }
}
