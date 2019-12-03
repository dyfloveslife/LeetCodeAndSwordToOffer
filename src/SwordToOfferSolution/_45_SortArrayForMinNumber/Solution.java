package SwordToOfferSolution._45_SortArrayForMinNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 把数组排成最小的数
 * 思路:
 * 1. 比较字符串 s1s2 与 s2s1 的大小，如果 s1s2 > s2s1，则把 s2 排在前面；否则把 s1 排在前面；
 * 2. 由于在拼接数字的时候有可能会涉及到整数范围的溢出（大数问题），所以使用字符串表示；
 * 3. 注意在拼接的时候，由于 "" 的位置不同，则会产生不同的效果。
 */
public class Solution {
    public String printMinNumber(int[] nums) {
        int length = nums.length;
        if (nums == null || length < 0) return "";

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < length; i++)
            list.add(nums[i]);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer str1, Integer str2) {
                String s1 = str1 + "" + str2;
                String s2 = str2 + "" + str1;
                return s1.compareTo(s2);
            }
        });

        String res = "";
        for (Integer j : list)
            res += j;
        return res;
    }

    public static void main(String[] args) {
        String s1 = 123 + "" + 123;
        String s2 = 123 + 123 + "";
        System.out.println(s1); // 123123
        System.out.println(s2); // 246
    }
}
