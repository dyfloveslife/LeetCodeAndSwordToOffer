package LeetCodeSolution.AlgorithmThought._06_Search._93_RestoreIPAddresses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 1. 有效IP地址段的条件：
 * - 0-255之间的整数
 * - 不能有前导零
 * - 必须是数字
 * <p>
 * 2. 剪枝条件：
 * - 字符串长度小于4或大于12时直接返回
 * - 当前数字大于255时停止当前分支
 * - 遇到前导零时只能取单个0
 * <p>
 * 3. 回溯终止条件：
 * - 已形成4段且使用完所有字符
 * - 已形成4段但还有剩余字符
 * - 用完字符但还不足4段
 */
public class Solution2 {

    private List<String> ans = new ArrayList<>();

    /**
     * 回溯
     *
     * @param s String
     * @return {@link List}<{@link String}>
     */
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return ans;
        }

        backtrack(s, 0, new LinkedList<>());
        return ans;
    }

    private void backtrack(String s, int start, LinkedList<String> segments) {
        // 如果有 4 个段且使用完了所有字符，则说明找到了一个有效的 IP
        if (segments.size() == 4 && start == s.length()) {
            ans.add(String.join(".", segments));
            return;
        }

        // 已经有 4 个段，或者已经用完字符但不够 4 个段
        if (segments.size() == 4 || start == s.length()) {
            return;
        }

        // 处理前导 0 的情况
        if (s.charAt(start) == '0') {
            // 当前字符是 0，则只能作为单独的一个段
            segments.add("0");
            backtrack(s, start + 1, segments);
            segments.removeLast();
            return;
        }

        // 尝试所有可能的段（处理非 0 开头的段）
        int num = 0;
        // 最多取 3 位数
        for (int i = start; i < s.length() && i < start + 3; i++) {
            // 累计构建数字
            num = num * 10 + (s.charAt(i) - '0');
            if (num <= 255) {
                segments.add(String.valueOf(num));
                backtrack(s, i + 1, segments);
                segments.removeLast();
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        String s1 = "25525511135";

        Solution2 solution2 = new Solution2();
        System.out.println(solution2.restoreIpAddresses(s1));
    }
}
