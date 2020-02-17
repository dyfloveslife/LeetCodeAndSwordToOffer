package LeetCodeSolution.AlgorithmThought._04_BinarySearch._744_FindSmallestLetterGreaterThanTarget;

/*
 * 寻找比目标字母大的最小字母
 *
 * 题目描述：
 * 给定一个只包含小写字母的有序数组 letters 和一个目标字母 target，寻找有序数组里面比目标字母大的最小字母。
 *
 * 思路：
 * 1. 使用二分法进行查找；
 * 2. 注意 middle 是如何取值的；
 * 3. 如果找不到就返回数组中的第一个字符。
 */
public class Solution {
    public static char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) {
            return target;
        }

        int left = 0;
        int right = letters.length - 1;
        while (left <= right) {
            // 注意：+ 的优先级比 >> 高，所以需要带括号
            int middle = left + ((right - left) >> 1);
            // 这里需要注意：如果先写从右侧查找，则需要指定等于 middle
            // 否则，则不需要指定等于 middle
            if (target >= letters[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return left < letters.length ? letters[left] : letters[0];
    }


    public static void main(String[] args) {
        char[] chars = {'c', 'f', 'j'};
        System.out.println(nextGreatestLetter(chars, 'c'));
    }
}
