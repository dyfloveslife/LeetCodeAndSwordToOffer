package Other.BasicInputOutput;

import java.util.Scanner;

/*
 * 需要注意的是：数字越界的情况
 *
 * 输入：
 * 输入有多组测试用例，每组空格隔开两个整数
 *
 * 输出：
 * 对于每组数据输出一行两个整数的和
 *
 * 示例：
 * 输入：
 * 1 1
 *
 * 输出：
 * 2
 */
public class StringSort4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            System.out.println(a + b);
        }
    }
}
