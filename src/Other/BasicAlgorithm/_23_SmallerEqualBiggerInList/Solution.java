package Other.BasicAlgorithm._23_SmallerEqualBiggerInList;

/*
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * 题目描述 1：
 * 给定一个单向链表的头节点 head，在给定一个整数 pivot，实现一个调整链表的函数。
 * 将链表调整为左部分的值都是小于 pivot 的节点，中间都是等于 pivot 的节点，右部分的值都是大于 pivot 的部分。
 *
 * 题目描述 2 (进阶)：
 * 使该算法具有稳定性，时间复杂度为 O(N)，额外空间复杂度为 O(1)
 *
 * 思路 1：
 * 开一个节点类型的数组，使用荷兰国旗问题后，再将其串成链表即可。
 *
 * 思路 2：
 * 1. 设置三个 node 类型的变量：less、equal、more；
 * 2. 遍历一遍链表，将第一个小于 pivot 的节点给 less，将第一个等于 pivot 的节点给 equal，将第一个大于 pivot 的节点给 more；
 * 3. 再遍历一遍链表，同时将不同的值放进不同的区域中，然后串起来；
 * 4. 最后将“小于区域”的尾部与“等于区域”的头部重连，将“等于区域”的尾部与“大于区域”的头部重连即可；
 * 5. 相当于将一个大连表拆分成三个小链表，然后将这三个小链表重新串起来。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 方法一：使用数组
    public static ListNode listPartition1(ListNode head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        int i = 0;
        // 统计节点总数
        while (cur != null) {
            i++;
            cur = cur.next;
        }

        ListNode[] nodeArr = new ListNode[i];
        i = 0;
        cur = head;
        // 将节点填入到节点类型的数组中
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        // 使用荷兰国旗问题进行数据划分
        arrPartition(nodeArr, pivot);
        // 划分完之后再将数组中的节点串起来
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        // 处理最后一个节点
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    public static void arrPartition(ListNode[] nodeArr, int pivot) {
        int less = -1;
        int more = nodeArr.length;
        int index = 0;
        while (less != more) {
            if (nodeArr[index].val < pivot) {
                swap(nodeArr, ++less, index++);
            } else if (nodeArr[index].val > pivot) {
                swap(nodeArr, --more, index);
            } else {
                index++;
            }
        }
    }

    private static void swap(ListNode[] nodeArr, int i, int j) {
        ListNode temp = nodeArr[i];
        nodeArr[i] = nodeArr[j];
        nodeArr[j] = temp;
    }

    // 方法二：使用几个变量就可以搞定
    public static ListNode listPatition2(ListNode head, int pivot) {
        if (head == null || head.next == null) {
            return head;
        }

        // 划分三个区域
        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode moreHead = null;
        ListNode moreTail = null;
        // 存储下一个节点
        ListNode nextNode = null;

        // 将链表中每个节点划分到三个不同的区域中
        while (head != null) {
            // nextNode 用于保存节点，避免链表“断开”
            nextNode = head.next;
            // 初始化的时候，让头节点与链表分离
            head.next = null;
            if (head.val < pivot) {
                // 如果一开始 small 区域中没有节点的话，则直接存进去
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                    // 否则，如果 small 区域中已经存在其它的节点的话，则让尾部指向新加入的节点
                    // 然后再让尾指针后移到新加入的那个节点
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.val == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            } else {
                if (moreHead == null) {
                    moreHead = head;
                    moreTail = head;
                } else {
                    moreTail.next = head;
                    moreTail = head;
                }
            }
            // 后移，进行下一个节点的判断
            head = nextNode;
        }
        // 连接 small 区域和 equal 区域
        if (smallTail != null) {
            smallTail.next = equalHead;
            // 如果 equalTail == null，说明 equal 区域里没有数据，则 equalTail 直接等于 small 区域的 smallTail 就可以了
            equalTail = equalTail == null ? smallTail : equalTail;
        }
        // 再将剩下的 more 区域进行连接
        if (equalTail != null) {
            equalTail.next = moreHead;
        }
        // 最后看一看返回哪个 head
        return smallHead != null ? smallHead : equalHead != null ? equalHead : moreHead;
    }
}
