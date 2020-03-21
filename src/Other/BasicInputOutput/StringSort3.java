package Other.BasicInputOutput;

import java.util.Arrays;
import java.util.Scanner;

/*
 * 题目描述：
 * 对输入的字符串进行排序后输出
 *
 * 输入：
 * 多个测试用例，每个测试用例一行。
 * 每行通过 , 隔开，有 n 个字符，n ＜ 100
 *
 * 输出：
 * 对于每组测试用例，输出一行排序过的字符串，每个字符串通过空格隔开
 *
 * 示例：
 * 输入：
 * a,c,bb
 * f,dddd
 * nowcoder
 *
 * 输出：
 * a,bb,c
 * dddd,f
 * nowcoder
 */
public class StringSort3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] str = sc.nextLine().split(",");
            Arrays.sort(str);
            for (int i = 0; i < str.length - 1; i++) {
                System.out.print(str[i] + ",");
            }
            System.out.println(str[str.length - 1]);
        }
    }
}
