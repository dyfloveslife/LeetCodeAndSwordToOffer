package Other.BasicAlgorithm._19_ReverseList;

/*
 * 反转单向链表和双向链表
 */
public class Solution {
    // 单向链表反转
    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 双向链表反转
    class DoubleNode {
        int val;
        // 上一个
        DoubleNode last;
        DoubleNode next;

        DoubleNode(int val) {
            this.val = val;
        }
    }

    public static DoubleNode reserveDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
