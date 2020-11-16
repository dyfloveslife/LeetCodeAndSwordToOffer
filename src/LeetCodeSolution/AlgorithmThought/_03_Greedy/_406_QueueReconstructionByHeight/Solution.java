package LeetCodeSolution.AlgorithmThought._03_Greedy._406_QueueReconstructionByHeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * 根据身高重建队列
 *
 * 题目描述：
 * 假设有打乱顺序的一群人站成一个队列。
 * 每个人由一个整数对 (h, k) 表示，其中 h 是这个人的身高，k 是排在这个人前面且身高大于或等于 h 的人数。
 * 编写一个算法来重建这个队列。
 *
 * 思路：
 * 1. 先按照 h 进行降序，h 相等的话则按照 k 进行升序排序；
 * 2. 假如队列中所有的人的 (h, k) 中 h 都相等，只有 k 不同的话，则每个人在队列的索引 index==k；
 * 3. 假设候选队列为 A，已排好的队列为 B，则从 A 中选一个身高最高的人 x，插入到 B 中；
 * 4. 由于在已排好的队列 B 中的每个人都比 x 要高，因此 x 插入的位置就是看 x 前面有多少个人就行了；
 * 5. 比如 x 的前面有 k=5 个人，则 x 就直接插入到 B 中第 5 个位置即可。
 */
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0) {
            return new int[0][0];
        }

        // 身高降序，如果身高相等，则按照 k 进行升序
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[0] == b[0] ? a[1] - b[1] : b[0] - a[0];
            }
        });

        List<int[]> ans = new ArrayList<>();
        for (int[] p : people) {
            // equals add(index, element)
            ans.add(p[1], p);
        }
        return ans.toArray(new int[people.length][2]);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};

        System.out.println(Arrays.deepToString(solution.reconstructQueue(arr)));
    }
}
