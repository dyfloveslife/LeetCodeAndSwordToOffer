package LeetCodeSolution.AlgorithmThought._01_DoublePointer._42_TrappingRainWater;

import java.util.Stack;

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
 * 3. 注意两个不同的状态转移方程；
 * 4. 时间复杂度 O(N)，空间复杂度 O(N)。
 *
 * 思路三：栈
 * 1. 将每堵墙看作括号，通过对括号采用入栈、出栈的方式计算结果；
 * 2. 使用栈存储索引，当遍历到墙的高度的时候，如果当前墙的高度小于等于栈顶墙的高度，则说明会有积水，此时将当前墙的索引入栈；
 * 3. 如果当前墙的高度大于栈顶墙的高度，则说明需要结算积水了，计算完后需要把当前墙的索引入栈，作为新的积水的墙；
 * 4. 时间复杂度 O(N)，空间复杂度 O(N)。
 *
 * 思路四：双指针
 * 1. 使用 lMax 和 rMax 分别记录 height[0...left] 中最高的柱子高度以及 height[right...end] 中最高的柱子的高度；
 * 2. 时间复杂度为 O(N)，空间复杂度为 O(1)。
 */
public class Solution {
    // 思路一
    public int trap1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int res = 0;
        // i 的位置不能在数组的左右边界上，要不然就取不到其左右部分的最高值
        for (int i = 1; i < height.length - 1; i++) {
            // 寻找 i 以及 i 的左侧部分的最大值
            int lMax = 0;
            for (int j = i; j >= 0; j--) {
                lMax = Math.max(lMax, height[j]);
            }
            // 寻找 i 以及 i 的右侧部分的最大值
            int rMax = 0;
            for (int k = i; k < height.length; k++) {
                rMax = Math.max(rMax, height[k]);
            }
            res += Math.min(lMax, rMax) - height[i];
        }

        return res;
    }

    // 思路二
    public int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int res = 0;
        int length = height.length;
        int[] lMax = new int[length];
        int[] rMax = new int[length];

        // 注意这里的初始化，与下方两个 for 循环的判断条件是相关的
        lMax[0] = height[0];
        rMax[length - 1] = height[length - 1];

        // 从左到右，lMax 中的元素逐渐增大
        for (int i = 1; i < length; i++) {
            lMax[i] = Math.max(height[i], lMax[i - 1]);
        }
        // 从右到左，rMax 中的元素逐渐增大
        for (int j = length - 2; j > 0; j--) {
            rMax[j] = Math.max(height[j], rMax[j + 1]);
        }
        for (int k = 1; k < length - 1; k++) {
            res += Math.min(lMax[k], rMax[k]) - height[k];
        }

        return res;
    }

    // 思路三
    public int trap3(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        // 指向当前墙的索引
        int idx = 0;
        int ans = 0;
        while (idx < height.length) {
            // 注意结算的条件
            while (!stack.isEmpty() && height[idx] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                // 两墙之间的距离
                int currentWidth = idx - stack.peek() - 1;
                int currentHeight = Math.min(height[idx], height[stack.peek()]) - height[top];
                ans += currentWidth * currentHeight;
            }
            stack.push(idx++);
        }

        return ans;
    }

    // 思路四
    public int trap4(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int res = 0;
        int i = 0, j = height.length - 1;

        int lMax = 0, rMax = 0;
        while (i <= j) {
            lMax = Math.max(lMax, height[i]);
            rMax = Math.max(rMax, height[j]);
            //res += lMax < rMax ? (lMax - height[i++]) : (rMax - height[j--]);
            if (lMax < rMax) {
                res += (lMax - height[i]);
                i++;
            } else {
                res += (rMax - height[j]);
                j--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height1 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height2 = {4, 2, 0, 3, 2, 5};

        System.out.println(solution.trap1(height1)); // 6
        System.out.println(solution.trap2(height1)); // 6
        System.out.println(solution.trap3(height1)); // 6
        System.out.println(solution.trap4(height1)); // 6
        System.out.println("--");
        System.out.println(solution.trap1(height2)); // 9
        System.out.println(solution.trap2(height2)); // 9
        System.out.println(solution.trap3(height2)); // 9
        System.out.println(solution.trap4(height2)); // 9
    }
}
