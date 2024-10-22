package SwordToOfferSolution._57_02_ContinuousSquenceWithSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 和为 s 的连续正数序列
 *
 * 题目描述：
 * 输入一个正数 s，打印出所有和为 s 的连续正数序列（至少含有两个数）。
 * 例如输入 15，由于 1+2+3+4+5=4+5+6=7+8=15，所以结果打印出 3 个连续序列 1～5、4～6 和 7～8。
 *
 * 思路：
 * 1. 使用两个指针 left 和 right：
 *    1.1) 如果从 left 到 right 的序列的和大于 s，则可以从序列中去掉较小的值，即增大 left 的值；
 *    1.2) 如果从 left 到 right 的序列的和小于 s，则可以从序列中增加较大的值，即增大 right 的值，让这个序列包含更多的数；
 *    1.3) 如果从 left 到 right 的序列的和等于 s，那么我们就找到了一组结果，此时需要记录下来；
 *         然后再从 left+1 的地方开始找下一组，因此，left 需要向右移动。
 * 2. 注意在求序列和的时候，由于公差是 1，所以可以直接用求和公式：n*(a1+an)/2。
 */
public class Solution {
    public int[][] findContinuousSequence(int target) {

        int left = 1;
        int right = 2;
        List<int[]> res = new ArrayList<>();
        while (left < right) {
            int curSum = (right - left + 1) * (left + right) >> 1;
            if (curSum == target) {
                int[] nums = new int[right - left + 1];
                int i = left;
                for (int j = 0; j < nums.length; j++) {
                    nums[j] = i++;
                }
                res.add(nums);
                // 向右移动 left，继续收集下一组满足要求的序列
                left++;
            } else if (curSum < target) {
                right++;
            } else {
                left++;
            }
        }

        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.deepToString(solution.findContinuousSequence(9)));
    }
}
