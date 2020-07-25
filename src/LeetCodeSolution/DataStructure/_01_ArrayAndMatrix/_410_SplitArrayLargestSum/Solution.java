package LeetCodeSolution.DataStructure._01_ArrayAndMatrix._410_SplitArrayLargestSum;

/*
 * 分割数组的最大值
 *
 * 题目描述：
 * 给定一个非负整数数组和一个整数 m，你需要将这个数组分成 m 个非空的连续子数组。设计一个算法使得这 m 个子数组各自和的最大值最小。
 *
 * 思路：
 * 0. 二分法，例如 nums=[7, 2, 5, 10, 8]
 * 1. 考虑 m 的情况：
 *    1.1) 若 m=1，则只能将给定的数组划分成一个子数组，那么这个子数组其实就是给定的数组本身，这个数组的和是每个元素相加的结果，
 *         因此最小的最大值就是把每个元素加起来即可，即 32；
 *    1.2) 若 m=n，则每个元素单独成为一个子数组，那么每个子数组各自的和的最大值中，最小的那个最大值就是数组中最大的那个元素，即 10。
 * 2. 因此，最小的那个最大值的范围就是在 [10, 32] 之间；
 * 3. 我们利用二分法，找出符合 m 的最小的那个最大值；
 * 4. 对于二分法中的 middle=21 来说，这个值就是一个子数组最大容量；
 * 5. 假设刚开始子数组的个数是 count=1，那么我们根据给定的数组，将每个元素都分别往子数组中放就可以了；
 * 6. 7 < 21
 *    7 + 2 < 21
 *    7 + 2 + 5 < 21
 *    7 + 2 + 5 + 10 > 21
 *    由于 10 的加入，第一个子数组已经不能放下 10 了，因此需要再开一个子数组，即 count = count + 1 = 2，也就是新开一个子数组来存放剩下的元素；
 *    10 < 21
 *    10 + 8 < 21
 * 7. 至此，所有的元素都已经被分配到相应的子数组中了，并且我们只使用了 2 个子数组，count 刚好等于 m，
 *    因此，分割出的子数组 [7, 2, 5] 和 [10, 8] 中最小的最大值就是 18，
 *    需要注意的是，“最大值”包含很多种情况，根据不同的划分方式，有很多的“最大值”，而我们需要找的就是这些“最大值”中最小的那个。
 * 8. 由于需要选择的是连续的子数组，因此：
 *    如果我们最终得到的 count > m，那么就说明我们划分的子数组太多了，也就是意味着其中一个子数组的容量太少了，我们需要扩大容量，因此 left = mid + 1；
 *    反之，如果得到 count < m，那么表示我们划分的子数组太少了，也就意味着一个子数组的容量太大了，因此需要缩小容量，即 right = mid；
 * 9.
 */
public class Solution {
    public int splitArray(int[] nums, int m) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        for (int num : nums) {
            left = Math.max(left, num);
            right += num;
        }
        while (left < right) {
            int count = 1;
            int sum = 0;
            int mid = left + ((right - left) >> 1);
            for (int num : nums) {
                // 在逐渐向子数组添加元素的过程中，如果一个子数组放不下，则需要新开一个子数组，即 count++
                if (sum + num > mid) {
                    sum = 0;
                    count++;
                }
                sum += num;
            }
            if (count > m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;

        System.out.println(solution.splitArray(nums, m));
    }
}
