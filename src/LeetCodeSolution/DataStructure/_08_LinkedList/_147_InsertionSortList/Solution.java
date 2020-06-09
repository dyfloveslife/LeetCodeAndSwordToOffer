package LeetCodeSolution.DataStructure._08_LinkedList._147_InsertionSortList;

/*
 * 对链表进行插入排序
 *
 * 题目描述：
 * 使用插入排序的方式，将未排序的链表进行插入排序。
 *
 * 思路：
 * 1. 既然用到了插入排序，整体思路是找到了已排序节点的最后一个节点 pre 以及未排序节点的第一个节点 cur 以后；
 * 2. 需要从头节点 dummy 到 pre 区间内，找到比 cur 大的首个节点；
 * 3. 然后进行节点 next 域的转换，转换的过程中记得不能将链表断开，最好画图理解。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode insertionSortLisst(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // pre 指向头节点
        ListNode pre = head;
        // cur 指向头节点的下一个节点，也表示未排序链表的第一个节点
        ListNode cur = head.next;

        // 如果在未排序链表中还有未排序节点的话，则执行 while，
        // 注意：cur 所指的节点始终代表未排序链表的第一个节点，
        // 我们要做的就是将它插入到已排序链表里面
        while (cur != null) {
            // 如果本来就是有序（从小到大）的，则两个指针都往后移动
            if (pre.val <= cur.val) {
                pre = cur;
                cur = cur.next;
            } else {
                // 能来到这里说明 pre 所指节点比 cur 所指节点要大，那么需要将 cur 插入到 pre 的前面，
                // 准确的说，是在 p 到 pre 这个区间内，找到一个合适的位置将 cur 进行插入
                ListNode p = dummy;
                // 如果 p 的 next 节点比 cur 小，说明还没有找到一个合适的位置，
                // 则将 p 往后移动
                while (p.next != null && p.next.val < cur.val) {
                    p = p.next;
                }
                // 当 while 结束后，就找到了大于 cur 的节点，p 就指向该节点的前一个节点，
                // 然后进行交换
                // -1 -> 5 -> 3
                //  p   pre  cur
                // 为了防止链表断裂，需要保存 cur 的下一个节点
                pre.next = cur.next;
                cur.next = p.next;
                p.next = cur;
                // 完成插入后，cur 需要来到未排序节点的新的第一个节点
                cur = pre.next;
            }
        }
        return dummy.next;
    }
}
