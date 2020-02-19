package Other.AdvancedAlgorithm._03_KMP_T1SubtreeEqualsT2;

/*
 * 题目描述：
 * 问 T1 中有没有一棵子树能与 T2 对应？
 * 这里的子树必须都得将孩子给算上，不是说只有左孩子或者只有右孩子。
 *
 * 思路：
 * 将两棵数序列化成字符串，如果 T1 中包含 T2 子串，则 T2 一定包含在 T1 中。
 */
public class Solution {

    public static class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    // 主函数
    public static boolean isSubtree(Node t1, Node t2) {
        String s1 = serialByPre(t1);
        String s2 = serialByPre(t2);
        return getIndexOf(s1, s2) != -1;
    }

    // 序列化
    private static String serialByPre(Node head) {
        if (head == null) {
            return "#!";
        }

        String res = head.val + "!";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    // KMP
    private static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2);
        while (i1 < str1.length && i2 < str2.length) {
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == str2.length ? i1 - i2 : -1;
    }

    private static int[] getNextArray(char[] str2) {
        if (str2.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[str2.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0; // 往前跳的索引
        while (i < next.length) {
            // 如果当前跳到的位置和前一个字符相同
            // 则当前位置的值就是 cn+1 的值，然后 i 继续往后移动
            if (str2[i - 1] == str2[cn]) {
                next[i++] = ++cn;
                // 如果当前跳到的位置和前一个字符不同
                // 分为可以再往前跳和不能再往前跳
                // 还可以往前跳
            } else if (cn > 0) {
                cn = next[cn];
                // 不能往前跳了
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Node t1 = new Node(1);
        t1.left = new Node(2);
        t1.right = new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.right = new Node(8);
        t1.left.right.left = new Node(9);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.right = new Node(8);
        t2.right = new Node(5);
        t2.right.left = new Node(9);

        System.out.println(isSubtree(t1, t2));
    }
}