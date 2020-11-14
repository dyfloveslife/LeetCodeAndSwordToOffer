package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._1122_RelativeSortArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 数组的相对排序
 *
 * 题目描述：
 * 给你两个数组，arr1 和 arr2，其中：
 * 1. arr2 中的元素各不相同；
 * 2. arr2 中的每个元素都出现在 arr1 中；
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。
 * 未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * 思路：
 * 1. 可以使用自定义排序，也就是重写 Compare 方法；
 * 2. 另一种方法是使用计数排序，具体看代码，很好理解。
 */
public class Solution {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] map = new int[1001];
        for (int x : arr1) {
            map[x]++;
        }

        List<Integer> list = new ArrayList<>();
        for (int x : arr2) {
            while (map[x] != 0) {
                list.add(x);
                map[x]--;
            }
        }

        for (int i = 0; i < map.length; i++) {
            while (map[i] != 0) {
                list.add(i);
                map[i]--;
            }
        }

        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = {2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19};
        int[] arr2 = {2, 1, 4, 3, 9, 6};

        System.out.println(Arrays.toString(solution.relativeSortArray(arr1, arr2)));
    }
}
