package LeetCodeSolution.AlgorithmThought._06_Search._127_WordLadder;

import java.util.*;

/*
 * 单词接龙
 *
 * 题目描述：
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。
 * 转换需遵循如下规则：
 *     每次转换只能改变一个字母。
 *     转换过程中的中间单词必须是字典中的单词。
 *
 * 思路：
 * 1. 使用 BFS+队列，对于每个当前的单词，例如 hit，首先将 h 依次换成 a、b、c、...、z；
 * 2. 即将 hit 变成 ait、bit、cit、...、zit，依次在 wordList 中进行查找，看看有没有相等的；
 * 3. 如果找到了，就将其添加到队列中，由于每次可能会添加多个单词，所以每次都需要统计队列的长度；
 * 4. 如果新组成的单词之前在 set 中出现过，则将其在 set 中删除。
 */
public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        // 记录当前路径的长度
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // 将当前队列中的每个元素逐一弹出
                String cur = queue.poll();
                // 如果当前的字符串 cur 能够搜索到终点的 endWord 的话，
                // 则直接返回即可
                if (cur.equals(endWord)) {
                    return level + 1;
                }
                // 进行 BFS 搜索
                // 假如 cur = hit，则 j 首先会来到字母 h，然后尝试将 h 换成从 a 到 z 中的某个字母
                char[] chars = cur.toCharArray();
                for (int j = 0; j < cur.length(); j++) {
                    // temp 所指的字母就是 h
                    char temp = chars[j];
                    // 搜索字母 h 可以改变的所有可能情况
                    for (char c = 'a'; c <= 'z'; c++) {
                        // 例如先将 h 替换成 a
                        chars[j] = c;
                        // 然后再组成一个新的字符串，即从原来的 hit 变成 ait
                        String s = new String(chars);
                        // 如果发现 ait 在 wordList 中的话，则将搜索到的所有可能情况放到队列中
                        // 再将 ait 从 wordList 中去掉，避免下次重复添加
                        if (set.contains(s)) {
                            queue.offer(s);
                            set.remove(s);
                        }
                    }
                    // 当前位置已经从 a 到 z 搜索完了，然后再将当前字母进行还原过来
                    chars[j] = temp;
                }
            }
            level++;
        }
        // 注意返回 0
        return 0;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("got");
        wordList.add("hot");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(solution.ladderLength(beginWord, endWord, wordList));
    }
}
