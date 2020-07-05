package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._667_BeautifulArrangementII;

import java.util.Arrays;

/*
 * 优美的排列 Ⅱ
 *
 * 题目描述：
 * 给定两个整数 n 和 k，你需要实现一个数组，这个数组包含从 1 到 n 的 n 个不同整数，同时满足以下条件：
 * 1. 如果这个数组是 [a1, a2, a3, ... , an] ，那么数组 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数；
 * 2. 如果存在多种答案，你只需实现并返回其中任意一种.
 *
 * 思路：
 * 1. 找规律的一道题；
 * 2. 例如 n=50，k=17，则有
 *    [1,18,2,17,3,16,4,15,5,14,6,13,7,12,8,11,9,10,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50]
 *                                                k K+1
 * 3. 也就是说，对于下标在 [0, k] 的范围内的元素，偶数下标填充的是 [1,2,3...]，奇数下标填充的是 [k+1,K,k-1,k-2...]，后面 [k+1,...,n-1] 的范围内都是顺序填充。
 */
public class Solution {
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        int numK = k + 1;
        int num = 1;
        // 下标在 [0, k] 范围内时，偶数下标填充 [1,2,3...]
        for (int i = 0; i <= k; i += 2) {
            res[i] = num++;
        }
        // 下标在 [0, k] 范围内时，奇数下标填充 [k+1,K,k-1,k-2...]
        for (int i = 1; i <= k; i += 2){
            res[i] = numK--;
        }
        // 下标段为 [k + 1,...,n - 1]都是顺序填充
        for (int i = k + 1; i < n; i++) {
            res[i] = i + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.constructArray(3, 1)));
        System.out.println(Arrays.toString(solution.constructArray(3, 2)));
    }
}
