package LeetCodeSolution.DataStructure._03_Tree._109_ConvertSortedListToBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/*
 * 有序链表转换二叉搜索树
 *
 * 题目描述：
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 思路：
 * 1. 由于链表中的节点是有序的，因此可以将链表转换成数组，然后通过数组转换成二叉搜索树；
 * 2. 但转换成数组的话，就不是该题考察的地方了；
 * 3. 可以通过快慢指针，找到链表的中间节点，以及靠近中间节点的那两个节点；
 * 4. 然后再通过递归的方式创建二叉搜索树的左右子树；
 * 5. 其实这种方式也可以当做数组来看待。
 */
public class Solution {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 先将链表转换成数组，然后再将数组转换成二叉搜索树
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        List<Integer> list = new ArrayList<>();
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            list.add(cur.val);
            cur = cur.next;
        }
        int[] nums = new int[list.size()];
        int index = 0;
        for (int i : list) {
            nums[index++] = i;
        }

        return process(nums, 0, nums.length - 1);
    }

    private TreeNode process(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int middle = left + ((right - left) >> 1);
        TreeNode root = new TreeNode(nums[middle]);
        root.left = process(nums, left, middle - 1);
        root.right = process(nums, middle + 1, right);
        return root;
    }

    public TreeNode sortedListToBST1(ListNode head) {
        if (head == null) {
            return null;
        }
        // 由于采用的是递归，因此如果只有头节点 head 的话，那么就直接创建只含有一个节点的二叉搜索树即可
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode preMid = getPreMid(head);
        ListNode middle = preMid.next;
        // 将两个链表断开后，分别处理两部分链表
        preMid.next = null;

        TreeNode root = new TreeNode(middle.val);
        root.left = sortedListToBST1(head);
        root.right = sortedListToBST1(middle.next);
        return root;
    }

    // 返回的是链表中中间节点的前一个节点
    private ListNode getPreMid(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return pre;
    }
}
