package LeetCodeSolution.AlgorithmThought._02_DP._300_LongestIncreasingSubsequence;

import java.util.Arrays;

/*
 * 最长上升子序列
 *
 * 题目描述：
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 思路一：DP
 * 1. 声明数组 dp[i] 表示以元素 nums[i] 结尾的情况下，当前最长上升子序列的长度；
 * 2. 然后程序最终需要返回数组 dp 中最大的值；
 * 3. 需要注意的是，我们知道了 dp[0...i-1]，如何求 dp[i] 呢？
 *    既然 dp[i] 是子序列，那么我们可以通过找到 i 之前的那些结尾比 nums[i] 小的子序列，
 *    然后将 nums[i] 接到最后就可以成为一个新的递增子序列，而这个新的递增子序列也就相当于增加了 1；
 * 4. 在找的过程中，很定会有多个子序列，因此我们只关注的是最长的那个子序列即可；
 * 5. 时间复杂度：最坏情况下为 O(n^2)。
 *
 * 思路二：二分
 * 1. 类似于电脑上的“蜘蛛纸牌”游戏，在玩游戏的过程中会分成不同的“部分”，每个部分的纸牌摆放规则是：
 *    只有小牌才能放在大牌上面，如果有更大牌的话，则需要新开一个“部分”；
 *    这样的话，每个“部分”最上面的那个牌构成了一个有序的序列，然后对于一张给定的牌，需要通过二分
 *    的方式将它放到对应的“部分”上面；
 * 2. 等到牌都摆放完了以后，“部分”的数量就是最终的结果；
 * 3. 时间复杂度为 O(nlogn)。
 */
public class Solution {
    // DP
    public int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        // 子序列最少要包含自己，因此初始化为 1
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    // 注意这里是 dp[j]+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int num : dp) {
            res = Math.max(res, num);
        }

        return res;
    }

    // 二分
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] top = new int[nums.length];
        // 初始化“部分”的数量
        int parts = 0;
        for (int i = 0; i < nums.length; i++) {
            // 准备插入到“部分”中的牌
            int poker = nums[i];

            // 由于需要将待插入的牌从左到右插入到小的“部分”中，
            // 因此下面的是二分的变体，
            // 这里 right = parts 是因为由于“部分”是在不断增加的，
            // 因此我插入的时候的右边界是众多“部分”的右边界，
            // 也就是说，在每次插入一个新牌的时候，如果这个新牌没有找到合适的部分，
            // 则需要新开出一个“部分”，既然新开了一个新的“部分”，那么就需要扩大右边界了
            int left = 0;
            int right = parts;
            while (left < right) {
                int middle = left + ((right - left) >> 1);
                if (poker < top[middle]) {
                    // 如果 poker 比中间的牌小，则 right 应该来到 middle，
                    // 因为 poker 有可能会放到 middle 的上面
                    right = middle;
                } else if (poker > top[middle]) {
                    // 但是，如果 poker 比中间的 middle 大的话，
                    // 那么就不可能放到 middle 的上面了，因为我们规定小牌只能放在大牌上面，
                    // 当前的牌 poker 比 middle 大，因此只能来到下一个 parts 了
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }

            // 也就是说，原本的范围是 [left, parts]，此时 left 来到了 parts 部分，
            // 说明没有找到合适的“部分”，则新建一个“部分”
            if (left == parts) {
                parts++;
            }
            // 把这张牌放到“部分”的顶部
            top[left] = poker;
        }
        return parts;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        System.out.println(solution.lengthOfLIS1(nums));
        System.out.println(solution.lengthOfLIS2(nums));
    }
}
