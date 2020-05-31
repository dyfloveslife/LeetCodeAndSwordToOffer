package LeetCodeSolution.AlgorithmThought._02_DP._354_RussianDollEnvelopes;

import java.util.Arrays;

/*
 * 俄罗斯套娃信封问题
 *
 * 题目描述：
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 说明：不允许旋转信封。
 *
 * 思路一：DP
 * 1. 只要将数组进行排好序，然后利用“最长上升子序列”解即可；
 * 2. 排序的规则：如果 w 不同，则按 w 从小到大排序；
 *               如果 w 相同，则按 h 从大到小排序。
 * 3. 先对宽度 w 进行升序排序，如果遇到 w 相同的情况，则按照高度 h 降序排序。
 *
 * 思路二：二分
 * 1. 二分的思路与“最长上升子序列”中的“蜘蛛纸牌”相同；
 * 2. 只不过需要在主函数中调用二分的方法。
 */
public class Solution {

    // DP
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
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
    public int maxEnvelopes2(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0 || envelopes[0].length == 0) {
            return 0;
        }

        // 按照宽度升序排序，如果宽度相同，则按照高度降序排序
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            } else {
                return o2[1] - o1[1];
            }
        });

        int[] height = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

    private int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 表示每个“部分”
        int parts = 0;
        int[] top = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];

            int left = 0;
            int right = parts;
            while (left < right) {
                int middle = left + ((right - left) >> 1);
                if (poker < top[middle]) {
                    right = middle;
                } else if (poker > top[middle]) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }
            if (left == parts) {
                parts++;
            }
            top[left] = poker;
        }
        return parts;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] envelopes = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};

        System.out.println(solution.maxEnvelopes(envelopes));
        System.out.println(solution.maxEnvelopes2(envelopes));
    }
}
