package LeetCodeSolution.AlgorithmThought._07_Sort._451_SortCharactersByFrequency;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 根据字符出现频率排序
 *
 * 题目描述：
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 *
 * 思路：
 * 1. 使用 map + heap 的方法，首先统计每个字符出现的次数；
 * 2. 然后将每个字符以及出现的次数封装成 entry 放进 heap 中，根据自定义的排序规则进行排序；
 * 3. 最后将 entry 取出，拼接每个字符即可。
 */
public class Solution {

    public static String frequencySort1(String s) {
        // 索引是字符所对应的值，数组中的元素代表该字符出现的次数
        int[] fre = new int[128];
        for (int i = 0; i < s.length(); i++) {
            fre[s.charAt(i)]++;
        }

        // 自定义大根堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> fre[n2] - fre[n1]);
        for (int i = 0; i < 128; i++) {
            if (i != 0) {
                // 将字符串 s 中的每个字符所对应的出现的次数存入 heap
                maxHeap.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = (char) (int) maxHeap.poll();
            for (int i = fre[c]; i > 0; i--) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String frequencySort2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((n1, n2) -> map.get(n2) - map.get(n1));
        // 把每个字符放进大顶堆中，字符大的在堆顶
        maxHeap.addAll(map.keySet());

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            // 根据字符查找对应的出现次数
            for (int i = map.get(c); i > 0; i--) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String frequencySort3(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }

        char[] chars = str.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((n1, n2) -> {
            // 按照出现次数的高低进行排序，如果次数相同，则按照字符前后进行排序
            if (n1.getValue().equals(n2.getValue())) {
                return n2.getKey() - n1.getKey();
            } else {
                return n2.getValue() - n1.getValue();
            }
        });
        for (Map.Entry entry : map.entrySet()) {
            maxHeap.add(entry);
        }

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> item = maxHeap.poll();
            for (int i = 0; i < item.getValue(); i++) {
                sb.append(item.getKey());
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort2("Aabb"));
        System.out.println(frequencySort2("tree"));
        System.out.println(frequencySort2("cccaaa"));
    }
}
