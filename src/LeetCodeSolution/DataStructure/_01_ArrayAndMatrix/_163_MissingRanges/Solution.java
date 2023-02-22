package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._163_MissingRanges;

import java.util.ArrayList;
import java.util.List;

/*
 * 缺失的区间
 *
 * 题目描述：
 * 给定一个排序的整数数组 nums，其中元素的范围在闭区间 [lower, upper] 当中，返回不包含在数组中的缺失区间。
 *
 * 思路：
 * 1. 直接模拟即可。
 */
public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> ans = new ArrayList<>();
        int n = nums.length;
        if (n == 0) {
            ans.add(deal(lower, upper));
            return ans;
        }
        if (nums[0] > lower) {
            ans.add(deal(lower, nums[0] - 1));
        }
        for (int i = 1; i < n; i++) {
            int pre = nums[i - 1], cur = nums[i];
            // pre 和 cur 之间存在其它数字
            if (cur - pre > 1) {
                ans.add(deal(pre + 1, cur - 1));
            }
        }
        if (nums[n - 1] < upper) {
            ans.add(deal(nums[n - 1] + 1, upper));
        }

        return ans;
    }

    private String deal(int a, int b) {
        return a == b ? a + "" : a + "->" + b;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0, 1, 3, 50, 75};
        int lower1 = 0, upper1 = 99;
        int lower2 = -100, upper2 = 100;
        int lower3 = 0, upper3 = 200;

        System.out.println(solution.findMissingRanges(nums, lower1, upper1)); // ["2", "4->49", "51->74", "76->99"]
        System.out.println(solution.findMissingRanges(nums, lower2, upper2)); // ["-100->-1", "2", "4->49", "51->74", "76->100"]
        System.out.println(solution.findMissingRanges(nums, lower3, upper3)); // ["2", "4->49", "51->74", "76->200"]
    }
}
