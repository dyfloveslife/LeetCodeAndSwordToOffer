package LeetCodeSolution.DataStructure._02_String._819_MostCommonWord;

import org.omg.CORBA.CharSeqHelper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.SplittableRandom;

/*
 * 最常见的单词
 *
 * 题目描述：
 * 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
 * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
 * 禁用列表中的单词用小写字母表示，不含标点符号。
 * 段落中的单词不区分大小写。
 * 答案都是小写字母。
 *
 * 思路：
 * 1. 统计每个单词出现的次数，忽略标点符号和大小写，答案就是出现次数最多的且不在禁用单词列表中的那个单词；
 * 2. 在统计单词的时候，由于含有空格、标点符号等，因此在扫描的过程中，如果遇到非字符的情况，那就把之前遇到的字母作为一个单词；
 * 3. 每将一个单词放进 map 中的时候，如果该单词不在禁用单词列表中，那么就更新其出现的次数。
 */
public class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) {
            return "";
        }

        // 为了保证最后一个字符遍历完以后，也能够当作单词处理，因此需要增加一个额外的非字母字符，
        // 其实添加非字母字符的原因在于下面的 if(Character.isLetter(c)) 的判断
        paragraph += ".";
        // 使用 map 存储当前单词及其出现的次数
        HashMap<String, Integer> map = new HashMap<>();
        // 使用 set 存储禁用单词列表 banned
        HashSet<String> set = new HashSet<>(Arrays.asList(banned));
        // 或者使用 set.addAll(Arrays.asList(banned));

        String res = "";
        int maxCount = 0;
        // 使用 StringBuilder 来拼接每个单词
        StringBuilder sb = new StringBuilder();

        paragraph = paragraph.toLowerCase();
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c);
                // 如果 c 不是字符，则说明当前的 c 遇到了空格或特殊符号，那么就可以收集单词了
            } else if (sb.length() > 0) {
                String word = sb.toString();
                // 判断当前收集到的单词是否在禁用单词列表中，
                // 如果不包含在禁用单词列表中，则说明这是一个可用的单词，那么就需要更新它出现的次数
                if (!set.contains(word)) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                    // 如果当前单词出现的次数比 maxCount 大，则更新 maxCount，
                    // 并同时更新最终结果 res
                    if (map.get(word) > maxCount) {
                        maxCount = map.get(word);
                        res = word;
                    }
                }
                // 如果 set 中包含 word 这个单词，则更新 StringBuilder
                // 相当于每个单词都是使用 StringBuilder 进行拼接的，因此下一次拼接单词的时候，
                // 就需要一个新的 StringBuilder 来进行拼接了
                sb = new StringBuilder();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};

        System.out.println(solution.mostCommonWord(paragraph, banned));
    }
}
