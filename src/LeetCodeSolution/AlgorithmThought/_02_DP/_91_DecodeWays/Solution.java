package LeetCodeSolution.AlgorithmThought._02_DP._91_DecodeWays;

/*
 * 解码方法
 *
 * 题目描述：
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *   'A' -> 1
 *   'B' -> 2
 *   ...
 *   'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 思路：
 * 1. dp[i] 定义为从 s[0...i] 的方法总数；
 * 2. 当 s[i]='0' 的时候，如果 s[i-1]='1'或'2'，则 dp[i]=dp[i-2]，否则返回 0；
 *    这是因为 s[i-1]+s[i] 被唯一译码；
 * 3. 当 s[i-1]='1' 的时候，则 dp[i]=dp[i-1]+dp[i-2]；
 *    这是因为 s[i-1]与s[i]分开译码，则有 dp[i-1]；合并译码，则有 dp[i-2]；
 * 4. 当 s[i-1]='2' 并且 s[i] 在 '1' 到 '6' 的范围内的时候，则有 dp[i]=dp[i-1]+dp[i-2]；
 * 5. 因此，dp[i] 仅与前两项有关。
 */
public class Solution {
    public int numDeCodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }

        // dp[-1]=dp[1]=1
        int prepre = 1;
        int pre = 1;
        
        for (int i = 1; i < s.length(); i++) {
            int temp = pre;
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') {
                    pre = prepre;
                } else {
                    return 0;
                }
            } else if (s.charAt(i - 1) == '1' || 
                    (s.charAt(i - 1) == '2' && s.charAt(i) >= '1' && s.charAt(i) <= '6')) {
                pre = pre + prepre;
            }
            prepre = temp;
        }
        return pre;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "226";
        String s2 = "12";

        System.out.println(solution.numDeCodings(s1));
        System.out.println(solution.numDeCodings(s2));
    }
}
