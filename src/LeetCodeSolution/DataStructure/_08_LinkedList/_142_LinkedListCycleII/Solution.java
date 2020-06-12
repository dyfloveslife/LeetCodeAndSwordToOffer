package LeetCodeSolution.DataStructure._08_LinkedList._142_LinkedListCycleII;

/*
 * 环形链表 Ⅱ
 *
 * 题目描述：
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 思路：
 * 1. 使用快慢指针，直到相遇；
 * 2. 相遇后，让快指针指向链表的表头，然后再让快慢指针每次走一步；
 * 3. 等到再次相遇的时候，快满指针就会停留在入环的第一个节点上。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }
}
