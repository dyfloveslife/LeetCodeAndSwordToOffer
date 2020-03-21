package Other.BasicInputOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * 题目描述：
 * 对输入的字符串进行排序后输出
 *
 * 输入：
 * 输入有两行，第一行 n
 * 第二行是 n 个空格隔开的字符串
 *
 * 输出：
 * 输出一行排序后的字符串，空格隔开，无结尾空格
 *
 * 示例：
 * 输入：
 * 5
 * c d a bb e
 *
 * 输出：
 * a bb c d e
 */
public class StringSort1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            list.add(sc.next());
        }

        Collections.sort(list);
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.print(list.get(i) + " ");
        }
        // 单独处理最后一个字符
        System.out.println(list.get(list.size() - 1));
    }
}
