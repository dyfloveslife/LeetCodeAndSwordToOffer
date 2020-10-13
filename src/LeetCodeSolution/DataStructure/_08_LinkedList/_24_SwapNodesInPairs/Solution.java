package LeetCodeSolution.DataStructure._08_LinkedList._24_SwapNodesInPairs;

/*
 * 两两交换链表中的节点
 *
 * 题目描述：
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 思路：
 * 1. 这题需要改变两个节点的 next 指针的引用；
 * 2. 首先需要创建一个 dummy 节点，让它的 next 指向 head；
 * 3. 然后创建一个引用 pre，让其指向 dummy；
 * 4. 在 pre 的后继节点以及后继的后继不为 null 的情况下，遍历链表；
 * 5. 创建每个节点的引用，然后交换两个节点的引用，最后再让 pre 的 next 指向两者之间的后者节点；
 * 6. 也就是说 pre 需要来到 cur1 的位置，这点很重要；
 * 7. 最后需要返回 dummy 节点的 next；
 * 8. 画图解决一切。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;

        while (pre.next != null && pre.next.next != null) {
            ListNode cur1 = pre.next;
            ListNode cur2 = pre.next.next;
            ListNode nex = cur2.next;

            cur1.next = nex;
            cur2.next = cur1;
            pre.next = cur2;

            pre = cur1;
        }
        return dummy.next;
    }
}
