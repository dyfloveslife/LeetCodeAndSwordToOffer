package LeetCodeSolution.DataStructure._02_String._43_MultiplyStrings;

/*
 * 字符串相乘
 *
 * 题目描述：
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 思路：
 * 1. 从字符串的最后一位开始，不断地往前计算，采用竖式相乘的思想；
 * 2. 当两个数相乘的时候，它们乘得结果的位数将会位于 res[i+j] 和 res[i+j+1] 上；
 * 3. 需要注意的是：由于我们是从索引为 0 的位置开始遍历的，因此格外注意个位和进位应该放在 res[i+j] 还是 res[i+j+1]。
 */
public class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int[] ans = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = ans[i + j + 1] + n1 * n2;
                // 注意
                ans[i + j + 1] = sum % 10;
                ans[i + j] += sum / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.length; i++) {
            if (i == 0 && ans[i] == 0) {
                continue;
            }
            sb.append(ans[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String num1 = "2";
        String num2 = "3";
        String num3 = "123";
        String num4 = "456";

        System.out.println(solution.multiply(num1, num2));
        System.out.println(solution.multiply(num3, num4));
    }
}
