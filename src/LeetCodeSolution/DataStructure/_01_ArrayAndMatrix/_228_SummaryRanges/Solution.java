package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._228_SummaryRanges;

import java.util.ArrayList;
import java.util.List;

/*
 * 汇总区间
 *
 * 题目描述：
 * 给定一个无重复元素的有序整数数组 nums。
 * 返回恰好覆盖数组中所有数字的最小有序区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x。
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 *   "a->b"，如果 a != b
 *   "a"，如果 a == b
 *
 * 思路：
 * 1.
 *
 */
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int n = nums.length;
        int i = 0;
        while (i < n) {
            int low = i;
            i++;
            while (i < n && nums[i] == nums[i - 1] + 1) {
                i++;
            }
            // 开始结算
            ans.add(deal(nums[low], nums[i - 1]));
        }

        return ans;
    }

    private String deal(int a, int b) {
        return a == b ? a + "" : a + "->" + b;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {0, 1, 2, 4, 5, 7};
        int[] nums2 = {0, 2, 3, 4, 6, 8, 9};

        System.out.println(solution.summaryRanges(nums1)); // ["0->2","4->5","7"]
        System.out.println(solution.summaryRanges(nums2)); // ["0","2->4","6","8->9"]
    }
}
