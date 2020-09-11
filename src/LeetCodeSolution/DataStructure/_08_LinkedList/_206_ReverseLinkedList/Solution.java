package LeetCodeSolution.DataStructure._08_LinkedList._206_ReverseLinkedList;

/*
 * 反转链表
 *
 * 题目描述：
 * 反转一个单链表
 *
 * 思路一：迭代
 * 1. 使用迭代的方式，需要在链表的头部新创建一个 dummy 节点，让其指向 null；
 * 2. 然后通过不断移动指针的方式，逐渐遍历到最后一个节点；
 * 3. 最后返回最后一个节点即可。
 *
 * 思路二：递归
 * 1. 在递归的时候不是很好理解；
 * 2. 首先，需要来到链表的最后一个节点；
 * 3. 然后，在每次函数返回的过程中，让当前节点的下一个节点的 next 指针指向当前节点；
 * 4. 同时，让当前节点的 next 指向 null，
 * 5. https://pic.leetcode-cn.com/8951bc3b8b7eb4da2a46063c1bb96932e7a69910c0a93d973bd8aa5517e59fc8.gif
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 迭代
    public ListNode reverseList1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = null;
        ListNode pre = dummy;
        ListNode cur = head;

        while (cur != null) {
            // 防止链表断开
            ListNode nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }

    // 递归
    public ListNode reverseList2(ListNode head) {
        // 递归结束的条件
        if (head == null || head.next == null) {
            return head;
        }

        // 我需要通过递归的方式，一直来到链表的最后一个节点，
        // 例如 1->2->3->4->5->null，通过递归以后，newHead 来到的是 5 的位置，
        // 也就是最后需要返回的节点
        // 因为最后一个节点的 next 指针指向的是 null，因此栈空间会不断地弹栈
        ListNode newHead = reverseList2(head.next);
        // 来到最后一个节点以后，开始改变节点的指向，
        // 此时 head 是 4，head 的下一个是 5，head 的下下一个是 null，
        // head.next 表示节点 5，而节点 5 的 next 指针指向 4，也就是当前的 head
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
