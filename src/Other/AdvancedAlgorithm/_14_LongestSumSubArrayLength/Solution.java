package Other.AdvancedAlgorithm._14_LongestSumSubArrayLength;

import java.util.HashMap;

/*
 * 累加和等于 k 的最长子数组的长度
 *
 * 题目描述：
 * 给定一个包含正数、负数、零的数组，再给定一个整数目标值 aim，求累加和为 aim 的最长子数组的长度。
 * 例如：
 *     给定数组：[7, 3, 2, 1, 1, 7, -6, -1, 7]
 *     给定目标值：aim = 7
 *     则满足要求的最长子数组为：[3, 2, 1, 1, 7, -6, -1]，返回 7。
 *
 * 思路：
 *   [7, 3, 2, 1, 1, 7, -6, -1, 7]，aim=7
 *    0  1  2  3  4  5   6   7  8
 *
 * 1. 先求从 0 位置开始到任意 i 位置构成的的子数组的累加和 sum，即 arr[0]+arr[1]+arr[2]+...+arr[i]=sum；
 *    如果此时 sum = aim，则从 0 到 i 位置的子数组就是一个满足要求的解，
 *    但不一定是最长的，这里使用 HashMap 判断是否是最长的子数组；
 * 2. 对于数组 arr[0]、arr[1]、...、arr[j]、...、arr[i]...arr[n-1] 来说，
 *    假如从 0 位置开始到 i 位置构成的的子数组的累加和为 sum，如果从 0 到 j 位置构成的累加和为 sum-aim 的话，
 *    则从 j+1 位置到 i 位置所构成的累加和必然是 aim；
 * 3. 由于累加和为 0  这个数最早出现在 -1 位置，即在 HashMap 中表示为 (0, -1)，因为一个数都没有的情况下也能累加出 0；
 * 4. 将 arr[0]=7 加到 sum 中，即 sum=7，此时 sum 表示从 0 位置开始一直累加到 0 位置的累加和，
 *    则 sum-aim=7-7=0，即 0 这个累加和通过查 HashMap 出现在 -1 位置，所以从 -1 的下一个位置（0 位置）开始到当前 0 位置开始
 *    所构成的累加和是 7，此时长度为 1，由于 7 是新出现的累加和，所以将 (7, 0) 放到 HashMap 中；
 * 5. 将 arr[1]=3 加到 sum 中，即 sum=7+3=10，此时 sum-aim=10-7=3，则 3 这个累加和没有在 HashMap 中出现，
 *    所以以 3 结尾的情况下，不可能累加出 aim 为 7 的子数组，由于 10 是新出现的累加和，所以将 (10, 1) 放到 HashMap 中；
 * 6. 将 arr[2]=2 加到 sum 中，即 sum=10+2=12，此时 sum-aim=12-7=5，则 5 这个累加和没有在 HashMap 中出现，
 *    所以以 2 结尾的情况下，不可能累加出 aim 为 7 的子数组，由于 12 是新出现的累加和，所以将 (12, 2) 放到 HashMap 中；
 * 7. 将 arr[3]=1 加到 sum 中，即 sum=12+1=13，此时 sum-aim=13-7=6，则 6 这个累加和没有在 HashMap 中出现，
 *    所以以 1 结尾的情况下，不可能累加出 aim 为 7 的子数组，由于 13 是新出现的累加和，所以将 (13, 3) 放到 HashMap 中；
 * 8. 将 arr[4]=1 加到 sum 中，即 sum=13+1=14，此时 sum-aim=14-7=7，则 7 这个累加和在 HashMap 中出现了，
 *    所以从 0 开始的下一个位置到当前的 4 位置构成的累加和是 7，即从索引 1 到索引 4，长度是 4，将 (14, 4) 放进 HashMap；
 * 9. 以此类推；
 * 10. 一开始将 (0, -1) 加入到 HashMap 中是因为如果不加的话，则所有从 0 开始的可能性就都错过了。
 */
public class Solution {
    public static int longestSubArrayLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        // 先将 (0, -1) 放进 HashMap 中
        map.put(0, -1);
        int res = 0;
        int sum = 0;
        int curLen = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // 如果 sum-aim 这个累加和在 map 中出现
            if (map.containsKey(sum - aim)) {
                // 从当前 i 到 map.get(sum - aim) 所构成的满足要求的子数组的长度
                curLen = i - map.get(sum - aim);
                res = Math.max(curLen, res);
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {7, 3, 2, 1, 1, 7, -6, -1, 7};
        System.out.println(longestSubArrayLength(arr, 7));
    }
}
