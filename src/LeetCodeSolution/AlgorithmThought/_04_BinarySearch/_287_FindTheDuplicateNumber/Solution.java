package LeetCodeSolution.AlgorithmThought._04_BinarySearch._287_FindTheDuplicateNumber;

/*
 * 寻找重复数
 *
 * 题目描述：
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 *
 * 思路一：二分
 * 1. 抽屉原理：假设有 3 个苹果放入 2 个抽屉中，则必然有一个抽屉中有 2 个苹果；
 * 2. 即假如有 n+1 个元素放到 n 个集合中去，其中必定有一个集合里至少有两个元素；
 * 3. 在每次求得 middle 之后，就在统计整个数组中小于等于 middle 的元素，并将其计数，得到 count；
 * 4. 如果 count 大于 middle，说明小于等于 middle 的元素在左半部分，否则在右半部分。
 *
 * 思路二：快慢指针
 * 1. 用 0 位置上的元素作为索引，去找下一个元素，重复此操作；
 * 2. 由于数组中的元素被限定在 1 到 n 之间，所以重复的元素会形成环；
 * 3. 因此，就用判断链表的入环节点的思想，解该题。
 */
public class Solution {
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 1;
        int right = nums.length - 1;

        while (left < right) {
            int middle = left + ((right - left) >> 1);

            int count = 0;
            for (int num : nums) {
                if (num <= middle) {
                    count++;
                }
            }
            if (count > middle) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return left;
    }

    public int findDuplicate2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = nums[0];
        int fast = nums[nums[0]];

        // 慢指针每次走一步
        // 快指针每次走两步
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 然后快慢指针一起从头开始走，直到相遇就结束
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 3, 4, 2, 2};
        int[] nums2 = {3, 1, 3, 4, 2};

        System.out.println(solution.findDuplicate(nums1));
        System.out.println(solution.findDuplicate(nums2));
        System.out.println("==");

        System.out.println(solution.findDuplicate2(nums1));
        System.out.println(solution.findDuplicate2(nums2));
    }
}
