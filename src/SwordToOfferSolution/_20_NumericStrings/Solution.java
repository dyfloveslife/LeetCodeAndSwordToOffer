package SwordToOfferSolution._20_NumericStrings;

/*  表示数值的字符串
	[]  ： 字符集合
	()  ： 分组
	?   ： 重复 0 ~ 1 次
	+   ： 重复 1 ~ n 次
	*   ： 重复 0 ~ n 次
	.   ： 任意字符
	\\. ： 转义后的 .
	\\d ： 数字
 */
public class Solution {
    public boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }

    public static void main(String[] args) {
        Solution numericStrings = new Solution();
        boolean b = numericStrings.isNumeric("123.45e+6".toCharArray());
        System.out.println(b);
    }
}
