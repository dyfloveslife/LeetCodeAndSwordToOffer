package LeetCodeSolution.AlgorithmThought._01_DoublePointer._680_ValidPalindromeII;

/*
 * 验证回文字符串 II
 *
 * 题目描述：
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * 其中，对于 abca，你可删除 c，然后返回 true。
 *
 * 思路：
 * 1. 使用左右指针，左指针在增加的同时右指针也在减少；
 * 2. 在遍历的时候，只要两个指针对应的字符不相等则返回 false；
 * 3. 然后试着删除左指针或右指针所指的字符，继续遍历比较，而已经遍历过的字符则不需要再重新遍历；
 * 4. 上面说的删除，其实是跳过当前的字符，判断剩下的字符。
 */
public class Solution {

    public static boolean validPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }

        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            // 只要有不相等的字符，则“跳过”该字符，然后比较剩下的字符
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
        }
        return true;
    }


    public static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Solution.validPalindrome("deeee"));
        System.out.println(Solution.validPalindrome("abca"));
    }
}