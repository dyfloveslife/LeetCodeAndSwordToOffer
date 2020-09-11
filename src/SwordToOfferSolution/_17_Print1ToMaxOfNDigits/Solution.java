package SwordToOfferSolution._17_Print1ToMaxOfNDigits;

import java.util.ArrayList;
import java.util.List;

/*
 * 打印从 1 到最大的 n 位数
 *
 * 题目描述：
 * 输入数字 n，按顺序打印出从 1 最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。
 *
 * 思路：
 * 0. https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/solution/mian-shi-ti-17-da-yin-cong-1dao-zui-da-de-nwei-1-8/
 * 1. 将 1~pow(10, n)-1 问题转化为求 0~9 在 n 个位置上的全排列；
 * 2. 还需要考虑大数问题和前导 0 的问题；
 * 3. 对于给出大数容易爆栈的问题，可以使用模拟字符串相加的方法。
 */
public class Solution {

    // 求全排列
    // 缺点：给出大数会爆栈
    public List<String> printNumbers0(int n) {
        List<String> ans = new ArrayList<>();
        // 找出列表 [0,1,2,3,4,5,6,7,8,9] 中所有的子集，
        // 当然也包括空集 []，所以应将其删除
        backtracking(n, new StringBuilder(), ans);
        ans.remove(0);
        return ans;
    }

    private void backtracking(int n, StringBuilder sb, List<String> ans) {
        if (sb.length() > n) {
            return;
        }
        ans.add(sb.toString());
        // 从参数列表 [0,1,2,3,4,5,6,7,8,9] 中进行选择
        for (int i = 0; i < 10; i++) {
            // 剪枝操作，不能出现前导 0
            if (sb.length() == 0 && i == 0) {
                continue;
            }
            sb.append(i);
            backtracking(n, sb, ans);
            // 回溯
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // 模拟字符串相加
    // 具体的过程为：
    // 1. 对每一位上的字符数子转化成数字；
    // 2. 模拟进位操作；
    // 3. 存储结果。
    public void printNumbers1(int n) {
        StringBuilder sb = new StringBuilder();
        // 初始化
        for (int i = 0; i < n; i++) {
            sb.append(0);
        }
        // 开始打印
        int start = 0;
        while (true) {
            // 进行加 1 操作
            int point = addString(sb, 0);
            // 找到数字字符串最高位不为 0 的位置
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) != '0') {
                    start = i;
                    break;
                }
            }
            // 如果最后一次进位的位置超过了数字的最高位，那么说明已经全部打印完了
            if (point == n) {
                break;
            }
            System.out.print(sb.substring(start, sb.length()) + " ");
        }
    }

    // 该函数的作用是让字符串 sb 加 1
    // sb 表示给定的字符串
    // point 表示进位的位置
    // 函数输出最后一次进位的位置
    private int addString(StringBuilder sb, int point) {
        int temp = 0;
        // 从字符串 sb 的最后一位开始模拟计算
        for (int i = sb.length() - 1; i >= 0; i--) {
            // 如果当前位置是最后一位，则开始进行加 1 操作，
            // 否则进行加进位操作
            if (i == sb.length() - 1) {
                temp += (sb.charAt(i) - '0') + 1;
            } else {
                temp += (sb.charAt(i) - '0');
            }
            // 加完以后，改变当前字符串
            sb.setCharAt(i, Integer.toString(temp % 10).charAt(0));
            // 如果字符串计算的结果小于 10，则计算结束，
            // 否则，说明计算结果大于 10，那么就需要用临时变量记录进位，进位的位置加 1
            if (temp < 10) {
                break;
            } else {
                temp /= 10;
                point++;
            }
        }
        return point;
    }

    public int[] printNumbers(int n) {
        if (n < 1) {
            return null;
        }

        int max = (int) Math.pow(10, n);
        int[] res = new int[max - 1];
        for (int i = 1; i <= max - 1; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    // 方法一
    private void print1ToMaxOfNDigits_Solution1(int n) {
        if (n <= 0) {
            return;
        }

        // 初始化数组，并将每个字符置为 '0'
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = '0';
        }
        // 递增打印
        while (!increment(arr)) {
            printNumber(arr);
        }
    }

    private boolean increment(char[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        boolean isOverFlow = false; // 是否超过最大数值
        int nTakeOver = 0; // 是否进位
        for (int i = arr.length - 1; i >= 0; i--) {
            // arr[i] - '0' 的目的就是取到索引为 i 的整型数字，因为之前存到数组中的都是字符，减 '0' 后就变成了整型
            // 取到第 i 个字符，减 '0' 后变成整数，然后再加上进位符
            int nSum = arr[i] - '0' + nTakeOver;
            if (i == arr.length - 1) {
                // 每次将最低位进行加一
                nSum++;
            }
            // 如果发生了进位
            if (nSum >= 10) {
                if (i == 0) {
                    // 最高位发生进位，则已经达到最大值，发生溢出
                    isOverFlow = true;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    arr[i] = (char) (nSum + '0');
                }
            } else {
                arr[i] = (char) (nSum + '0');
                break;
            }
        }
        return isOverFlow;
    }

    // 方法二
    private void print1ToMaxOfNDigits_Solution2(int n) {
        if (n <= 0) {
            return;
        }

        char[] nums = new char[n];
        for (int i = 0; i < 10; i++) {
            // 将整数转换成字符串
            String str = String.valueOf(i);
            // 将字符串中索引为 0 的字符赋值给数组中索引也为 0 的位置，charAt() 方法用于返回指定索引处的字符
            nums[0] = str.charAt(0);
            print1ToMaxOfDigitsRecursively(nums, n, 0);
        }
    }

    private void print1ToMaxOfDigitsRecursively(char[] nums, int length, int index) {
        if (nums == null) {
            return;
        }
        if (index == length - 1) {
            printNumber(nums);
            return;
        }

        for (int i = 0; i < 10; i++) {
            String str = String.valueOf(i);
            // 将 index+1 处的元素设置为 i 的值
            nums[index + 1] = str.charAt(0);
            print1ToMaxOfDigitsRecursively(nums, length, index + 1);
        }
    }

    // 通用的打印输出方法
    private void printNumber(char[] arr) {
        if (arr == null) {
            return;
        }

        boolean isBegin0 = true;
        for (char c : arr) {
            if (isBegin0 && c != '0') {
                isBegin0 = false;
            }
            if (!isBegin0) {
                System.out.print(c);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

//        System.out.println(Arrays.toString(solution.printNumbers(2)));
//        solution.print1ToMaxOfNDigits_Solution1(2);
//        solution.print1ToMaxOfNDigits_Solution2(2);
        solution.printNumbers1(2);
    }
}
