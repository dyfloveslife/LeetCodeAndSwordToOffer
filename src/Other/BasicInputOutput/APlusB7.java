package Other.BasicInputOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * 题目描述：
 * 计算一系列数的和
 *
 * 输入：
 * 输入数据有多组, 每行表示一组输入数据。
 * 每行不定有n个整数，空格隔开。(1 <= n <= 100)。
 *
 * 输出：
 * 每组数据输出求和的结果
 *
 * 示例：
 * 输入：
 * 1 2 3
 * 4 5
 * 0 0 0 0 0
 *
 * 输出：
 * 6
 * 9
 * 0
 */
public class APlusB7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int sum = 0;
            String[] str = sc.nextLine().split(" ");
            for (String s : str) {
                sum += Integer.valueOf(s);
            }
            System.out.println(sum);
        }
    }
}
