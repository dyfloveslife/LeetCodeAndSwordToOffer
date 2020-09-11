package SwordToOfferSolution._24_ReverseList;

/*
 * 反转单链表
 *
 * 题目描述：
 * 定义一个函数，输入一个链表的头结点，反转该链表并输出反转后链表的头结点。
 *
 * 思路：
 * 0. https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/solution/dong-hua-yan-shi-duo-chong-jie-fa-206-fan-zhuan-li/
 * 1. 在某个链表调整指针的过程中，需要将当前节点的前一个节点和后一个节点进行保存，
 *    因为，如果不保存后一个节点的话，则当前节点会和后面的链表断开；
 * 2. 而反转后的链表的头节点就是原始链表中 next 为 null 的节点；
 * 3. 迭代的方式很好理解，那么递归的方式呢？
 * 4. 递归的方式就是从当前节点的下一个节点开始递归，一直来到最后一个节点；
 * 5. 反转之后，再将当前节点挂到 nextNode 节点后面；
 * 6. 最后，经过链表的反转，head 相当于尾节点了，所以要将它的 next 域指向 null。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode nextNode = null;

        while (cur != null) {
            nextNode = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextNode;
        }
        return pre;
    }

    // 递归
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 以 1->2->3->4->5 为例，此时的 nextHead 就是 5，
        // 而 head 是 4，head 的 next 是 5，head 的 next 的 next 是 null，
        ListNode newHead = reverseList2(head.next);
        // 所以，这里将 head 的 next 的 next 指向 4，也就是将 5 的 next 域指向 4，这是反转链表的核心
        head.next.next = head;
        // 然后再让 4 的 next 指向 null，也就是断开原来的连接
        head.next = null;
        return newHead;
    }
}
