package LeetCodeSolution.DataStructure._08_LinkedList._1171_RemoveZeroSumConsecutiveNodesFromLinkedList;

import java.util.HashMap;

/*
 * 从链表中删去总和值为零的连续节点
 *
 * 题目描述：
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 * 删除完毕后，请你返回最终结果链表的头节点。
 * 你可以返回任何满足题目要求的答案。
 *
 * 思路：
 * 1. 遍历两次 map，第一次将当前节点所对应的连续和作为 key，将当前节点作为 value，存入 map 中，有新的 value 就将其覆盖；
 * 2. 第二次遍历 map，直接将当前节点的 next 指向当前和所对应的 value 的 next 节点；
 * 3. 由于在第一次遍历 map 的时候，含有相同连续和的节点是被更新过的，也就是位于链表的后面的位置；
 * 4. 所以，在第二次遍历 map 的时候，直接来到被更新过的节点的 next 节点即可，也就是将之前的那些节点给删除掉。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode removeZeroSumSublists(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        HashMap<Integer, ListNode> map = new HashMap<>();
        int sum = 0;
        for (cur = dummy; cur != null; cur = cur.next) {
            sum += cur.val;
            map.put(sum, cur);
        }

        sum = 0;
        for (cur = dummy; cur != null; cur = cur.next) {
            sum += cur.val;
            cur.next = map.get(sum).next;
        }
        return dummy.next;
    }
}
