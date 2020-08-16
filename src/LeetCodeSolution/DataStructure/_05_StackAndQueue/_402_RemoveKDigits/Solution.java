package LeetCodeSolution.DataStructure._05_StackAndQueue._402_RemoveKDigits;

import java.util.LinkedList;

/*
 * 移掉 K 位数字
 *
 * 题目描述：
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 *
 * 思路：
 * 1. 这里有一个前提知识：例如给定 123a45 和 123b45，如果 a > b，那么 123a45 > 123b45，否则 123a45 <= 123b45；
 * 2. 也就是说：对于两个相同位数的数字来说，它们的大小关系取决于从左到右，第一个不相等的那个数字；
 * 3. 算法思路是：从左到右遍历 num，对于当前数字，我们选择保留；
 * 4. 但是我们可以选择性地丢弃它前面的那个数字；
 * 5. 例如 num = 1432219，k = 3：
 *        对于 1 来说，它没有左侧相邻的元素，所以保留 1；
 *        对于 4 来说，如果将左侧的 1 删除的话，那么 num 会变大，因此我们仍然不丢弃 1 和 4；
 *        对于 3 来说，由于左侧的 4 比当前的 3 大，如果保留 4 的话，那么 num 会变大，因此，我们需要将 4 删除；
 * 6. 但是，如果给定的是一个递增的字符串，那么根据以上的逻辑，显然都不会进行删除；
 * 7. 因此，每次丢弃一次，k 就减去 1，当 k 减到 0 的时候，就可以终止遍历了；
 * 8. 遍历完成后，如果 k 仍然大于 0，那么不妨假设还剩下 x 个需要删除，那么我们直接将末尾的 x 个元素删除即可；
 * 9. 反过来说，对于删除 k 个元素，就相当于保留 n-k 个数字；
 * 10.我们可以使用单调栈来做这道题，虽说是单调栈，但是除了 stack 以外，双端队列 LinkedList 也可以当作单调栈来使用。
 */
public class Solution {
    public String removeKthDigits(String num, int k) {
        if (num == null || num.length() == 0) {
            return "";
        }

        LinkedList<Character> list = new LinkedList<>();
        char[] chars = num.toCharArray();
        for (char c : chars) {
            while (list.size() > 0 && k > 0 && list.peekLast() > c) {
                list.pollLast();
                k -= 1;
            }
            list.offerLast(c);
        }

        // 当 k 没有减到 0 的时候，需要将单调栈中的元素删除
        for (int i = 0; i < k; i++) {
            list.removeLast();
        }

        // 处理前导 0，并生成最后的结果
        StringBuilder sb = new StringBuilder();
        boolean hasZero = true;
        for (char c : list) {
            if (hasZero && c == '0') {
                continue;
            }
            hasZero = false;
            sb.append(c);
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "1432219";
        String s2 = "10200";

        System.out.println(solution.removeKthDigits(s1, 3));
        System.out.println(solution.removeKthDigits(s2, 1));
    }
}
