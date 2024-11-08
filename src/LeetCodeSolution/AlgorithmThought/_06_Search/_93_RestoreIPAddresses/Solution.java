package LeetCodeSolution.AlgorithmThought._06_Search._93_RestoreIPAddresses;

import java.util.ArrayList;
import java.util.List;

/**
 * 复原 IP 地址
 * <p>
 * 题目描述：
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 回溯算法的三个关键点：
 * 1. 选择（Choice）
 * 2. 约束（Constraints）
 * 3. 目标（Goal）
 * <p>
 * 思路：
 * 1. 回溯 + 剪枝；
 * 2. 看 {@link Solution2}
 */
public class Solution {

    private List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.isEmpty()) {
            return ans;
        }

        List<String> segment = new ArrayList<>();
        dfs(s, 0, segment);
        return ans;
    }

    private void dfs(String s, int start, List<String> segment) {
        if (start == s.length()) {
            if (segment.size() == 4) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < segment.size(); i++) {
                    sb.append(segment.get(i));
                    if (i != segment.size() - 1) {
                        sb.append(".");
                    }
                }
                ans.add(sb.toString());
            }
        } else {
            if (segment.size() >= 4) {
                return;
            }
            for (int i = start; i < s.length() && i < start + 3; i++) {
                // 判断 str 这一段是否是合法的 ip
                String str = s.substring(start, i + 1);
                // 形如 01 这样的情况，直接忽略掉
                if (str.charAt(0) == '0' && str.length() > 1) {
                    break;
                }
                int value = Integer.parseInt(str);
                if (value >= 0 && value <= 255) {
                    segment.add(str);
                    dfs(s, i + 1, segment);
                    segment.remove(segment.size() - 1);
                }
            }
        }
    }

    public List<String> restoreIpAddresses1(String s) {
        List<String> allIpAddress = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return allIpAddress;
        }

        int[] path = new int[4];
        dfs1(allIpAddress, s, 0, path, 0);
        return allIpAddress;
    }

    /**
     * 搜索满足要求的所有的 IP 地址
     *
     * @param allIpAddress 所有满足要求的 IP 地址的结果集
     * @param s            给定的 IP 地址
     * @param index        该索引在给定的字符串 s 上向右滑动，其实就是指向字符串 s 中每个字符的指针
     * @param path         用于组成其中的一个符合要求的 IP 地址
     * @param segment      segment 可能的取值为 0、1、2、3
     */
    public void dfs1(List<String> allIpAddress, String s, int index, int[] path, int segment) {
        // base case，也就是我们的目标，由两部分构成
        // 1. catch answers，即得到的满足要求的情况
        // 2. 不满足要求的情况
        if (segment == 4 && index == s.length()) {
            allIpAddress.add(path[0] + "." + path[1] + "." + path[2] + "." + path[3]);
            // 对于两者不同时满足的情况，直接回溯，即 return
        } else if (segment == 4 || index == s.length()) {
            return;
        }

        for (int len = 1; len <= 3 && (index + len <= s.length()); len++) {
            String snapshot = s.substring(index, index + len);
            int value = Integer.parseInt(snapshot);
            // 约束
            if (value > 255 || len >= 2 && s.charAt(index) == '0') {
                break;
            }
            // 以下是我们的“选择”
            path[segment] = value;
            dfs1(allIpAddress, s, index + len, path, segment + 1);
            path[segment] = -1;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "25525511135";
        String s2 = "010010";
        String s3 = "101023";
        String s4 = "0000";

        System.out.println(solution.restoreIpAddresses1(s1));
        System.out.println(solution.restoreIpAddresses1(s2));

        System.out.println("===");
        System.out.println(solution.restoreIpAddresses(s1));
    }
}
