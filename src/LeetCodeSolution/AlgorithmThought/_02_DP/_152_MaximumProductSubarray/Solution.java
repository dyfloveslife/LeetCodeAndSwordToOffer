package LeetCodeSolution.AlgorithmThought._02_DP._152_MaximumProductSubarray;

/*
 * 乘积最大子数组
 *
 * 题目描述：
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 * 思路：
 * 1. 思路和之前的求最大子数组的和类似，只不过需要额外设置一个用于记录当前最小值的变量；
 * 2. 定义 dpMax[i] 表示以第 i 个元素结尾的子数组所乘积的最大值；
 * 3. 对于 dpMax[i] 来说，有以下几种可能：
 *    当 nums[i] >= 0 并且 dpMax[i] > 0 时，得到 dpMax[i] = dpMax[i] * nums[i]；
 *    当 nums[i] >= 0 并且 dpMax[i] < 0 时，得到 dpMax[i] = nums[i]；
 *    当 nums[i] < 0 时，如果前面的累乘结果是一个很大的负数，则再乘以当前 nums[i] 的时候，会变成更大的正数，
 *       因此这里需要使用 dpMin 来记录以第 i 个元素结尾的子数组乘积的最小值。
 *       当 dpMin[i - 1] < 0 时，dpMax[i] = dpMin[i - 1] * nums[i]；
 *       当 dpMin[i - 1] >= 0 时，dpMax[i] = nums[i]。
 * 4. 由于 dpMax[i] 的取值只有以上三种，因此只需要从这三者之中取最大即可；
 * 5. 同理，dpMin[i] 也是只需要在这三者之中取最小即可。
 */
public class Solution {
    // 空间复杂度为 O(N)
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(dpMax[i - 1] * nums[i], Math.max(dpMin[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMax[i - 1] * nums[i], Math.min(dpMin[i - 1] * nums[i], nums[i]));
            res = Math.max(res, dpMax[i]);
        }
        return res;
    }

    // 空间复杂度为 O(1)
    public int maxProduct2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int dpMax = nums[0];
        int dpMin = nums[0];
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int preMax = dpMax;
            dpMax = Math.max(dpMax * nums[i], Math.max(dpMin * nums[i], nums[i]));
            dpMin = Math.min(dpMin * nums[i], Math.min(preMax * nums[i], nums[i]));
            res = Math.max(res, dpMax);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2, 3, -2, 4};
        int[] nums2 = {0, 2};
        int[] nums3 = {-4, -3};
        int[] nums4 = {-2, 3, -4};

        System.out.println(solution.maxProduct(nums1));
        System.out.println(solution.maxProduct(nums2));
        System.out.println(solution.maxProduct(nums3));
        System.out.println(solution.maxProduct(nums4));

        System.out.println("===");
        System.out.println(solution.maxProduct2(nums1));
        System.out.println(solution.maxProduct2(nums2));
        System.out.println(solution.maxProduct2(nums3));
        System.out.println(solution.maxProduct2(nums4));
    }
}
