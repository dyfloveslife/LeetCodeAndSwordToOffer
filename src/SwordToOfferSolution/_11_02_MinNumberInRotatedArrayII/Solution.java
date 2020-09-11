package SwordToOfferSolution._11_02_MinNumberInRotatedArrayII;

/*
 * 寻找旋转排序数组中的最小值 Ⅱ
 *
 * 题目描述：
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。请找出其中最小的元素。
 * 注意：数组中可能存在重复的元素。
 *
 * 思路：
 * 1. 直接使用二分法进行查找；
 * 2. 拿 nums[middle] 和 nums[right] 进行比较：
 *    2.1) 如果 nums[middle] < nums[right]，说明 middle 和 right - 1 之内的所有的元素都比 right 小，
 *         则此最小的元素有可能在 nums[middle] 位置上，也有可能在 nums[middle] 的左边，所以需要将 right 定位到 middle 的位置；
 *    2.2) 如果 nums[middle] > nums[right]，说明最小的元素在 middle 的右侧，
 *         因此需要将 left 置为 middle + 1。
 * 3. 如果数组中存在重复元素，则让右边界的 right-- 即可，因为右边界和中间数是相等的，即使去掉了右边界，而中间的数还是存在的，
 *    然后再让新的中间数和右边界继续跑循环。
 */
public class Solution {
    // 数组中元素重复
    public int minNumberInRotateArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            // nums[middle] 比 nums[right] 小，由于是排好序的，
            // 则说明从 middle 到 right-1 都是小于 right 的，
            // 则直接将 right 定位到 middle
            if (nums[middle] < nums[right]) {
                right = middle;
            } else if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else {
                // 来到这里，说明 nums[middle] 和 nums[right] 是相等的，此时无法判断需要向左边调整还是右边调整
                // 因此，可以执行 right-- 缩小范围
                right--;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums1 = {0, 1, 1, 1, 1, 1, 1};
        int[] nums2 = {2, 2, 2, 0, 1};

        Solution solution = new Solution();
        System.out.println(solution.minNumberInRotateArray(nums1));
        System.out.println(solution.minNumberInRotateArray(nums2));
    }
}
