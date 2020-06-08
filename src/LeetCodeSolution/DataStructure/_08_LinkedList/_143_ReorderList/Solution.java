package LeetCodeSolution.DataStructure._08_LinkedList._143_ReorderList;

/*
 * 重排链表
 *
 * 题目描述：
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 思路：
 * 1. 分三步走；
 * 2. 首先使用快慢指针，找到链表的中间节点，奇链表的中间节点就是中间的那个，而偶链表的中间节点会来到后面的那个节点；
 * 3. 将链表分成两部分后，然后将第二个链表反转；
 * 4. 最后合并两个链表，也就是将第二个链表的节点依次插入到第一个节点中。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }

        // 找到链表的中间节点
        ListNode midNode = findMidNode(head);

        // 根据中间节点，分成两个链表
        ListNode left = head;
        ListNode right = midNode.next;
        midNode.next = null;

        // 将第二个链表进行反转
        right = reverseList(right);

        // 合并两个链表
        mergeList(left, right);
    }

    // 返回链表的中间节点
    private ListNode findMidNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == null) {
                return slow;
            }
        }
        return slow;
    }

    // 反转链表
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }

    // 合并两个链表
    private void mergeList(ListNode l1, ListNode l2) {
        // 防止链表断裂
        ListNode temp1 = null;
        ListNode temp2 = null;

        while (l1 != null && l2 != null) {
            temp1 = l1.next;
            temp2 = l2.next;

            l1.next = l2;
            l2.next = temp1;

            l1 = temp1;
            l2 = temp2;
        }
    }
}
