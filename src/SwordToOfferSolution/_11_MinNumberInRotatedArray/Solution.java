package SwordToOfferSolution._11_MinNumberInRotatedArray;

/*
 * 旋转数组的最小数字
 *
 * 题目描述：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组 {3, 4, 5, 1, 2} 为 {1, 2, 3, 4, 5} 的一个旋转，该数组的最小值为 1。
 *
 */
public class Solution {
    public int minNumberInRotateArray(int[] array) {
        if (array.length <= 0) {
            return 0;
        }

        int index1 = 0;
        int index2 = array.length - 1;
        //如果旋转后的数组是排序数组本身，则第一个元素就是最小的
        int indexMid = index1;
        while (array[index1] >= array[index2]) {
            if (index2 - index1 == 1) {
                indexMid = index2;
                break;
            }
            indexMid = (index1 + index2) / 2;
            // 如果 index1、index2、indexMid 都相等的话，只能顺序查找
            if (array[index1] == array[index2] && array[indexMid] == array[index1]) {
                return minInOrder(array, index1, index2);
            }
            if (array[indexMid] >= array[index1]) {
                index1 = indexMid;
            }
            if (array[indexMid] <= array[index2]) {
                index2 = indexMid;
            }
        }
        return array[indexMid];
    }

    private int minInOrder(int[] array, int index1, int index2) {
        int res = array[index1];
        for (int i = index1 + 1; i < index2; i++) {
            if (array[i] < res) {
                return array[i];
            }
        }
        return res;
    }
}
