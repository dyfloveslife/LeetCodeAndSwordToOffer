package SwordToOfferSolution._18_01_DeleteNodeInList;

/**
 * 删除链表中的节点
 * <p>
 * 题目描述：
 * 有一个单链表的 head，我们想删除它其中的一个节点 node。
 * 给你一个需要删除的节点 node 。你将无法访问第一个节点  head。
 * 链表的所有值都是唯一的，并且保证给定的节点 node 不是链表中的最后一个节点。
 * <p>
 * 分析：
 * 1、一般情况下，如果要删除当前节点，需要设置前驱节点 pre，当前节点 cur，后继节点 cur.next，然后执行 pre.next = cur.next;
 * 2、但本题只传入「待删除节点」，无法找到其前驱；
 * 3、为了删除节点 node，可以复制其后继节点 node.next 的节点值给 node，然后再删除 node 即可。
 */
public class Solution2 {
    public static class ListNode {
        private int val;
        private ListNode next;

        private ListNode(int val) {
            this.val = val;
        }
    }

    public void deleteNode(ListNode node) {
        if (node == null) {
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
