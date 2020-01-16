package Other.BasicAlgorithm._22_IsPalindromeList;

import java.util.Stack;

/*
 * 判断一个链表是否是回文结构
 *
 * 思路 1：
 * 1. 若可以使用额外空间的话，可以使用一个栈；
 * 2. 将链表从头节点灌入栈，然后出栈的时候，每遍历一个节点就和出栈的节点比较；
 * 3. 如果每次比较的节点值都相等，则说明此链表具有回文结构。
 * 4. 或者用两个指针，快指针走两步，慢指针走一步，等到快指针走完的时候，慢指针大约走到链表的中间节点（注意奇偶数节点的差异）；
 * 5. 然后将慢指针指向的节点以及后面的所有节点入栈，此时再从头遍历链表，不停的出栈比较，这也是可行的方法。
 * 6. 时间复杂度 O(N)。
 *
 * 思路 2：
 * 1. 若不适用辅助空间；
 * 2. 开始的时候还是使用快慢指针，当慢指针来到链表的中间节点的时候，令中间节点指向 null；
 * 3. 其次修改右半部分每个节点的指针，让其指向前节点；
 * 4. 然后，首尾两个指针分别对应比较，一直到比到中间节点都是相等的话，则说明具有回文结构；
 * 5. 最后还需要将右半部分节点的 next 指针再次修改回来即可；
 * 6. 注意：对于偶数个节点，慢指针需要来到两个相同节点的前一个节点。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 使用 N 的辅助空间
    public static boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        // 先将链表中的每个节点入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 从头节点开始遍历，比较每个节点值与栈中弹出的节点值是否相等
        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            // 然后再比较链表中的下一个节点
            head = head.next;
        }
        return true;
    }

    // 使用 N/2 的辅助空间，利用快慢指针
    public static boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode cur = head;
        ListNode right = head.next;
        // 快慢指针移动
        // 对于奇数个节点，则 right 最终会指向中间节点的下一个节点，也就是右半部分节点中的第一个节点
        // 对于偶数个节点，则 right 最终会指向相同节点的右边那个节点
        while (cur.next != null && cur.next.next != null) {
            right = right.next;
            cur = cur.next.next;
        }

        Stack<ListNode> stack = new Stack<>();
        // 将右半部分的节点入栈
        while (right != null) {
            stack.push(right);
            right = right.next;
        }
        // 不断将节点出栈，再从链表的头节点开始比较
        while (!stack.isEmpty()) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 不适用额外的辅助空间，即使用双指针的方法
    public static boolean isPalindome3(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            // 对于奇数个节点，slow 来到中间节点
            // 对于偶数个节点，slow 来到相同中间节点的前一个节点
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时，slow 已经来到了中间节点
        // 让 fast 指向右半部分的第一个节点
        fast = slow.next;
        // 中间节点 slow 指向 null
        slow.next = null;

        ListNode temp = null;
        // 右半部分所有的节点指针反转
        while (fast != null) {
            // temp 用于暂存节点
            temp = fast.next;
            // 链表反转操作
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        // 经过上面的 while 后，slow 已经来到最后一个节点了
        // 然后让 temp 指向最后一个节点
        temp = slow;
        fast = head;
        boolean res = true;
        // 检查是否是回文数组
        while (slow != null && fast != null) {
            if (slow.val != fast.val) {
                res = false;
                // 不能直接 return，因为还需要将右半部分的链表修改过来
                break;
            }
            // slow 和 fast 共同移向中间节点
            slow = slow.next;
            fast = fast.next;
        }
        slow = temp.next;
        temp.next = null;
        // 调整右半部分链表的指向
        while (slow != null) {
            fast = slow.next;
            slow.next = temp;
            temp = slow;
            slow = fast;
        }
        return res;
    }
}
