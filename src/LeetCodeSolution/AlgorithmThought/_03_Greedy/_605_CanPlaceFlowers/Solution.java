package LeetCodeSolution.AlgorithmThought._03_Greedy._605_CanPlaceFlowers;

/*
 * 种花问题
 *
 * 题目描述：
 * 给定一个花坛（表示为一个数组包含 0 和 1，其中 0 表示没种植花，1 表示种植了花），和一个数 n 。
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回 True，不能则返回 False。
 *
 * 思路：
 * 1. 判断当前位置的元素，如果为 1，则直接跳到下一个数；
 * 2. 如果当前位置的元素为 0，则需要判断当前位置的前后，只有都为 0 的时候才能插入 1；
 * 3. 对于数组的边界，即如果当前索引是 0 或者数组的长度减一，则只需要考虑一侧即可；
 * 4. 最后种花的数量要大于等于给定的 n，才能满足条件。
 */
public class Solution {
    public static boolean canPlaceFlowers1(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) {
            return false;
        }

        int i = 0;
        int count = 0;
        while (i < flowerbed.length) {
            if (flowerbed[i] == 0
                    && (i == 0 || flowerbed[i + 1] == 0)
                    && (i == flowerbed.length - 1 || flowerbed[i - 1] == 0)) {

                flowerbed[i] = 1;
                count++;
            }
            i++;
        }
        return count >= n;
    }


    public static boolean canPlaceFlowers2(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) {
            return false;
        }

        int len = flowerbed.length;
        int count = 0;
        for (int i = 0; i < len && count < n; i++) {
            if (flowerbed[i] == 1) {
                continue;
            }
            int i_pre = i == 0 ? 0 : flowerbed[i - 1];
            int i_next = i == len - 1 ? 0 : flowerbed[i + 1];
            // 如果 i 前后相邻的位置都是 0，则可以种花
            if (i_pre == 0 && i_next == 0) {
                count++;
                flowerbed[i] = 1;
            }
        }
        return count >= n;
    }

    public static void main(String[] args) {
        int[] arr = {1, 0, 0, 0, 1};
        int[] arr1 = {0, 0, 1, 0, 1};
        System.out.println(canPlaceFlowers1(arr, 2));
    }
}
