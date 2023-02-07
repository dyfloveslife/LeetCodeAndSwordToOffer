package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._41_FirstMissingPositive;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 缺失的第一个正整数
 *
 * 题目描述：
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * 思路一：HashSet
 * 0. 首先将每个元素放到 Set 中，
 * 1. 然后从数字 1 开始遍历，若数字 x 不在 Set 中，说明缺失的就是该数，直接返回即可（此时如果能直接返回的话，说明原始数据中不含有数字 1）；
 * 2. 如果没有返回，则说明原始数字中含有 1，那么"其中没有出现的最小的正整数"就是数组长度加一。
 *
 * 思路二：排序 + 二分
 * 1. 先对数组排序，然后对于从 1 开始的每个元素，都去进行二分；
 * 2. 如果当前进行二分的 i 找不到的话，那么就返回这个 i 即可。
 *
 * 思路三：置换（将数组视为哈希表）
 * 1. 使用两次循环，第一次循环的作用是：将元素为 i 的数映射到索引为 i - 1 的位置，即索引 0 存储数字 1，索引 1 存储数字 2，索引 2 存储数字 3；
 * 2. 也就是说，对于每一个正整数，我们使用元素值作为索引去存储它在数组中对应的值；
 * 3. 整理完数组后，然后再遍历一遍数组，第一个缺失的正数将是映射关系不对的那个数；
 * 4. 此外，由于题目要求的限制（正整数），要找的数一定在 [1, n + 1] 里面，其中 n 为数组的长度；
 * 5. 但其实最后一个元素 n + 1 不用找，因为在前面 n 个元素都找不到的情况下，直接返回 n + 1 即可。
 */
public class Solution {
    // 思路一
    public int firstMissingPositive(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int length = nums.length;
        if (length == 0) {
            return 1;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 1; i <= length; ++i) {
            if (!set.contains(i)) {
                return i;
            }
        }
        // 如果遍历完数组后，没有找到第一个最小的正整数，
        // 那么就说明整个数组是一个升序的、公差为 1 的等差数列，
        // 所以，第一个最小的正整数应该就是数组的长度加一
        return length + 1;
    }

    // 思路二
    public int firstMissingPositive2(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int length = nums.length;
        if (length == 0) {
            return 1;
        }

        Arrays.sort(nums);
        for (int i = 1; i <= length; ++i) {
            // 等于 -1 说明这个数缺失了
            if (binarySearch(nums, i) == -1) {
                return i;
            }
        }

        return length + 1;
    }

    private int binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i <= j) {
            int mid = i + ((j - i) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        return -1;
    }

    // 思路三
    public int firstMissingPositive3(int[] nums) {
        if (nums == null) {
            return -1;
        }

        int length = nums.length;
        if (length == 0) {
            return 1;
        }

        for (int i = 0; i < length; i++) {
            // 将元素 i 映射到索引 i - 1 的位置
            // 例如，元素 3 应该放在索引 2 的位置
            while (nums[i] > 0 && nums[i] < length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        // [1, -1, 3, 4]
        //  0   1  2  3
        for (int i = 0; i < length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // 如果所有元素的摆放位置都正确，则直接返回数组长度加一
        return length + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 0};
        int[] nums2 = {3, 4, -1, 1};
        int[] nums3 = {7, 8, 9, 11, 12};

        System.out.println(solution.firstMissingPositive(nums1)); // 3
        System.out.println(solution.firstMissingPositive(nums2)); // 2
        System.out.println(solution.firstMissingPositive(nums3)); // 1
        System.out.println("--");
        System.out.println(solution.firstMissingPositive2(nums1)); // 3
        System.out.println(solution.firstMissingPositive2(nums2)); // 2
        System.out.println(solution.firstMissingPositive2(nums3)); // 1
        System.out.println("--");
        System.out.println(solution.firstMissingPositive3(nums1)); // 3
        System.out.println(solution.firstMissingPositive3(nums2)); // 2
        System.out.println(solution.firstMissingPositive3(nums3)); // 1
    }
}
