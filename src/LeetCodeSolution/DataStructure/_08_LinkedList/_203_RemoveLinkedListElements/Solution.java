package LeetCodeSolution.DataStructure._08_LinkedList._203_RemoveLinkedListElements;

/*
 * 移除链表元素
 *
 * 题目描述：
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 思路：
 * 1. 直接创建一个 dummy 即可，然后逐一比较；
 * 2. 如果两个节点不相等，则后移指针；
 * 3. 如果两个节点相等，则越过相等的节点。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;

        while (cur.next != null) {
            // 只要 cur 等于 val，那么 cur 就不动
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
