package Other.AdvancedAlgorithm._11_MountainsAndFalme;

import java.util.Stack;

/*
 * 可见的山峰对的数量
 *
 * 思路：
 * 1. 根据公式实现；
 * 2. 使用单调栈实现。
 */
public class Solution {
    public static class Pair {
        int val;
        int times;

        public Pair(int val) {
            this.val = val;
            this.times = 1;
        }
    }

    // 求山峰对的数量
    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int size = arr.length;
        int maxIndex = 0;
        // 首先在数组中找到最大值的位置
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }

        int value = arr[maxIndex];
        // 得到最大值的下一个数的索引，从这个数开始遍历
        int index = nextIndex(size, maxIndex);
        long res = 0L;
        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(value));
        // 如果相等的话，说明环数组已经遍历完了
        while (index != maxIndex) {
            value = arr[index];
            // 由于不满足大小规则，现在开始结算山峰对的数量
            while (!stack.isEmpty() && stack.peek().val < value) {
                int times = stack.pop().times;
//                res += getInternalSum(times) + times;
//                res += stack.isEmpty() ? 0 : times;
                // 由于最大值一定在栈的底部，所以栈不可能为空
                // 例如 5-4-4-4-4-5，其中 2 * times 表示每个 4 左右两侧都有 5
                res += getInternalSum(times) + 2 * times;
            }
            // 如果当前的数等于栈顶的值，则将栈顶的值加一
            // 否则创建一个新的记录
            if (!stack.isEmpty() && stack.peek().val == value) {
                stack.peek().times++;
            } else {
                stack.push(new Pair(value));
            }
            // 再遍历下一个数
            index = nextIndex(size, index);
        }
        // 如果遍历完数组后，栈中还有元素，则单独结算
        while (!stack.isEmpty()) {
            int times = stack.pop().times;
            res += getInternalSum(times);
            if (!stack.isEmpty()) {
                res += times;
                if (stack.size() > 1) {
                    res += times;
                } else {
                    res += stack.peek().times > 1 ? times : 0;
                }
            }
        }
        return res;
    }

    // 求环形数组中 i 位置的下一个位置
    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    // 内部产生的 C(K, 2) 种组合
    // 如 5-4-4-4-4-5，则求的是 4-4-4-4 共有多少种组合
    public static long getInternalSum(int n) {
        return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 3};
        System.out.println(communications(arr)); // 7
    }
}