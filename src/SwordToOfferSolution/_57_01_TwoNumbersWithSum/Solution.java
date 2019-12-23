package SwordToOfferSolution._57_01_TwoNumbersWithSum;

import java.util.ArrayList;

/*
 * 和为 s 的两个数字
 *
 * 思路：双指针
 * 1. 指针 p1 指向数组的第一个元素，指针 p2 指向数组的第二个元素；
 * 2. 将这两个指针所指的元素进行相加，由于数组已经排好序了：
 *     如果 p1 + p2 的值比目标值小，则 p1 向后移动；
 *     如果 p1 + p2 的值比目标值大，则 p2 向前移动。
 * 3. 直到找到目标值为止。
 */
public class Solution {
    public static ArrayList<Integer> findNumbersWithSum(int[] arr, int sum) {
        ArrayList<Integer> res = new ArrayList<>();
        if (arr == null || arr.length < 2) {
            return res;
        }

        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int curSum = arr[left] + arr[right];
            if (curSum == sum) {
                res.add(arr[left]);
                res.add(arr[right]);
                break;
            } else if (curSum < sum) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 7, 11, 15};
        ArrayList<Integer> list = findNumbersWithSum(arr, 15);
        System.out.println(list);
    }
}
