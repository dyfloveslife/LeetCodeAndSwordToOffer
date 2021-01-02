package LeetCodeSolution.AlgorithmThought._02_DP._17_13_ReSpaceLCCI;

/*
 * 恢复空格
 *
 * 题目描述：
 * 哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。
 * 像句子 "I reset the computer. It still didn’t boot!" 已经变成了 "iresetthecomputeritstilldidntboot"。
 * 在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一本厚厚的词典 dictionary，不过，有些词没在词典里。
 * 假设文章用 sentence 表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。
 *
 * 思路：
 * 1. 使用 dp；
 * 2. dp[i] 表示以索引 i 结尾的字符串，它之前最多能匹配单词的数量；
 * 3. 需要注意的是，最后需要返回的是未识别的字符数，也就是没有匹配成功的字符数；
 * 4. 以  S="leetCodeGood"，dict=["Leet","etc","code","go"]；
 * 5. 详细解释，参见博客：https://dyfloveslife.github.io/2020/07/09/alg-re-space-LCCI/
 */
public class Solution {
    public int reSpace(String[] dictionary, String sentence) {
        // 长度为 sentence 的长度加 1，是为了保证 sentence 中的第一个字符是从 1 开始的
        int[] dp = new int[sentence.length() + 1];
        int index = 1;

        while (index <= sentence.length()) {
            // 这里先把 dp 中 index 的值设置为它的前一个值，
            // 如果有更新的话，则通过下面的状态转移方程进行更新
            dp[index] = dp[index - 1];
            for (String word : dictionary) {
                if (index < word.length()) {
                    continue;
                }
                // 分割出字符串，并判断该字符串是否在字典中
                String temp = sentence.substring(index - word.length(), index);
                // 如果分割出的字符串不在字典中，则跳过
                if (!temp.equals(word)) {
                    continue;
                }
                // 如果能够在字典中找到，则更新 dp 数组
                dp[index] = Math.max(dp[index], dp[index - word.length()] + word.length());
            }
            index++;
        }
        return sentence.length() - dp[sentence.length()];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] dictionary = {"looked","just","like","her","brother"};
        String sentence = "jesslookedjustliketimherbrother";

        System.out.println(solution.reSpace(dictionary, sentence));
    }
}
