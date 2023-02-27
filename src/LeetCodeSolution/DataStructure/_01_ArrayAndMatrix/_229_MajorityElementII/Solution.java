package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._229_MajorityElementII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 多数元素 II
 *
 * 题目描述：
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊n/3⌋ 次的元素。
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * 思路一：HashMap
 * 1. 使用 HashMap 统计给定数组中每个元素出现的次数；
 * 2. 统计完后，从 HashMap 中找到出现次数大于 ⌊n/3⌋ 次的元素，将其加入到结果中；
 * 3. 时间复杂度为 O(n)，空间复杂度为 O(n)。
 *
 * 思路二：摩尔投票法
 * 1. 首先有一点是：数组中满足"超过 ⌊n/3⌋ 次"这一条件的元素最多只有两个；
 * 2. 还是采用相互抵消的思想；
 * 3. 查看当前元素是否为第一个选中的元素或第二个选中的元素；（A 阵营和 B 阵营）
 * 4. 若都不是，则抵消一次；（若新来的士兵既不是 A 阵营也不是 B 阵营，则该士兵与 A 中某个士兵、B 中某个士兵（共 3 人）进行抵消）
 * 5. 若最终存在选票大于 0 的元素，则还需要再统计已选中元素出现的次数，检查元素出现的次数是否大于 ⌊n/3⌋；
 * 6. 时间复杂度为 O(n)，空间复杂度为 O(1)。
 */
public class Solution {
    // 思路一
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int times = n / 3;
        for (int i : map.keySet()) {
            if (map.get(i) > times) {
                ans.add(i);
            }
        }

        return ans;
    }

    // 思路二
    public List<Integer> majorityElement1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }

        int element1 = 0, element2 = 0;
        int vote1 = 0, vote2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                // 若营地 1 有人, 并且新来的士兵属于营地 1, 则人数++
                vote1++;
            } else if (vote2 > 0 && num == element2) {
                // 若营地 2 有人, 并且新来的士兵属于营地 2, 则人数++
                vote2++;
            } else if (vote1 == 0) {
                // 对于新来一个士兵 num, 若之前没有营地 1, 则把 num 划分到营地 1, 人数++
                element1 = num;
                vote1++;
            } else if (vote2 == 0) {
                // 对于新来一个士兵 num, 若之前没有营地 2, 则把 num 划分到营地 2, 人数++
                element2 = num;
                vote2++;
            } else {
                // 若新来的士兵 num 均不属于营地 1 和营地 2, 则从两个营地中分别拉出一个人去与 num 进行抵消
                vote1--;
                vote2--;
            }
        }

        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
            if (vote1 > 0 && num == element1) {
                cnt1++;
            }
            if (vote2 > 0 && num == element2) {
                cnt2++;
            }
        }

        int times = nums.length / 3;
        if (vote1 > 0 && cnt1 > times) {
            ans.add(element1);
        }
        if (vote2 > 0 && cnt2 > times) {
            ans.add(element2);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {3, 2, 3};
        int[] nums2 = {1};
        int[] nums3 = {1, 2};
        int[] nums4 = {2, 2};

        System.out.println(solution.majorityElement(nums1)); // [3]
        System.out.println(solution.majorityElement(nums2)); // [1]
        System.out.println(solution.majorityElement(nums3)); // [1, 2]
        System.out.println(solution.majorityElement(nums4)); // [2]

        System.out.println("---");
        System.out.println(solution.majorityElement1(nums1)); // [3]
        System.out.println(solution.majorityElement1(nums2)); // [1]
        System.out.println(solution.majorityElement1(nums3)); // [1, 2]
        System.out.println(solution.majorityElement1(nums4)); // [2]
    }
}
