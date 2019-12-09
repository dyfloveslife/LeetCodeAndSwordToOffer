package SwordToOfferSolution._18_02_DeleteDuplicatedNode;

/*
 * 删除链表中重复的结点
 */
public class Solution {
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }

        // 定义一个虚拟头节点dummy，指向链表的头节点
        // 这个dummy保证不会被删掉，这种情况下就可以不用管头节点有可能被删掉的情况了
        ListNode dummy = new ListNode(-1);
        dummy.next = pHead;
        // 指向上一个有效的节点
        ListNode last = dummy;
        // 指向当前遍历的节点
        ListNode temp = pHead;
        while (temp != null && temp.next != null) {
            if (temp.val == temp.next.val) {
                // 如果相同，则后移temp，一直到 temp 和 temp.next 不同为止
                while (temp.next != null && temp.val == temp.next.val) {
                    temp = temp.next;
                }
                // 到这里说明 temp 和 temp.next 不同了，则找到不用删除的节点，例如从 11223 中找到 3
                last.next = temp.next;
                temp = temp.next;
                // 如果没有相同的
            } else {
                //当前节点就不用删除（这里 last 要指向 temp，因为 temp 已经和 temp.next 不同了，就用 last 指向该有效的节点）
                last = temp;
                temp = temp.next;
            }
        }
        return dummy.next;
    }
}
