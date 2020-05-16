package LeetCodeSolution.DataStructure._08_LinkedList._25_ReverseNodesInkGroup;

/*
 * K 个一组翻转链表
 *
 * 题目描述：
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 思路：
 * 1. 可以使用递归的方式，实现一个函数，该函数专门用来对指定区间范围内的链表进行翻转；
 * 2. 也就是说，正常情况下我们翻转以 start 为头节点的链表其实就是翻转 start 到 null 之间的节点，
 *    那么如果翻转从 start 到 end 之间的链表也是可以实现的；
 * 3. 然后在主函数中调用该函数即可。
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode start = head;
        ListNode end = head;
        for (int i = 0; i < k; i++) {
            // 如果链表不足 k 个，则不需要翻转
            if (end == null) {
                return head;
            }
            // end 来到 k 的位置
            end = end.next;
        }
        // 翻转前 k 个节点，即 [start, end) 区间内的节点
        ListNode newHead = reverse(start, end);
        // 递归翻转后序链表并连接起来
        start.next = reverseKGroup(end, k);
        return newHead;
    }

    // 翻转 start 到 end 之间的节点
    private ListNode reverse(ListNode start, ListNode end) {
        ListNode pre = null;
        ListNode cur = start;
        ListNode nxt = start;
        // 这里仅仅将终止条件改成了节点 end
        while (cur != end) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur =  nxt;
        }
        return pre;
    }

    // 这是翻转以 start 为头节点的链表
    public ListNode reverseNomal(ListNode start) {
        ListNode pre = null;
        ListNode cur = start;
        ListNode nxt = start;
        while (cur != null) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
