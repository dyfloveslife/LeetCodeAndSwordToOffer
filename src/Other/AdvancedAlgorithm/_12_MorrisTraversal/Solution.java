package Other.AdvancedAlgorithm._12_MorrisTraversal;

/*
 * Morris 遍历
 *
 * 类似于线索二叉树，但和它不同。 Morris 遍历利用的是叶节点上左右孩子的空指针指向父节点的操作。
 */
public class Solution {

    public static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    // 中序
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        // 当前节点 cur 的左子树上最右的节点，其实也是 cur 在中序遍历下的前驱节点
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                // 不断的在找左子树上最右的节点，即当前节点的前驱节点
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 步骤 2： a)
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                    // 步骤 2：b)
                } else {
                    mostRight.right = null;
                }
            }
            // 步骤 1
            // 当前节点在往右走之前，将当前节点进行打印
            // 即在完成 “左中” 之后，“右”之前，将当前节点打印
            System.out.println(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    // 先序遍历
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.println(cur.val + " ");
                    cur = cur.left;
                    continue;
                } else {
                    cur.right = null;
                }
                // 当前节点没有左子树的时候
            } else {
                System.out.println(cur.val + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    // 后序遍历
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }

        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    // 逆序打印当前节点的左子树的右边界
                    printRightEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        // 逆序打印整棵树的右边界
        printRightEdge(head);
        System.out.println();
    }

    // 逆序打印某个节点左子树的右边界
    private static void printRightEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.println(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    // 逆序操作
    private static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }
}
