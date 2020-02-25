package Other.AdvancedAlgorithm._20_MaxEOR;

/*
 * 子数组的最大异或和
 */
public class Solution {

    // 暴力
    public static int getMaxEOR(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int start = 0; start <= i; start++) {
                int xor = 0;
                for (int k = start; k <= i; k++) {
                    xor ^= arr[k];
                }
                max = Math.max(max, xor);
            }
        }
        return max;
    }

    // 记忆化 DP
    // 0 ~ start-1, start ~ i
    // 0 ~ i 的异或结果等于 0 ~ start-1 的异或结果再异或上 start ~ i 的异或结果
    public static int getMaxEOR2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int[] dp = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            // 0~i 的异或结果
            eor ^= arr[i];
            max = Math.max(max, eor);
            for (int start = 1; start <= i; start++) {
                // start~i
                int startToEOR = eor ^ dp[start - 1];
                max = Math.max(max, startToEOR);
            }
            dp[i] = eor;
        }
        return max;
    }

    // 定义节点
    public static class Node {
        // 只有 0 和 1 两条路
        Node[] nexts = new Node[2];
    }

    // 定义前缀树
    public static class NumTrim {
        Node head = new Node();

        // 通过提取每一位数，将每一位依次加入到前缀树里
        public void add(int num) {
            Node cur = head;
            for (int move = 31; move >= 0; move--) {
                // 首先得到符号位，即 0 或者 1
                // 然后从高到底，依次提取每一位
                int path = ((num >> move) & 1);
                cur.nexts[path] = (cur.nexts[path] == null) ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        // num 表示 0~i 的异或结果
        public int maxXor(int num) {
            Node cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int path = ((num >> move) & 1);
                // best 表示所期待的最优选择，分为符号位和普通位
                // 如果是符号位，则需要保持原来的符号位
                // 如果是普通位，则需要取反
                // 这样才能达到最优
                int best = (move == 31) ? path : (path ^ 1);
                // 这里的 best 表示实际的选择
                // 如果 best 不为空，则说明前缀树中之前有这个 best，此时就选它
                // 否则只能走另一条路
                best = (cur.nexts[best] != null) ? best : (best ^ 1);
                // 设置每一位的答案
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }
    }

    public static int maxXorSubArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrim numTrim = new NumTrim();
        numTrim.add(0);
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
            max = Math.max(max, numTrim.maxXor(eor));
            numTrim.add(eor);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 1, 4, 5, 1, 0, 3};
        System.out.println(maxXorSubArray(arr));
        System.out.println(getMaxEOR(arr));
    }
}
