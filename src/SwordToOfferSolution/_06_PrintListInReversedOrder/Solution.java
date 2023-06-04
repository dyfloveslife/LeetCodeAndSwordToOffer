package SwordToOfferSolution._06_PrintListInReversedOrder;

import java.util.ArrayList;
import java.util.Stack;

/*
 * 从尾到头打印链表
 *
 * 题目描述：
 * 输入一个链表，按链表值从尾到头的顺序返回一个 ArrayList。
 * 也就是说，输入一个链表的头结点，从尾到头反过来打印出每个结点的值。
 *
 * 思路：
 * 1. 可以使用栈的先进后出的特点，将链表灌进栈中之后，再依次输出栈中的值即可，但额外空间复杂度为 O(N)；
 * 2. 还可以只使用遍历的方式，不使用额外空间。即第一次遍历的目的是为了确定链表的长度，而第二次遍历是
 *    为了将链表中的每个数从数组中的最后一个位置开始填入。
 */
public class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 不使用栈，不使用递归
    public int[] reversePrint1(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }

        int[] res = new int[count];
        node = head;
        for (int i = count - 1; i >= 0; i--) {
            res[i] = node.val;
            node = node.next;
        }
        return res;
    }

    // 非递归实现
    private static ArrayList<Integer> printListFromTailToHead(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        while (!stack.isEmpty()) {
            //出栈的时候需要放在 list 中
            list.add(stack.pop());
        }
        return list;
    }

    // 递归实现
    private static ArrayList<Integer> list_Recursively = new ArrayList<>();

    private static ArrayList<Integer> printListFromTailToHead_Recursively(ListNode head) {
        if (head != null) {
            printListFromTailToHead_Recursively(head.next);
            list_Recursively.add(head.val);
        }
        return list_Recursively;
    }

    // 使用栈
    public static int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }

        int count = 0;
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            count++;
            head = head.next;
        }

        int[] res = new int[count];
        int pos = 0;
        while (!stack.isEmpty()) {
            res[pos++] = stack.pop();
        }
        return res;
    }

    // 使用递归
    static ArrayList<Integer> list = new ArrayList<>();

    public static int[] reversePrint2(ListNode head) {
        reverse(head);
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    public static void reverse(ListNode head) {
        if (head == null) {
            return;
        }
        reverse(head.next);
        head = head.next;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(0);
        ListNode node = listNode;
        for (int i = 1; i < 10; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        ArrayList<Integer> list = printListFromTailToHead_Recursively(listNode);
        System.out.println(list);
    }
}
