package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._565_ArrayNesting;

import java.util.HashSet;

/*
 * 数组嵌套
 *
 * 题目描述：
 * 索引从 0 开始长度为 N 的数组 A，包含 0 到 N - 1 的所有整数。找到最大的集合 S 并返回其大小。
 * 其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... } 且遵守以下的规则。
 * 假设选择索引为 i 的元素 A[i] 为 S 的第一个元素，S 的下一个元素应该是 A[A[i]]，之后是 A[A[A[i]]]... 以此类推，不断添加直到  S 出现重复的元素。
 *
 * 思路：
 * 1. 遍历数组，如果从一个位置开始嵌套查找直到找到一个重复元素，这个过程找到的所有元素就会形成一个环；
 * 2. 因此，如果再次遇到相同的元素，则将其置为 -1 或去除即可；
 * 3. 该题其实是使用了赋值 -1 并判断，通过遍历数组的方式来判断是否含有符合要求的元素；
 * 4. 如果在遍历过程中含有符合要求的元素，则开始统计数量。
 */
public class Solution {
    // 使用 HashSet
    public int arrayNesting1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int k = i;
            while (!set.contains(nums[k])) {
                set.add(nums[k]);
                count++;
                k = nums[k];
            }
            res = Math.max(res, count);
        }
        return res;
    }

    // 不使用 HashSet
    public int arrayNesting2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int k = i;
            // 将走过的位置置为 -1，这样一来等到再次遍历到的值为 -1 的时候，就知道已经出现了重复的元素
            while (nums[k] != -1) {
                count++;
                int temp = nums[k];
                nums[k] = -1;
                k = temp;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5, 4, 0, 3, 1, 6, 2};

        System.out.println(solution.arrayNesting1(nums));
        System.out.println(solution.arrayNesting2(nums));
    }
}
