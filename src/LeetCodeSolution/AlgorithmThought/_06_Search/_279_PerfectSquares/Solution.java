package LeetCodeSolution.AlgorithmThought._06_Search._279_PerfectSquares;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 * 完全平方数
 *
 * 题目描述：
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 *
 * 思路：
 * 1. 由于每次都需要进行判断多种情况，所以可以使用 BFS；
 * 2. 队列用于存储每一轮遍历得到的节点；
 * 3. 对于已经访问过的节点，应将它进行标记，防止重复遍历；
 * 4. 将第一次可能出现的情况做分析，只要 i * i < n 即可；
 * 5. 再此基础上进行第二次分析，只要满足 i * i < n 即可；
 * 6. 如果 (n-以前出现过的平方数) 之前已经出现过，则将该数忽略。
 */
public class Solution {
    public static int numSquares(int n) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);

        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            res++;
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                // j*j+cur 比 n 小，则说明还能找到一个完全平方数
                for (int j = 1; j * j + cur <= n; j++) {
                    int nextVal = j * j + cur;
                    // 如果这个完全平方数已经等于 n，则将结果返回
                    if (nextVal == n) {
                        return res;
                    }
                    // 如果这个完全平方数小于 n，并且之前没有出现过，则将其添加到队列中
                    if (nextVal < n && !visited.contains(nextVal)) {
                        queue.offer(nextVal);
                        visited.add(nextVal);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(numSquares(12));
    }
}
