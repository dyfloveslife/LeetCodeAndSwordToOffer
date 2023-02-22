package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._162_FindPeakElement;

/*
 * 寻找峰值
 *
 * 题目描述：
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 思路一：线性扫描
 * 1. 由于给定的 nums[i] != nums[i + 1]，所以在遍历元素时，仅判断 nums[i] 和 nums[i + 1] 的大小即可；
 * 2. 时间复杂度 O(N)，空间复杂度 O(1)。
 *
 * 思路二：二分
 * 1. 因为起点是负无穷，开始时一定是上坡，其目标是找到第一个下降的点，序列从左到右是从"不满足"到"满足"状态；
 * 2. 如果 nums[mid] < nums[mid + 1]，说明还是不满足，此时不必包含 mid，即 left = mid + 1；
 * 3. 如果 nums[mid] > nums[mid + 1]，说明 mid 这个位置是满足状态，但不一定是第一个满足的，所以要把 mid 包含在内，继续向左侧寻找，即 right = mid；
 * 4. 循环的退出条件是 left = right；
 * 5. 时间复杂度 O(log n)，空间复杂度 O(1)。
 */
public class Solution {
    // 思路一
    public int findPeakElement1(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }

        return nums.length - 1;
    }

    // 思路二
    public int findPeakElement2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        int[] nums3 = {1};
        int[] nums4 = {1, 2};
        int[] nums5 = {1, 2, 1};
        int[] nums6 = {3, 2, 1};

        System.out.println(solution.findPeakElement1(nums1)); // 2
        System.out.println(solution.findPeakElement1(nums2)); // 1 或 5
        System.out.println(solution.findPeakElement1(nums3)); // 0
        System.out.println(solution.findPeakElement1(nums4)); // 1
        System.out.println(solution.findPeakElement1(nums5)); // 1
        System.out.println(solution.findPeakElement1(nums6)); // 0

        System.out.println("--");
        System.out.println(solution.findPeakElement2(nums1)); // 2
        System.out.println(solution.findPeakElement2(nums2)); // 1 或 5
        System.out.println(solution.findPeakElement2(nums3)); // 0
        System.out.println(solution.findPeakElement2(nums4)); // 1
        System.out.println(solution.findPeakElement2(nums5)); // 1
        System.out.println(solution.findPeakElement2(nums6)); // 0

    }
}
