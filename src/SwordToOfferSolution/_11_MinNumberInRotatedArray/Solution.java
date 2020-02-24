package SwordToOfferSolution._11_MinNumberInRotatedArray;

/*
 * 旋转数组的最小数字
 *
 * 题目描述：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 数组中不存在重复元素。
 * 例如数组 {3, 4, 5, 1, 2} 为 {1, 2, 3, 4, 5} 的一个旋转，该数组的最小值为 1。
 *
 * 思路：
 * 1. 直接使用二分法进行查找；
 * 2. 拿 nums[middle] 和 nums[right] 进行比较：
 *    2.1) 如果 nums[middle] <= nums[right]，说明 middle 和 right - 1 之内的所有的元素都比 right 小，
 *         则此最小的元素有可能在 nums[middle] 位置上，也有可能在 nums[middle] 的左边，所以需要将 right 定位到 middle 的位置；
 *    2.2) 如果 nums[middle] > nums[right]，说明最小的元素在 middle 的右侧，
 *         因此需要将 left 置为 middle + 1。
 * 3. 如果数组中存在重复元素，则让右边界的 right-- 即可，因为右边界和中间数是相等的，即使去掉了右边界，而中间的数还是存在的，
 *    然后再让新的中间数和右边界继续跑循环。
 */
public class Solution {

    // 数组中元素不重复
    public static int minNumberInRotateArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] <= nums[right]) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return nums[left];
    }

    // 数组中元素重复
    public static int minNumberInRotateArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int middle = left + ((right - left) >> 1);
            if (nums[middle] < nums[right]) {
                right = middle;
            } else if (nums[middle] > nums[right]) {
                left = middle + 1;
            } else {
                right--;
            }
        }
        return nums[left];

    }

    public static int minNumberInRotateArray(int[] array) {
        if (array.length <= 0) {
            return 0;
        }

        int index1 = 0;
        int index2 = array.length - 1;
        //如果旋转后的数组是排序数组本身，则第一个元素就是最小的
        int indexMid = index1;
        while (array[index1] >= array[index2]) {
            if (index2 - index1 == 1) {
                indexMid = index2;
                break;
            }
            indexMid = (index1 + index2) / 2;
            // 如果 index1、index2、indexMid 都相等的话，只能顺序查找
            if (array[index1] == array[index2] && array[indexMid] == array[index1]) {
                return minInOrder(array, index1, index2);
            }
            if (array[indexMid] >= array[index1]) {
                index1 = indexMid;
            }
            if (array[indexMid] <= array[index2]) {
                index2 = indexMid;
            }
        }
        return array[indexMid];
    }

    private static int minInOrder(int[] array, int index1, int index2) {
        int res = array[index1];
        for (int i = index1 + 1; i < index2; i++) {
            if (array[i] < res) {
                return array[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
        int[] arr2 = {3, 4, 5, 1, 2};
        int[] arr3 = {0, 1, 1, 1, 1, 1, 1};

        System.out.println(minNumberInRotateArray2(arr3));
    }
}