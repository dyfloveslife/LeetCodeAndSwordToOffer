package LeetCodeSolution.AlgorithmThought._03_Greedy._452_MinimumNumberOfArrowsToBurstBalloons;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 用最少数量的箭引爆气球
 *
 * 题目描述：
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
 * 由于它是水平的，所以 y 坐标并不重要，因此只要知道开始和结束的x坐标就足够了。开始坐标总是小于结束坐标。
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。
 * 在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 x_start 和 x_end，且满足 x_start ≤ x ≤ x_end，则该气球会被引爆。
 * 可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。
 * 我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 *
 * 思路：
 * 该题的重点在于，边界接触是算重叠的，所以不更新 x。
 */
public class Solution {

    public static int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        int count = 1;
        int x_end = points[0][1];
        for (int[] point : points) {
            int start = point[0];
            if (x_end < start) {
                count++;
                x_end = point[1];
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        System.out.println(findMinArrowShots(arr));
    }
}
