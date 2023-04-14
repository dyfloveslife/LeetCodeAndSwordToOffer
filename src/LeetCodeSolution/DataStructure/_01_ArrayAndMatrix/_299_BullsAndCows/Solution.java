package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._299_BullsAndCows;

/*
 * 猜数字游戏
 *
 * 题目描述：
 *
 * 写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：
 * 猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls"，公牛），
 * 有多少位属于数字猜对了但是位置不对（称为 "Cows"，奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
 * 给你一个秘密数字 secret 和朋友猜测的数字 guess，请你返回对朋友这次猜测的提示。
 * 提示的格式为 "xAyB"，x 是公牛个数，y 是奶牛个数，A 表示公牛，B 表示奶牛。
 * 请注意秘密数字和朋友猜测的数字都可能含有重复数字。
 *
 * 思路：
 * 1. 分别比较对应位置的两个字符，如果相等，则 x++；
 * 2. 如果不想等，则使用两个哈希表分别统计两个字符串中当前字符出现的次数（词频），用于确定 y 的值；
 * 3. 由于数字只会在 0 到 9 范围内，因此 y 的值需要取两个哈希表中对应字符出现的最小次数。
 */
public class Solution {
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() == 0 || guess.length() == 0) {
            return "";
        }

        int n = secret.length();
        int a = 0, b = 0;
        int[] cnt1 = new int[10], cnt2 = new int[10];
        for (int i = 0; i < n; i++) {
            int ch1 = secret.charAt(i) - '0', ch2 = guess.charAt(i) - '0';
            if (ch1 == ch2) {
                a++;
            } else {
                cnt1[ch1]++;
                cnt2[ch2]++;
            }
        }

        for (int i = 0; i < 10; i++) {
            // 取 min 的原因是给定的字符串中可能会存在重复数字
            b += Math.min(cnt1[i], cnt2[i]);
        }

        return a + "A" + b + "B";
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String secret1 = "1807", guess1 = "7810";
        System.out.println(solution.getHint(secret1, guess1)); // "1A3B"

        String secret2 = "1123", guess2 = "0111";
        System.out.println(solution.getHint(secret2, guess2)); // "1A1B"
    }
}
