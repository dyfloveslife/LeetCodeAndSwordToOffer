package Other.AdvancedAlgorithm._17_MaxDistanceInTree;

/*
 * 二叉树上的最远距离
 *
 * 思路 1：
 * 1. 求每个节点为头的整棵树的最大距离即可；
 * 2. 每个节点携带的信息有最大的距离和深度。
 *
 * 思路 2：
 * 1. 在计算最远距离的时候，不一定要经过树的根节点；
 * 2. 满足最远距离的条件是：以某个节点为根节点的子树的左右子树高度之和；
 * 3. 用 distance 表示以 root 为根节点的子树的最长路径，
 *    即从 root 到其左子树的最深节点的长度加上从 root 到其右子树的最深节点的长度；
 * 4. root 的最远距离 = root 左子树的高度 + root 右子树的高度
 * 5. root 的高度 = max{root 左子树高度, root 右子树高度} + 1。
 */
public class Solution {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    int distance = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return distance;
    }

    // 找出以 root 为根节点的二叉树的最大深度
    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = dfs(root.left);
        int rightDepth = dfs(root.right);
        if (leftDepth + rightDepth > distance) {
            distance = leftDepth + rightDepth;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    // 思路 1
    class ReturnType {
        int maxDistance;
        int h;

        ReturnType(int maxDistance, int h) {
            this.maxDistance = maxDistance;
            this.h = h;
        }
    }

    public int getMaxDistance(TreeNode head) {
        return process(head).maxDistance;
    }

    public ReturnType process(TreeNode head) {
        if (head == null) {
            return new ReturnType(0, 0);
        }

        ReturnType leftSubTreesInfo = process(head.left);
        ReturnType rightSubTreesInfo = process(head.right);
        int p1 = leftSubTreesInfo.maxDistance;
        int p2 = rightSubTreesInfo.maxDistance;
        int includeHeadDistance = leftSubTreesInfo.h + 1 + rightSubTreesInfo.h;
        int returnDistance = Math.max(Math.max(p1, p2), includeHeadDistance);
        int itSelf = Math.max(leftSubTreesInfo.h, rightSubTreesInfo.h) + 1;
        return new ReturnType(returnDistance, itSelf);
    }
}
