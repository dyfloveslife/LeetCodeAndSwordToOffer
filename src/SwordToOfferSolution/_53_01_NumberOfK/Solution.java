package SwordToOfferSolution._53_01_NumberOfK;

/*
 * 某个数字在排序数组中出现的次数
 *
 * 思路：二分法的应用
 * 1. 由于需要统计某个数字 k 在数组中的次数，所以只需要知道 k 在数组中第一次出现的位置和最后一次出现的位置即可；
 * 2. 利用二分法找位置：
 *     对于 k 第一次出现的位置，首先拿中间的数和 k 比较：
 *         如果中间的数大于 k，则说明 k 在数组的前半段；
 *         如果中间的数小于 k，则说明 k 在数组的后半段；
 *         如果中间的数等于 k，还得判断这个数字是不是第一个 k：
 *             如果中间数字的前面一个数字不是 k，则说明当前中间的数字就是第一个 k；
 *             如果中间数组的前面一个数字是 k，则还要在左侧继续寻找。
 *     对于 k 最后一次出现的位置，也是拿中间的数和 k 比较：
 *         如果中间的数大于 k，则说明 k 在数组的前半段；
 *         如果中间的数小于 k，则说明 k 在数组的后半段；
 *         如果中间的数等于 k，还得判断这个数字是不是最后一个 k：
 *             如果中间数字的后面一个数字不是 k，则说明当前中间的数字就是第一个 k；
 *             如果中间数组的后面一个数字是 k，则还要在右侧继续寻找。
 */
public class Solution {
    public static int getNumberOfK(int[] arr, int k) {
        if (arr == null || arr.length < 1) {
            return 0;
        }

        // 保存结果
        int res = 0;

        int firstIndex = getFirstK(arr, k, 0, arr.length - 1);
        int lastIndex = getLastK(arr, k, 0, arr.length - 1);
        if (firstIndex > -1 && lastIndex > -1) {
            res = lastIndex - firstIndex + 1;
        }
        return res;
    }

    // 得到第一次出现的位置
    private static int getFirstK(int[] arr, int k, int start, int end) {
        if (start > end) {
            return -1;
        }

        int middleIndex = start + ((end - start) >> 1);
        int middleData = arr[middleIndex];

        if (middleData == k) {
            // 注意不要越界
            if ((middleIndex > 0 && arr[middleIndex - 1] != k) || middleIndex == 0) {
                return middleIndex;
            } else {
                end = middleIndex - 1;
            }
        } else if (middleData > k) {
            end = middleIndex - 1;
        } else {
            start = middleIndex + 1;
        }
        return getFirstK(arr, k, start, end);
    }


    // 得到最后一次出现的位置
    private static int getLastK(int[] arr, int k, int start, int end) {
        if (start > end) {
            return -1;
        }

        int middleIndex = start + ((end - start) >> 1);
        int middleData = arr[middleIndex];

        if (middleData == k) {
            // 注意不要越界
            if ((middleIndex < arr.length - 1 && arr[middleIndex + 1] != k) || middleIndex == arr.length - 1) {
                return middleIndex;
            } else {
                start = middleIndex + 1;
            }
        } else if (middleIndex > k) {
            end = middleIndex - 1;
        } else {
            start = middleIndex + 1;
        }
        return getLastK(arr, k, start, end);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 3, 3, 4, 5};
        int[] arr2 = new int[0];
        System.out.println(getNumberOfK(arr2, 0));
    }
}
