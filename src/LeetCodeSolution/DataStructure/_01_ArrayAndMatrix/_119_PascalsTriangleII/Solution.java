package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._119_PascalsTriangleII;

import java.util.ArrayList;
import java.util.List;

/*
 * 杨辉三角 II
 *
 * 题目描述：
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 思路：递推
 * 1. 方法与「杨辉三角」相同，最后从结果 ans 中根据入参返回对应的行即可；
 * 2. 但其实没有必要将所有的杨辉三角求出来，因为对第 i + 1 行进行计算时，仅用到了第 i 行数据，因此可以采用"滚动数据"减少空间占用。
 */
public class Solution {
    public List<Integer> getRow1(int rowIndex) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));
                }
            }
            ans.add(row);
        }

        return ans.get(rowIndex);
    }

    public List<Integer> getRow2(int rowIndex) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                } else {
                    cur.add(ans.get(j - 1) + ans.get(j));
                }
            }
            ans = cur;
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.getRow1(3)); // [1, 3, 3, 1]
        System.out.println(solution.getRow1(0)); // [1]
        System.out.println(solution.getRow1(1)); // [1, 1]

        System.out.println("-------");
        System.out.println(solution.getRow2(3)); // [1, 3, 3, 1]
        System.out.println(solution.getRow2(0)); // [1]
        System.out.println(solution.getRow2(1)); // [1, 1]
    }
}
