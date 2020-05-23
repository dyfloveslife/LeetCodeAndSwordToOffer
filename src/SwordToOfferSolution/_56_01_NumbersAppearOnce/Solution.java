package SwordToOfferSolution._56_01_NumbersAppearOnce;

import java.util.Arrays;

/*
 * 数组中只出现一次的两个数字
 *
 * 题目描述：
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。
 * 请写程序找出这两个只出现一次的数字。要求时间复杂度是 O(n)，空间复杂度是 O(1)。
 *
 * 思路：
 * 1. 两个相同的数，它们的异或结果是 0；任何一个数与 0 异或，其结果也是 0；
 * 2. 对于数组中，只有一个数字只出现了一次，而其它的数字只出现了两次，在找的时候，只需要将它们进行异或即可；
 * 3. 现在扩展到只有两个数字只出现了一次，而其它的数字只出现了两次，那么在找的时候，还是将它们进行异或操作；
 * 4. 然后在异或的结果中从右到左找到第一个为 1 的位置；
 * 5. 用这个位置上的数是不是 1 做划分，将原数组划分成一组在该位置上是 1 的部分，而另一组划分成在该位置上不是 1（也就是 0）的部分；
 * 6. 分别在这两部分做步骤（2）操作，即利用（2）中的思想，就可以分别得出这两部分只出现一次的数。
 *
 * 此外，异或运算是不考虑进位的加法运算，如下：
 *  0 ^ 0 = 0 + 0 = 0
 *  0 ^ 1 = 0 + 1 = 1
 *  1 ^ 1 = 1 + 1 = 0（不进位）
 */
public class Solution {
    // 重要的是如何将这两个不同的数划分在各自的组中
    public int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int res = 0;
        for (int num : nums) {
            res ^= num;
        }

        res = Integer.highestOneBit(res);

        int[] ans = {0, 0};
        for (int num : nums) {
            if ((res & num) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 10, 4, 1, 4, 3, 3};

        System.out.println(Arrays.toString(solution.singleNumbers(nums)));
    }
}
