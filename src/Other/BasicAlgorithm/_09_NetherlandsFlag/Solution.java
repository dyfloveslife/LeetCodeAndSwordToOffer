package Other.BasicAlgorithm._09_NetherlandsFlag;

/*
 * 荷兰国旗问题
 * 给定一个数组 arr 和一个数 num，需要把小于 num 的数放在数组的左边，等于 num 的数放在数组的中间，大于 num 的数放在数组的右边。
 *
 * 思路：
 * 1. 将数组划分成小于 num 的区域、等于 num 的区域以及大于 num 的区域三部分；
 * 2. cur 指向数组的当前元素，如果当前元素等于 num 的话，则 cur 直接后移；
 * 3. 如果当前元素小于 num，则将当前元素与小于区域的下一个元素进行交换，然后 cur++，同时小于区域要向右扩大一个位置；
 * 4. 如果当前元素大于 num，则将当前元素与大于区域的前一个元素进行交换，然后大于区域向左扩大一个位置。此时由于被换过来的元素不知道大小，
 *    所以还需要比较 小于 num 或等于 num 或大于 num。
 * 5. 直到 cur 与大于区域相遇的时候，流程结束。
 */
public class Solution {
    // 该函数可以实现在 left 和 right 范围内的划分问题
    // 一开始的时候，less 在 left 的左边，more 在 right 的右边
    public static int[] netherlandsFlag(int[] arr, int left, int right, int num) {
        int less = left - 1;
        int more = right + 1;
        int cur = left;

        // cur 不能与 more 部分相遇
        while (cur < more) {
            // 第一种情况：如果 cur 指向的元素比 num 小，则将 cur 所指向的元素与 less 的下一个元素交换，然后 cur 后移
            if (arr[cur] < num) {
                swap(arr, ++less, cur++);
                // 第二种情况：若果 cur 指向的元素比 num 大，则将 cur 所指向的元素与 more 的前一个元素交换，
                // 然后 cur 不变，继续判断 cur 与 num 的大小
            } else if (arr[cur] > num) {
                swap(arr, --more, cur);
                // 第三种情况：如果 cur 指向的元素和 num 相同，则 cur 直接后移
            } else {
                cur++;
            }
        }
        // 将等于 num 区域的左边界和右边界做成一个数组返回
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 4, 1, 5, 6};
        int[] ints = Solution.netherlandsFlag(arr, 0, arr.length - 1, 3);

        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();
        for (int j : ints) {
            System.out.print(j + " ");
        }
    }
}
