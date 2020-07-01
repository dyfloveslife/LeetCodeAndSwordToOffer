package LeetCodeSolution.DataStructure._02_String._696_CountBinarySubstrings;

/*
 * 计数二进制子串
 *
 * 题目描述：
 * 给定一个字符串 s，计算具有相同数量 0 和 1 的非空(连续)子字符串的数量，并且这些子字符串中的所有 0 和所有 1 都是组合在一起的。
 * 重复出现的子串要计算它们出现的次数。
 *
 * 思路：
 * 1. 比价当前字符与前一个字符是否相等，如果相等，则当前长度加一，如果不等，则说明遇到了一个新的字符，则记录之前的长度，
 *    然后将当前长度置为 1,；
 * 2. 如果之前的长度大于等于当前长度，则说明已经满足了题目的要求，则 res++。
 */
public class Solution {

    public int countBinarySubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int pre = 0;
        int cur = 1;
        int res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                cur++;
            } else {
                pre = cur;
                cur = 1;
            }
            if (pre >= cur) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "00110011";
        String s2 = "10101";

        System.out.println(solution.countBinarySubstrings(s1));
        System.out.println(solution.countBinarySubstrings(s2));
    }
}
