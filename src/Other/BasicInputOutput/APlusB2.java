package Other.BasicInputOutput;

import java.util.Scanner;

/*
 * 题目描述：
 * 计算 a + b
 *
 * 输入：
 * 输入第一行包括一个数据组数 t(1 <= t <= 100)
 * 接下来每行包括两个正整数 a,b(1 <= a, b <= 10^9)
 *
 * 输出：
 * 输出 a + b 的结果
 *
 * 示例：
 * 输入：
 * 2
 * 1 5
 * 10 20
 *
 * 输出：
 * 6
 * 30
 */
public class APlusB2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(a + b);
        }
    }
}