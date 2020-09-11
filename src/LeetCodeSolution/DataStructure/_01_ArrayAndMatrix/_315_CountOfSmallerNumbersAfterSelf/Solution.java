package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._315_CountOfSmallerNumbersAfterSelf;

import java.util.ArrayList;
import java.util.List;

/*
 * 计算右侧小于当前元素的个数
 *
 * 题目描述：
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质：counts[i] 的值是 nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 思路：
 * 1. 暴力的方法很容易想到，但时间复杂度为 O(N^2)；
 * 2. 采用递归排序，其实该题和【逆序对】是同一种类型的题目；
 * 3. 只不过该题在此基础上需要进行一些修改。
 */
public class Solution {

    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 结果数组，也就是最后需要返回的结果，也就是用于收集结果的数组
        count = new int[nums.length];

        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        // 归并排序
        mergeSort(nums, index, 0, nums.length - 1);

        for (int num : count) {
            res.add(num);
        }
        return res;
    }

    private void mergeSort(int[] nums, int[] index, int left, int right) {
        if (left == right) {
            return;
        }
        int middle = left + ((right - left) >> 1);
        mergeSort(nums, index, left, middle);
        mergeSort(nums, index, middle + 1, right);
        merge(nums, index, left, middle, right);
    }

    private void merge(int[] nums, int[] index, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        int cur = 0;
        int pos1 = left;
        int pos2 = middle + 1;
        // 这里比归并排序多了一个变量，用于统计当前位置 i 上的数有多少个比它小的
        int rightCount = 0;

        while (pos1 <= middle && pos2 <= right) {
            // 如果左边的数较大，那么右边的较小值个数加 1
            if (nums[index[pos1]] > nums[index[pos2]]) {
                help[cur] = index[pos2];
                rightCount++;
                pos2++;
            } else {
                // 如果右边的数更大，则将刚刚统计的个数 rightCount 加到 count 数组中
                help[cur] = index[pos1];
                count[index[pos1]] += rightCount;
                pos1++;
            }
            cur++;
        }
        // 左边有剩余，剩下的都是比右边的值要大的，所以需要添加
        while (pos1 <= middle) {
            help[cur] = index[pos1];
            count[index[pos1]] += rightCount;
            cur++;
            pos1++;
        }
        while (pos2 <= right) {
            help[cur++] = index[pos2++];
        }
        for (int i = 0; i < help.length; i++) {
            index[left + i] = help[i];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5, 2, 6, 1};

        System.out.println(solution.countSmaller(nums));
    }
}
