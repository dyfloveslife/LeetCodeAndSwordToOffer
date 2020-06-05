package LeetCodeSolution.DataStructure._08_LinkedList._160_IntersectionOfTwoLinkedLists;

/*
 * 相交链表
 *
 * 题目描述：
 * 找到两个单链表相交的起始节点
 *
 * 思路：
 * 1. 设链表 A 的长度的 a+c，链表 B 的长度是 b+c，这里都加上 c 表示它们的公共节点；
 * 2. 那么可以得到：a+c+b=b+c+a
 * 3. 当访问 A 链表的指针访问到链表尾部时，令它从链表 B 的头部开始访问链表 B；
 * 4. 同样地，当访问 B 链表的指针访问到链表尾部时，令它从链表 A 的头部开始访问链表 A；
 * 5. 这样就能控制访问 A 和 B 两个链表的指针能同时访问到交点。
 * 6. 如果不存在交点，那么 a + b = b + a，以下实现代码中 l1 和 l2 会同时为 null，从而退出循环。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode l1 = headA;
        ListNode l2 = headB;

        while (l1 != l2) {
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }
}
