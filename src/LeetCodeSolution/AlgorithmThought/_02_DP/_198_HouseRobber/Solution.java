package LeetCodeSolution.AlgorithmThought._02_DP._198_HouseRobber;

/*
 * 打家劫舍
 *
 * 题目描述：
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 实例：
 * 输入: [2, 7, 9, 3, 1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *       偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 * 思路：
 * 1. 当前的金额，要么是前一个房子（i - 1）里面的金额，要么是当前房子里的金额（nums[i]）加上前间隔（注意是 i - 2）房子里的金额；
 *    整体就是偷不偷当前的第 i 个房间里的钱：
 *         如果偷的话，则不能偷 i-1 房间的钱，只能偷 i-2 房间的钱，然后再加上当前 i 房间的钱；
 *         如果不偷的话，只能计算 i-1 房间的钱。
 *         即 dp[i] = max(dp[i - 2] + nums[i], dp[i - 1]);
 * 2. 从这两者取最大值即可；
 * 3. 当然，如果只有一个房间，则只能偷该房间的钱；
 * 4. 如果只有两个房间，那么由于不能连续偷，所以需要从这两个房间中选择一个最大的金额去偷。
 */
public class Solution {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int[] dp = new int[nums.length + 1];
        // 如果只有一个房间，那么只能偷这个房间的钱，因为需要得到最高的金额
        dp[0] = nums[0];
        // 如果有两个房间，由于不能连续地偷，因此只能从两个房间中选择一个金额高的去偷
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }

    // 将空间复杂度优化到 O(1)
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int prepre = 0;
        int pre = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = Math.max(prepre + nums[i], pre);
            prepre = pre;
            pre = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};

        System.out.println(solution.rob(nums1));
        System.out.println(solution.rob(nums2));
        System.out.println("==");

        System.out.println(solution.rob2(nums1));
        System.out.println(solution.rob2(nums2));
    }
}
