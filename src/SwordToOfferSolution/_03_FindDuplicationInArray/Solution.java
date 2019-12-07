package SwordToOfferSolution._03_FindDuplicationInArray;

/*
 * 数组中重复的数字
 */
public class Solution {
    public static boolean duplicate(int[] nums, int length, int[] duplicate) {
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

