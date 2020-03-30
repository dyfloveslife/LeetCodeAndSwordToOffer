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
 * 2. 将 2 作为根节点，[] 作为左子树，[3...n] 的所有可能作为右子树；
 * 3. 将 3 作为根节点，[1 2] 的所有可能作为左子树，[4... n] 的所有可能作为右子树，然后左子树和右子树两两组合；
 * 4. 将 4 作为根节点，[1 2 3] 的所有可能作为左子树，[5...n] 的所有可能作为右子树，然后左子树和右子树两两组合。
 * 5. 以此类推；
 * 6. 最后将 n 作为根节点，[1...n] 的所有可能作为左子树，[]作为右子树。
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

        if (start > end) {
            res.add(null);
            return res;
        }

        // 只有一个数字，只需将这一个数字组成的节点作为结果进行返回
        if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }

        for (int i = start; i <= end; i++) {
            // 分治
            List<TreeNode> leftSubTree = generateCore(start, i - 1);
            List<TreeNode> rightSubTree = generateCore(i + 1, end);

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
