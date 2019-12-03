package SwordToOfferSolution._38_StringPermutation;

import java.util.ArrayList;
import java.util.Collections;

/*
 * 字符串的排列
 * 图示： https://i.loli.net/2019/11/25/6qRl5XMgOAUcfmZ.png
 * 思路：递归
 * 1. 遍历出所有可能出现在第一个位置的字符，即将第一个字符依次和后面的字符交换；
 * 2. 固定第一个字符，将后面的字符进行排列。
 * 3. 这时对于后面的字符来说，重复步骤 1、2 即可。
 * 4. 在递归过程中，还需要考虑重复的排序结果，即：如果当前排序结果在之前已排序结果中没有出现的话，则将其加入到整个的结果集中。
 */
public class Solution {
    private ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str.length() == 0) return res;
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

    private void swap(char[] ch, int i, int j) {
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
    }

    public static void main(String[] args) {
        String str = "abc";
        Solution s = new Solution();
        ArrayList<String> list = s.permutation(str);
        System.out.println(list);
    }
}
