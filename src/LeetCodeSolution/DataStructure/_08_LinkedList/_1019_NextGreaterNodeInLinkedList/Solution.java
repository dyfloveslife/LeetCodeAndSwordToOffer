package LeetCodeSolution.DataStructure._08_LinkedList._1019_NextGreaterNodeInLinkedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 链表中的下一个更大节点
 *
 * 题目描述：
 * 给出一个以头节点 head 作为第一个节点的链表。链表中的节点分别编号为：node_1, node_2, node_3, ... 。
 * 简单地说，就是找出比当前节点大的距离当前节点最近的那个节点，如果没有的话，则返回 0。
 *
 * 示例：
 * 输入：[1,7,5,1,9,2,5,1]
 * 输出：[7,9,9,9,0,5,0,0]
 *
 * 思路：
 * 1. 单调栈的经典使用；
 * 2. 从栈底到栈顶依次是从大到小的单调顺序，如果由于节点 B 造成了节点 A 出栈，则节点 A 所对应的下一个更大节点就是 B；
 * 3. 如果没有任何一个节点造成 A 出栈，那么就说明链表已经遍历完了，最后栈中的这些节点就没有下一个更大节点，因此也就返回 0 了；
 * 3. 最好画图理解，下面的做法是从后往前遍历链表的，每当遍历到一个节点的时候就开始结算数组，这样一来，等到遍历到第一个节点
 *    的时候，数组就填充完了。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    public int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode cur = head;
        List<Integer> list = new ArrayList<>();
        int len = 0;
        while (cur != null) {
            list.add(cur.val);
            len++;
            cur = cur.next;
        }

        Stack<Integer> stack = new Stack<>();

        int[] res = new int[len];
        for (int i = 0; i < list.size(); i++) {
            while (!stack.isEmpty() && stack.peek() < list.get(i)) {
                stack.pop();
            }

            res[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(list.get(i));
        }
        return res;
    }
}
