package LeetCodeSolution.AlgorithmThought._05_DivideAndConquer._95_UniqueBinarySearchTreesII;

import java.util.ArrayList;
import java.util.List;

/*
 * 不同的二叉搜索树 Ⅱ
 *
 * 题目描述：
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的二叉搜索树。
 *
 * 思路：
 * 1. 如果求 1...n 的所有可能，我们只需要把 1 作为根节点，空 [] 作为左子树，[2...n] 作为所有可能作为右子树；
 * 2. 将 2 作为根节点，[1] 作为左子树，[3...n] 的所有可能作为右子树；
 * 3. 将 3 作为根节点，[1 2] 的所有可能作为左子树，[4...n] 的所有可能作为右子树，然后左子树和右子树两两组合；
 * 4. 将 4 作为根节点，[1 2 3] 的所有可能作为左子树，[5...n] 的所有可能作为右子树，然后左子树和右子树两两组合。
 * 5. 以此类推；
 * 6. 最后将 n 作为根节点，[1...n] 的所有可能作为左子树，[] 作为右子树；
 * 7. 如果只有一个数字，那么只需要将该数作为根即可；
 * 8. 如果是 []，则返回 null。
 */
public class Solution {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode (int val) {
            this.val = val;
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n < 1) {
            return new ArrayList<>();
        }
        return generateCore(1, n);
    }

    public List<TreeNode> generateCore(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        // 此时没有数字，那么直接添加 null
        if (start > end) {
            res.add(null);
            return res;
        }

        // 只有一个数字，只需将这一个数字组成的节点作为结果进行返回
        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }

        // 尝试每个数字作为根节点
        for (int i = start; i <= end; i++) {
            // 分治
            // 所有可能的左子树集合和右子树集合
            List<TreeNode> leftSubTree = generateCore(start, i - 1);
            List<TreeNode> rightSubTree = generateCore(i + 1, end);
            // 从左子树集合中选出一棵左子树，从右子树集合中选出一棵右子树，然后拼接到根节点上
            for (TreeNode leftTree : leftSubTree) {
                for (TreeNode rightTree : rightSubTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = leftTree;
                    root.right = rightTree;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
