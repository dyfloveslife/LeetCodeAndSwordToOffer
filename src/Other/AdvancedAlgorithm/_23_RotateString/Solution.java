package Other.AdvancedAlgorithm._23_RotateString;

/*
 * 旋转字符串
 *
 * 题目描述：
 * 给定一个字符串 str 和长度 leftSize，请把 str 左侧 leftSize 的部分和右部分进行整体交换。
 * 要求额外空间复杂度为 O(1)。
 * 进阶：如果交换代价很高，有没有更好的方法。
 *
 * 例如：str = abcdef, leftSize = 4,则交换后的 str 为 efabcd
 *
 * 思路一：逆序
 * 1. 如果要做到原地交换，不适用额外的数组空间的话，可以先将左部分逆序，然后将右部分逆序，最后整体逆序；
 * 2. 逆序的时候可以使用两个指针，两个指针所指字符进行交换，相遇就停止。
 *
 * 思路二：还是交换的思路
 * 0. https://i.loli.net/2020/05/16/QhtFVTEwqBKrkU6.png
 *    https://i.loli.net/2020/05/16/p3BeujngOLIwqJt.png
 * 1. 但是这里的交换方式不同，对于 [a, b, c, d, e, f, g, h]，leftSize = 6 来说；
 * 2. 由于右侧部分只有两个，因此先将 [g, h] 和 [a, b] 交换，得到 [g, h, c, d, e, f, a, b]；
 *    其中 [g, h] 固定不动，因为它已经在最终的位置上了；
 * 3. 然后对 [c, d, e, f, a, b] 进行以上操作，将 [a, b] 与 [c, d] 进行交换，得到 [g, h, a, b, e, f, c, d]；
 *    其中 [a, b] 固定不动，因为它已经在最终的位置上了；
 * 4. 然后对 [e, f, c, d] 进行以上操作，直至结束；
 * 5. 需要注意的是，整体的思路是将短的那部分交换过去后就不动了，然后再操作剩下的部分即可。
 * 6. 也就是说，如果右部分短，则在左侧中，从左开始与右部分等长的部分进行交换，交换完之后，短的部分不动；
 *             如果左部分短，则在右侧中，从右开始与左部分等长的部分进行交换，交换完之后，短的部分不动。
 * 7. 这种方法比思路一的好处在于：如果在子问题中，需要交换的部分是等长的话，则直接交换即可，不需要后序的交换了。
 *     https://i.loli.net/2020/05/16/FZYbHou3g5Q2Unc.png
 * 8. 此方法在最坏情况下和思路一的世间复杂度是一样的。
 */
public class Solution {
    // 思路一
    public String rotateString1(String s, int leftSize) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0 || leftSize > s.length()) {
            return s;
        }

        return process(s.toCharArray(), 0, leftSize - 1, s.length() - 1);
    }

    private String process(char[] str, int left, int middle, int right) {
        reverse(str, left, middle);
        reverse(str, middle + 1, right);
        reverse(str, left, right);

        return String.valueOf(str);
    }

    private void reverse(char[] str, int left, int right) {
        while (left < right) {
            char temp = str[left];
            str[left++] = str[right];
            str[right--] = temp;
        }
    }

    // 思路二
    public String rotateString2(String s, int leftSize) {
        if (s == null) {
            return null;
        }
        if (s.length() == 0 || leftSize > s.length()) {
            return s;
        }

        char[] str = s.toCharArray();
        // 字符数组的左右边界
        int left = 0;
        int right = str.length - 1;
        // 根据给定的 leftSize 来将字符数组划分成左右两个部分
        int leftPart = leftSize;
        int rightPart = str.length - leftSize;
        // 找到这两个部分中长度较小的那个部分，
        // 当然这里的 same 也是需要交换的部分
        int same = Math.min(leftPart, rightPart);
        int diff = leftPart - rightPart;

        exchange(str, left, right, same);

        while (diff != 0) {
            // diff 大于 0，说明交换之前左部分大，即 leftPart 的长度大于 rightPart 的长度
            if (diff > 0) {
                // 更新左部分的起始索引以及下次迭代的左部分的大小
                // https://i.loli.net/2020/05/16/L7X2I9R5Azrcn4y.png
                left += same;
                leftPart = diff;
            } else {
                right -= same;
                // 这里的 diff 是负数，因此再取相反数
                rightPart = -diff;
            }
            same = Math.min(leftPart, rightPart);
            diff = leftPart - rightPart;
            exchange(str, left, right, same);
        }
        return String.valueOf(str);
    }

    // 在 str[left...] 中从左往右的 size 大小的字符与在 str[...right] 中从右往左 size 大小的字符进行交换
    private void exchange(char[] str, int left, int right, int size) {
        int i = right - size + 1;
        char temp;
        while (size-- != 0) {
            temp = str[left];
            str[left] = str[i];
            str[i] = temp;
            left++;
            i++;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcdefg";
        int leftSize = 6;

        System.out.println(solution.rotateString1(s, leftSize));
        System.out.println(solution.rotateString2(s, leftSize));
    }
}
