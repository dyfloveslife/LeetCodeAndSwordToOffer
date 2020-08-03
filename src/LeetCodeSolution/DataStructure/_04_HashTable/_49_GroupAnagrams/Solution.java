package LeetCodeSolution.DataStructure._04_HashTable._49_GroupAnagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * 字母异位词分组
 *
 * 题目描述：
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 思路：
 * 1. 使用哈希表，key 存储排序后的字母异位词，value 存储 list，用于存储与 key 具有相同字符的单词；
 * 2. 遍历字符串数组，将每个字符串进行字典序排序，如果排序后的字符串等于 key 中的字符串，则说明它俩是字母异位词，
 *    然后就将这个字符串添加到 value 中的 list 中。
 */
public class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // key: [val1, val2, val3]
        HashMap<String, List<String>> map = new HashMap<>();
        // str = "eat"
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            // key = "aet"
            String key = String.valueOf(chars);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};

        System.out.println(solution.groupAnagrams(strs));
    }
}
