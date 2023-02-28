package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._244_ShortestWordDistanceII;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 最短单词距离 II
 *
 * 题目描述：
 * 请设计一个类，使该类的构造函数能够接收一个单词列表。
 * 然后再实现一个方法，该方法能够分别接收两个单词 word1 和 word2，并返回列表中这两个单词之间的最短距离。
 * 您的方法将被以不同的参数调用多次。
 *
 * 实现 Solution 类：
 * Solution(String[] wordsDict) 用字符串数组 wordsDict 初始化对象。
 * int shortest(String word1, String word2) 返回数组 wordDict 中 word1 和 word2 之间的最短距离。
 *
 * 提示：
 * 1. 1 <= wordsDict.length <= 3 * 10^4
 * 2. 1 <= wordsDict[i].length <= 10
 * 3. wordsDict[i] 由小写英文字母组成
 * 4. word1 和 word2 在数组 wordsDict 中
 * 5. word1 != word2
 * 6. shortest 操作次数不大于 5000
 *
 * 思路：
 * 1. 由于需要频繁调用，所以最好使用 HashMap 存储传入的 wordDict，其中 key 为 String 类型的单词，value 为 List 类型的该单词出现的位置；
 * 2. 因为是从左到右的顺序遍历 wordDict 的，索引每个 List 中的索引是有序的；
 * 3. 从 map 中取出 word1 和 word2 的索引（两个 List ）后，不断更新最小值即可；
 * 4. 每次比较两个 List 最小的两个值，得到一个差值，然后把较小的那个给去掉。
 */
public class Solution {

    public static class WordDistance {
        Map<String, List<Integer>> map;

        public WordDistance(String[] wordDict) {
            map = new HashMap<>();
            for (int i = 0; i < wordDict.length; i++) {
                String word = wordDict[i];
                if (map.containsKey(word)) {
                    map.get(word).add(i);
                } else {
                    List<Integer> idxes = new ArrayList<>();
                    idxes.add(i);
                    map.put(word, idxes);
                }
            }
        }

        public int shortest(String word1, String word2) {
            List<Integer> l1 = map.get(word1);
            List<Integer> l2 = map.get(word2);

            int ans = Integer.MAX_VALUE;
            int p1 = 0, p2 = 0;
            while (p1 < l1.size() && p2 < l2.size()) {
                int v1 = l1.get(p1), v2 = l2.get(p2);
                ans = Math.min(ans, Math.abs(v1 - v2));
                if (v1 < v2) {
                    p1++;
                } else {
                    p2++;
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        String[] wordDict = {"practice", "makes", "perfect", "coding", "makes", "coding"};
        String word1 = "coding", word2 = "practice";
        String word3 = "makes", word4 = "coding";

        Solution.WordDistance wordDistance = new WordDistance(wordDict);
        System.out.println(wordDistance.shortest(word1, word2)); // 3
        System.out.println(wordDistance.shortest(word3, word4)); // 1
    }
}
