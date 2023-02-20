package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._135_Candy;

import java.util.Arrays;

/*
 * 分发糖果
 *
 * 题目描述：
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的最少糖果数目。
 *
 * 思路一：两次遍历数组
 * 1. 由于"在相邻的两个孩子中，评分更高的孩子会获得更多的糖果"，那么可以理解为两者的差值为 1；
 * 2. 以上分为两种情况：
 *  2.1 情况一：若 ratings[i - 1] < ratings[i]，说明 i 号孩子比 i - 1 号孩子的糖果数量多；
 *  2.2 情况二：若 ratings[i] > ratings[i + 1]，说明 i 号孩子比 i + 1 号孩子的糖果数量多；
 * 3. 遍历两次数组，分别处理以上两种情况，每个人最终分得的糖果数量就是两个组数各个元素之和的最大值；
 * 4. 其实只声明一个数组即可，因为另一个数组可以使用变量来代替；
 * 5. 时间复杂度 O(N), 空间复杂度 O(N)。
 *
 * 思路二：
 * 1. https://leetcode.cn/problems/candy/solution/fen-fa-tang-guo-by-leetcode-solution-f01p/
 * 2. 根据规律，将已分配的糖果数量看作上升序列或下降序列；
 * 3. 从左到右遍历每个同学，其中前一个同学分得的糖果数量记为 pre；
 * 4. 如果当前同学比前一个同学评分高，则说明是升序，则给当前同学分配 pre + 1 个；
 * 5. 否则为降序，给当前同学分配 1 个糖果，当然可以分配 pre - 1 个，但题目要求"最少是 1 个"，因此我们直接分配 1 个，
 *    紧接着给该同学所在降序序列中所有的同学都再多分配一个糖果，以保证糖果数量满足条件。
 */
public class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int n = ratings.length;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(left[i], right[i]);
        }

        return ans;
    }

    // 思路一
    public int candy1(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i - 1] < ratings[i]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }

        int right = 0, ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ans += Math.max(left[i], right);
        }

        return ans;
    }

    // 思路二
    public int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }

        int n = ratings.length;
        int ans = 1;
        // incLen: 最近的升序序列的长度
        // decLen: 当前降序序列的长度
        // pre: 前一个同学已分配的糖果数量
        int incLen = 1, decLen = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                decLen = 0;
                pre = (ratings[i] == ratings[i - 1] ? 1 : pre + 1);
                ans += pre;
                incLen = pre;
            } else {
                decLen++;
                // 若当前降序序列的长度与最近的升序序列的长度相同,
                // 则需要把最近的升序序列的最后一个同学也划进递减序列中
                if (decLen == incLen) {
                    decLen++;
                }
                ans += decLen;
                pre = 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ratings1 = {1, 0, 2};
        int[] ratings2 = {1, 2, 2};
        int[] ratings3 = {1, 3, 2, 2, 1};

        // 分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果
        System.out.println(solution.candy(ratings1)); // 5
        // 分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果
        System.out.println(solution.candy(ratings2)); // 4
        // 分别给第一个、第二个、第三个、第四个、第五个孩子分发 1、2、1、2、1 颗糖果
        System.out.println(solution.candy(ratings3)); // 7

        System.out.println("--");
        System.out.println(solution.candy1(ratings1)); // 5
        System.out.println(solution.candy1(ratings2)); // 4
        System.out.println(solution.candy1(ratings3)); // 7

        System.out.println("--");
        System.out.println(solution.candy2(ratings1)); // 5
        System.out.println(solution.candy2(ratings2)); // 4
        System.out.println(solution.candy2(ratings3)); // 7
    }
}
