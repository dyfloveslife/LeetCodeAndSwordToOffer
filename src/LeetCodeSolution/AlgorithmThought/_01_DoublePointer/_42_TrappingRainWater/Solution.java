package LeetCodeSolution.AlgorithmThought._01_DoublePointer._42_TrappingRainWater;

/*
 * 接雨水
 *
 * 题目描述：
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 思路一：暴力
 * 1. 首先 i 位置能接多少雨水取决于其左右柱子的高度的最小值再减去 i 位置的柱子高度，
 *    即：min(lMax, rMax)-height[i]。
 * 2. 对于每一个位置 i，我都按照上面的方法进行求解，然后将每个位置上的雨水加起来即可；
 * 3. 但是此方法的时间复杂度是 O(N^2)，空间复杂度是 O(1)，时间方面还可以优化。
 *
 * 思路二：备忘录优化
 * 1. 暴力方法的缺点在于：对于每个位置 i 都要计算 lMax 和 rMax，我们可以使用数组将结果先保存下来，用到的时候直接去数组查询即可；
 * 2. lMax[i] 表示位置 i 左侧最高的柱子高度，rMax[i] 表示位置 i 右侧最高柱子的高度；
 * 3. 时间复杂度 O(N)，空间复杂度 O(N)。
 *
 * 思路三：双指针
 * 1. 使用 lMax 和 rMax 分别记录 height[0...left] 中最高的柱子高度以及 height[right...end] 中最高的柱子的高度；
 * 2. 时间复杂度为 O(N)，空间复杂度为 O(1)。
 */
public class Solution {
    public int trap1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int res = 0;
        // i 的位置不能在数组的左右边界上，要不然就取不到其左右部分的最高值
        for (int i = 1; i < height.length - 1; i++) {
            int lMax = 0;
            int rMax = 0;
            // 寻找 i 的左侧部分的最大值
            for (int j = i; j >= 0; j--) {
                lMax = Math.max(lMax, height[j]);
            }
            // 寻找 i 的右侧部分的最大值
            for (int k = i; k < height.length; k++) {
                rMax = Math.max(rMax, height[k]);
            }
            res += Math.min(lMax, rMax) - height[i];
        }
        return res;
    }

    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int res = 0;
        int len = height.length;
        int[] lMax = new int[len];
        int[] rMax = new int[len];

        // 初始化
        lMax[0] = height[0];
        rMax[len - 1] = height[len - 1];

        // 从左向右计算
        for (int i = 1; i < len; i++) {
            lMax[i] = Math.max(height[i], lMax[i - 1]);
        }
        // 从右向左计算
        for (int j = len - 2; j > 0; j--) {
            rMax[j] = Math.max(height[j], rMax[j + 1]);
        }
        // 计算结果
        for (int k = 1; k < len - 1; k++) {
            res += Math.min(lMax[k], rMax[k]) - height[k];
        }
        return res;
    }

    public int trap3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int res = 0;
        int len = height.length;
        int left = 0;
        int right = len - 1;

        int lMax = height[0];
        int rMax = height[len - 1];

        while (left <= right) {
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);
            // 这里的 lMax < rMax 表示的是选出两个 max 中的最小值，
            // 拿到了最小值后再减去当前位置上的值，就可以得到当前位置所接到的水
//            res += lMax < rMax ? (lMax - height[left++]) : (rMax - height[right--]);
            if (lMax < rMax) {
                res += (lMax - height[left]);
                left++;
            } else {
                res += (rMax - height[right]);
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        System.out.println(solution.trap1(height));
        System.out.println(solution.trap2(height));
        System.out.println(solution.trap3(height));
    }
}
