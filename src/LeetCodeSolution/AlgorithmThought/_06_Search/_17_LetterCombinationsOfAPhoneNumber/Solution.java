package LeetCodeSolution.AlgorithmThought._06_Search._17_LetterCombinationsOfAPhoneNumber;

import java.util.ArrayList;
import java.util.List;

/*
 * 电话号码的组合
 *
 * 题目描述：
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。
 * 注意 1 不对应任何字母。
 *
 * 思路：
 * 0. https://www.youtube.com/watch?v=iFP-xQhg6IU
 * 1. 回溯：
 * 2. 输入的是一个字符串数字，需要用给定的数字找到对应的字符映射关系；
 * 3. 例如给定 "27"，则 2 对应的字符是 "abc"，7 对应的字符是 "pqrs"；
 *    3.1) 首先拿出 2 中的 'a'，然后用 'a' 去与 'p' 组合，生成 "ap"；
 *    3.2) 然后还得再回到 2 中的 'a'，再用 'a' 去与 'q' 组合，生成 "aq"；
 *    3.3) 一直组合，一直回溯，直至结束。
 */
public class Solution {

    // 索引 0 和 1 不对应任何字符串
    private static String[] keyboard = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // 0 表示从 digits 的第 0 个位置开始进行组合
        dfs(digits, 0, sb, res);
        return res;
    }

    private static void dfs(String digits, int index, StringBuilder sb, List<String> res) {
        // 如果已经来到了 digits 的末尾，则说明对于给定的字符串数字已经遍历完了
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }
        // 例如给定的 digits 是 "27"，则 letters 就表示字符串 "abc"
        String letters = keyboard[digits.charAt(index) - '0'];
        // 用 for 循环依次拿出 abc 中的每个字符，即 'a'、'b'、'c'
        for (int i = 0; i < letters.length(); i++) {
            char c = letters.charAt(i);
            // 先将字符 'a' 添加进行 sb
            sb.append(c);
            // 然后再来到字符串数字 7 中对应的 "pqrs"，分别与 'a' 进行组合
            // index + 1 指向的是字符串数字 7
            dfs(digits, index + 1, sb, res);
            // 由于子过程给我的 sb 返回了 ap，所以需要将 p 删除
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
