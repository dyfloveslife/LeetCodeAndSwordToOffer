package SwordToOfferSolution._61_ContinousCards;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * 扑克牌中的顺子
 *
 * 题目描述：
 * 从扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这 5 张牌是不是连续的。
 * 2～10 为数字本身，A 为 1，J 为 11，Q 为 12，K 为 13，而大、小王可以看成任意数字。
 *
 * 思路一：
 * 1. 将数组排序，然后统计数组中 0 的个数，最后统计排序之后的数组中相邻数字之间的空缺总数；
 * 2. 如果空缺的总数少于 0 的个数，则可以将 0 补到空缺的位置，使之构成连续的数组；
 * 3. 如果数组中非 0 数字重复出现，即牌中存在对子，则该数组不是连续的。
 *
 * 思路二：
 * 1、使用哈希表，本质上是需要找到数组中最大的数和最小的数，并且满足 max-min<5 即可；
 * 2、
 */
public class Solution {
    public boolean isContinuesCards(int[] nums) {
        if (nums == null || nums.length < 5) {
            return false;
        }
        // 将数组排序
        Arrays.sort(nums);

        // 统计数组中 0 的个数
        int zero = 0;
        // 相邻数字之间的空缺总数
        int gap = 0;

        // i 不能到达 nums.length-1 的位置，为了防止越界
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                zero++;
                continue;
            }
            // 若存在对子，则直接返回
            if (nums[i] == nums[i + 1]) {
                return false;
            }
            // 统计相邻数字之间的空缺总数
            // 若 2 和 4 相邻，则它们之间的空缺数：4 - 2 - 1 = 1
            // 若 2 和 5 相邻，则它们之间的空缺数：5 - 2 - 1 = 2
            gap += nums[i + 1] - nums[i] - 1;
        }

        return gap <= zero;
    }

    public boolean checkDynasty(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        int max = 0, min = 14;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }

            max = Math.max(max, num);
            min = Math.min(min, num);
            if (set.contains(num)) {
                return false;
            }

            set.add(num);
        }

        return max - min < 5;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = {1, 3, 2, 6, 4};
        int[] arr2 = {10, 11, 0, 12, 6};
        int[] arr3 = {0, 6, 9, 0, 7};

        System.out.println(solution.isContinuesCards(arr1));
        System.out.println(solution.isContinuesCards(arr2));
        System.out.println(solution.isContinuesCards(arr3));
        System.out.println("---");

        System.out.println(solution.checkDynasty(arr1));
        System.out.println(solution.checkDynasty(arr2));
        System.out.println(solution.checkDynasty(arr3));
    }
}
