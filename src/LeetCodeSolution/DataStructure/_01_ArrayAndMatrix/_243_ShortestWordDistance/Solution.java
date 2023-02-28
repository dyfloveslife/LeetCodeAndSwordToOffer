package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._243_ShortestWordDistance;

/*
 * 最短单词距离
 *
 * 题目描述：
 * 给定一列单词列表 words 和两个单词 word1、word2，返回列表中这两个单词的最短距离。
 * 你可以假设两个给定的单词不同，并且均在单词列表中。
 *
 * 思路：
 * 1. 在遍历 words 的过程中，判断当前单词是否等于给定的 word1 或 word2；
 * 2. 若等于，则更新索引，并维护一个 ans 取最小的距离。
 */
public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return 0;
        }

        int idx1 = -1, idx2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i  = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                idx1 = i;
            }
            if (word.equals(word2)) {
                idx2 = i;
            }
            if (idx1 != -1 && idx2 != -1) {
                ans = Math.min(ans, Math.abs(idx1 - idx2));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding", word2 = "practice";
        String word3 = "makes", word4 = "coding";

        Solution solution = new Solution();
        System.out.println(solution.shortestDistance(words, word1, word2)); // 3
        System.out.println(solution.shortestDistance(words, word3, word4)); // 1
    }
}
