package Other.BasicAlgorithm._26_PreInPosTraversal;

import java.util.Stack;

/*
 * 二叉树的前中后遍历（递归和非递归）
 *
 * 注意：
 * 在递归遍历的时候，依次访问到的节点有哪些；
 *                     1
 *                /        \
 *              2           3
 *            /   \       /   \
 *           4     5     6     7
 *         /  \   /  \  /  \  /  \
 *        n1  n2 n3 n4 n5  n6 n7  n8
 *  先序遍历：1->2->4->4->4->2->5->5->5->2->1->3->6->6->6->3->7->7->7->3->1
 *  可以看到，对于递归来说，每个节点都被访问到了 3 次。
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

    // 先序遍历(递归)
    public void preOrderRecursion(TreeNode head) {
        if (head == null) {
            return;
        }

        System.out.println(head.val);
        preOrderRecursion(head.left);
        preOrderRecursion(head.right);
    }

    // 中序遍历(递归)
    public void inOrderRecursion(TreeNode head) {
        if (head == null) {
            return;
        }

        inOrderRecursion(head.left);
        System.out.println(head.val);
        inOrderRecursion(head.right);
    }

    // 后序遍历(递归)
    public void postOrderRecursion(TreeNode head) {
        if (head == null) {
            return;
        }

        postOrderRecursion(head.left);
        postOrderRecursion(head.right);
        System.out.println(head.val);
    }

    // 先序遍历(非递归)
    public void preOrderNoRecursion(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            // 当前节点在入栈的时候，先让右孩子入栈，再让左孩子入栈
            // 出栈的时候，就是先出左孩子，后出右孩子
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    // 中序遍历(非递归)
    // 对于当前节点来说，如果当前节点不为空，则会把它的左边界全都入栈，一压压一溜
    // 如果当前节点为空，那么就开始出栈吧，然后再来到当前节点的右孩子
    public void inOrderNoRecursion(TreeNode head) {
        if (head == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        // head != null 是为了考虑中序遍历的最后一个节点
        while (!stack.isEmpty() || head != null) {
            // 将当前节点的左边界全部入栈
            if (head != null) {
                stack.push(head);
                head = head.left;
                // 能进入 else，说明 while 满足的条件是 !stack.isEmpty()，并且当前节点为 null
                // 则从栈中弹出一个节点，然后打印输出，最后再来到当前节点的右侧
            } else {
                head = stack.pop();
                System.out.println(head.val);
                head = head.right;
            }
        }
    }

    // 后序遍历(非递归)
    // 之前的先序遍历是 中左右，即先压右孩子，再压左孩子；
    // 则可以通过先压左孩子，再压右孩子，变成 中右左；
    // 最后再将要输出的节点放到一个栈中，从而逆序输出后序的 左右中。
    public void postOrderNoRecursion(TreeNode head) {
        if (head == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(head);
        while (!s1.isEmpty()) {
            head = s1.pop();
            // 本来应该打印输出的，但这里将其存入到另外一个栈中
            s2.push(head);
            if (head.left != null) {
                s1.push(head.left);
            }
            if (head.right != null) {
                s1.push(head.right);
            }
        }
        // 最后再单独打印辅助栈即可
        while (!s2.isEmpty()) {
            System.out.println(s2.pop().val);
        }
    }
}