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
        if (head == null) return null;

        // 指针 oddHead 表示在节点编号为奇数的节点上进行移动
        // 指针 evenHead 表示在节点编号为偶数的节点上进行移动
        ListNode oddHead = head, evenHead = head.next;
        // 程序最终会将给定的链表分成两个链表，一个奇数链表，一个偶数链表，
        // 而 cur 表示偶数链表的头节点，我们最后需要将奇数链表的最后一个节点的 next 域指向 cur，
        // 最终完成拼接
        ListNode cur = evenHead;

        ListNode oddNext = null, evenNext = null;
        while (oddHead.next != null && oddHead.next.next != null && evenHead.next != null) {
            // 防止链表断裂
            oddNext = oddHead.next.next;
            evenNext = evenHead.next.next;
            // 修改节点的引用
            oddHead.next = oddNext;
            evenHead.next = evenNext;
            // 来到下一个节点，继续处理
            oddHead = oddNext;
            evenHead = evenNext;
        }
        // 最后，需要将奇数链表中最后一个节点的 next 域指向偶数节点的头节点
        oddHead.next = cur;
        return head;
    }
}
