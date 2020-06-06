package LeetCodeSolution.DataStructure._08_LinkedList._19_RemoveNthNodeFromEndOfList;

/*
 * 删除链表的倒数第 N 个节点
 *
 * 题目描述：
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 思路：
 * 1. 一开始的思路是遍历两次链表，第一次用于统计链表的长度 len，第二次用于找到 len-n 的节点，然后改变其后继指针即可；
 * 2. 如何遍历一趟来解决该题？
 * 3. 使用快满指针，快指针先走 n 步，然后再让慢指针和快指针一起走，等到快指针的下一个为空的时候停止，此时的慢指针就指向的是待删除的节点；
 * 4. 然后改变慢指针的 next 域即可。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 遍历两次链表
    public ListNode removeNthFronEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        if (len == 1) {
            return null;
        }

        cur = head;
        if (len == n) {
            return cur.next;
        }

        int num = len - n;
        while (cur != null && num > 1) {
            num--;
            cur = cur.next;
        }
        // 经过上面的 while，现在 cur 指向的是待删除节点的前一个节点，
        // 然后直接越过待删除节点即可
        cur.next = cur.next.next;
        return head;
    }

    // 快慢指针
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode fast = head;
        while (n > 0) {
            fast = fast.next;
            n--;
        }

        // 有两种情况 fast 为 null：
        // 1) 如果链表长度和 n 相等，则删除的是头节点，因此需要返回头节点的下一个节点，即 head.next；
        // 2) 如果链表中只有 1 个节点，并且 n=1，此时需要将该节点删除，然后返回空链表
        if (fast == null) {
            return head.next;
        }

        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }
}