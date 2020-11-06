package LeetCodeSolution.DataStructure._07_BitManipulation._1356_SortIntegersByTheNumberOf1Bits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * 根据数字二进制下 1 的数目排序
 *
 * 题目描述：
 *
 * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
 * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
 * 请你返回排序后的数组。
 *
 * 1 <= nums.length <= 500
 * 0 <= nums[i] <= 10^4
 *
 * 思路：
 * 1. 遍历每个数字，统计该数字中 1 出现的次数，然后重写排序即可；
 * 2. 在统计一个数的二进制表示中含有 1 的个数的时候，可以每次消去最右侧的那个 1，
 *    在消去的同时 cnt++，这样就完成了统计二进制中 1 的个数的方法。
 */
public class Solution {

    // 方法一
    public static int[] sortByBits1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // bit[i] 存储的是 i 这个数字的二进制形式中 1 出现的次数
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
            bit[num] = getCountOfOne(num);
        }

        // 对 list 中的元素按照指定方式进行排序
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                // 如果两个数字 o1 和 o2 中的二进制表示中，含有的 1 的个数不同，
                // 则按照含有 1 的数量从小到达排序
                if (bit[o1] != bit[o2]) {
                    return bit[o1] - bit[o2];
                    // 如果这两个数的二进制表示中，含有的 1 的个数相同，
                    // 则按照数字本身从小到大进行排序
                } else {
                    return o1 - o2;
                }
            }
        });

        // 对 list 排完序后，覆盖到原来的 nums 数组即可
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }

    // 方法二
    public static int[] sortByBits2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] map = new int[n];
        for (int i = 0; i < n; i++) {
            map[i] = Integer.bitCount(nums[i]) * 10_000_000 + nums[i];
        }

        Arrays.sort(map);
        for (int i = 0; i < n; i++) {
            map[i] = map[i] % 10_000_000;
        }
        return map;
    }

    // 统计 x 的二进制形式中 1 的个数
    private static int getCountOfOne(int x) {
        int ans = 0;
        while (x != 0) {
            x &= (x - 1);
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        System.out.println(Arrays.toString(sortByBits1(nums)));
        System.out.println(Arrays.toString(sortByBits2(nums)));
    }
}
