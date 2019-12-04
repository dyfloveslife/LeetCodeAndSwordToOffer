package Other.BasicAlgorithm._05_Recursive;

/*
 * 计算递归时间复杂度的 Master 公式:T(n)=aT(n/b)+O(n^d)
 * 表示一个规模为 n 的问题被分成规模为 n/b 的 a 个子问题，递归的求这 a 个子问题，通过对 a 个子问题的解的综合得到原问题的解。
 *
 * 对于二分法，递归所用的时间复杂度为 T(n)=2T(n/2)+O(1)。
 * 2 表示将原问题划分成了两个子问题；
 * n/2 表示子问题的规模是原问题的 n/2；
 * O(1) 表示除去调用子过程之外，剩下的过程。
 *
 * 有三种情况：log(b,a) 表示以 b 为底 a 的对数
 * 1) 若 log(b,a) > d，则复杂度为 O(N^log(b,a))
 * 2) 若 log(b,a) = d，则复杂度为 O(N^d*logN)
 * 3) 若 log(b,a) < d，则复杂度为 O(N^d)
 */
public class Solution {
}
