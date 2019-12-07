package Other.BasicAlgorithm._13_ArrayStack;

/*
 * 用数组实现固定的栈
 * 思路：
 * 1. 用 index 表示新进来的数应该放在哪个位置；
 * 2. 如果需要添加元素（进栈），则将新的元素放到 index 的位置，然后 index++；
 * 3. 如果需要删除元素（出栈），则出去的就是 --index 位置上的元素。
 */
public class Solution {
    public static class ArrayStack {
        private Integer[] arr;
        private Integer index;

        // 由于是固定数组，所以需要给定一个初始化数组大小
        public ArrayStack(int initSize) {
            if (initSize < 0) {
                throw new IllegalArgumentException("The init size is less than 0.");
            }
            arr = new Integer[initSize];
            index = 0;
        }

        // 取栈顶元素
        public Integer peek() {
            if (index == 0) {
                return null;
            }
            return arr[index - 1];
        }

        // 压栈操作
        public void push(int num) {
            if (index == arr.length) {
                throw new ArrayIndexOutOfBoundsException("The stack is full.");
            }
            arr[index++] = num;
        }

        // 出栈操作
        public Integer pop() {
            if (index == 0) {
                throw new ArrayIndexOutOfBoundsException("The stack is empty.");
            }
            return arr[--index];
        }
    }
}
