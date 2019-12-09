package SwordToOfferSolution._33_VerifySquenceOfBST;

/*
 * 二叉搜索树（排序树、查找树）的后序遍历序列
 * 思路：
 * 1. 后序遍历序列中，最后一个值是 根节点；
 * 2. 遍历除去根节点的序列，找到第一个大于 根节点 的位置，则该位置左侧全部为小于 根节点 的节点，右侧全部为大于 根节点 的节点；
 * 3. 遍历右子树，如果有小于 根节点 的节点，直接返回 false；
 * 4. 分别递归判断左右子树是否是二叉搜索树。
 */
public class Solution {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }

        return verify(sequence, 0, sequence.length - 1);
    }

    public boolean verify(int[] sequence, int start, int end) {
        // 递归结束的条件
        //if (end - start <= 1)
        if (start >= end) {
            return true;
        }

        // 得到 根节点 的值
        int rootVal = sequence[end];
        int curIndex = start;
        // 找到第一个大于 根节点 的位置
        // curIndex < end：满足索引从小到大的原则
        // sequence[curIndex] < root：满足 根节点 的值要比 根节点左子树上的值 要大的原则
        while (curIndex < end && sequence[curIndex] < rootVal) {
            curIndex++;
        }
        // 遍历根节点右子树上的值
        for (int i = curIndex; i < end; i++)
            if (sequence[i] < rootVal) {
                return false;
            }
        return verify(sequence, start, curIndex - 1) && verify(sequence, curIndex, end - 1);
    }
}
