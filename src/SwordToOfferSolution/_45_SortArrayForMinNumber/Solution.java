package SwordToOfferSolution._45_SortArrayForMinNumber;

import java.util.Arrays;

/*
 * 把数组排成最小的数
 *
 * 题目描述：
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 例如输入数组 {3, 32, 321}，则打印出这 3 个数字能排成的最小数字 321323。
 *
 * 思路:
 * 1. 比较字符串 s1s2 与 s2s1 的大小，如果 s1s2 > s2s1，则把 s2 排在前面；否则把 s1 排在前面；
 * 2. 由于在拼接数字的时候有可能会涉及到整数范围的溢出（大数问题），所以使用字符串表示；
 * 3. 注意在拼接的时候，由于 "" 的位置不同，则会产生不同的效果。
 */
public class Solution {
    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }

        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(str, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));
        StringBuilder sb = new StringBuilder();
        for (String s : str) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {3, 30, 34, 5, 9};

        System.out.println(solution.minNumber(nums));

        String s1 = 123 + "" + 123;
        String s2 = 123 + 123 + "";
        System.out.println(s1); // 123123
        System.out.println(s2); // 246
    }
}
