package SwordToOfferSolution._39_MoreThanHalfNumber;

/*
 * 数组中出现次数超过一半的数字
 *
 * 题目描述：
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为 9 的数组 {1, 2, 3, 2, 2, 2, 5, 4, 2}。由于数字 2 在数组中出现了 5 次，超过数组长度的一半，因此输出 2。
 *
 * 思路：多数投票算法
 * 1. 定义两个变量：一个是数组中的第一个数，另一个是出现的次数，初始值为 1；
 * 2. 从数组中第二个数开始，如果该数与之前保存的数相同，则计数器加 1；
 * 3. 如果下一个数与之前保存的数不同，则计数器减 1；
 * 4. 若计数器减到 0 的话，则取下一个数，并将计数器置为 1，最后返回的就是最后一次将次数记为 1 的那个数；
 * 5. 最后需要判断 res 的出现次数是不是大于数组长度的一半。
 */
public class Solution {
    public static int moreThanHalfNumber(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        int res = arr[0];
        int times = 1;
        // 注意这里从 1 开始
        for (int i = 1; i < arr.length; i++) {
            if (times == 0) {
                // 更新 res 为当前元素
                res = arr[i];
                times = 1;
            } else if (res == arr[i]) {
                times++;
            } else {
                times--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        int[] arr1 = {6, 5, 5};
        System.out.println(moreThanHalfNumber(arr1));
    }
}
