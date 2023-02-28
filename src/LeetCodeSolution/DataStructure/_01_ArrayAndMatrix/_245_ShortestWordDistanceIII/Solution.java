package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._245_ShortestWordDistanceIII;

/*
 * 最短单词距离 III
 *
 * 题目描述：
 * 给定一个单词列表和两个单词 word1 和 word2，返回列表中这两个单词之间的最短距离。
 * word1 和 word2 是有可能相同的，并且它们将分别表示为列表中两个独立的单词。
 * 你可以假设 word1 和 word2 都在列表里。
 *
 * 思路：
 * 1. 与 243 题类似，只不过给定的两个单词有可能是相同的；
 * 2. 若给定的两个单词不同，则处理逻辑与 243 题类似；若不同，则用 idx1 的索引去更新 idx2 的索引。
 */
public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return 0;
        }

        int idx1 = -1, idx2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                idx1 = i;
            } else if (word.equals(word2)) {
                idx2 = i;
            }
            if (idx1 != -1 && idx2 != -1 && idx1 != idx2) {
                ans = Math.min(ans, Math.abs(idx1 - idx2));
            }
            if (word1.equals(word2)) {
                idx2 = idx1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "makes", word2 = "coding";
        String word3 = "makes", word4 = "makes";

        Solution solution = new Solution();
        System.out.println(solution.shortestDistance(words, word1, word2)); // 1
        System.out.println(solution.shortestDistance(words, word3, word4)); // 3
    }
}
