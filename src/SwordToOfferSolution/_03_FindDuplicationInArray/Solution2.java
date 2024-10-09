package SwordToOfferSolution._03_FindDuplicationInArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到所有数组中消失的数字
 * <p>
 * 题目描述：
 * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。
 * 请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
 * <p>
 * 思路一:
 * 1、新开一个数组（或哈希表），统计每个元素的出现次数；
 * 2、最终再遍历一遍新数组，若次数为 0，则将其加入到结果集中；
 * 3、时间复杂度 O(n)，空间复杂度 O(n)。
 * <p>
 * 思路二：
 * 1、遍历数组，将每个数字交换到它理应出现的位置上；
 * 2、若当前数字本身就在理应的位置上，则不交换；
 * 3、若当前数字理应出现的位置上，本身就是该数字，则不交换；
 * 4、再次遍历数组，若当前位置没有对应正确的数字，则将其加入到结果集中；
 * 5、时间复杂度 O(n), 空间复杂度 O(1)。
 */
public class Solution2 {
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        int[] arr = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 0) {
                ans.add(i);
            }
        }

        return ans;
    }

    public List<Integer> findDisappearedNumbers2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] -1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                ans.add(i + 1);
            }
        }

        return ans;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums2 = {1, 1};

        Solution2 solution2 = new Solution2();
        System.out.println(solution2.findDisappearedNumbers1(nums1));
        System.out.println(solution2.findDisappearedNumbers1(nums2));

        System.out.println("---");
        System.out.println(solution2.findDisappearedNumbers2(nums1));
        System.out.println(solution2.findDisappearedNumbers2(nums2));
    }
}
