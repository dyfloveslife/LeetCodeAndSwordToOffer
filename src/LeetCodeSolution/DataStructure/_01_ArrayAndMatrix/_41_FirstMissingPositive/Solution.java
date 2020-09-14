package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._41_FirstMissingPositive;

import java.util.Arrays;
import java.util.HashSet;

/*
 * 缺失的第一个正整数
 *
 * 题目描述：
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * 思路：
 * 1. 如果不考虑额外空间复杂度，那么可以从数字 1 开始，依次判断 2、3、4、... 是否在数组中；
 * 2. 如果的当前考虑的数不在数组中，那么我们就找到了这个缺失的最小正数。
 * 3. 然后就是二分查找的思路，先对数组排序，然后对于从 1 开始的每个元素，都去进行二分；
 * 4. 如果当前进行二分的 i 找不到的话，那么就返回这个 i 即可。
 * 5. 还有一种思路是：将 1 放在下标为 0 的位置，将 2 放在下标为 1 的位置；
 * 6. 整理完数组后，然后再遍历一遍数组，第一个遇到的元素值不等于下标的数，就是缺失的数。
 */
public class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null) {
            return -1;
        }
        // 注意此边界条件
        if (nums.length == 0) {
            return 1;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 1; i <= nums.length; ++i) {
            if (!set.contains(i)) {
                return i;
            }
        }
        // 如果遍历完数组后，没有找到第一个最小的正整数，
        // 那么就说明整个数组是一个升序的、公差为 1 的等差数列，
        // 所以，第一个最小的正整数因该就是数组的长度
        return nums.length + 1;
    }

    public int firstMissingPositive2(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 1;
        }

        Arrays.sort(nums);
        for (int i = 1; i <= nums.length; ++i) {
            // 等于 -1 说明这个数缺失了
            if (binarySearch(nums, i) == -1) {
                return i;
            }
        }
        return nums.length + 1;
    }

    private int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }

    public int firstMissingPositive3(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length == 0) {
            return 1;
        }

        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] > 0 && nums[i] < nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};
        int[] nums4 = {};

        System.out.println(solution.firstMissingPositive(nums1));
        System.out.println(solution.firstMissingPositive(nums2));
        System.out.println(solution.firstMissingPositive(nums3));
        System.out.println(solution.firstMissingPositive(nums4));
        System.out.println("--");
        System.out.println(solution.firstMissingPositive2(nums1));
        System.out.println(solution.firstMissingPositive2(nums2));
        System.out.println(solution.firstMissingPositive2(nums3));
        System.out.println(solution.firstMissingPositive2(nums4));
        System.out.println("--");
        System.out.println(solution.firstMissingPositive3(nums1));
        System.out.println(solution.firstMissingPositive3(nums2));
        System.out.println(solution.firstMissingPositive3(nums3));
        System.out.println(solution.firstMissingPositive3(nums4));

    }
}
