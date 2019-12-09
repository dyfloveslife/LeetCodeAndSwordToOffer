package SwordToOfferSolution._41_02_FirstAppearingOnce;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 字符流中第一个不重复的字符
 *
 * 题目描述:
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。
 * 例如，当从字符流中只读出前两个字符 “go” 时，第一个只出现一次的字符是 "g"；
 * 当从该字符流中读出前六个字符 “google” 时，第一个只出现一次的字符是 "l"。
 * 如果当前字符流没有存在出现一次的字符，返回 # 字符。
 *
 * 思路：
 * 1. 构造一个类似于哈希数组的结果，每次 insert 的时候统计当前字符出现的次数，并将该字符加入到队列中；
 * 2. 只要队头字符的数量大于 1，则说明该字符是重复的，即让其出队；
 * 3. 由于重复的字符都出队了，所以最后返回队头元素就是第一个不重复出现的字符。
 */
public class Solution {
    private int[] count = new int[256];
    private Queue<Character> queue = new LinkedList<>();

    // 从字符流中插入一个字符
    public void insert(char ch) {
        // ch 就是对应的 ASCII 值
        // 统计每个 ch 的数量
        count[ch]++;
        // 入队
        queue.offer(ch);
        while (!queue.isEmpty() && count[queue.peek()] > 1) {
            // 出队
            queue.poll();
        }
    }

    // 返回当前字符流中第一个不重复出现的字符
    public char firstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }
}
