package Other.AdvancedAlgorithm._17_MaxDistanceInTree;

/*
 * 二叉树上的最远距离
 *
 * 思路：
 * 1. 求每个节点为头的整棵树的最大距离即可；
 * 2. 每个节点携带的信息有最大的距离和深度。
 */
public class Solution {

    class Node {
        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
    }

    class ReturnType {
        int maxDistance;
        int h;

        ReturnType(int maxDistance, int h) {
            this.maxDistance = maxDistance;
            this.h = h;
        }
    }

    // 主函数
    public int getMaxDistance(Node head) {
        return process(head).maxDistance;
    }

    public ReturnType process(Node head) {
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
