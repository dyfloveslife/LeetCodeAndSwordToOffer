package LeetCodeSolution.AlgorithmThought._04_BinarySearch._36_ValidSudoku;

import java.util.HashSet;
import java.util.Set;

/*
 * 有效的数独
 *
 * 题目描述：
 * 请你判断一个 9 x 9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 *
 * 注意：
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 空白格用 '.' 表示。
 *
 * 思路一：直接遍历矩阵
 * 1. 用哈希表记录每一行、每一列、每一个小九宫格中数字出现的次数，在更新次数后，进行以上三个条件的判断即可；
 * 2. 因为总共就只有 81 个格子，所以直接使用矩阵即可；
 * 3. 其中，对于 board[i][j] 在九宫格的位置，计算方式为 i/3、j/3，均为下取整。
 *
 * 思路二：将收集到的元素根据不同位置组装成字符串
 * 1. https://leetcode.com/problems/valid-sudoku/solutions/15472/short-simple-java-using-strings/
 * 2. 例如，将第 7 行的字符 '4' 表示为 (4)7，将第 7 列的字符 '4' 表示为 7(4)，将左上角第三个九宫格中的字符 '4' 表示为 0(4)2；
 * 3. 还是依次遍历矩阵中的每个字符，使用 Set 存储每个字符串；
 * 4. 由于 Set 的 add(x) 方法在 x 已经存在时会返回 false，所以若返回 false，则说明违反了以上三个条件中的某一个。
 */
public class Solution {

    // 思路一
    public boolean isValidSudoku1(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subBoxes = new int[3][3][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }

                // 减一的目的是将数字转换为数组下标
                int idx = c - '0' - 1;
                rows[i][idx]++;
                columns[j][idx]++;
                subBoxes[i / 3][j / 3][idx]++;
                if (rows[i][idx] > 1 || columns[j][idx] > 1 || subBoxes[i / 3][j / 3][idx] > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    // 思路二
    public boolean isValidSudoku2(char[][] board) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }

                String str = "(" + c + ")";
                if (!set.add(str + i) || !set.add(j + str) || !set.add(i / 3 + str + j / 3)) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        char[][] board2 = {
                {'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        Solution solution = new Solution();
        System.out.println(solution.isValidSudoku1(board1)); // true
        System.out.println(solution.isValidSudoku1(board2)); // false
        System.out.println("----------");
        System.out.println(solution.isValidSudoku2(board1)); // true
        System.out.println(solution.isValidSudoku2(board2)); // false
    }
}
