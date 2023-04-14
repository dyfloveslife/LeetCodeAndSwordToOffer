package LeetCodeSolution.AlgorithmThought._04_BinarySearch._278_FirstBadVersion;

/*
 * 第一个错误版本
 *
 * 题目描述：
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
 * 实现一个函数来查找第一个错误的版本。
 * 你应该尽量减少对调用 API 的次数。
 *
 * 思路：
 * 1. 使用二分法即可；
 * 2. 注意：因为使用了 right = middle，所以 while 的条件应该是 left < right；
 * 3. 如果当前的 middle 是坏版本，则说明第一个怀版本在当前 middle 的左边，则继续往左边找。
 */
public class Solution {
    public int firstBadVersion(int n) {
        if (n < 1) {
            return 0;
        }

        int left = 1, right = n;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            // 如果 middle 是错误版本的话，则说明 middle 左侧可能还存在错误版本
            // 因此从 [left, middle] 区间内继续寻找
            if (isBadVersion(middle)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        return left;
    }

    private boolean isBadVersion(int version) {
        return true;
    }
}
