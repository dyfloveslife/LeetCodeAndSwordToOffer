package LeetCodeSolution.DataStructure._02_String._409_LongestPalindrome;

/*
 * 最长回文串
 *
 * 题目描述：
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 思路：
 * 1. 使用 58 大小的数组存储 s 中每个字母出现的次数，如果每个字母出现的次数都是偶数个，则可以构成回文串；
 * 2. 如果有单独的字符，可以将该字符放在回文串的中间；
 * 3. A 与 a 的 ASCII 相差 32，因此 32 + 26 = 58。
 */
public class Solution {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] nums = new int[58];
        for (char c : s.toCharArray()) {
            nums[c - 'A']++;
        }

        int res = 0;
        int odd = 0;
        for (int num : nums) {
            res += num % 2 == 0 ? num : num - 1;
            if (num % 2 == 1) {
                odd = 1;
            }
        }
        res += odd;
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abccccdd";

        System.out.println(solution.longestPalindrome(s));
    }
}
