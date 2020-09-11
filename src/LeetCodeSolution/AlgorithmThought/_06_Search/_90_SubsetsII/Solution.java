package LeetCodeSolution.AlgorithmThought._06_Search._90_SubsetsII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 子集 Ⅱ
 *
 * 题目描述：
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 思路：
 * 1. 同样是回溯，但需要注意应进行剪枝；
 * 2. 此外，数组中的元素可能存在重复的元素，因此需要进行排序，然后再去重；
 * 3. 当然，可是额外开一个 boolean 数组进行剪枝；
 * 4. 但还可以直接在当前深度进行剪枝，如图 https://i.loli.net/2020/08/25/2QYK1ItUSAZCfgj.png
 */
public class Solution {

    //第一种方式：在剪枝时，使用 boolean 数组
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }

        // 排序的目的是：让重复的元素放在一起
        Arrays.sort(nums);

        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtracking1(res, path, visited, nums, 0);
        return res;
    }

    public void backtracking1(List<List<Integer>> res, List<Integer> path, boolean[] visited, int[] nums, int start) {
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false) {
                continue;
            }

            path.add(nums[i]);
            visited[i] = true;
            backtracking1(res, path, visited, nums, i + 1);
            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }

    // 第二种方式：在剪枝时，直接在递归树上进行剪枝
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        backtracking2(nums, ans, new ArrayList<>(), 0);
        return ans;
    }

    private void backtracking2(int[] nums, List<List<Integer>> ans, List<Integer> path, int start) {
        ans.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 注意这里的区别
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtracking2(nums, ans, path, i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = {1, 2, 2};

        System.out.println(s.subsetsWithDup1(nums));
        System.out.println(s.subsetsWithDup2(nums));
    }
}
