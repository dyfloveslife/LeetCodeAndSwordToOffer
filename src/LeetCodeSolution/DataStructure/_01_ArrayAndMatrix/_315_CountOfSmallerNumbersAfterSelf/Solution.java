package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._315_CountOfSmallerNumbersAfterSelf;

import java.util.ArrayList;
import java.util.List;

/*
 * 计算右侧小于当前元素的个数
 *
 * 题目描述：
 * 给定一个整数数组 nums，按要求返回一个新数组 counts。
 * 数组 counts 有该性质：counts[i] 的值是 nums[i] 右侧小于 nums[i] 的元素的数量。
 *
 * 思路：
 * 1. 暴力的方法很容易想到，但时间复杂度为 O(N^2)；
 * 2. 采用递归排序，其实该题和【逆序对】是同一种类型的题目；
 * 3. 只不过该题在此基础上需要进行一些修改；
 * 4. 时间复杂度为 O(n log n)，空间复杂度为 O(n)。
 */
public class Solution {
    private int[] counts;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        int n = nums.length;
        counts = new int[n];

        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        // 归并排序
        mergeSort(nums, index, 0, n - 1);

        for (int num : counts) {
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
        int pos1 = left, pos2 = middle + 1;
        // 这里比归并排序多了一个变量，用于统计当前位置 i 上的数有多少个比它小的
        int rightCount = 0;

        while (pos1 <= middle && pos2 <= right) {
            // 如果左边的数较大，那么右边的较小值个数加 1
            if (nums[index[pos1]] > nums[index[pos2]]) {
                help[cur++] = index[pos2++];
                rightCount++;
            } else {
                // 如果右边的数更大，则将刚刚统计的个数 rightCount 加到 counts 数组中
                help[cur++] = index[pos1];
                counts[index[pos1]] += rightCount;
                pos1++;
            }
        }
        // 左边有剩余，剩下的都是比右边的值要大的，所以需要添加
        while (pos1 <= middle) {
            help[cur++] = index[pos1];
            counts[index[pos1]] += rightCount;
            pos1++;
        }
        while (pos2 <= right) {
            help[cur++] = index[pos2++];
        }
        for (int i = 0; i < help.length; i++) {
            index[left + i] = help[i];
        }
    }

    public List<Integer> countSmaller2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }

        int[] arr = new int[nums.length];
        // 新建一个list，用于排序
        List<Integer> list = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            // 将当前数字插入到新建list中
            // 使用二分查找找到插入位置
            int left = 0, right = list.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (num <= list.get(mid)) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 将当前数字插入到相应位置，保证list升序排列
            list.add(left, num);
            // 当前位置前所有数字均小于当前数字，将个数加入返回结果
            arr[i] = left;
        }
        for (int count : arr) {
            res.add(count);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {5, 2, 6, 1};
        int[] nums2 = {-1};
        int[] nums3 = {-1, -1};

        Solution solution = new Solution();
        System.out.println(solution.countSmaller(nums1)); // [2, 1, 1, 0]
        System.out.println(solution.countSmaller(nums2)); // [0]
        System.out.println(solution.countSmaller(nums3)); // [0, 0]

        System.out.println("--");
        System.out.println(solution.countSmaller2(nums1)); // [2, 1, 1, 0]
        System.out.println(solution.countSmaller2(nums2)); // [0]
        System.out.println(solution.countSmaller2(nums3)); // [0, 0]
    }
}
