package LeetCodeSolution.DataStructure._08_LinkedList._23_MergekSortedLists;

import java.util.Comparator;
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
 *    在堆中找到最小值的操作需要 O(1)，而总节点的数量是 n，所以总的时间复杂度为 O(nlogK)。
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
        PriorityQueue<ListNode> qMin = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return (o1.val < o2.val) ? -1 : 1;
            }
        });

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        // 实际上 curListHead 就是当前链表的头节点
        // 因为只要知道了链表的头节点，我就能遍历出这个链表
        for (ListNode curListHead : lists) {
            if (curListHead != null) {
                qMin.offer(curListHead);
            }
        }

        while (!qMin.isEmpty()) {
            cur.next = qMin.poll();
            cur = cur.next;
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
            if (lists[i] != null) {
                head = merge(head, lists[i]);
            }
        }
        return head;
    }

    public ListNode merge (ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        if (head1.val <= head2.val) {
            head1.next = merge(head1.next, head2);
            return head1;
        } else {
            head2.next = merge(head1, head2.next);
            return head2;
        }
    }
}
