package LeetCodeSolution.AlgorithmThought._07_Sort._347_TopKFrequentElements;

import java.util.*;

/*
 * 前 K 个高频元素
 *
 * 题目描述：
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 思路：
 * 1. 首先使用 map 存储数组中的每个数以及对应的出现次数；
 * 2. 然后根据谁出现次数多的方式进行比较，将数组中的值放进 heap 中，但 heap 中的个数不能超过给定的 k；
 * 3. 使用 LinkedList 存储前 k 个最高的元素，由于是大顶堆，所以最后再将 LinkedList 中的元素进行反转即可。
 */
public class Solution {
    public static List<Integer> topKFrequent(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        // map.getOrDefault(a, b) ：先判断 map 中有没有 a 这个 key，
        // 如果有的话，则返回对应的值；如果没有的话，则返回默认值 b
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n : arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> map.get(n1) - map.get(n2));
        for (int n : map.keySet()) {
            maxHeap.add(n);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // 由于涉及到添加元素，所以底层使用链表
        List<Integer> res = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            res.add(maxHeap.poll());
        }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        System.out.println(topKFrequent(arr, k));
    }
}
