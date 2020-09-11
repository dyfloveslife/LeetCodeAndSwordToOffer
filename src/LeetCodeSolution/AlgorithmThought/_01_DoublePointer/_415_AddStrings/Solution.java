package LeetCodeSolution.AlgorithmThought._01_DoublePointer._415_AddStrings;

/*
 * 字符串相加
 *
 * 题目描述：
 * 给定两个字符串形式的非负整数 num1 和 num2 ，计算它们的和。
 *
 * 思路：
 * 1. 使用双指针，分别指向两个字符串的末尾，逐渐向前计算，即模式计算加法的过程；
 * 2. 当指针走过了其中一个字符串的头部的时候，需要给其中短的字符串添加 0，以便于计算；
 * 3. 最后遍历完 num1 和 num2 后跳出循环，需要根据进位来判断是否在头部添加进位 1。
 */
public class Solution {
    public String addString(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        // 注意：这里使用 或 运算，因为 num1 和 num2 的长度不一定相等，
        // 所以，只要其中有一个字符串还可以计算，那么就继续执行 while 循环语句
        while (i >= 0 || j >= 0) {
            // 取 i 或 j 所指位置上的数字
            int n1 = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int n2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            res.append(sum % 10);
        }
        if (carry == 1) {
            res.append(1);
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String s1 = "123";
        String s2 = "456";
        System.out.println(solution.addString(s1, s2));
    }
}
