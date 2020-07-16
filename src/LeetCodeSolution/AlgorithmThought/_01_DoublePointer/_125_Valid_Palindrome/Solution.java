package LeetCodeSolution.AlgorithmThought._01_DoublePointer._125_Valid_Palindrome;

import sun.text.normalizer.UCharacter;

/*
 * 验证回文串
 *
 * 题目描述：
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 思路：
 * 1. 由于只考虑字母和数字字符，因此可以使用  函数，只要遇到不是字母和数字的字符，则将其越过；
 * 2. 使用前后指针分别进行比较即可。
 */
public class Solution {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        // 空字符串也属于回文串
        if (s.length() == 0) {
            return true;
        }
        // 统一转换成小写字母
        s = s.toLowerCase();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            // 这里的两个 while 循环表示的意思是：在不越界的情况下，只要当前的字符不是字母或者数字，则越过该字符
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            // 不要忘记更新 i 和 j 的索引
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "0p";

        System.out.println(solution.isPalindrome(s1));
        System.out.println(solution.isPalindrome(s2));
    }
}
