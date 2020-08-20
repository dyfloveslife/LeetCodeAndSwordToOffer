package Other.AdvancedAlgorithm._25_ReversePair;

import java.util.Arrays;

/*
 * 求解符合要求的逆序数组
 *
 * 题目描述：
 * 给定正数 power，给定一个数组 arr，给定另一个数组 reverse。
 * 其中，arr 的长度一定是 2 的 power 次方，reverse 中的每一个值都在 0~power 范围内，
 * 任何一个在前的数字可以和任何一个在后的数字，构成一对数，这对数可能是升序关系、降序关系或相等关系。
 *
 * 例如 power = 2，arr = [3, 1, 2, 5]，reverse = [1, 0, 2]，
 * reverse[0] = 1，表示在数组 arr 中，每 2^1=2 个数成为一组，组内进行逆序（注意不是降序），也就是变成 [1, 3, 5, 2]，
 * 然后在 [1, 3, 5, 2] 中，找到降序对的数量。
 * 降序对有 (3, 2)、(5, 2)，共有 2 个降序对。
 * 然后对于 reverse[1] = 0 来说，需要在 [1, 3, 5, 2] 的基础上进行分析，reverse[2] = 2 同理。
 *
 * 思路:
 * 1. 第一种方法就是模拟题目所描述的过程，每次都逆序相应的数组，然后再统计逆序对的数量；
 * 2. 在讲第二种方法之前，需要明白一个前提的知识点；
 * 3. 在一个长度为 2 的 k 次方的数组中，如果我们要统计该数组中的逆序对，那么我们可以将数组划分成 2 个数一组、4 个数一组、8 个数一组的方式进行计算逆序对的数量
 * 4. 例如 [3, 2, 4, 5, 0, 1, 3, 5]，如果将其划分成 4 个数一组，那么数组就变成 [3, 2, 4, 5 | 0, 1, 3, 5]；
 * 5. 我们在统计逆序对的时候，前一个数需要从左半部分选择，后一个数需要从右半部分选择；
 * 6. 只有这样选择的时候，才能统计出在 4 个数一组的情况下的逆序对的数量；
 * 7. 对于其它的，2 个数一组、8 个数一组，也是同样的道理。
 * 8. 通过这种划分，进而查找逆序对数量的时候，如果将原数组中的元素进行逆序操作，结果是不会影响逆序对的数量的，但是会交换逆序对数量和升序对数量；
 * 9. https://i.loli.net/2020/08/20/QdKPvO7shDtLUGV.png
 */
public class Solution {
    // 暴力的方法，也就是模拟求解过程
    public int[] reversePair1(int[] arr, int[] reverse, int power) {
        int[] ans = new int[reverse.length];
        for (int i = 0; i < reverse.length; i++) {
            // 按照 2 的 reverse[i] 次方，将数组 arr 进行分组，然后再统计逆序对的数量
            reverseArray(arr, 1 << reverse[i]);
            ans[i] = countReversePair(arr);
        }
        return ans;
    }

    private void reverseArray(int[] arr, int teamSize) {
        if (teamSize < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i += teamSize) {
            // 每组的范围是从 i 到 i + teamSize - 1
            reversePart(arr, i, i + teamSize - 1);
        }
    }

    private void reversePart(int[] arr, int left, int right) {
        while (left < right) {
            int temp = arr[left];
            arr[left++] = arr[right];
            arr[right--] = temp;
        }
    }

    private int countReversePair(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // 高效的方法
    public int[] reversePair2(int[] arr, int[] reverse, int power) {
        int[] arrReverse = copyArray(arr);
        // 将 arrReverse 进行逆序
        reversePart(arrReverse, 0, arrReverse.length - 1);
        int[] recordDown = new int[power + 1];
        int[] recordUp = new int[power + 1];
        process(arr, 0, arr.length - 1, power, recordDown);
        process(arrReverse, 0, arrReverse.length - 1, power, recordUp);

        // 已经生成好了 recordUp 和 recordDown
        // recordUp[i] 表示 2 的 i 次方个数组一组的划分中，升序的数量
        // recordDown[i] 同理
        int[] ans = new int[reverse.length];
        for (int i = 0; i < reverse.length; i++) {
            int curPower = reverse[i];
            // 将升序数量和降序数量进行交换
            for (int p = 1; p <= curPower; p++) {
                int temp = recordDown[p];
                recordDown[p] = recordUp[p];
                recordUp[p] = temp;
            }
            for (int p = 1; p <= power; p++) {
                ans[i] += recordDown[i];
            }
        }
        return ans;
    }

    private int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    // 这里使用归并排序生成 recordDown 和 recordUp
    private void process(int[] arr, int left, int right, int power, int[] record) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        process(arr, left, mid, power - 1, record);
        process(arr, mid + 1, right, power - 1, record);
        record[power] += merge(arr, left, mid, right);
    }

    private int merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int index = 0;
        int p1 = left;
        int p2 = mid + 1;
        int ans = 0;
        while (p1 <= mid && p2 <= right) {
            ans += arr[p1] > arr[p2] ? (mid - p1 + 1) : 0;
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= right) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[left + i] = help[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {3, 1, 2, 5};
        int[] reverse = {1, 0, 2};
        int power = 2;

        System.out.println(Arrays.toString(solution.reversePair1(arr, reverse, power)));
        System.out.println(Arrays.toString(solution.reversePair2(arr, reverse, power)));
    }
}
