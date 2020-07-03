package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._378_KthSmallestElementInASortedMatrix;

import java.util.PriorityQueue;

/*
 * 有序矩阵中第 K 小的元素
 *
 * 题目描述：
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 * 思路：
 * 1. 使用堆，在遍历矩阵的时候，将每个元素放进堆中，维护大根堆，最后返回堆顶即可；
 * 2. 使用二分查找的方法，
 */
public class Solution {

    public int kthSmallest1(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> (o2 - o1));

        for (int[] arr : matrix) {
            for (int num : arr) {
                heap.offer(num);
                while (heap.size() > k) {
                    heap.poll();
                }
            }
        }
        return heap.peek();
    }

    public int kthSmallest2(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;
        int left = matrix[0][0];
        int right = matrix[rows - 1][cols - 1];

        // 注意，这里的 left 和 right 指的是矩阵中的元素，而不是索引
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            int sum = 0;
            for (int i = 0; i < rows; i++) {
                // 计算矩阵中小于等于 middle 的元素个数
                for (int j = 0; j < cols && matrix[i][j] <= middle; j++) {
                    sum++;
                }
            }
            if (sum < k) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {
                {1, 5, 9},
                {10, 11, 13},
                {12, 13, 15}
        };

        System.out.println(solution.kthSmallest1(matrix, 8));
        System.out.println(solution.kthSmallest2(matrix, 8));
    }
}
