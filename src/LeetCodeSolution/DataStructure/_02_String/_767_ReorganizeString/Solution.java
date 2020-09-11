    package LeetCodeSolution.DataStructure._02_String._767_ReorganizeString;

import java.util.PriorityQueue;

/*
 * 重构字符串
 *
 * 题目描述：
 * 给定一个字符串 S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 *
 * 思路：
 * 0. https://www.cnblogs.com/grandyang/p/8799483.html
 * 1. 首先，将字符串进行排序，对于 “aaabbc” 来说，当发现从左到右的第二个字符也是 'a' 的时候，那么直接向后面找到第一个不是 'a' 的字符 'b'，然后
 *    交换 'a' 和 'b' 就可以了，然后继续往后面，重复相同的操作；
 * 2. 然而对于 “vvvlo” 来说，就不适用以上的方法了；
 * 3. 核心思想是：出现次数多的字符需要排在前面，这样才方便交换；
 * 4. 这里就使用到了大根堆，将每个字符以及其出现的次数首先放在 map 中，然后将 map 中的键值对扔到大根堆中；
 * 5. 每次从大根堆中取出两个键值对，并将出现的次数减 1，这两个键值对包含的字符肯定是不同的，然后就可以将它们放在一起了；
 * 6. 如果其中某个字符出现的次数不为 0，那么还需要将该键值对放进大根堆中；
 * 7. 这里可以进行剪枝，如果某个字符出现的频率大于总长度的一半的话，那么必然会有两个相邻的字符出现，那么直接返回空串即可。
 */
public class Solution {
    static class NewChar {
        // 出现的频率
        int count;
        // 字母
        char letter;

        NewChar(int count, char letter) {
            this.count = count;
            this.letter = letter;
        }
    }

    public String reorganizeString(String S) {
        int[] map = new int[26];
        for (int i = 0; i < S.length(); i++) {
            map[S.charAt(i) - 'a']++;
        }

        PriorityQueue<NewChar> maxHeap = new PriorityQueue<>((o1, o2) -> (o2.count - o1.count));
        for (int i = 0; i < 26; i++) {
            // 判断重构是否可行，map[i] <= (S.length() + 1) / 2)---某个字母过半就不能重构
            if (map[i] > 0 && map[i] <= (S.length() + 1) / 2) {
                // 可以重构，就往大顶堆里面塞对象
                maxHeap.add(new NewChar(map[i], (char) (i + 'a')));
            } else if (map[i] > (S.length() + 1) / 2) {
                return "";
            }
        }
        // 由大顶堆重构字符串
        StringBuilder sb = new StringBuilder();

        // 最后剩下一个字符或者一个不剩，终止
        while (maxHeap.size() > 1) {
            // 拿出来频率老大和老二
            NewChar cur1 = maxHeap.poll();
            NewChar cur2 = maxHeap.poll();
            sb.append(cur1.letter);
            sb.append(cur2.letter);

            if (--cur1.count > 0) {
                maxHeap.add(cur1);
            }
            if (--cur2.count > 0) {
                maxHeap.add(cur2);
            }
        }
        // 若剩下一个，特殊处理
        if (maxHeap.size() > 0) {
            sb.append(maxHeap.poll().letter);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "aab";
        String s2 = "aaab";
        String s3 = "vvvlo";

        System.out.println(solution.reorganizeString(s1));
        System.out.println(solution.reorganizeString(s2));
        System.out.println(solution.reorganizeString(s3));
    }
}
