package SwordToOfferSolution._56_01_NumbersAppearOnce;

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
    public void findNumsAppearOnce(int[] arr, int nums1[], int nums2[]) {
        int length = arr.length;
        if (arr == null || length < 2) {
            return;
        }

        int resultXOR = 0;
        // 从整个数组中得到异或结果
        for (int i = 0; i < length; i++) {
            resultXOR ^= arr[i];
        }

        int indexOf1 = findFirstBitIs1(resultXOR);
        for (int j = 0; j < length; j++) {
            if (isBit1(arr[j], indexOf1)) {
                nums1[0] ^= arr[j];
            } else {
                nums2[0] ^= arr[j];
            }
        }
    }

    // 找到 resultXOR 中从右往左第一位是 1 的索引
    private int findFirstBitIs1(int resultXOR) {
        int index = 0;
        // 如果 resultXOR 从右到左第 0 个位置就是 1 的话，则不进循环，直接返回 index = 0
        // 通过移位操作，每次判断 resultXOR 的都是第 0 位
        // (resultXOR & 1) == 0 说明 resultXOR 第 0 位为 0
        while ((resultXOR & 1) == 0 && (index < 32)) {
            resultXOR >>= 1;
            index++;
        }
        return index;
    }

    // 判断数字 target 从右往左的第 index 位是不是 1
    private boolean isBit1(int target, int index) {
        // 由于数字 target 在从右到左的第 index 位上的数是 1 还是 0，这里需要做判断
        // 先将 target 往右移动 index 位，然后再判断是不是 1
        // 如果是 1，则进 nums1[0] 数组，否则进 nums2[0] 数组
        return ((target >> index) & 1) == 1;
    }

    public static void main(String[] args) {

    }
}
