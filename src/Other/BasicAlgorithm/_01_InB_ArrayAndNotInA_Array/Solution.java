package Other.BasicAlgorithm._01_InB_ArrayAndNotInA_Array;

import java.util.ArrayList;
import java.util.List;

/*
 * 一个有序数组 A，另一个无序数组 B，请打印 B 中的所有不在 A 中的数。
 * A 数组长度为 N，B 数组长度为 M。
 *
 * 法一：二分法，每次砍掉数组中一半的数 O(logN)
 * 对于数组 B 中的每一个数，都在 A 中通过二分的方式找一下，所以时间复杂度是 O(M*logN)。
 *
 * 法二：先把数组 B 排序，然后用类似外排的方式打印所有不在 A 中出现的数。
 * 例如 A:1、2、4、6、7
 *      B:6、3、9
 *  1. 将数组 B 排序，即 3、6、9；
 *  2. 设置指针 a 指向数组 A 中的第一个元素，设置指针 b 指向数组 B 中的第一个元素；
 *  3. 比较 a 和 b 的大小，如果 a < b，则让 a 后移；假如此时 a 指向 4，b 指向 3，即 a > b，说明 4 之前的数都比 3 小，
 *  这时输出 b，也就是 3。然后后移 a 和 b，这时 a 和 b 都指向 6，则不打印，然后再往后移，直至结束。
 *  4. 若 b < a，则打印 b 并后移 b；若 b = a，则不打印 b，b 也得后移；除此之外，都是 a 移动。
 *
 *  最差情况下，a 移动 N 位，b 移动 M 位，则时间复杂度为 O(N+M)，再加上需要将数组 B 进行排序 O(M*logM)，
 *  由于不能确定样本量 N 和 M 的大小，所以总的时间复杂度就是 O(M*logM)+O(N+M)。
 *
 *  Debug 大法好~
 */
public class Solution {
    // 二分法，也就是将 B 的每个数都利用二分法在 A 中进行查找
    private static List<Integer> getAllNotIncluded(int[] A, int[] B) {
        List<Integer> res = new ArrayList<>();
        // for 循环用于访问 B 中的每个数
        for (int i = 0; i < B.length; i++) {
            int left = 0;
            int right = A.length - 1;
            boolean contains = false;

            while (left <= right) {
                int middle = left + ((right - left) >> 1);
                // 由于是二分法，则需要一个 middle
                // 如果当前 B 中的元素一开始就等于 A 中的 A[middle] 的话，就说明包含，则直接跳出
                if (A[middle] == B[i]) {
                    contains = true;
                    break;  // 跳出 while
                }
                // 从 A 中的左半部分开始查找
                if (A[middle] > B[i]) {
                    right = middle - 1;
                    // 从 A 中的右半部分开始查找
                } else {
                    left = middle + 1;
                }
            }
            // B 中的数在 A 中找不到的话，说明 B 中的某个数不在 A 中，则将 B 中当前的数添加到结果集中
            if (!contains) {
                res.add(B[i]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 4, 6, 7};
        int[] b = {9, 6, 3};
        List<Integer> list = getAllNotIncluded(a, b);
        System.out.println(list);
    }
}
