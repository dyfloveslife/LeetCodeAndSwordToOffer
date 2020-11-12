package LeetCodeSolution.AlgorithmThought._01_DoublePointer._680_ValidPalindromeII;

/*
 * 验证回文字符串Ⅱ
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

    public boolean validPalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;
        // while 循环的作用就是让 i 和 j 来到所指字符不同的位置
        while (i < j && s.charAt(i) == s.charAt(j)) {
            i++;
            j--;
        }
        // 当 i 和 j 所指的字符不同时，则开始删掉某些字符，进一步判断是否满足回文串
        if (isPalindrome(s, i + 1, j)) {
            return true;
        }
        if (isPalindrome(s, i, j - 1)) {
            return true;
        }
        return false;
    }

    private boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "deeee";
        String s2 = "abca";

        System.out.println(solution.validPalindrome(s1));
        System.out.println(solution.validPalindrome(s2));
    }
}