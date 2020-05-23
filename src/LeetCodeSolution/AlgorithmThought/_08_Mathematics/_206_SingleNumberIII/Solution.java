package LeetCodeSolution.AlgorithmThought._08_Mathematics._206_SingleNumberIII;

import org.jcp.xml.dsig.internal.SignerOutputStream;

import java.util.Arrays;

/*
 * 只出现一次的数字Ⅲ
 *
 * 题目描述：
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。
 * 找出只出现一次的那两个元素。
 *
 * 思路：
 * 1. 在之前的 136 题中，恰好只有一个元素出现一次，其余每个元素均出现两次；
 *    我们全部将数字进行异或之后，最后的结果就是只出现一次的那个数字，但现在需要找出两个，该如何做？
 * 2. 由于将数组中的数全部异或之后，得到的数就是那两个只出现一次的数所异或的结果；
 * 3. 例如 [1, 2, 1, 3, 2, 5]，由于题目要求数组结构的独特性质，所以对数组中的数进行异或操作，
 *        其实就是 3 ^ 5 = 6。
 * 4. 显然，将 3 和 5 所在的数组分成两组，对这两部分的数组分别进行组内的异或，最终就可以求出每个组的那个单独的数字；
 * 5. 但是，如何分组？
 *    在二进制中，我们要找的这两个数是不同的，它俩至少有一位是不同的。
 *    所以可以根据这一位来将它们划分成两个组，将数组划分成这一位都是 1 的一组和这一位都是 0 的一组。
 * 6. 通过一个方法，只保留数组异或结果的二进制中最高位的那个 1，其余的 1 变成 0，而 Integer.highestOneBit() 可以做到这一点；
 * 7. 通过这个最高位的 1 将原数组进行划分即可。
 */
public class Solution {
    public int[] singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int xorResult = 0;
        for (int num : nums) {
            xorResult ^= num;
        }

        // 保留异或结果的最高位的那个 1，其余位置为 0
        xorResult = Integer.highestOneBit(xorResult);

        // res[0] 和 res[1] 分别代表两个不同的组
        int[] res = {0, 0};
        for (int num : nums) {
            // 当前位是 0 的组，组内进行异或
            if ((xorResult & num) == 0) {
                res[0] ^= num;
                // 当前位是 1 的组，组内进行异或
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 1, 3, 2, 5};

        System.out.println(Arrays.toString(solution.singleNumber(nums)));
    }
}
