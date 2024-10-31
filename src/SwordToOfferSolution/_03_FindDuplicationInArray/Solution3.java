package SwordToOfferSolution._03_FindDuplicationInArray;

/**
 * 寻找重复数
 * <p>
 * 题目描述：
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有一个重复的整数，返回这个重复的数。
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 * <p>
 * 思路一：二分查找
 * 1、因为数字的范围在 [1, n]，可以对这个范围进行二分查找；
 * 2、对于中间数 mid，统计数组中小于等于 mid 的数的个数 count；
 * 2.1 如果 count > mid，说明重复的数在 [1, mid] 范围内；
 * 2.2 否则重复的数在 [mid+1, n] 内。
 * <p>
 * 思路二：快慢指针
 * 1、将数组值视为链表的下一个位置的指针。例如，nums[i] 表示从位置 i 指向位置 nums[i]。
 * 2、由于有重复的数，意味着有两个位置指向同一个位置，这就形成了环。
 * 3、使用快慢指针找到环，然后找到环的入口，该入口就是重复的数。
 */
public class Solution3 {

    /**
     * 二分查找
     *
     * @param nums int[]
     * @return int
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 1;
        int right = nums.length - 1; // n
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 统计小于等于 mid 的数的个数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 快慢指针
     *
     * @param nums int[]
     * @return int
     */
    public int findDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int slow = nums[0];
        int fast = nums[0];
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = nums[0];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {3, 1, 3, 4, 2};
        int[] nums3 = {3, 3, 3, 3, 3};

        Solution3 solution3 = new Solution3();
        System.out.println(solution3.findDuplicate(nums1)); // 2
        System.out.println(solution3.findDuplicate(nums2)); // 3
        System.out.println(solution3.findDuplicate(nums3)); // 3

        System.out.println("---");
        System.out.println(solution3.findDuplicate2(nums1)); // 2
        System.out.println(solution3.findDuplicate2(nums2)); // 3
        System.out.println(solution3.findDuplicate2(nums3)); // 3
    }
}
