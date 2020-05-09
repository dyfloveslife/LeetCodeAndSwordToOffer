package SwordToOfferSolution._36_ConvertBinarySearchTree;

/*
 * 二叉搜索树与双向链表
 *
 * 题目描述：
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
 * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 思路：
 * 1. 需要考虑以下三个要素：
 *    1.1) 排序链表：可使用中序遍历的方式得到从小到大的节点；
 *    2.2) 双向链表：在构建相邻节点之间的关系时，假设当前节点为 cur，前驱节点为 pre，则应满足：
 *                   pre.right = cur, cur.left = pre；
 *    3.3) 循环链表：假设链表的头尾节点分别为 head 和 tail，则应满足 head.left = tail, tail.right = head。
 * 2. 整体思路就是：使用中序遍历访问树的每个节点 cur，并在访问每个节点时构建 cur 和前驱节点 pre 的引用指向，
 * 3. 中序遍历完成后再构建头节点和尾节点的指向。
 */
public class Solution {
    class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    // pre 用于记录当前节点的前驱节点
    // head 用于记录二叉搜索树最左下角的节点，即最小值
    public Node pre, head;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        // 构建双向链表
        dfs(root);
        // 通过 dfs 已经将双向链表构建好了，现在需要构建首尾节点
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void dfs(Node cur) {
        if (cur == null) {
            return;
        }

        dfs(cur.left);
        // 如果当前节点的前驱为空，则说明当前节点就是头节点，即二叉搜索树的最小值，
        // 因此将其设置为头节点
        if (pre == null) {
            head = cur;
            // 如果前驱节点不为空，则需要将当前节点和前驱节点连接起来
        } else {
            pre.right = cur;
            cur.left = pre;
        }
        pre = cur;
        dfs(cur.right);
    }
}
