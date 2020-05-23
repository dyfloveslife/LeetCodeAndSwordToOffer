package SwordToOfferSolution._53_03_IntegerIdenticalToIndex;

/*
 * 数组中数值和对应下标相同的元素
 *
 * 题目描述：
 * 假设一个单调递增的数组里的每个元素都是整数并且是唯一的。
 * 请编程实现一个函数找出数组中任意一个数值等于其下标的元素。
 * 例如，在数组 {-3, -1, 1, 3, 5} 中，数字 3 和它的下标相等。
 *
 * 思路：
 * 1. 由于数组是递增有序的，所以可以使用二分法；
 * 2. 如果一开始的第 i 个数对应的下标就是 i，则直接就找到了；
 * 3. 如果第 i 个数对应的下标不是 i，假设元素为 m：
 *    3.1) 如果 m > i，即元素值大于它的下标，则对于任意大于 0 的数 k，位于下标 i + k 的数字的值大于或等于元素 m + k；
 *          也就是 nums[i + k] 大于或等于 m + k，又由于 m > i，则 m + k > i + k；
 *          也就是说，如果第 i 的数字的值大于 i，则它右边的数字都大于对应的下标；
 *          此时，只需要从左部分的数组中找就可以了。
 *    3.2) 如果 m < i，则情况正好相反，只需要从右部分的数组中找就可以了。
 */
public class Solution {
    public int integerIdenticalToIndex(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] == middle) {
                return middle;
            }
            if (nums[middle] > middle) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-3, -1, 1, 3, 5};

        System.out.println(solution.integerIdenticalToIndex(nums));
    }
}
