package LeetCodeSolution.AlgorithmThought._01_DoublePointer._11_ContainerWithMostWater;

/*
 * 盛最多水的容器
 *
 * 题目描述：
 * 给定一个长度为 n 的整数数组 height。
 * 有 n 条垂线，第 i 条线的两个端点分别是 (i, 0) 和 (i, height[i])。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 思路：
 * 1. 使用双指针，从数组两端不断地向中间靠拢；
 * 2. 每次比较两个指针所指的元素大小（也就是比较高度），水的多少取决于短板的长度再乘以两个指针之间的距离；
 * 3. 即维护一个最终的结果 res，每次计算水的容量时更新 res，遍历完整个数组后，最终的 res 就是答案。
 */
public class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int res = 0;
        int left = 0, right = height.length - 1;

        while (left < right) {
            if (height[left] < height[right]) {
                res = Math.max(res, (right - left) * height[left]);
                left++;
            } else {
                res = Math.max(res, (right - left) * height[right]);
                right--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};

        System.out.println(solution.maxArea(height));
    }
}
