package LeetCodeSolution.AlgorithmThought._01_DoublePointer._141_LinkedListCycle;

/*
 * 环形链表
 *
 * 题目描述：
 * 给定一个链表，判断链表中是否有环。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 思路:
 * 1. 使用两个指针，一个快指针走两步，一个满指针走一步；
 * 2. 如果它俩在遍历的时候相遇了，则一定在环内相遇。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
