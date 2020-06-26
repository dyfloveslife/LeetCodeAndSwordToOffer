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
 * 进阶：
 * 如果不得使用临时缓冲区，该怎么解决？
 *
 * 思路：
 * 1. 使用 HashSet 具有唯一性的特点，如果当前节点已经在 set 中出现过了，则越过（删除）该节点；
 * 2. 最后返回头节点 dummy.next 即可。
 * 3. 如果不使用额外空间的话，则需要用时间换空间；
 * 4. 使用两个指针 cur1 和 cur2，每次固定 cur1，然后每次使用 cur2 去后面寻找与 cur1 相等的节点，遇到相等的就将它越过去，继续找下一个与 cur1 相等的节点。
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 使用额外空间
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode pre = dummy;

        ListNode cur = head;
        HashSet<Integer> set = new HashSet<>();

        while (cur != null) {
            if (!set.contains(cur.val)) {
                set.add(cur.val);
                pre = cur;
            } else {
                // 如果 set 中已经存在了当前节点的值，
                // 则应该将 dummy 指向 cur 的下一个节点，将重复出现的节点值删除
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    // 不使用额外空间
    public ListNode removeDuplicateNodes1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur1 = head;
        while (cur1 != null) {
            ListNode cur2 = cur1;
            while (cur2.next != null) {
                if (cur2.next.val == cur1.val) {
                    cur2.next = cur2.next.next;
                } else {
                    cur2 = cur2.next;
                }
            }
            cur1 = cur1.next;
        }
        return head;
    }
}
