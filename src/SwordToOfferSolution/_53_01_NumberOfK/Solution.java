package SwordToOfferSolution._53_01_NumberOfK;

/*
 * 某个数字在排序数组中出现的次数
 *
 * 题目描述：
 * 统计一个数字在排序数组中出现的次数。
 * 例如输入排序数组 {1, 2, 3, 3, 3, 3, 4, 5} 和数字 3，由于 3 在这个数组中出现了 4 次，因此输出 4。
 *
 * 思路：二分法的应用
 * 1. 由于需要统计某个数字 k 在数组中的次数，所以只需要知道 k 在数组中第一次出现的位置和最后一次出现的位置即可；
 * 2. 利用二分法找位置：
 *     对于 k 第一次出现的位置，首先拿中间的数和 k 比较：
 *         如果中间的数大于 k，则说明 k 在数组的前半段；
 *         如果中间的数小于 k，则说明 k 在数组的后半段；
 *         如果中间的数等于 k，还得判断这个数字是不是第一个 k：
 *             如果中间数字的前面一个数字不是 k，则说明当前中间的数字就是第一个 k；
 *             如果中间数字的前面一个数字是 k，则还要在左侧继续寻找。
 *     对于 k 最后一次出现的位置，也是拿中间的数和 k 比较：
 *         如果中间的数大于 k，则说明 k 在数组的前半段；
 *         如果中间的数小于 k，则说明 k 在数组的后半段；
 *         如果中间的数等于 k，还得判断这个数字是不是最后一个 k：
 *             如果中间数字的后面一个数字不是 k，则说明当前中间的数字就是最后一个 k；
 *             如果中间数字的后面一个数字是 k，则还要在右侧继续寻找。
 * 3. 取 middle 的时候，里面的加 1 是为了避免死循环，
 *    在待搜索区间只要有 2 个元素的时候，mid = (left + right) >>> 1 只能取到左边那个元素，
 *    如果此时边界设置是 left = mid ，区间分不开，因此要改变下取整的行为，在括号里加 1 变成上取整。
 */
public class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int firstPosition = getFirstPosition(nums, target);
        if (firstPosition == -1) {
            return 0;
        }

        // 程序在执行到 getLastPosition() 方法之前，由于执行了 getFirstPosition() 方法，
        // 因此，说明 target 一定在 nums 中，所以不必再判断一次
        int lastPosition = getLastPosition(nums, target);
        return lastPosition - firstPosition + 1;
    }

    private int getFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int middle = left + ((right - left) >> 1);

            // 由于该函数想要得到等于 target 的第一个数的位置，
            // 因此，当 target 比中间的数大的时候，说明 middle 以及 middle 的左侧都比 target 小，
            // 则 left 就直接来到 middle + 1 的位置
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] == target) {
                // middle 可能是第一个等于 target 的元素位置，但 middle 的右侧肯定不是第一个等于 target 的
                right = middle;
            } else if (nums[middle] > target) {
                // 说明 middle 以及以后的都不是第一个等于 target 元素的位置
                right = middle - 1;
            }
        }

        if (nums[left] == target) {
            return left;
        }

        return -1;
    }

    private int getLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // 由于边界 middle = left，并且最后返回的是 left，
            // 因此在取中间数的时候，需要加 1，避免死循环
            int middle = 1 + left + ((right - left) >> 1);

            // 由于该函数想要得到等于 target 的最后一个数的位置，
            // 因此 target 大于 nums[middle] 说明需要在 middle+1 及以后的位置开始找
            if (nums[middle] < target) {
                left = middle + 1;
            } else if (nums[middle] == target) {
                // 说明 middle 可能是最后一个等于 target 的位置，
                // 但 middle 左侧不是需要找的位置，因为我要找的是最后一个位置，那么 middle 左侧的就不需要管了
                left = middle;
            } else if (nums[middle] > target) {
                // 说明 middle 以及 middle 以后的都不是我要找的数
                right = middle - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 3, 3, 3, 4, 5};
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int[] nums3 = {2, 2};

        System.out.println(solution.search(nums1, 3));
        System.out.println(solution.search(nums2, 3));
        System.out.println(solution.search(nums3, 3));
    }
}
