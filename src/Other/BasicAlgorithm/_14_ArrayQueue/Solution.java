package Other.BasicAlgorithm._14_ArrayQueue;

/*
 * 用数组实现固定的队列
 * 思路：
 * 1. 设置一个 end 和 start 都指向数组的 0 位置，end 表示新加入的元素应该放在哪个位置，start 表示从哪个位置上的数拿给用户；
 * 2. 设置 size 变量来约束 end 和 start 的行为：
 *    如果 size 小于数组的长度，则新加入的元素就放到 end 位置。其次，只要 end 到数组的底部了，就回到开头；
 *    如果 size 不等于 0，则把 start 指向的数给用户。其次，只要 start 到数组的底部了，就回到开头。
 */
public class Solution {
    public static class ArrayQueue {
        private Integer[] arr;
        private Integer size;
        private Integer start;
        private Integer end;

        // 初始化数组
        public ArrayQueue(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0.");
            }
            arr = new Integer[initSize];
            size = 0;
            start = 0;
            end = 0;
        }

        // 返回队首元素
        public Integer peek() {
            if (size == 0) {
                return null;
            }
            return arr[start];
        }

        // 入队
        public void push(int num) {
            if (size == arr.length) {
                throw new ArrayIndexOutOfBoundsException("The queue is full.");
            }
            size++;
            arr[end] = num;
            // 只要 end 到数组的底部了，就回到开头；否则 end+1
            end = end == arr.length - 1 ? 0 : end + 1;
        }

        // 出队
        public Integer pull() {
            if (size == 0) {
                throw new ArrayIndexOutOfBoundsException("The queue is empty.");
            }
            size--;
            int temp = start;
            start = start == arr.length - 1 ? 0 : start + 1;
            return arr[temp];
        }
    }
}
