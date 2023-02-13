package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._74_SearchA2DMatrix;

/*
 * 搜索二维矩阵
 *
 * 题目描述：
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。
 * 该矩阵具有如下特性：
 *     每行中的整数从左到右按升序排列。
 *     每行的第一个整数大于前一行的最后一个整数。
 *
 * 思路一：
 * 1. 这里需要根据二维矩阵的特性来确定某个元素在二维矩阵中的位置；
 * 2. 例如对于二维矩阵中任意一个元素 middle，它在二维矩阵中的位置就是 matrix[middle / cols][middle % cols]；
 * 3. 也就是说，将后一行的元素拼接在前一行元素的后面，组成一个升序数组；
 * 4. 由于数组是有序的，因此可以利用二分的方式，每次排除掉一半的元素；
 * 5. 由于经典的二分最后返回的是 left，因此，该题最后还需要对 left 位置上的数进行确认，判断 left 位置上的数是不是等于 target；
 * 6. 需要注意的是，二分有两种写法，第 4 点提到的前提是 while 中的表达式必须为 left < right，若 left <= right，则最后如果找不到 target 的话，最好返回 -1 或特殊值。
 *
 * 思路二：
 * 1. 自己想到的思路是针对矩阵中每行元素以及行与行之间的关系，通过二分的方式查找 target；
 * 2. 既然后一行的第一个元素大于前一行的最后一个元素，那么在二分之前，先判断给定数组的最后一个元素与 target 的大小；
 * 3. 如果最后一个元素小于 target，则直接来到下一行即可。
 *
 * 思路三：
 * 1. 使用给定矩阵的特性，通过不断修改列的值来缩小超找范围。
 */
public class Solution {
    // 思路一
    public boolean searchMatrix1(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length, n = matrix[0].length;
        // 相当于一维数组的左右边界索引
        int left = 0, right = m * n - 1;

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            int x = matrix[mid / n][mid % n];
            // 如果当前位置上的数比 target 小，则说明没有找到，因此需要将 left 范围往右移动
            if (x == target) {
                return true;
            } else if (x < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // 对于这种二分的写法，最终的位置会指向 left
        // 所以需要再判断 left 处的元素
        return matrix[left / n][left % n] == target;
    }

    // 思路一
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        // 相当于一维数组的左右边界下标
        int left = 0, right = m * n - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int x = matrix[mid / n][mid % n];
            if (x == target) {
                return true;
            } else if (target > x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    // 思路二
    public boolean searchMatrix3(int[][] matrix, int target) {
        for (int[] nums : matrix) {
            int result = binarySearch(nums, target);
            if (result == -2) {
                continue;
            }
            return result != -1;
        }

        return false;
    }

    private int binarySearch(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        if (nums[j] < target) {
            return -2;
        }

        while (i <= j) {
            int mid = i + ((j - i) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }

        return -1;
    }

    // 思路三
    public boolean searchMatrix4(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;

        while (i < m && j >= 0) {
            // 拿到每行的最后一个元素
            int cur = matrix[i][j];
            if (target == cur) {
                return true;
            }
            if (target > cur) {
                i++;
            } else {
                j--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};

        System.out.println(solution.searchMatrix1(matrix, 3)); // true
        System.out.println(solution.searchMatrix1(matrix, 12)); // false
        System.out.println("----");
        System.out.println(solution.searchMatrix2(matrix, 3)); // true
        System.out.println(solution.searchMatrix2(matrix, 12)); // false
        System.out.println("----");
        System.out.println(solution.searchMatrix3(matrix, 3)); // true
        System.out.println(solution.searchMatrix3(matrix, 12)); // false
        System.out.println("----");
        System.out.println(solution.searchMatrix4(matrix, 3)); // true
        System.out.println(solution.searchMatrix4(matrix, 12)); // false
    }
}
