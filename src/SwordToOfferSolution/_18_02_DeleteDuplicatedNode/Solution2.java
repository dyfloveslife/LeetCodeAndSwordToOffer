package SwordToOfferSolution._18_02_DeleteDuplicatedNode;

/**
 * 删除排序链表中的重复元素
 * <p>
 * 题目描述：
 * 给定一个已排序的链表的头 head，删除所有重复的元素，使每个元素只出现一次。返回已排序的链表。
 * <p>
 * 思路：
 * 1、使用一个指针进行判断即可，如果当前节点与其后继节点相同，则当前指针的 next 指向下一个节点，相当于把重复的节点删除了；
 * 2、如果当前节点与其后继节点不同，则指针后移即可。
 */
public class Solution2 {
    public static class ListNode {
        private int val;
        private ListNode next;

        private ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}
