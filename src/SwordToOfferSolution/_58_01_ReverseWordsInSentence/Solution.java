package SwordToOfferSolution._58_01_ReverseWordsInSentence;

/*
 * 翻转单词顺序
 *
 * 题目描述：
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串 "I am a student. "，则输出 "student. a am I"。
 *
 * 思路一：
 * 1. 不能使用额外的辅助空间；
 * 2. 先翻转整个句子，然后再翻转每个单词；
 * 3. 翻转单词的时候，根据空格来确定每个单词的起始位置;
 * 4. 也可以先翻转每个单词，再翻转句子。
 * 5. 用双指针，begin 和 end 分别控制单词的首尾，end 不断往后移动；
 * 6. 如果 end 到了最后或者遇到了空格，则翻转每个单词；
 * 7. 翻转完后要更新 begin 的位置，并且 end 继续向后移动；
 * 8. 直到最后一个单词翻转完后，再翻转整个句子。
 * 9. 需要注意的是：
 *      三个空格被 split(" ") 划分后会变成 ""；
 *      这里应使用 equals 而不是 == 号。
 *
 * 思路二：双指针
 * 1. 倒序遍历字符串 str，记录单词的左右索引边界 i、j；
 * 2. 确定好每个单词的边界后，将其添加到 StringBuilder 中；
 * 3. 最后拼接单词成为字符串，再返回。
 */
public class Solution {
    public String reverseWords(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        String[] strArr = str.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strArr.length - 1; i >= 0; i--) {
            if (!strArr[i].isEmpty()) {
                sb.append(strArr[i]).append(" ");
            }
        }

        return sb.toString().trim();
    }

    public String reverseWords2(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        str = str.trim();
        int j = str.length() - 1;
        int i = j;

        while (i >= 0) {
            // 搜索首个空格
            while (i >= 0 && str.charAt(i) != ' ') {
                i--;
            }
            sb.append(str.substring(i + 1, j + 1) + " ");
            // 跳过单词间的空格
            while (i >= 0 && str.charAt(i) == ' ') {
                i--;
            }
            // j 此时就指向了下一个单词的词尾
            j = i;
        }

        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str1 = "I am a student.";
        String str2 = "a good   example";

        System.out.println(solution.reverseWords(str1));
        System.out.println(solution.reverseWords(str2));
        System.out.println("===");

        System.out.println(solution.reverseWords2(str1));
        System.out.println(solution.reverseWords2(str2));
    }
}