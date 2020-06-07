package LeetCodeSolution.DataStructure._08_LinkedList._23_MergekSortedLists;

import java.util.PriorityQueue;

/*
 * 合并 K 个排序链表
 *
 * 题目描述：
 * 合并 k 个排序链表，返回合并后的排序链表。
 * 请分析和描述算法的复杂度。
 *
 * 思路一：优先队列
 * 1. 可以使用小根堆；
 * 2. 之前的想法是将 K 个链表的每个节点都放进小根堆中，然后依次弹出堆顶元素，再连接成最终的链表；
 *    但是“将 K 个链表的每个节点都放进小根堆”的时间复杂度很高，并且空间复杂度是 O(N)，即所有节点的个数。
 * 3. 后来想到的是可以将每个链表中的第一个节点放进小根堆，这样小根堆所占用的空间就是 K 个大小了，K 肯定是小于所有节点的数量；
 * 4. 因此空间复杂度为 O(K)，K 表示的是链表的数量；
 * 5. 而对于时间复杂度来说，在堆中比较每个元素的大小所花费的时间就是堆排序的时间，即 O(logK)，
 *    在堆中找到最小值的操作需要 O(1)，而总节点的数量是 n，所以总的时间复杂度为 O(nlogK)；
 * 6. 需要注意的是：对于给定的 ListNode[]，可以直接通过 ListNode[0]、ListNode[1]、ListNode[2] 这样的方式获取到每个链表的头节点。
 *
 * 思路二：两两合并
 * 1. 类似于之前的一个题目：“合并两个有序链表”；
 * 2. 比较每个链表的头节点，获得最小值的节点；
 * 3. 将选中的节点连接在最终有序链表的后面；
 * 4. 空间复杂度是 O(n)，因为递归用到栈的空间大小为 n；
 * 5. 时间复杂度为 O(kn)，因为两两合并总共需要进行 k-1 次合并操作。
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode (int val) {
            this.val = val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 定义小根堆
        PriorityQueue<ListNode> qMin = new PriorityQueue<>((o1, o2) -> (o1.val - o2.val));

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        // 将每个链表的头节点放进堆中
        for (ListNode curListHead : lists) {
            if (curListHead != null) {
                qMin.offer(curListHead);
            }
        }

        while (!qMin.isEmpty()) {
            cur.next = qMin.poll();
            cur = cur.next;
            // 如果
            if (cur.next != null) {
                qMin.offer(cur.next);
            }
        }

        return dummy.next;
    }

    // 两两合并
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            // 注意特判
            if (lists[i] != null) {
                head = merge(head, lists[i]);
            }
        }
        return head;
    }

    private ListNode merge (ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
