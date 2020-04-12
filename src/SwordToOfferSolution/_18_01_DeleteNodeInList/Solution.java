package SwordToOfferSolution._18_01_DeleteNodeInList;

/*
 * 在 O(1) 的时间内删除链表节点
 *
 * 题目描述：
 * 给定单向链表的头指针和一个结点指针，定义一个函数在 O(1) 时间删除该结点。
 *
 * 思路：
 * 1. 如果链表有头节点的话，则可以在头节点之前再新建一个节点，最后直接返回该节点的 next 即可；
 * 2. 在处理的时候，如果当前节点的下一个节点的值等于被删除节点的值，则需要将当前节点的 next 指针指向
 *    被删除节点的下一个节点即可。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteNode(ListNode head, int target) {
        if (head == null) {
            return null;
        }

        if (head.val == target) {
            return head.next;
        }
        // 在头节点之前新建一个节点
        ListNode preNode = new ListNode(-1);
        preNode.next = head;

        ListNode curNode = head;
        while (curNode.next != null) {
            if (curNode.next.val == target) {
                curNode.next = curNode.next.next;
            } else {
                curNode = curNode.next;
            }
        }
        return preNode.next;
    }
}
