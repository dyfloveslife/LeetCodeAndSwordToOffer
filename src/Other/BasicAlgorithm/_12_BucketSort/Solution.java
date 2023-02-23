package Other.BasicAlgorithm._12_BucketSort;

/*
 * 桶排序是一个大的逻辑概念，计数排序是桶排序的具体实现。
 * 桶排序、计数排序、基数排序都是稳定的，不是基于比较的排序，与实际的数据状况有关。
 * 时间复杂度 O(N)
 * 空间复杂度 O(N)
 *
 *  题目：给定一个数组，求排序之后相邻两数的最大差值，要求时间复杂度为 O(N)，不能使用非基于比较的排序。
 *  思路：
 *  1. 如果数组中有 n 个数，则准备 n + 1 个桶；
 *  2. 遍历数组，找到最小值和最大值:
 *	    如果 min==max，说明数组中只有一种数，则最大差值为 0，就返回 0；
 *	    如果不相等，则将最小值放到第 0 个桶里，最大值放到第 n 个桶里；
 *	    那中间的数字放到哪里呢？将最小值到最大值的范围等分成 n + 1 份，该数属于哪个范围就放到那个桶里。
 *  3. 设置一个空桶，用来查找两个桶之间的最大差值，而在一个桶内不可能产生最大差值。
 *  4. 最大差值就来自于前一个桶的最大值以及后一个桶的最小值。
 *  5. 只统计每个桶内的两个信息，包括所有进入到该桶的最大值和最小值，以及该桶是否进来过数字。
 *  6. 每进一个数，则只更新该桶的最大值和最小值，然后记录进来过数字。
 *  7. 从头遍历桶，如果该桶为空，则继续遍历下一个桶；如果是非空桶，则找前一个离它最近的非空桶。
 *  8. 计算 前一个离它最近的非空桶的最大值 和 当前非空桶的最小值之间的 差值，即用最小值减最大值。
 *  9. 就这样可以算出许多的差值，然后用一个全局变量，计算出这些差值中的最大值即可。
 */
public class Solution {
    public int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        // 首先找到数组中的最小值和最大值
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        // 说明数组中只有一种数，则返回 0
        if (min == max) {
            return 0;
        }

        // 定义三个数组，表示每个桶中的信息，分别表示桶中是否有数、桶中的最大值、桶中的最小值
        boolean[] hasNum = new boolean[n + 1];
        int[] maxs = new int[n + 1];
        int[] mins = new int[n + 1];
        int bucketId;
        // 当前数字去几号桶，相应的桶内的信息（最大值、最小值、桶内是否有数字）就得更新
        for (int num : nums) {
            bucketId = getBucketId(n, num, min, max);
            mins[bucketId] = hasNum[bucketId] ? Math.min(mins[bucketId], num) : num;
            maxs[bucketId] = hasNum[bucketId] ? Math.max(maxs[bucketId], num) : num;
            hasNum[bucketId] = true;
        }

        int ans = 0;
        int lastMax = maxs[0];
        // 找到每一个非空桶和离它左边最近的非空桶，用当前的最小减左边的最大即可
        for (int i = 1; i <= n; i++) {
            if (hasNum[i]) {
                ans = Math.max(ans, mins[i] - lastMax);
                // lastMax 用于记录"左边最近的非空桶的最大值"
                lastMax = maxs[i];
            }
        }

        return ans;
    }

    // 当前数字进对应的桶
    private int getBucketId(long n, long num, long min, long max) {
        return (int) ((num - min) * n / (max - min));
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {3, 6, 9, 1};
        int[] nums2 = {10};
        int[] nums3 = {15255, 15256, 15257};
        int[] nums4 = {100, 3, 2, 1};
        int[] nums5 = {1, 1, 1, 1};
        int[] nums6 = {2, 99999999};

        System.out.println(solution.maxGap(nums1)); // 3
        System.out.println(solution.maxGap(nums2)); // 0
        System.out.println(solution.maxGap(nums3)); // 1
        System.out.println(solution.maxGap(nums4)); // 97
        System.out.println(solution.maxGap(nums5)); // 0
        System.out.println(solution.maxGap(nums6)); // 99999997
    }
}
