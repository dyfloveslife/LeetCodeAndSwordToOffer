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
 */
public class Solution {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 非递归实现
    private static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> list = new ArrayList<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            //出栈的时候需要放在 list 中
            list.add(stack.pop());
        }
        return list;
    }

    // 递归实现
    private static ArrayList<Integer> list_Recursively = new ArrayList<>();

    private static ArrayList<Integer> printListFromTailToHead_Recursively(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead_Recursively(listNode.next);
            list_Recursively.add(listNode.val);
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

    public static void reverse(ListNode head){
        if (head == null) {
            return ;
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
