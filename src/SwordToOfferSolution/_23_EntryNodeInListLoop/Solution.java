package SwordToOfferSolution._23_EntryNodeInListLoop;

/*
 * 如何在一个包含环的链表中，找出环的入口点?
 * 分析：
 * 两个指针一个fast、一个slow同时从一个链表的头部出发，
 * fast一次走2步，slow一次走一步，如果该链表有环，两个指针必然在环内相遇。
 * 此时只需要把其中的一个指针重新指向链表头部，另一个不变（还在环内），
 * 这次两个指针一次走一步，相遇的地方就是入口节点。
 */

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public ListNode entryNodeInListLoop(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return null;
        }

        ListNode slowNode = pHead;
        ListNode fastNode = pHead;
        while (fastNode != null && fastNode.next != null) {
            // 慢指针走一步，快指针走两步
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
            // 如果两个指针相遇了，就让快指针重新指向链表头，然后和慢指针一起同速走
            if (slowNode == fastNode) {
                fastNode = pHead;
                while (slowNode != fastNode) {
                    slowNode = slowNode.next;
                    fastNode = fastNode.next;
                }
            }
            // 再次相遇的时候就是环入口
            if (slowNode == fastNode) {
                return fastNode;
            }
        }
        // 要是没有相遇，此链表没有环返回空
        return null;
    }
}
