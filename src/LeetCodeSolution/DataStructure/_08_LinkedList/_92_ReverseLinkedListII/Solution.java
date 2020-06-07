package LeetCodeSolution.DataStructure._08_LinkedList._92_ReverseLinkedListII;

/*
 * 反转链表 Ⅱ
 *
 * 题目描述：
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 思路：
 * 1. 头插法，将 m 到 n 之内的节点不断地插入到 m 所指的节点之前；
 * 2. 还是需要理清楚各个节点之间的引用关系，具体看代码。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;

        int mm = m;
        ListNode cur = head;
        // 注意这里是 --m，
        // cur 会来到 m 的位置
        while (--m > 0) {
            cur = cur.next;
            pre = pre.next;
        }

        int times = n - mm;
        while (cur.next != null && times-- > 0) {
            ListNode temp = cur.next;
            // 下面这一行语句用于防止链表丢失
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummy.next;
    }
}
