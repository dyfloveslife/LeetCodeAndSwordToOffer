package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._283_MoveZeroes;

import java.util.Arrays;

/*
 * 移动零
 *
 * 题目描述：
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 说明:
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * 思路：
 * 1. 使用两个指针 i 和 j，j 在后面，i 在前面，每次判断 i 位置上的数是不是非零数字；
 * 2. 如果 i 上的数是非零数字，那么就将 i 和 j 进行交换；
 * 3. 因此，i 一直指向的都是非零数，而 j 一直指向的是数字 0；
 * 4. 但是，对于该方法来说，如果数组中没有 0，那么将会执行 n 次的交换；
 * 5. 可以使用方法二，也就是只有在 i > j 的情况下，才将 i 位置上的数字赋值到 j 位置上；
 * 6. 这样在给定数组都是非零数字的情况下，就不需要频繁的交换数组了。
 */
public class Solution {
    // 方法一
    public void moveZeroes1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int i = 0, j = 0;
        while (i < nums.length) {
            if (nums[i] != 0) {
                swap(nums, i, j);
                j++;
            }
            i++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 方法二
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {0, 1, 0, 3, 12};
        int[] nums2 = {0, 1, 0, 3, 12};

        solution.moveZeroes1(nums1);
        solution.moveZeroes2(nums2);

        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
    }
}
