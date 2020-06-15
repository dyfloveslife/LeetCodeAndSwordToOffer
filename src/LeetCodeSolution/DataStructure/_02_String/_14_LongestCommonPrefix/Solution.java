package LeetCodeSolution.DataStructure._02_String._14_LongestCommonPrefix;

/*
 * 最长公共前缀
 *
 * 题目描述：
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 思路：
 * 1. 采用两两比较的方式，首先拿第一个字符串做“基准”，然后逐个与后面的字符串进行比较；
 * 2. 每次在比较的过程中，如果返回 -1，则说明需要将“基准”的最后一个字符删除；
 * 3. 通过不断的删除，那么该“基准”就会逐渐的变成要返回的最长公共前缀。
 */
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String res = strs[0];
        for (String str : strs) {
            // 如果不为 0，说明 res 不在 str 中
            while (str.indexOf(res) != 0) {
                // 通过删除最后一个字符，不断地更新 res
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"flower", "flow", "flight"};

        System.out.println(solution.longestCommonPrefix(strs));
    }
}
