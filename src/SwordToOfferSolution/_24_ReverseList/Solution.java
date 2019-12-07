package SwordToOfferSolution._24_ReverseList;

/*
 * 翻转单链表
 * 递归实现：
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Solution {
    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newList = reverseList(head.next);
        ListNode t1 = head.next;
        t1.next = head;
        head.next = null;
        return newList;
    }

    /*
     * 定义三个节点：
     * preNode: 当前节点的前一个节点
     * curNode: 当前节点
     * nextNode: 当前节点的下一个节点，防止链表断开
     * reversedHead: 链表翻转后的头节点
     */
    private static ListNode reserseList2(ListNode head) {
        ListNode preNode = null;
        ListNode curNode = head;
        ListNode reversedHead = null;
        while (curNode != null) {
            ListNode nextNode = curNode.next; //让 nextNode 保存当前节点 curNode 的下一个节点信息，防止链表断开。
            // 如果当前节点的下一个节点为空，即 nextNode 为空，则说明链表中只有一个节点，这时将当前节点传递给 reversedHead 返回即可。
            if (nextNode == null) {
                reversedHead = curNode;
            }
            // 翻转操作（注意：curNode.next 表示当前节点的指针域域，节点有数据域和指针域）
            curNode.next = preNode;
            // 后移操作，继续翻转下一个节点
            preNode = curNode;
            curNode = nextNode;
        }
        return reversedHead;
    }
}
