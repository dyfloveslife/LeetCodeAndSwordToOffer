package SwordToOfferSolution._18_02_DeleteDuplicatedNode;

/*
 * 删除链表中重复的结点
 *
 * 题目描述：
 * 在一个排序的链表中，如何删除重复的结点？即将链表中所有重复的节点全部删除。
 * 例如，在图(a)中重复结点被删除之后，链表如图(b)所示。
 * (a) 1->2->3->3->4->4->5
 * (b) 1->2->5
 *
 * 思路：
 * 1. 注意，该题目要求的是将链表中重复的节点全部删除，而不保留重复的节点；
 * 2. 因此，我们需要找到重复节点的最后一个位置，如在链表 1->2->3->3->4 中，需要找到第四个节点 3；
 * 3. 然后让节点 2 的 next 直接指向 4 即可。
 */
public class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode deleteDuplication(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 定义一个虚拟节点 dummy，让其 next 指向链表的头节点
        // 这个 dummy 保证不会被删掉，这种情况下就可以不用管头节点有可能被删掉的情况了
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        // 指向上一个有效的节点
        ListNode pre = dummy;
        // 从头节点开始遍历
        ListNode cur = head;
        
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                // 如果相同，则后移 cur，直到 cur 和 cur.next 不同为止
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                // 1->2->3->3->4->4->5
                // 此时的 pre 指向的是第二个节点 2，而 cur 指向的是第四个节点 3
                pre.next = cur.next;
                cur = cur.next;
            } else {
                // 如果当前节点的值和下一个节点的值不相等，则指针后移
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
