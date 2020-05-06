package LeetCodeSolution.AlgorithmThought._06_Search._47_PermutationsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 全排列Ⅱ
 *
 * 题目描述：
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 思路：
 * 1. 还是使用回溯 + 递归的方式；
 * 2. 只不过由于存在重复的数字，在判重时需要判断当前数字是否与前一个数字相等以及前一个数字是否没有访问过。
 */
public class Solution {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        // 先对 nums 进行排序
        Arrays.sort(nums);

        boolean[] visited = new boolean[nums.length];
        List<Integer> path = new ArrayList<>();
        dfs(res, path, visited, nums);
        return res;
    }

    public static void dfs(List<List<Integer>> res, List<Integer> path, boolean[] visited, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false) {
                continue;
            }

            visited[i] = true;
            path.add(nums[i]);
            dfs(res, path, visited, nums);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        System.out.println(permuteUnique(nums));
    }
}
