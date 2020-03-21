package Other.BasicInputOutput;

import java.util.Scanner;

/*
 * 题目描述：
 * 计算一系列数的和
 *
 * 输入：
 * 输入的第一行包括一个正整数 t(1 <= t <= 100), 表示数据组数。
 * 接下来 t 行, 每行一组数据。
 * 每行的第一个整数为整数的个数 n(1 <= n <= 100)。
 * 接下来 n 个正整数, 即需要求和的每个正整数。
 *
 * 输出：
 * 每组数据输出求和的结果
 *
 * 示例：
 * 输入：
 * 2
 * 4 1 2 3 4
 * 5 1 2 3 4 5
 *
 * 输出：
 * 10
 * 15
 */
public class APlusB5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int sum = 0;
            int size = sc.nextInt();
            for (int i = 0; i < size; i++) {
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}
