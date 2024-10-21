package SwordToOfferSolution._38_StringPermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/*
 * 字符串的排列 https://i.loli.net/2019/11/25/6qRl5XMgOAUcfmZ.png
 *
 * 题目描述：
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 例如输入字符串 abc，则打印出由字符 a、b、c 所能排列出来的所有字符串 abc、acb、bac、bca、cab 和 cba。
 *
 * 思路一：
 * 1. DFS + 回溯。
 *
 * 思路二：交换元素位置
 * 1. 遍历出所有可能出现在第一个位置的字符，即将第一个字符依次和后面的字符交换；
 * 2. 固定第一个字符，将后面的字符进行排列。
 * 3. 这时对于后面的字符来说，重复步骤 1、2 即可。
 * 4. 在递归过程中，还需要考虑重复的排序结果，即：如果当前排序结果在之前已排序结果中没有出现的话，则将其加入到整个的结果集中。
 */
public class Solution {

    public String[] permutation1(String s) {
        int len = s.length();
        if (len == 0) {
            return new String[0];
        }

        char[] chars = s.toCharArray();
        // 由于输出的结果不能含有重复的，因此排序是为了去重方便
        Arrays.sort(chars);

        StringBuilder path = new StringBuilder();
        boolean[] visited = new boolean[len];

        List<String> res = new ArrayList<>();
        // 0 表示当前递归的深度
        dfs(chars, len, 0, visited, path, res);
        return res.toArray(new String[0]);
    }

    private void dfs(char[] chars, int len, int depth, boolean[] visited, StringBuilder path, List<String> res) {
        if (depth == len) {
            res.add(path.toString());
            return;
        }
        for (int i = 0; i < len; i++) {
            if (!visited[i]) {
                // 剪枝
                if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) {
                    continue;
                }
                visited[i] = true;
                path.append(chars[i]);

                dfs(chars, len, depth + 1, visited, path, res);

                path.deleteCharAt(path.length() - 1);
                visited[i] = false;
            }
        }
    }

    // 思路二（超时）
    private ArrayList<String> permutation2(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str.length() == 0) {
            return res;
        }

        backtracking(str.toCharArray(), res, 0);
        // 对 res 中的每个排列进行排序，即按照字典序进行排列
        Collections.sort(res);
        return res;
    }

    private void backtracking(char[] ch, ArrayList<String> res, int i) {
        // 递归结束条件
        // 如果 i 已经到了字符数组的末尾时，则将这一组字符串加入到结果集中
        if (i == ch.length - 1) {
            // 如果已有的 res 中不包含新的 ch 的话，则添加到 res 中
            if (!res.contains(new String(ch))) {
                res.add(new String(ch));
            }
        } else {
            // 固定 i，对 i 后面的进行排列
            for (int j = i; j < ch.length; j++) {
                swap(ch, i, j);
                backtracking(ch, res, i + 1);
                swap(ch, i, j);
            }
        }
    }

    private List<String> ans = new LinkedList<>();
    private char[] chars;

    /**
     * 递归 + 剪枝
     *
     * @param s String
     * @return String[]
     */
    public String[] permutation3(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }

        chars = s.toCharArray();
        myDFS(0);

        return ans.toArray(new String[ans.size()]);
    }

    private void myDFS(int x) {
        if (x == chars.length - 1) {
            // 添加排列方案
            ans.add(String.valueOf(chars));
            return;
        }

        Set<Character> set = new HashSet<>();
        for (int i = x; i < chars.length; i++) {
            char c = chars[i];
            // 剪枝
            if (set.contains(c)) {
                continue;
            }

            set.add(c);
            // 交换，将 c 固定在 x 的位置
            swap(chars, i, x);
            myDFS(x + 1);
            // 恢复
            swap(chars, i, x);
        }
    }

    private void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.permutation1("abc")));
        System.out.println(Arrays.toString(solution.permutation1("aab")));
    }
}