package SwordToOfferSolution._22_KthNodeFromEnd;

/*
 * 求链表中倒数第 K 个节点
 *
 * 题目描述：
 * 输入一个链表，输出该链表中倒数第 k 个结点。为了符合大多数人的习惯，本题从 1 开始计数，即链表的尾结点是倒数第 1 个结点。
 * 例如一个链表有 6 个结点，从头结点开始它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个结点是值为 4 的结点。
 *
 * 思路：
 * 1. 使用双指针，快指针先走 k-1 步，然后两个指针再一起走；
 * 2. 等到快指针走到最后一个节点的时候，此时慢指针就是倒数第 k 个节点，将其返回即可；
 * 3. 对于给定的 k 来说，判断其是否合理也是很重要的，例如 k 大于链表长度的情况，需要进行额外的判断；
 */
public class Solution {

    static class ListNode {
        private int val;
        private ListNode next;

        private ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }

        // 先让快指针走 k 步
        ListNode fast = head;
        while (k-- > 0 && fast != null) {
            fast = fast.next;
        }

        // 然后再让快满指针一起走
        ListNode cur = head;
        while (cur != null && fast != null) {
            cur = cur.next;
            fast = fast.next;
            if (fast == null) {
                return cur;
            }
        }

        return cur;
    }

    public ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k == 0) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;
        for (int i = 0; i < k - 1; i++) {
            if (fast.next != null) {
                fast = fast.next;
            } else {
                return null;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public ListNode trainingPlan(ListNode head, int cnt) {
        if (head == null) {
            return null;
        }

        ListNode fast = head, slow = head;
        for (int i = 0; i < cnt; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        Solution solution = new Solution();
        System.out.println(solution.trainingPlan(head, 2).val);
    }
}
