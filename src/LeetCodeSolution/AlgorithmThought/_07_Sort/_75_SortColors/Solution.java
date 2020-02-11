package LeetCodeSolution.AlgorithmThought._07_Sort._75_SortColors;

/*
 * 颜色分类
 *
 * 题目描述：
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，
 * 原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 思路：
 * 1. 荷兰国旗问题的修改版，只不过这里的数组只包含 0、1 和 2；
 * 2. zero 表示等于 0 的区域，two 表示等于 2 的区域，one 表示等于 1 的区域；
 * 3. 全程查看 arr[one] 的状态，根据不同的状态进行不同的交换。
 */
public class Solution {

    public static void sortColors(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int zero = -1;
        int one = 0;
        int two = arr.length;
        while (one < two) {
            if (arr[one] == 0) {
                swap(arr, ++zero, one++);
            } else if (arr[one] == 2) {
                swap(arr, --two, one);
            } else {
                one++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2, 0, 2, 1, 1, 0};
        int[] arr1 = {1, 0, 2};

        sortColors(arr1);
        for (int i : arr1) {
            System.out.print(i + " ");
        }
    }
}
