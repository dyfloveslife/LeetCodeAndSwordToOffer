package LeetCodeSolution.AlgorithmThought._01_DoublePointer._922_SortArrayByParityII;

import java.util.Arrays;

/*
 * 按奇偶排序数组Ⅱ
 *
 * 题目描述：
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时，i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 * 思路：
 * 1. 普通的解法是遍历两遍数组，第一遍将偶数放到相应的位置，第二遍将奇数放到相应的位置即可；
 * 2. 第二种方法是使用双指针，i 始终指向偶数索引，j 始终指向奇数索引，它们每次只能向右移动 2 个位置；
 * 3. 如果 A[i] 是偶数，则无需关心，直接有移 2 个位置；
 * 4. 而如果 A[i] 是奇数，则需要使用 j 来找到一个偶数，将这个偶数与 A[i] 进行交换；
 * 5. 交换完之后，i 和 j 所指索引上的数字就满足题目要求了。
 */
public class Solution {
    public int[] sortArrayByParityII1(int[] A) {
        if (A == null || A.length == 0) return new int[0];

        int[] ans = new int[A.length];
        int i = 0;
        for (int a : A) {
            if ((a & 1) == 0) {
                ans[i] = a;
                i += 2;
            }
        }
        i = 1;
        for (int a : A) {
            if ((a & 1) == 1) {
                ans[i] = a;
                i += 2;
            }
        }
        return ans;
    }

    public int[] sortArrayByParityII2(int[] A) {
        if (A == null || A.length == 0) return new int[0];

        // i 始终指向偶数索引，j 始终指向奇数索引
        int j = 1;
        for (int i = 0; i < A.length; i += 2) {
            if ((A[i] & 1) == 1) {
                while ((A[j] & 1) == 1) {
                    j += 2;
                }
                swap(A, i, j);
            }
        }
        return A;
    }

    private void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] A = {4, 2, 5, 7};

        System.out.println(Arrays.toString(solution.sortArrayByParityII1(A)));
        System.out.println(Arrays.toString(solution.sortArrayByParityII2(A)));
    }
}