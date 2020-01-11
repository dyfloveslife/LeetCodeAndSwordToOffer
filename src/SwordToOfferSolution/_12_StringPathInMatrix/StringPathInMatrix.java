package SwordToOfferSolution._12_StringPathInMatrix;

/*
 * 矩阵中的路径
 *
 * 题目描述：
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。
 * 如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 * 例如在下面的 3×4 的矩阵中包含一条字符串 “bfce” 的路径（路径中的字母用下划线标出）。
 * 但矩阵中不包含字符串 “abfb” 的路径，因为字符串的第一个字符 b 占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 *
 * A B T G
 * C F C S
 * J D E H
 *
 * 实现一：
 */
public class StringPathInMatrix {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        // 1.设置一个标志位，初始值为 false 表示未走过
        boolean[] flag = new boolean[matrix.length];
        // 2.遍历数组，找到第一个与 str 字符串的第一个字符相匹配的矩阵元素，并进入 hasPathCore()
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathCore(matrix, row, col, rows, cols, flag, str, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 参数分别表示：初始的字符矩阵、索引行坐标 row、索引列坐标 col、矩阵的行数、矩阵的列数、
    // 标志位数组、待判断的字符串、待判断字符串中字符的位置 k（传过来的是0，即先判断字符串的第一个字符）
    private boolean hasPathCore(char[] matrix, int row, int col, int rows, int cols, boolean[] flag, char[] str, int k) {
        // 3.根据 row 和 col 计算匹配第一个元素在字符数组中的位置
        int index = row * cols + col;
        // 4.递归终止的条件: 越界、在字符矩阵中找到的字符不等于待判定字符串 str 中的字符、已经走过的字符
        if (row < 0 || col < 0 || row >= rows || col >= cols || matrix[index] != str[k] || flag[index] == true) {
            return false;
        }
        // 5.待判断字符串中字符的位置 k 已经到达 str 的末尾了，说明之前的都应匹配成功了，则返回 true
        if (k == str.length - 1) {
            return true;
        }
        // 6.要走的第一个位置为 true，说明已经走过了
        flag[index] = true;
        // 7.回溯递归寻找周围四个格子是否符合条件，要是找到了的话就 k+1
        //	只要有一个格子符合条件，就继续再找这个符合条件的格子的四周是否存在符
        //	合条件的格子，直到 k 到达末尾或者不满足递归条件就停止
        if (hasPathCore(matrix, row - 1, col, rows, cols, flag, str, k + 1) ||
                hasPathCore(matrix, row + 1, col, rows, cols, flag, str, k + 1) ||
                hasPathCore(matrix, row, col - 1, rows, cols, flag, str, k + 1) ||
                hasPathCore(matrix, row, col + 1, rows, cols, flag, str, k + 1)) {
            return true;
        }
        // 8.走到这说明该路不通，则回溯，再找其他的路
        flag[index] = false;
        return true;
    }

}

/*
 * 实现二：
 * 创建一个辅助数组来确定每个位置是否是字符串中的字符，如果是，则为true，如果不是，则为false，初始数组都为false；
 * 创建一个变量来储存当前路径的长度，当长度和字符串一样时，说明已经完成了，直接返回true；
 *
 * 先遍历矩阵，找到和字符串第一个字符相同的字符，作为路径的开始；
 * 如果相等，则矩阵路径+1，并将当前位置置为true，然后对当前位置的上下左右四个位置进行遍历，找寻路径的下一个位置，
 * 如果四个位置都没有找到，则返回false，并将当前位置重新改为false，然后路径-1，因为当前位置不是正确的路径，
 * 因为路径-1，所以回到上一位置，重新寻找。
 */
class Solution {
    public boolean hasPath(char[][] matrix, String str) {
        if (matrix == null || str == null) {
            return false;
        }

        int rows = matrix.length;
        if (rows == 0) {
            return false;
        }

        int cols = matrix[0].length;
        int pathLength = 0;
        boolean[][] visited = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, i, j, str, pathLength, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasPathCore(char[][] matrix, int rows, int cols, int i, int j, String str, int pathLength, boolean[][] visited) {
        boolean flag = false;
        if (i >= 0 && i < rows && j >= 0 && j < cols && !visited[i][j] && matrix[i][j] == str.charAt(pathLength)) {
            pathLength++;
            visited[i][j] = true;
            if (pathLength == str.length()) {
                return true;
            }
            flag = hasPathCore(matrix, rows, cols, i + 1, j, str, pathLength, visited) ||
                    hasPathCore(matrix, rows, cols, i, j + 1, str, pathLength, visited) ||
                    hasPathCore(matrix, rows, cols, i - 1, j, str, pathLength, visited) ||
                    hasPathCore(matrix, rows, cols, i, j - 1, str, pathLength, visited);
            if (!flag) {
                // 回溯
                pathLength--;
                visited[i][j] = false;
            }
        }
        return flag;
    }
}
