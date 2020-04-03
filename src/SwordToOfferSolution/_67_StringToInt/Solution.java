package SwordToOfferSolution._67_StringToInt;

/*
 * 把字符串转换成整数
 *
 * 题目描述：
 * 请你写一个函数StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 *
 * 思路：
 * 该题需要考虑的特殊情况比较多，例如：数据溢出、空串、只有正负号、有无正负号、非法输入等。
 * 0. 尽量避免使用库函数；
 * 1. 需要去掉前导空格；
 * 2. 需要判断第 1 个字符为 + 和 - 的情况，因此，可以设计一个变量 sign，初始化的时候为 1，如果遇到 - ，将 sign 修正为 -1；
 * 3. 判断是否是数字，可以使用字符的 ASCII 码数值进行比较，即 0 <= c <= '9'；
 * 4. 在遇到第 1 个不是数字的字符的情况下，就得退出循环；
 * 5. 如果转换以后的数字超过了 int 类型的范围，需要截取。这里需要将结果 res 变量设计为 long 类型，
 *    注意：由于输入的字符串转换以后也有可能超过 long 类型，因此需要在循环内部就判断是否越界，
 *    只要越界就退出循环，这样也可以减少不必要的计算；
 * 6. 因为涉及下标访问，因此全程需要考虑数组下标是否越界的情况。
 */
public class Solution {

    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int index = 0;
        // 去掉前导空格
        while (index < len && chars[index] == ' ') {
            index++;
        }
        // 去掉前导空格以后，就到末尾了
        if (index == len) {
            return 0;
        }

        boolean isNegative = false;
        if (chars[index] == '-') {
            isNegative = true;
            index++;
        } else if (chars[index] == '+') {
            index++;
            // 如果当前的字符不是数字的话，则返回 0
        } else if (!Character.isDigit(chars[index])) {
            return 0;
        }

        int res = 0;
        while (index < len && Character.isDigit(chars[index])) {
            int digit = chars[index] - '0';
            // 本来应该是 res * 10 + digit > Integer.MAX_VALUE，
            // 但 *10 和 +digit 操作都有可能越界，所以将其移到右边
            if (res > (Integer.MAX_VALUE - digit) / 10) {
                // 如果 res 超出了整数所表示的最大范围，则需要判断 res 是超出了正数的最大范围还是超出了负数的最大范围
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            // 这里每次乘以 10 是因为后面需要添加 digit 的原因，
            // 因为每次总是需要给 digit 空出“个位”给它
            res = res * 10 + digit;
            index++;
        }
        return isNegative ? -res : res;
    }


    public int strToInt(String str) {
        if (str == null) {
            return 0;
        }

        int res = 0;
        boolean isNegative = false;
        int i = 0;
        int len = str.length();

        // limit 用来判断整数是否溢出
        int limit = -Integer.MAX_VALUE;
        int mulitMin;
        int digit;

        if (len > 0) {
            char firstChar = str.charAt(0);
            // 可能是 '+' 或 '-'
            if (firstChar < '0') {
                if (firstChar == '-') {
                    isNegative = true;
                    // 在负号的条件下，判断溢出的值就变成了整数的最小负数了
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+') {
                    return 0;
                }

                if (len == 1) {
                    return 0;
                }
                i++;
            }
            mulitMin = limit / 10;
            while (i < len) {
                digit = str.charAt(i++) - '0';
                if (digit < 0 || digit > 9) {
                    return 0;
                }
                // 判断溢出
                if (res < mulitMin) {
                    return 0;
                }
                res *= 10;
                if (res < limit + digit) {
                    return 0;
                }
                res -= digit;
            }
        } else {
            return 0;
        }
        // res 一直是负数
        return isNegative ? res : -res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.myAtoi("   42"));
        System.out.println(solution.myAtoi("   -42"));
        System.out.println(solution.myAtoi(" 42"));
        System.out.println(solution.myAtoi("4193 with words"));
        System.out.println(solution.myAtoi("words and 987"));
        System.out.println("=============");
        System.out.println(solution.strToInt("1a23"));
        System.out.println(solution.strToInt("-2147483648"));
        System.out.println(solution.strToInt("-2147483649"));
        System.out.println(solution.strToInt("2147483647"));
        System.out.println(solution.strToInt("2147483649"));
    }
}