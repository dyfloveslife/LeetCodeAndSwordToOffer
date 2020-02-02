package SwordToOfferSolution._03_FindDuplicationInArray;

/*
 * 找出数组中重复的数字
 *
 * 题目描述：
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。例如，如果输入长度为 7 的数组 {2, 3, 1, 0, 2, 5, 3}，那么对应的输出是重复的数字 2 或者 3。
 */
public class Solution {
    private static boolean duplicate(int[] nums, int length, int[] duplicate) {
        if (nums == null || length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] < 0 || nums[i] > length - 1) {
                return false;
            }
        }
        for (int i = 0; i < length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    duplicate[0] = nums[i];
                    return true;
                }
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 1, 0, 2, 5};
        int dup[] = {2};
        System.out.println(duplicate(arr, 6, dup));
    }
}

