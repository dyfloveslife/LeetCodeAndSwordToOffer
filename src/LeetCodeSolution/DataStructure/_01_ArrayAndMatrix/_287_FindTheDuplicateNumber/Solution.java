package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._287_FindTheDuplicateNumber;

/*
 * 寻找重复数
 *
 * 题目描述：
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。
 *
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n^2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * 思路一：二分
 * 1. 直接使用二分，类似于将 10 个苹果放进 9 个抽屉的游戏，至少有一个抽屉会有至少 2 个苹果；
 * 2. 二分的时间复杂度是 O(log n)，而其内部 for 循环的时间复杂度为 O(n)，因此整体时间复杂度为 O(n log n)。
 *
 * 思路二：快慢指针
 * 1. 根据题目描述，给定的数组中存在一个重复的整数，可以用每个元素的下标将所有元素串联成一个环形链表；
 * 2. 例如 nums[3] = 6，即下标为 3 的节点的下一个节点是下标为 6 的节点；
 * 3. 快指针一次走一步，慢指针一次走两步，都从 0 位置开始出发，如果路径上不存在环的话，快指针会先走完整条路径，慢指针会使用快指针 2 倍的时间走完路径；
 * 4. 但题目要求一定会存在重复元素，因此，两个指针一定会在环上的某一点相遇；
 * 5. 相遇后，我们将其中一个指针的位置不变，另外一个指针回到位置 0，然后再以每次前进一步的相同的速度继续走，直到两指针再次相遇时，该相遇点一定是边与环的交点。
 */
public class Solution {
    public int findDuplicate1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 1, right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            int count = 0;
            // 统计小于等于 middle 的元素数量
            for (int num : nums) {
                if (num <= middle) {
                    count++;
                }
            }
            // 由于 while 条件中是小于，因此 right 还需要来到 middle 的位置再次进行判断
            // count 大于 middle，说明在 [1, middle] 范围内小于等于 middle 的元素数量超过了 middle，所以一定有一个重复数字，
            // 因此需要从左侧继续寻找
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
            return -1;
        }

        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
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

        System.out.println(solution.findDuplicate1(nums1)); // 2
        System.out.println(solution.findDuplicate1(nums2)); // 3

        System.out.println("--");
        System.out.println(solution.findDuplicate2(nums1)); // 2
        System.out.println(solution.findDuplicate2(nums2)); // 3
    }
}
