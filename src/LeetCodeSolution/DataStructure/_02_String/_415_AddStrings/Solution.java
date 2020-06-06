package LeetCodeSolution.DataStructure._02_String._415_AddStrings;

/*
 * 字符串相加
 *
 * 题目描述：
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * 注意：
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * 思路：
 * 1. 使用双指针，从两个字符串的最后一位开始模拟相加操作，将进位单独保存下来；
 * 2. 将每次的计算结果添加到 StringBuilder 中；
 * 3. 最后需要将 StringBuilder 进行翻转，然后再返回。
 */
public class Solution {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0 || num2 == null || num2.length() == 0) {
            return "";
        }

        int i = num1.length() - 1;
        int j = num2.length() - 1;

        StringBuilder sb = new StringBuilder();

        int carry = 0;
        while (i >= 0 || j >= 0) {
            int n1 = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int n2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;
            int sum = n1 + n2 + carry;
            carry = sum / 10;
            sum = sum % 10;

            sb.append(sum);
        }
        if (carry == 1) {
            sb.append("1");
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String num1 = "123";
        String num2 = "234";

        System.out.println(solution.addStrings(num1, num2));
    }
}
