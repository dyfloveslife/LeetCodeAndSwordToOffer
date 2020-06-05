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

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 如果其中一个链表为空，则直接返回另一个链表即可
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = (l1 == null) ? l2 : l1;
        return dummy.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode mergedHead;
        if (l1.val <= l2.val) {
            mergedHead = l1;
            mergedHead.next = mergeTwoLists2(l1.next, l2);
        } else {
            mergedHead = l2;
            mergedHead.next = mergeTwoLists2(l1, l2.next);
        }
        return mergedHead;
    }
}
