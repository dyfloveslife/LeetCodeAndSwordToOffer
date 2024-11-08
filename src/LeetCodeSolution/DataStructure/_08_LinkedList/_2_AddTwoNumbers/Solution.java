package LeetCodeSolution.DataStructure._08_LinkedList._2_AddTwoNumbers;

/*
 * 两数相加
 *
 * 题目描述：
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 思路：
 * 1. 还是利用两个数字字符串相加的计算方法，只不过这里涉及到链表的节点，所以需要特殊处理；
 * 2. 创建一个假节点 dummy，然后用一个 pre 引用指向它；
 * 3. 在求得每一位后，表示它是一个节点，因此需要创建该节点，然后指针后移，以便于处理下一个节点。
 */
public class Solution {
    static class ListNode {
        private int val;
        private ListNode next;

        private ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 直接模拟即可
     *
     * @param l1 ListNode
     * @param l2 ListNode
     * @return ListNode
     */
    public ListNode adfTwoNumbers(ListNode l1, ListNode l2) {
        // 虚拟头节点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        // 存储进位
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            // 获取两个链表当前位置的值，如果某个链表已经遍历完成，则补 0
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            // 计算和与进位
            int sum = x + y + carry;
            carry = sum / 10;

            // 创建新节点，存储当前位的值
            cur.next = new ListNode(sum % 10);
            cur = cur.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return dummy.next;
    }
}
