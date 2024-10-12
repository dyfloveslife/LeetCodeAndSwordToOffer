package SwordToOfferSolution._18_03_DeleteDuplicatedNodeII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 从未排序的链表中移除重复元素
 * <p>
 * 题目描述：
 * 给定一个链表的第一个节点 head ，找到链表中所有出现多于一次的元素，并删除这些元素所在的节点。
 * 返回删除后的链表。
 */
public class Solution2 {
    static class ListNode {
        private int val;
        private ListNode next;

        private ListNode(int val) {
            this.val = val;
        }

        private ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode deleteDuplicatesUnsorted(ListNode head) {
        if (head == null) {
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (ListNode cur = head; cur != null; cur = cur.next) {
            map.put(cur.val, map.getOrDefault(cur.val, 0) + 1);
        }

        ListNode dummy = new ListNode(0, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (map.get(cur.val) > 1) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }

            cur = cur.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);

        Solution2 solution2 = new Solution2();
        ListNode newListNode = solution2.deleteDuplicatesUnsorted(head);
        System.out.println(printListNode(newListNode));
    }

    private static List<Integer> printListNode(ListNode head) {
        if (head == null) {
            return null;
        }

        List<Integer> ans = new ArrayList<>();
        ListNode cur = head;
        while (cur != null) {
            ans.add(cur.val);
            cur = cur.next;
        }

        return ans;
    }
}
