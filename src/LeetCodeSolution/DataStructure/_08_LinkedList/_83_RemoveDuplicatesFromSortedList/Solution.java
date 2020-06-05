package LeetCodeSolution.DataStructure._08_LinkedList._83_RemoveDuplicatesFromSortedList;

/*
 * 删除排序链表中的重复元素
 *
 * 题目描述：
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 思路：
 * 1. 如果当前节点和它的下一个节点相等，则当前节点就指向它的下下节点，也就是将相等的节点给空出来，相当于删除了；
 * 2. 如果当前节点和它的下一个节点不相等，那么我们就不需要管了，而是直接移动当前节点的指针来到它的下一个节点，继续判断。
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

        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }
}
