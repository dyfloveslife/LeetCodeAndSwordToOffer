package SwordToOfferSolution._59_MaxInSlidingWindow;

import java.util.ArrayList;
import java.util.LinkedList;

/*
 * 滑动窗口的最大值
 *
 * 题目描述：
 * 给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。
 * 例如，如果输入数组 {2, 3, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那么一共存在 6 个滑动窗口，它们的最大值分别为 {4, 4, 6, 6, 6, 5}。
 *
 * 思路：
 * 1. 使用双端队列统计每次窗口滑动时的最大值；`
 * 2. 用两个指针 L 和 R 表示窗口，R 向右移动一个位置表示加数操作，L 向右移动一个位置表示减数操作；
 * 3. 若一开始 R 向右移动一个数，则将当前数与当前数的索引共同从队尾进入到双端队列中，由于双端队列从队头到队尾是由大到小的，所以进入的时候
 *    也得保证当前进入的元素满足从大到小；
 * 4. 每次想要查询窗口的最大值，则队头代表的就是当前窗口的最大值，队尾就是当前窗口的最小值；
 * 5. 如果当前准备进入双端队列的值比队尾的值要大，则先将队尾的值弹出，弹出后就不要了，一直等到当前准备进入双端队列的值比队尾的值要小的时候，
 *    当前值才进入双端队列；
 * 6. 如果当前准备进入双端队列的值与队尾的值相等，则也需要将队尾的值弹出后，再让新的值进入，目的是为了更新索引；
 * 7. 对于减数逻辑，如果 L 往右移动，则检查双端队列中队头元素的索引过没过期，如果过期了，则从头部弹出。
 */
public class Solution {
    public static ArrayList<Integer> maxInSlidingWindow(int[] arr, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (arr == null || arr.length < 1 || arr.length < size || size < 1) {
            return res;
        }

        // 双端队列存的是索引
        LinkedList<Integer> qmax = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            // 当双端队列为空，并且双端队列尾部的元素比当前元素小或相等的话，则尾部的元素弹出
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            // 弹完之后，就将当前元素的索引从队尾加入到双端队列中
            qmax.addLast(i);
            // 如果窗口在开始的时候没有完全形成，则不会有任何一个下标弹出
            // 如果 i - size，即当前索引减去窗口大小所得的值等于头部的值，则说明过期了
            if (qmax.peekFirst() == i - size) {
                qmax.pollFirst();
            }
            // 判断窗口形成了没
            // 如果满足 if 的话，说明窗口已经形成了，则开始收集窗口内的最大值
            // 从索引 2 开始才形成窗口
            if (i >= size - 1) {
                // 每次窗口的最大值就是队头元素的索引所对应的值
                res.add(arr[qmax.peekFirst()]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 2, 6, 2, 5, 1};
        System.out.println(maxInSlidingWindow(arr, 3));
    }
}
