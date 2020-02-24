package Other.AdvancedAlgorithm._16_BiggestSubBSFInTree;

/*
 * 如何构建最大搜索二叉子树
 *
 * 思路：
 * 使用递归进行携带信息，包括最大搜索子树的大小、最大搜索子树的头节点、最大搜索子树的最大值和最小值
 */
public class Solution {
    public static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    // 定义递归函数需要返回的信息
    public static class ReturnType {
        int size;
        Node head;
        int max;
        int min;

        ReturnType(int size, Node head, int max, int min) {
            this.size = size;
            this.head = head;
            this.max = max;
            this.min = min;
        }
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        // 左右子树分别携带信息进行返回
        Node left = head.left;
        Node right = head.right;
        ReturnType leftSubTreesInfo = process(left);
        ReturnType rightSubTreesInfo = process(right);

        // 由于之前左右子树返回了信息，现在计算构成整个二叉搜索树的信息
        int includeSelf = 0;
        // 左右子树的头部必须分别是 head 的左右孩子
        if (leftSubTreesInfo.head == left && rightSubTreesInfo.head == right
                && head.val > leftSubTreesInfo.max && head.val < rightSubTreesInfo.min) {
            includeSelf = leftSubTreesInfo.size + 1 + rightSubTreesInfo.size;
        }

        int p1 = leftSubTreesInfo.size;
        int p2 = rightSubTreesInfo.size;
        // 点前二叉搜索子树的大小就是：左右子树的大小以及当前节点的大小，它们中的最大值
        int maxSize = Math.max(Math.max(p1, p2), includeSelf);

        Node maxHead = p1 > p2 ? leftSubTreesInfo.head : rightSubTreesInfo.head;
        if (maxSize == includeSelf) {
            maxHead = head;
        }
        return new ReturnType(maxSize, maxHead,
                Math.max(Math.max(leftSubTreesInfo.max, rightSubTreesInfo.max), head.val),
                Math.min(Math.min(leftSubTreesInfo.min, rightSubTreesInfo.min), head.val));
    }
}