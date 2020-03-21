package Other.BasicInputOutput;

import java.util.Scanner;

/*
 * 题目描述：
 * 计算一系列数的和
 *
 * 输入：
 * 输入数据包括多组。
 * 每组数据一行, 每行的第一个整数为整数的个数 n(1 <= n <= 100), n 为 0 的时候结束输入。
 * 接下来 n 个正整数, 即需要求和的每个正整数。
 *
 * 输出：
 * 每组数据输出求和的结果
 *
 * 示例：
 * 输入：
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 * 0
 *
 * 输出：
 * 10
 * 15
 */
public class APlusB4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        while ((n = sc.nextInt()) != 0) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}