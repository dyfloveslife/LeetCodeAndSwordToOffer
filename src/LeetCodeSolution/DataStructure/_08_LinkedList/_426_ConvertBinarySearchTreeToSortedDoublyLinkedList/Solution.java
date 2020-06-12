package LeetCodeSolution.DataStructure._08_LinkedList._426_ConvertBinarySearchTreeToSortedDoublyLinkedList;

import java.util.Stack;

/*
 * 将二叉搜索树转化为排序的双向链表
 *
 * 题目描述：
 * 将一个二叉搜索树就地转化为一个已排序的双向循环链表。
 * 对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 * 特别地，我们希望可以 就地 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
 * 还需要返回链表中最小元素的指针。
 *
 * 思路：
 * 1. 既然是二叉搜索树，那么可以使用中序遍历，在遍历的过程中通过改变节点的引用关系来构造出双向的循环链表；
 * 2. 可以新创建一个节点，让它指向双向链表的头节点；
 * 3. 最后需要将头节点的 left 指针指向最后一个节点，将尾节点的 right 指向第一个节点。
 */
public class Solution {
    class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 迭代
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node pre = new Node(0, null, null);
        Node res = pre;

        Stack<Node> stack = new Stack<>();

        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                // 每弹出一个节点，就开始构造它的前驱和后继
                pre.right = root;
                root.left = pre;

                // 后移指针
                pre = root;

                root = root.right;
            }
        }
        // 经过 while 后，pre 会来到最后一个节点
        pre.right = res.right;
        res.right.left = pre;

        return res.right;
    }

    // 递归
    private Node first;
    private Node pre;

    public Node treeToDoublyList2(Node root) {
        if (root == null) {
            return null;
        }

        inorder(root);
        // 经过 inorder 后，pre 回来到最后一个节点
        pre.right = first;
        first.left = pre;
        return first;
    }

    // 中序遍历
    private void inorder(Node node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        if (first == null) {
            first = node;
        }
        if (pre != null) {
            pre.right = node;
            node.left = pre;
        }
        pre = node;
        inorder(node.right);
    }
}
