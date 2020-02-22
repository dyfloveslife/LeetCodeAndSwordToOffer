package SwordToOfferSolution._03_FindDuplicationInArray;

import java.util.HashSet;

/*
 * 找出数组中重复的数字
 *
 * 题目描述：
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。例如，如果输入长度为 7 的数组 {2, 3, 1, 0, 2, 5, 3}，那么对应的输出是重复的数字 2 或者 3。
 *
 * 思路：1
 * 1. 由于需要找到重复的，则可以使用一种容器，在每次遍历数组的时候，将其加入到该容器中；
 * 2. 如果判断出已经添加过，则直接将该元素返回即可，该容器可以使用 HashSet；
 * 3. 此方法不会改变数组中元素的位置，但是会产生额外的 O(N) 空间。
 *
 * 思路：2
 * 1. 第二种方法空间复杂度是 O(1) 的，它是通过值与索引的对应（调整）关系来进行解决的，因此会修改数组中元素的位置；
 * 2. 举个栗子：
 *          [2, 3, 1, 0, 2, 5, 3]
 *           0  1  2  3  4  5  6
 *    整体思路是：我们要做到让索引为 i 的值是 i，例如要做到让索引为 0 的值是 0，让索引为 1 的值是 1，让索引为 2 的值是 2；
 *    假如当前遍历到的索引是 0，由于 0 上面的数不是 0，所以将 0 上面的 2 与索引为 2 上面的 1 交换。但在交换之前，需要判断一下元素 2 是否与元素 1 相等，
 *    如果相等，则找到了重复值，直接返回即可，但此时不等，所以进行交换，如下所示：
 *          [1, 3, 2, 0, 2, 5, 3]
 *           0  1  2  3  4  5  6
 *    此时，2 已经来到了索引为 2 的位置，而索引为 0 的位置还是不满足条件，因此，再将索引为 0 位置上面的 1 与索引为 1 上面的值 3 进行交换。但是，
 *    在交换之前，需要进行判断两个元素是否相等。此时不等，所以进行交换，如下所示：
 *          [3, 1, 2, 0, 2, 5, 3]
 *           0  1  2  3  4  5  6
 *    此时，1 又来到了索引为 1 的位置，而索引为 0 的位置还是不满足条件，因此，再将索引为 0 位置上面的 3 与索引为 3 上面的值 0 进行交换。但是，
 *    在交换之前，需要进行判断两个元素是否相等。此时不等，所以进行交换，如下所示：
 *          [0, 1, 2, 3, 2, 5, 3]
 *           0  1  2  3  4  5  6
 *    此时，索引为 0 位置、1 位 置、2 位置、3 位置上的数已经对应起来了，接下来，来到了索引为 4 的位置。由于索引为 4 位置上的 2 与索引为 2 上面的 2 相等了，
 *    此时我们找到了重复的元素，直接返回即可。
 */
public class Solution {

    // 方法一
    public static int duplicate1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.add(nums[i])) {
                return nums[i];
            }
        }
        return -1;
    }

    // 方法二
    private static int duplicate2(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, nums[i], nums[nums[i]]);
            }
        }
        return -1;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 1, 0, 2, 5};
        System.out.println(duplicate1(arr));
    }
}