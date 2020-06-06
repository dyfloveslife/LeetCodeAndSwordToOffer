package LeetCodeSolution.DataStructure._08_LinkedList._725_SplitLinkedListInParts;

/*
 * 分隔链表
 *
 * 题目描述：
 * 给定一个头结点为 root 的链表, 编写一个函数以将链表分隔为 k 个连续的部分。
 * 每部分的长度应该尽可能的相等: 任意两部分的长度差距不能超过 1，也就是说可能有些部分为 null。
 * 这 k 个部分应该按照在链表中出现的顺序进行输出，并且排在前面的部分的长度应该大于或等于后面的长度。
 * 返回一个符合上述规则的链表的列表。
 *
 * 示例：
 * 1->2->3->4, k = 5 // 5 结果 [ [1], [2], [3], [4], null ]
 *
 * 思路：
 * 1. 首先求出链表的长度 len，然后 size=len/k，extra=len%k；
 * 2. 表示有 extra 个子链表的长度是 size+1，剩余子链表的长度是 size；
 * 3. 通过控制 curSize 来达到【前面部分的长度大于等于后面部分的长度】这个要求。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        if (head == null || k < 0) {
            return new ListNode[k];
        }

        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        // 有 extra 个大小为 size+1 的子链表，
        // 剩余的链表长度为 size，
        // extra 表示靠前子链表的数量，而 size 表示靠后子链表的数量
        int size = len / k;
        int extra = len % k;

        ListNode[] res = new ListNode[k];
        cur = head;
        for (int i = 0; cur != null && i < k; i++) {
            res[i] = cur;
            // 先求前半部分的子链表
            int curSize = size + (extra-- > 0 ? 1 : 0);
            // 这里的边界最好需要画图，才能看清楚
            // cur 来到的是第一个部分的最后一个节点
            for (int j = 0; j < curSize - 1; j++) {
                // 第一轮，cur 来到节点 4
                cur = cur.next;
            }

            // 第一轮，nex 来到节点 5
            ListNode nex = cur.next;
            // 该子链表已经遍历结束，先将其断开
            cur.next = null;
            // cur 来到下一个子链表的第一个节点
            cur = nex;
        }
        return res;
    }
}
