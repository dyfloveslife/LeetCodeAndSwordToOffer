package LeetCodeSolution.AlgorithmThought._01_DoublePointer._209_MinimumSizeSubarraySum;

/*
 * 长度最小的子数组
 *
 * 题目描述：
 * 给定一个含有 n 个正整数的数组和一个正整数 target，找出该数组中满足其和大于等于 target 的长度最小的连续子数组，并返回其长度。
 * 如果不存在符合条件的连续子数组，返回 0。
 *
 * 进阶:
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(nlog n) 时间复杂度的解法。
 *
 * 思路一：暴力
 * 1. 当外层循环遍历到当前元素时，需判断当前元素是否大于等于 target，如果是，则直接返回 1；
 * 2. 内层循环从当前元素的下一个元素开始遍历，不断计算累加和，然后再判断累加和是否大于等于 target 即可。
 *
 * 思路二：双指针
 * 1. 将两个指针包含的区间看做滑动窗口，计算滑动窗口内的元素和；
 * 2. 如果窗口内的和小了，则扩大窗口的右边，如果窗口内的和大了，则缩小窗口的左边；
 * 3. 类似于入队、出队操作。
 *
 * 思路三：前缀和 + 二分
 * 1. 思路一的时间复杂度为 O(n^2)，因为在确定每个子数组的下标后，找到长度最小的子数组的用时是 O(n)；
 * 2. 如果想要优化这个查找过程的话，可以使用二分，将时间复杂度控制在 O(log n)；
 * 3. 由于数组是正数，数组中每个元素的前缀和是不断上升的，即呈线性增长的，因此可以使用二分；
 * 4. 因此可以构成一个前缀和数组，它的最小长度为 1，最大长度为数组长度的最大值；
 * 5. 时间复杂度为 O(nlogN)，因为遍历数组需要 O(n)，而对于每个开始的下标，通过二分查找得到长度最小的子数组，需要 O(log n)。
 */
public class Solution {
    // 思路一
    public int minSubArrayLen1(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sum = nums[i];
            if (sum >= target) {
                return 1;
            }
            for (int j = i + 1; j < n; j++) {
                // 计算累加和
                sum += nums[j];
                if (sum >= target) {
                    ans = Math.min(ans, j - i + 1);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 思路二
    public int minSubArrayLen2(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int sum = 0;
        while (right < nums.length) {
            sum += nums[right];
            right++;
            while (sum >= target) {
                ans = Math.min(ans, right - left);
                sum -= nums[left];
                left++;
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    // 思路三
    public int minSubArrayLen3(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        // 构造前缀和数组
        int[] sumArr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sumArr[i + 1] = nums[i] + sumArr[i];
        }

        // 由于是在 sumArr 上操作二分，并且其长度为 n + 1
        // 因此 right 需要设置为 n
        int left = 1, right = n;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(sumArr, mid, target)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return check(sumArr, left, target) ? left : 0;
    }

    // 判断某个长度的子数组的和是否满足大于等于 target
    private boolean check(int[] sumArr, int k, int target) {
        for (int i = k; i < sumArr.length; i++) {
            if (sumArr[i] - sumArr[i - k] >= target) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {2, 3, 1, 2, 4, 3};
        int target1 = 7;

        System.out.println(solution.minSubArrayLen1(target1, nums1)); // 2
        System.out.println(solution.minSubArrayLen2(target1, nums1)); // 2
        System.out.println(solution.minSubArrayLen3(target1, nums1)); // 2

        System.out.println("--");
        int[] nums2 = {1, 4, 4};
        int target2 = 1;
        System.out.println(solution.minSubArrayLen1(target2, nums2)); // 1
        System.out.println(solution.minSubArrayLen2(target2, nums2)); // 1
        System.out.println(solution.minSubArrayLen3(target2, nums2)); // 1

        System.out.println("--");
        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println(solution.minSubArrayLen1(target3, nums3)); // 0
        System.out.println(solution.minSubArrayLen2(target3, nums3)); // 0
        System.out.println(solution.minSubArrayLen3(target3, nums3)); // 0

        System.out.println("--");
        int[] nums4 = {1, 2, 3, 4, 5};
        int target4 = 15;
        System.out.println(solution.minSubArrayLen1(target4, nums4)); // 5
        System.out.println(solution.minSubArrayLen2(target4, nums4)); // 5
        System.out.println(solution.minSubArrayLen3(target4, nums4)); // 5
    }
}