package LeetCodeSolution.DataStructure._08_LinkedList._82_RemoveDuplicatesFromSortedListII;

/*
 * 删除排序链表中的重复元素 Ⅱ
 *
 * 题目描述：
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 思路：
 * 1. 这里在改变指针的时候比较绕；
 * 2. 首先创建一个 dummy，以便于最后返回头节点，即 dummy.next；
 * 3. 通过 temp 来不断的划过相同的节点，然后 temp 停在相同节点的最后一个节点；
 * 4. 最后改变 cur 的 next 域，让它指向 temp 的下一个节点。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                ListNode temp = cur.next;
                // 这里让 temp 一直来到相同节点的最后一个节点，
                // temp 来到最后一个节点后，temp 的下一个节点就是一个不同的节点，
                // 然后让 cur 的 next 指向 temp 的下一个节点
                while (temp != null && temp.next != null && temp.val == temp.next.val) {
                    temp = temp.next;
                }
                cur.next = temp.next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
