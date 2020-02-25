package Other.AdvancedAlgorithm._14_02_LongestSumSubArrayLength;

/*
 * 求累加和等于 aim 的最长子数组（全是正数）
 *
 * 思路：
 * 使用双指针。
 */
public class Solution {

    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == aim) {
                len = Math.max(len, right - left + 1);
                // 在相等的情况下，left 可以往右，right 也可以往右
                // 但记得在往右的过程中，需要将边界的那个值在 sum 中减掉
                sum -= arr[left];
                left++;
            } else if (sum < aim) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left];
                left++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 1, 1, 1, 1, 1, 1};
        System.out.println(getMaxLength(arr, 6));
    }
}
