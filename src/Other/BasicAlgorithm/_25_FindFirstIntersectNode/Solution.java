package Other.BasicAlgorithm._25_FindFirstIntersectNode;

/*
 * 两个单链表相交的一系列问题
 *
 * 题目描述：
 * 给定两个单链表，如果这两个链表相交，则返回相交的第一个节点；如果不相交，则返回 null。
 * 链表可能有环，也有可能无环。
 * 时间复杂度 O(N+M)，空间复杂度 O(1)。
 *
 * 思路：
 * 1. 先判断链表是否有环：
 *    1.1）方法一：使用快慢指针；
 *    1.2) 方法二：使用哈希表；
 * 2. 如果两个链表都没有环：
 *    2.1) 要是使用 map 的话，先将链表 1 的节点放到 map 中，然后每次遍历链表 2 中的一个节点，
 *         都去 map 中查，第一个在的节点，就是两个链表相交的第一个节点；如果链表 2 遍历到最后都没有在 map 中找到对应的节点，
 *         则说明该两个链表不相交。
 *    2.2) 要是不用 map 的话，可以先遍历一遍链表 1，得到链表 1 的长度 len1 和最后一个节点 end1；链表 2 做同样的操作，得到长度 len2 和最后一个节点 end2；
 *         比较 end1 和 end2 是不是同一个节点，即内存地址是否相同。
 *         2.2.1) 如果 end1 != end2，则该两个链表不可能相交，因为如果相交的话，它俩的最后一个节点应该是同一个；
 *         2.2.2) 如果 end1 == end2，则该两个链表是相交的，但未必是第一个相交的节点。此时假设链表 1 的长度为 100，链表 2 的长度为 80，
 *              可以先让链表 1 走 20 步，然后链表 1 和链表 2 再一起走，最后就能碰到相交的节点。
 * 3. 如果一个链表无环，另一个链表有环，则不可能相交。
 * 4. 如果两个链表都有环，则有三种情况：
 *    4.1) 如果各自成环，则不可能相交；
 *    4.2) 如果两个链表先相交，然后再共享同一个环，则含有相交的第一个节点；
 *    4.3) 如果两个链表先不相交，而是直接共享同一个环，则含有相交的第一个节点。
 * 5. 如何区分上面的三种情况？
 *    5.1) 如果链表 1 的第一个入环节点的内存地址 等于 链表 2 的第一个入环节点的内存地址，则属于情况 4.2)；
 *         这时不看带有环的部分，此时又回到了和 2) 相同的问题，一样的操作。
 *    5.2) 如果链表 1 的第一个入环节点的内存地址 不等于 链表 2 的第一个入环节点的内存地址，则此时有可能是情况 4.1) 或者是情况 4.3)；
 *         5.2.1) 此时不断沿着链表 1 的第一个入环节点的 next 往后判断，如果走了一圈又转回自己，还没有遇到链表 2 的第一个入环节点的话，则为情况 4.1)；
 *         5.2.2) 如果在遍历的时候链表 1 在不停的 next 之后遇到链表 2 的第一个入环节点的话，则为情况 4.3)，
 *                此时返回距离链表 1 更近的入环节点也可以，返回距离链表 2 更近的入环节点也可以。
 */
public class Solution {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 主逻辑，得到两个链表的相交节点
    public static ListNode getIntersectNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        // 分别得到链表 1 和链表 2 的第一个入环的节点
        ListNode loop1 = getLoopNode(head1);
        ListNode loop2 = getLoopNode(head2);

        // 两个链表都无环的相交问题
        if (loop1 == null && loop2 == null) {
            return noLoopNode(head1, head2);
        }
        // 两个有环链表的相交问题
        if (loop1 != null && loop2 != null) {
            return boothLoop(head1, loop1, head2, loop2);
        }
        // 如果一个链表无环，另一个链表有环，则不可能相交
        return null;
    }

    // 在带有环的链表中返回第一个入环的节点
    public static ListNode getLoopNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;
        // 快指针和慢指针在环中相遇的时候就停
        while (slow != fast) {
            // 快指针在走的时候，如果 next 或 next.next 为 null 了，就说明没有环，则返回 null
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果快慢指针在环中相遇的话，就让其中一个指针回到链表的头部
        // 然后两个指针再一起走
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        // 最后由于快满指针相遇了，所以返回哪个指针都可以
        return slow;
    }

    // 对于两个无环链表，返回它们相交的第一个节点，对应 2.2)
    public static ListNode noLoopNode(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode cur1 = head1;
        ListNode cur2 = head2;
        // n 表示两个链表长度的差值
        int n = 0;
        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        // 经过上面的两个 while，此时的 cur1 和 cur2 都来到了各自链表的最后一个节点
        // 如果 cur1 和 cur2 不相等的话，则说明链表不可能相交
        if (cur1 != cur2) {
            return null;
        }
        // 根据差值 n 的不同，让 cur1 和 cur2 指向不同链表的头节点
        cur1 = n > 0 ? head1 : head2;
        // 如果 cur1 已经指向 head1 了，则让 cur2 指向 head2
        cur2 = cur1 == head1 ? head2 : head1;
        n = Math.abs(n);

        // 长的链表先走 n 步，然后再和短的链表一起走
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        // 相遇的时候返回两个链表第一个相交的节点
        return cur1;
    }

     // 对于两个有环链表，返回它们第一个相交的节点，对应情况 4)；
     // loop1 表示链表 1 中的第一个入环的节点；
     // loop2 表示链表 2 中的第一个入环的节点。
    public static ListNode boothLoop(ListNode head1, ListNode head2, ListNode loop1, ListNode loop2) {
        ListNode cur1 = null;
        ListNode cur2 = null;
        // 如果两个链表的入环节点相同的话，则复用之前的逻辑即可
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
            // 对应情况 5.2)
        } else {
            cur1 = loop1.next;
            // 不断沿着链表 1 的第一个入环节点的 next 往后判断，如果走了一圈又转回自己，还没有遇到链表 2 的第一个入环节点的话，
            // 则为情况 4.1)
            while (cur1 != loop1) {
                // 如果在遍历的时候链表 1 在不停的 next 之后遇到链表 2 的第一个入环节点的话，则为情况 4.3)，
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }
}
