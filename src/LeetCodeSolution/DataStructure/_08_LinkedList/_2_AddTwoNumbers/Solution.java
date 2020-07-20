package LeetCodeSolution.DataStructure._08_LinkedList._2_AddTwoNumbers;

/*
 * 两数相加
 *
 * 题目描述：
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 思路：
 * 1. 还是利用两个数字字符串相加的计算方法，只不过这里涉及到链表的节点，所以需要特殊处理；
 * 2. 创建一个假节点 dummy，然后用一个 pre 引用指向它；
 * 3. 在求得每一位后，表示它是一个节点，因此需要创建该节点，然后指针后移，以便于处理下一个节点。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode adfTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = (l1 != null) ? l1.val : 0;
            int n2 = (l2 != null) ? l2.val : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            // 开始新建节点
            ListNode node = new ListNode(sum);
            pre.next = node;
            // 后移
            pre = pre.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            pre.next = new ListNode(1);
        }
        return dummy.next;
    }
}
