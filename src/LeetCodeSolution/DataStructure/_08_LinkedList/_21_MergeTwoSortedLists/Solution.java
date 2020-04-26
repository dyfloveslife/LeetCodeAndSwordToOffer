package LeetCodeSolution.DataStructure._08_LinkedList._21_MergeTwoSortedLists;

/*
 * 合并两个有序链表
 *
 * 题目描述：
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 思路一：双指针
 * 1. 首先创建一个假的节点 dummy，然后使用两个指针分别从两个链表的头部进行比较节点的大小；
 * 2. 将值小的节点连接在 dummy 之后，再后移指针；
 * 3. 如果其中一个链表已经遍历完了而另一个链表没有遍历完，则需要将没有遍历完的链表接在新生成的链表的后面；
 * 4. 最后返回 dummy.next，就是新生成的已经排好序的链表的头节点。
 *
 * 思路二：递归
 * 1. 首先创建一个合并后的新的头节点，然后再比较两个链表的头节点的大小；
 * 2. 将小的接在新的头节点的后面，在递归下一个节点。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        // 如果其中一个链表为空，则直接返回另一个链表即可
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }

        cur.next = (head1 == null) ? head2 : head1;
        return dummy.next;
    }

    public ListNode mergeTwoLists2(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ListNode mergedHead;
        if (head1.val <= head2.val) {
            mergedHead = head1;
            mergedHead.next = mergeTwoLists2(head1.next, head2);
        } else {
            mergedHead = head2;
            mergedHead.next = mergeTwoLists2(head1, head2.next);
        }
        return mergedHead;
    }
}
