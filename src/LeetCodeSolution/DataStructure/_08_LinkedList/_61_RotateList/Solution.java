package LeetCodeSolution.DataStructure._08_LinkedList._61_RotateList;

import java.util.LinkedList;

/*
 * 旋转链表
 *
 * 题目描述：
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 *
 * 思路：
 * 1. 一开始的思路是根据链表长度与 k 的关系，来确定节点需要移动的次数；
 * 2. 如果 k 小于链表的长度，则使用双端队列处理；
 * 3. 如果 k 大于链表的长度，那么可以使用 k % len 求出最终移动多次的位置；
 * 4. 然后再次调用双端队列进行处理。
 * 5. 不过可以使用快慢指针来解决，具体看代码。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 快慢指针
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        // 实际上要移动的次数
        k = k % len;

        ListNode fast = head;
        ListNode slow = head;
        while (k-- > 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 注意改变节点 next 域的方式
        fast.next = head;
        head = slow.next;
        slow.next = null;

        return head;
    }


    public ListNode rotateRight1(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        if (k < len) {
            return process(head, k);
        } else {
            int times = k % len;
            return process(head, times);
        }
    }

    private ListNode process(ListNode head, int k) {
        LinkedList<Integer> queue = new LinkedList<>();
        ListNode cur = head;

        while (cur != null) {
            queue.offerLast(cur.val);
            cur = cur.next;
        }
        while (k-- > 0) {
            queue.offerFirst(queue.pollLast());
        }
        ListNode newHead = null;
        while (!queue.isEmpty()) {
            // 头插法，将 node 节点插入到头节点的下一个节点
            ListNode node = new ListNode(queue.pollLast());
            node.next = newHead;
            newHead = node;
        }
        return newHead;
    }
}
