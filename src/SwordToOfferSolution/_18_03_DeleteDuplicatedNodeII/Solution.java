package SwordToOfferSolution._18_03_DeleteDuplicatedNodeII;

import java.util.HashSet;

/*
 * 删除链表中的重复节点Ⅱ
 *
 * 题目描述：
 * 移除未排序链表中的重复节点，但保留最开始出现的节点。
 * 例如给定链表：[1, 2, 3, 3, 2, 1]
 * 结果为：[1, 2, 3]
 *
 * 思路：
 * 1. 使用 HashSet 具有唯一性的特点，如果当前节点已经在 set 中出现过了，则越过（删除）该节点；
 * 2. 最后返回头节点 head 即可。
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode preHead = new ListNode(-1);
        preHead.next = head;

        ListNode cur = head;
        HashSet<Integer> set = new HashSet<>();

        while (cur != null) {
            if (!set.contains(cur.val)) {
                set.add(cur.val);
                preHead = cur;
            } else {
                // 如果 set 中已经存在了当前节点的值，
                // 则应该将 preHead 指向 cur 的下一个节点，将重复出现的节点值删除
                preHead.next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
