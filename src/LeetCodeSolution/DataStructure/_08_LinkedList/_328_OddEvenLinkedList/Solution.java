package LeetCodeSolution.DataStructure._08_LinkedList._328_OddEvenLinkedList;

/*
 * 奇偶链表
 *
 * 题目描述：
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 思路：
 * 1. 遍历一趟链表，交叉地改变奇偶节点的引用关系；
 * 2. 最后再将最后一个奇节点的 next 域指向偶节点链表中的第一个节点即可。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode oddHead = head;
        ListNode evenHead = head.next;
        ListNode cur = evenHead;

        while (oddHead.next != null && oddHead.next.next != null && evenHead.next != null) {
            ListNode oddNext = oddHead.next.next;
            ListNode evenNext = evenHead.next.next;

            oddHead.next = oddNext;
            evenHead.next = evenNext;

            oddHead = oddNext;
            evenHead = evenNext;
        }
        oddHead.next = cur;
        return head;
    }
}
