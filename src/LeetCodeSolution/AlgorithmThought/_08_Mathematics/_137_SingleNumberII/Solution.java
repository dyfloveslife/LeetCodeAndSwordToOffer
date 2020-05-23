package LeetCodeSolution.AlgorithmThought._08_Mathematics._137_SingleNumberII;

/*
 * 只出现一次的数字Ⅱ
 *
 * 题目描述：
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。
 * 找出那个只出现了一次的元素。
 *
 * 思路：
 * 1. 一个数与自己异或的结果是 0；
 * 2. 一个数与 0 进行异或的结果是该数；
 * 3. 这里采用位运算的思路，如果一个数字出现了 3 次，则它的二进制表示的每一位也出现 3 次；
 * 4. 如果把所有出现 3 次的数字的二进制表示的每一位都分别加起来，那么每一位的和都可以被 3 整除；
 * 5. 如果不能被 3 整除的话，则说明那个只出现 1 次的数贡献出了 1；
 * 6. 将所有不是 3 的倍数的列写成 1，其它列写 0；
 * 7. 例如 1、2、6、1、1、2、2、3、3、3：
 *    1：0 0 1
 *    2: 0 1 0
 *    6: 1 1 0
 *    1: 0 0 1
 *    1: 0 0 1
 *    2: 0 1 0
 *    2: 0 1 0
 *    3: 0 1 1
 *    3: 0 1 1
 *    3: 0 1 1
 *    可以看到，最右边的一列，共有 6 个 1，再往前一列，共有 7 个 1，再往前一列，共有 1 个 1；
 * 8. 因此，将 1 的个数是 3 的倍数的写成 0，将 1 的个数不是 3 的倍数的写成 1；
 * 9. 那么从最左列到最右列，可以得到 1 1 0， 也就是 6。
 */
public class Solution {

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int res = 0;
        // 考虑某个数当中的每位 bit 位
        for (int i = 0; i < 32; i++) {
            // count 首先会统计第 0 位上 1 的个数，
            // 然后 i++，再统计第 1 位上 1 的个数...
            int count = 0;
            for (int num : nums) {
                // 奇数与 1 进行 & 操作，会产生 1
                // 偶数与 1 进行 & 操作，会产生 0
                // 这里其实就是判断 num>>i 的奇偶性
                if ((num >> i & 1) == 1) {
                    count++;
                }
            }
            // 判断当前的第 i 位上的 1 的个数是不是 3 的倍数
            if (count % 3 != 0) {
                // 如果不是 3 的倍数，则将第 i 位改成 1，
                // 这里用到了或运算
                res = res | 1 << i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 6, 1, 1, 2, 2, 3, 3, 3};

        System.out.println(solution.singleNumber(nums));
    }
}
