package Other.BasicInputOutput;

import java.util.Scanner;

/*
 * 题目描述：
 * 计算 a + b
 *
 * 输入：
 * 输入包括两个正整数 a, b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为 0 0 则结束输入
 *
 * 输出：
 * 输出 a + b 的结果
 *
 * 示例：
 * 输入：
 * 1 5
 * 10 20
 * 0 0
 *
 * 输出：
 * 6
 * 30
 */
public class APlusB3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == 0 && b == 0) {
                return;
            }
            System.out.println(a + b);
        }
    }
}