package LeetCodeSolution.AlgorithmThought._03_Greedy._763_PartitionLabels;

import java.util.ArrayList;
import java.util.List;

/*
 * 划分字母区间
 *
 * 题目描述：
 * 字符串 S 由小写字母组成。
 * 我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
 * 返回一个表示每个字符串片段的长度的列表。
 *
 * 思路：
 * 1. 难点在于如果找到字符串的断点，即拆分成为子串的位置；
 * 2. 仔细观察题目中的例子，可以发现一旦某个字母多次出现了，那么其最后一次出现位置必须要在当前子串中；
 * 3. 所以我们关注的是每个字母最后的出现位置，可以通过数组来建立字母和其最后出现位置之间的映射，如下：
 *    a -> 8
 *    b -> 5
 *    c -> 7
 *    d -> 14
 *    e -> 15
 *    f -> 11
 *    g -> 13
 *    h -> 19
 *    i -> 22
 *    j -> 23
 *    k -> 20
 *    l -> 21
 * 4. 建立好映射之后，就需要开始遍历字符串 S 了，我们维护一个 start 变量，是当前子串的起始位置，还有一个 last 变量，是当前子串的结束位置；
 * 5. 每当我们遍历到一个字母，我们需要在数组中提取出其最后一个位置；
 * 6. 因为一旦当前子串包含了一个字母，其必须包含所有的相同字母，所以我们要不停的用当前字母的最后一个位置来更新 last 变量；
 * 7. 只有当 i 和 last 相同了，即当前子串包含了所有已出现过的字母的最后一个位置，
 *    即之后的字符串里不会有之前出现过的字母了，此时就应该是断开的位置，我们将长度加入结果 res 中。
 */
public class Solution {

    public static List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }

        // 将字符作为数组的下标，但相同的字符会被覆盖，从而得到相同字符的最后一个字符的位置
        // 各字母最后一次出现的下标
        int[] map = new int[26];
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a'] = i;
        }

        int start = 0;
        int last = 0;
        for (int i = 0; i < s.length(); i++) {
            last = Math.max(last, map[s.charAt(i) - 'a']);
            // 当前子串包含了所有已出现过的字母的最后一个位置
            if (i == last) {
                res.add(i - start + 1);
                start = i + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }
}
