package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._769_MaxChunksToMakeSorted;

/*
 * 最多能完成排序的块
 *
 * 题目描述：
 * 数组 arr 是 [0, 1, ..., arr.length - 1] 的一种排列，我们将这个数组分割成几个“块”，并将这些块分别进行排序。
 * 之后再连接起来，使得连接的结果和按升序排序后的原数组相同。
 * 我们最多能将数组分成多少块？
 *
 * 思路：
 * 0. 从前先后遍历，区间最大值等于数组索引的时候，就得到了一个所求区间；
 *    也就是说，一个区间内最大的数字，不应该大于这个区间最右边的 index，因此我们可以从左向右遍历数组的元素，
 *    如果已经观测到的最大值等于这个区间的 index，则可划分区间。
 * 1. 如果在分割后的数组中（也就是子数组中），最大元素的下标与 nums[i] 不等，那么排序后就无法形成从小到大连续的数组了；
 * 2. 所以只有找到最大的数与下标相等时，用来计数的 count 才能加 1；
 * 3. 如果没有找到，那么子数组继续向后寻找，找到后重新查找新的子数组。
 */
public class Solution {
    public int maxChunksToSorted(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        int cur = nums[0];
        for (int i = 0; i < nums.length; i++) {
            cur = Math.max(cur, nums[i]);
            if (cur == i) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 0, 2, 3, 4};

        System.out.println(solution.maxChunksToSorted(nums));
    }
}
