package SwordToOfferSolution._61_ContinousCards;

import java.util.Arrays;

/*
 * 扑克牌中的顺子
 *
 * 思路：
 * 1. 将数组排序，然后统计数组中 0 的个数，最后统计排序之后的数组中相邻数字之间的空缺总数；
 * 2. 如果空缺的总数少于 0 的个数，则可以将 0 补到空缺的位置，使之构成连续的数组；
 * 3. 如果数组中非 0 数字重复出现，即牌中存在对子，则该数组不是连续的。
 */
public class Solution {
    public static boolean isContinousCards(int[] arr) {
        if (arr == null || arr.length < 5) {
            return false;
        }
        // 将数组排序
        Arrays.sort(arr);

        // 统计数组中 0 的个数
        int numOfZero = 0;
        // 相邻数字之间的空缺总数
        int numOfGap = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == 0) {
                numOfZero++;
                // 结束当前循环，继续下次循环
                continue;
            }
            // 若存在对子，则直接返回
            if (arr[i] == arr[i + 1]) {
                return false;
            }
            // 统计相邻数字之间的空缺总数
            // 例如 2 和 4 之间的空缺数：4 - 2 - 1 = 1
            // 例如 2 和 5 之间的空缺数：5 - 2 - 1 = 2
            numOfGap += arr[i + 1] - arr[i] - 1;
        }

        if (numOfGap > numOfZero) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 2, 6, 4};
        System.out.println(isContinousCards(arr1));
    }
}
