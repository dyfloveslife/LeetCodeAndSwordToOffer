package LeetCodeSolution.AlgorithmThought._01_DoublePointer._167_TwoSumII;

/*
 * 两数之和Ⅱ- 输入有序数组
 *
 * 题目描述：
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2，返回的下标不是从 0 开始的。
 *
 * 思路：
 * 1. 使用双指针的方法，left 指向左侧小的数，right 指向右侧大的数；
 * 2. 如果两个指针所指向的数的和等于 target，则返回索引；
 * 3. 如果两个指针所指向的数的和小于 target，则 left 右移，让和变大即可；
 * 4. 如果两个指针所指向的数的和大于 target，则 right 左移，让和变小即可；
 * 5. 由于数组中的数最多遍历一次，所以时间复杂度为 O(N)，空间复杂度为 O(1)。
 */
public class Solution {

    public static int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return null;
        }

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] res = Solution.twoSum(numbers, target);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
}

