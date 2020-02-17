package LeetCodeSolution.AlgorithmThought._04_BinarySearch._540_SingleElementInASortedArray;

/*
 * 有序数组中的单一元素
 *
 * 题目描述:
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 *
 * 思路：
 * 1. 根据题意可知，数组元素的个数为奇数个，并且可以知道，只出现一次的元素一定位于索引值为偶数的位置上；
 * 2. 可以对所有的偶数索引进行搜索，直到遇到第一个其后元素不相同的索引；
 * 3. 首先确保 middle 是偶数，如果不是偶数，则将其减 1；
 * 4. 然后检查 middle 上的元素是否与其后面的元素相同；
 *    4.1) 如果相同，则说明 middle 上的元素不是单个元素，则单个元素在 middle 之后，此时修改 left 为 middle + 2；
 *    4.2) 如果不相同，则说明单个元素位于 middle 或位于 middle 之前，此时修改 right 为 middle；
 * 5. 只要 left == right，则当前搜索的空间为 1 个元素，则返回该元素;
 * 6. 由于使用了表达式 right = middle，所以应使用 left < right 的形式，否则会出现死循环。
 */
public class Solution {
    public static int singleNonDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (middle % 2 == 1) {
                middle--;
            }

            if (nums[middle] == nums[middle + 1]) {
                left = middle + 2;
            } else {
                right = middle;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] arr = {3, 3, 7, 7, 10, 11, 11};
        System.out.println(singleNonDuplicate(arr));
    }
}
