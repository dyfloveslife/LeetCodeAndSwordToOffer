package LeetCodeSolution.AlgorithmThought._01_DoublePointer._18_4Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 四数之和
 *
 * 题目描述：
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 *
 * 思路：
 * 1. 在“三数之和”的基础上，再增加一个外层循环；
 * 2. 使用 a<b<c<d，将 a 和 b=a+1 固定在左边，然后 c=b+1 和 d=nums.length-1 在右边，移动这两个指针即可；
 * 3. 如果 sum 小了，则 c++，如果 sum 大了，则 d--；
 * 4. c 和 d 相遇时，说明以当前 a 和 b 为最小值的解已经全部求出了，此时 b++，也就是让 b 进入下一轮，继续查找；
 * 5. 当 b 结束后，让 a++。
 */
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return res;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            // 如果数组中只有正数，则需要有如下的判断
//            if (nums[i] > 0) {
//                break;
//            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 0, -1, 0, -2, 2};
        int target1 = 0;
        int[] nums2 = {-3, -1, 0, 2, 4, 5};
        int target2 = 2;

        System.out.println(solution.fourSum(nums1, target1));
        System.out.println(solution.fourSum(nums2, target2));
    }
}
