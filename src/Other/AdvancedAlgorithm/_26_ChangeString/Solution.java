package Other.AdvancedAlgorithm._26_ChangeString;

/*
 * 整体交换字符串
 *
 * 题目描述：
 * 给定一个字符串 str 和长度 leftSize，请把左侧 leftSize 的部分和有部分做整体交换。
 * 要求空间复杂度是 O(1)。
 *
 * 思路：
 * 0. https://github.com/dyfloveslife/LeetCodeAndSwordToOffer/blob/master/src/Other/AdvancedAlgorithm/_23_RotateString/Solution.java
 * 1. 先将 [0...leftSize] 部分进行逆序，然后再将 [leftSize + 1...s.length() - 1] 进行逆序，最后再将整体进行逆序；
 * 2. 另一种方法是：假如给定 [a, b, c, d, e, f, g, h]，leftSize=5：
 *    首先，右侧部分短，那么就将左侧部分（从左开始数）与右侧等长的部分，与右侧部分交换，也就是将 [a, b, c] 和 [f, g, h] 进行交换，
 *    也就是 a 和 f 交换，b 和 g 交换，c 和 h 交换；
 * 3. 然后就变成 [f, g, h | d, e, a, b, c]，此时对于 [d, e | a, b, c] 来说，左侧部分短，就将右侧（从右开始数）与左侧等长的部分，与左侧进行交换；
 * 4. 也就将 d 和 b 交换，e 和 c 交换，变成 [b, c, a, d, e]，然后 [d, e] 就不动了，也就是谁短，谁交换过来就不动了；
 * 5. 然后变成了 [b, c, a]，此时右侧短，继续之前的逻辑，将 b 和 a 交换，即 [a, c, b]，然后 a 就不动了；
 * 6. 最后，c 和 b 再进行交换，就结束了。
 * 7. 这种方法的好处就是，在某些情况下，例如左右部分是等长的，那么就可以划分结束了，就不需要再进行交换了。
 * 8. 这种方法可以在某些场景下表现得很出色，例如限制了交换次数，那么此时用该方法的话，就会比第一种方法要好很多。
 */
public class Solution {
    // 方法一
    public String changeString1(String str, int leftSize) {
        if (leftSize <= 0 || str == null || str.length() <= leftSize) {
            return "";
        }
        return process1(str.toCharArray(), 0, leftSize - 1, str.length() - 1);
    }

    // 将左部分 chars[left...mid] 整体移动到右部分 chars[mid+1...right]
    private String process1(char[] chars, int left, int mid, int right) {
        reverse(chars, left, mid);
        reverse(chars, mid + 1, right);
        reverse(chars, left, right);
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
    }

    // 方法二
    public String changeString2(String str, int leftSize) {
        if (leftSize <= 0 || str == null || str.length() <= leftSize) {
            return "";
        }
        char[] chars = str.toCharArray();
        int left = 0;
        int right = str.length() - 1;
        // 左右部分的大小
        int lpart = leftSize;
        int rpart = str.length() - leftSize;
        // 左右两个部分需要交换的长度，以少的部分为主
        int same = Math.min(lpart, rpart);
        // 左部分和右部分相差的长度
        int diff = lpart - rpart;
        exchange(chars, left, right, same);
        while (diff != 0) {
            // 左侧大
            if (diff > 0) {
                // 既然左侧大了，那么右侧缓过来之后，left 应该来到 left+same 的位置
                left += same;
                lpart = diff;
            // 右侧大，那么 right 就需要往左缩
            } else {
                right -= same;
                rpart = -diff;
            }
            same = Math.min(lpart, rpart);
            diff = lpart - rpart;
            exchange(chars, left, right, same);
        }
        return String.valueOf(chars);
    }

    // 该函数实现的是：在 chars[] 中，将 chars[left...] 数出（从左往右） size 长度的字符与 chars[...right] 数出（从右往左） size 长度的字符交换
    private void exchange(char[] chars, int left, int right, int size) {
        int i = right - size + 1;
        char temp = 0;
        while (size-- != 0) {
            temp = chars[left];
            chars[left] = chars[i];
            chars[i] = temp;
            left++;
            i++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcdefgh";

        System.out.println(solution.changeString1(s, 5));
        System.out.println(solution.changeString2(s, 5));
    }
}
