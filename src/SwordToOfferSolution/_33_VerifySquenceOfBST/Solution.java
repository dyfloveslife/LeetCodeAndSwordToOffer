package SwordToOfferSolution._33_VerifySquenceOfBST;

import java.util.Deque;
import java.util.LinkedList;

/*
 * 二叉搜索树（排序树、查找树）的后序遍历序列
 *
 * 题目描述：
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回 true，否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 * 思路 1：递归
 * 1. 后序遍历序列中，最后一个值是根节点；
 * 2. 遍历除去根节点的序列，从左到右找到第一个大于根节点的位置，
 *    则该位置左侧全部为小于根节点的节点，
 *    右侧全部（除了数组最后一个位置）为大于根节点的节点；
 * 3. 遍历右侧，如果有小于根节点的节点，说明不符合二叉搜索树的定义，因此直接返回 false；
 * 4. 分别递归判断左右子树是否是二叉搜索树。
 *
 * 思路 2：单调栈
 *                 10
 *                /  \
 *               6    14
 *              / \   / \
 *             2   8 12  16
 * 1. 使用 单调栈 实现，从栈底到栈顶表示的是由小到大的顺序；
 * 2. 对于上面的后序遍历: [2, 8, 6, 12, 16, 14, 10]，从数组尾部的 10 一直到 12，都表示的是以 10 为根的 10 的右子树上的节点；
 * 3. 这里将它们进入到单调栈中，只要比根节点大，则就一直入栈，直到当前的元素比栈顶元素小为止；
 * 4. 如果当前元素比栈顶元素小，则说明来到了根的左子树；
 * 5. 采取同样的方法，将左子树的根节点入栈，重复上面的操作即可。
 */
public class Solution {
    // 单调栈
    public boolean verifyPostorder1(int[] postOrder) {
        if (postOrder == null) {
            return false;
        }

        Deque<Integer> stack = new LinkedList<>();
        int pre = Integer.MAX_VALUE;
        for (int i = postOrder.length - 1; i >= 0; i--) {
            // 左子树的元素必须小于单调栈中栈顶(peek)的元素，否则就不满足二叉树
            if (postOrder[i] > pre) {
                return false;
            }
            while (!stack.isEmpty() && postOrder[i] < stack.peek()) {
                pre = stack.pop();
            }
            stack.push(postOrder[i]);
        }
        return true;
    }

    // 递归
    public boolean verifyPostorder2(int[] postOrder) {
        if (postOrder == null || postOrder.length == 0) {
            return false;
        }

        return verify(postOrder, 0, postOrder.length - 1);
    }

    private boolean verify(int[] postOrder, int start, int end) {
        // 递归结束的条件
        // 如果 start == end，则说明只有一个节点了，那么就不需要判断了
        // 如果 start > end，则说明没有节点了，那么也不需要判断了
        if (start >= end) {
            return true;
        }

        // 得到根节点的值
        int rootVal = postOrder[end];
        int curIndex = start;
        // 找到第一个大于根节点的位置
        // curIndex < end：满足索引从小到大的原则
        // postOrder[curIndex] < root：满足根节点的值要比根节点左子树上的值要大的原则
        while (curIndex < end && postOrder[curIndex] < rootVal) {
            curIndex++;
        }
        // 遍历根节点右子树上的值
        for (int i = curIndex; i < end; i++) {
            if (postOrder[i] < rootVal) {
                return false;
            }
        }
        return verify(postOrder, start, curIndex - 1) && verify(postOrder, curIndex, end - 1);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] postOrder = {1, 2, 5, 10, 6, 9, 4, 3};

        System.out.println(solution.verifyPostorder1(postOrder));
        System.out.println(solution.verifyPostorder2(postOrder));
    }
}
