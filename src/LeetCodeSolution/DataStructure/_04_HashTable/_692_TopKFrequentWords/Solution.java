package LeetCodeSolution.DataStructure._04_HashTable._692_TopKFrequentWords;

import java.util.*;

/*
 * 前 K 个高频单词
 *
 * 题目描述：
 *
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 *
 * 思路：
 * 1. 一般对于 topK 问题来说，可以使用堆进行处理；
 * 2. 首先使用哈希表统计每个单词出现的次数；
 * 3. 然后定义一个小根堆，但是这个小根堆有些特殊，整体的思路是按照单词出现的次数进行降序排序，但是，只要两个单词出现的次数相同，
 *    则按照字典序升序排序；
 * 4. 每次向堆中加入单词，如果堆的容量大于 k，则弹出堆顶单词，而弹出的这个堆顶单词，其实是堆中出现次数最少的单词，或者出现次数相同的情况下，
 *    位于字典序后面的单词；
 * 5. 最后由于堆中元素的特殊性，因此需要倒叙填充结果数组；
 * 6. 这里的特殊性在于：越往堆顶元素靠近，就越是字典序靠后的，或者出现次数最少的。
 */
public class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

//        PriorityQueue<String> heap = new PriorityQueue<>((o1, o2) -> (map.get(o1).equals(map.get(o2))) ?
//                o2.compareTo(o1) : (map.get(o1) - map.get(o2)));

        //  这里其实定义的是小顶堆，堆中保存的都是出现次数很多的单词，然后在最后收集结果进行返回的时候，需要倒序填充结果数组
        PriorityQueue<String> heap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                // 只要存在两个单词出现的次数相同的情况，则按照字典序升序排序
                if (map.get(str1).equals(map.get(str2))) {
                    return str2.compareTo(str1);
                }
                // 否则就按照单词出现的次数进行降序
                return map.get(str1) - map.get(str2);
            }
        });

        for (String word : map.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        String[] str = new String[k];
        for (int i = k - 1; i >= 0; i--) {
            str[i] = heap.poll();
        }
        return Arrays.asList(str);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words1 = {"i", "love", "leetcode", "i", "love", "coding"};
        int k1 = 2;

        String[] words2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k2 = 4;

        System.out.println(solution.topKFrequent(words1, k1));
        System.out.println(solution.topKFrequent(words2, k2));
    }
}
