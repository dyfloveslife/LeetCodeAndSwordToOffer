package LeetCodeSolution.AlgorithmThought._02_DP._376_WiggleSubsequence;

import java.util.Arrays;

/*
 * 最长摆动序列
 *
 * 题目描述：
 * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。
 * 第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
 *
 * 例如，[1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3) 是正负交替出现的。
 * 相反, [1,4,7,2,5] 和 [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
 *
 * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
 *
 * 示例：
 * 输入: [1,17,5,10,13,15,10,5,16,8]
 * 输出: 7
 * 解释: 这个序列包含几个长度为 7 摆动序列，其中一个可为 [1,17,10,13,10,16,8]。
 *
 * 思路：
 * 1. 整体思路与“最长上升子序列”相同，但是本题不能用一个数组来表示以 i 为结尾的最长上升子序列；
 * 2. 因为 i 有可能是最长摆动子序列上升后的元素，也可能是下降后的元素；
 * 3. 因此可以使用两个数组来保留上升的状态和下降的状态；
 * 4. 即 up[i] 表示以 nums[i] 结尾的最长摆动子数组的长度，也就是说， nums[i] 相比于前一个元素是上升的；
 * 5. down[i] 也类似。
 */
public class Solution {

    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                // 当出现下降的时候，需要在有效的升序数量基础上加 1
                if (nums[j] > nums[i]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                    // 当出现上升的时候，需要在有效的降序数量基础上加 1
                } else if (nums[j] < nums[i]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, Math.max(up[i], down[i]));
        }
        return res;
    }

    // 状态压缩
    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int up = 1;
        int down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                down = up + 1;
            } else if (nums[i - 1] < nums[i]) {
                up = down + 1;
            }
        }
        return Math.max(up, down);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 7, 4, 9, 2, 5};
        int[] nums2 = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        System.out.println(solution.wiggleMaxLength(nums1));
        System.out.println(solution.wiggleMaxLength(nums2));
        System.out.println(solution.wiggleMaxLength(nums3));
        System.out.println("==");

        System.out.println(solution.wiggleMaxLength2(nums1));
        System.out.println(solution.wiggleMaxLength2(nums2));
        System.out.println(solution.wiggleMaxLength2(nums3));
    }
}
