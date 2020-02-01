package Other.BasicAlgorithm._40_TowerOfHanoi;

/*
 * 汉诺塔问题
 *
 * 题目描述：
 * 打印 n 层汉诺塔从最左边移动到最后边的全部过程
 *
 * 思路：
 * 1. 现在不分左右中杆，只有 from、to 和 help，现在想将 from 上的 1~n 借助 help 移动到 to 上去；
 * 2. 第一步：将 1~n-1 从 from 移动到 help；
 * 3. 第二步：将 n 从 from 移动到 to；
 * 4. 第三步：将 1~n-1 从 help 移动到 to;
 * 5. 一共 (2^n)-1 步。
 */
public class Solution {

    // N 代表从 1 到 N
    public static void process(int N, String from, String to, String help) {
        if (N == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            // 这里分三步走
            process(N - 1, from, help, to);
            System.out.println("Move " + N + " from " + from + " to " + to);
            process(N - 1, help, to, from);
        }
    }

    public static void main(String[] args) {
        process(3, "左", "右", "中");
    }
}
