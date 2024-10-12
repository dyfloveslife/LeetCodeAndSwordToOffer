package SwordToOfferSolution._18_01_DeleteNodeInList;

/*
 * 在 O(1) 的时间内删除链表节点
 *
 * 题目描述：
 * 给定单向链表的头指针和一个结点指针，定义一个函数在 O(1) 时间删除该结点。
 *
 * 思路：
 * 1. 如果按照这道题的题干要求，删除的是一个 int 类型的 val，那么需要对链表进行遍历，从而找到这个节点值等于 val 的节点，找到之后再进行删除操作；
 * 2. 对于链表中节点的删除操作，需要格外注意待删除的节点是头节点或者尾节点的情况，避免空指针的发生。
 * 3. 这里新创建一个节点 dummy，让其指向头节点 head，指针 cur 的初始值为 dummy。
 * 4. 我们使用 cur.next 作为判断条件，如果 cur.next 为待删除的节点 val，那么就让 cur 的 next 域指向 val 的下一个节点，也就是相当于跳过 val 节点。
 * 5. 显然，函数最后返回的是 dummy 的 next 域。好处在于：即使待删除的节点是头节点 head，那么也能够正确的返回。
 * 6. 由于只删除一个节点，因此，删除了一个 val 节点以后，直接 break 就可以了，不需要再往后遍历了。
 * 7. 然而，如果给定的待删除节点是 ListNode 类型的呢？
 * 8. 如果给定的待删除节点是 ListNode 类型，那么可以使用覆盖的方式，让待删除节点 val 的后继赋值给 val。
 * 9. 如果给定的待删除节点是尾节点，那么我们需要 O(n) 的时间复杂度来从头节点开始，找到待删除节点的前驱节点。
 * 10.找到该前驱节点后，令其 next 域指向 null 即可。
 * 11. 所以，如果给定的待删除节点是 ListNode 类型的，那么它的时间复杂度为 (O(1)*(n-1)+O(n))/n，是 O(1) 的。
 */
public class Solution {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 给定待删除节点是 int 类型
    public ListNode deleteNode(ListNode head, int target) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 注意 cur 指针的初始状态
        ListNode cur = dummy;

        while (cur.next != null) {
            if (cur.next.val == target) {
                cur.next = cur.next.next;
                // 注意
                break;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    // 给定待删除节点是 ListNode 类型
    public ListNode deleteNode(ListNode head, ListNode target) {
        if (head == null || target == null) {
            return null;
        }
        // 待删除节点是头节点
        if (head == target) {
            return null;
            // 如果待删除节点不是尾节点，那么就将待删除节点的后继赋值给待删除节点，
            // 然后待删除节点就来到了后面，那么直接跨过待删除节点即可
        } else if (target.next != null) {
            ListNode nxt = target.next;
            target.val = nxt.val;
            target.next = nxt.next;
            // 待删除节点是尾节点，那么我们需要从链表的头节点开始遍历，
            // 找到待删除节点的前驱节点
        } else {
            ListNode cur = head;
            while (cur.next != target) {
                cur = cur.next;
            }
            cur.next = null;
        }
        return head;
    }
}
