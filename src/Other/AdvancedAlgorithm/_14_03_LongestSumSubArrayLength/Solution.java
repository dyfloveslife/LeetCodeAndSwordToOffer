package Other.AdvancedAlgorithm._14_03_LongestSumSubArrayLength;

/*
 * 求累加和小于等于 aim 的最长子数组（可正、可负、可 0）
 *
 */
public class Solution {
    public static int getLongestSubArray(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int[] min_sums = new int[arr.length];
        int[] min_sums_index = new int[arr.length];
        min_sums[arr.length - 1] = arr[arr.length - 1];
        min_sums_index[arr.length - 1] = arr.length - 1;

        // 生成两个辅助数组的过程
        for (int i = arr.length - 2; i >= 0; i--) {
            if (min_sums[i + 1] < 0) {
                min_sums[i] = arr[i] + min_sums[i + 1];
                min_sums_index[i] = min_sums_index[i + 1];
            } else {
                min_sums[i] = arr[i];
                min_sums_index[i] = i;
            }
        }

        int R = 0;
        int sum = 0;
        int res = 0;
        // 这里的 i 就是每一次的开头
        for (int L = 0; L < arr.length; L++) {
            while (R < arr.length && sum + min_sums[R] <= aim) {
                sum += min_sums[R];
                R = min_sums_index[R] + 1;
            }
            // 如果 R 不大于 L，则说明窗口内没有元素，则返回 0
            sum -= (R > L) ? arr[L] : 0;
            // L 到 R-1 位置
            res = Math.max(res, R - L);
            // 如果 0 位置的元素是 8，aim=6，则从一开始就不能构成最长子数组
            R = Math.max(R, L + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, -3, 4, -5, 7, 3, -6, 9};
        System.out.println(getLongestSubArray(arr, 6));
    }
}
