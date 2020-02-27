package SwordToOfferSolution._26_SubstructureInTree;

/*
 * 树的子结构
 *
 * 题目描述：
 * 输入两棵二叉树 A 和 B，判断 B 是不是 A 的子结构。
 *
 * 思路：
 * 1. 注意 null 的条件，HasSubTree 中，如果两棵树都不为空才进行判断，
 *    DoesTree1HasTree2 中，如果 Tree2 为空，则说明第二棵树遍历完了，即匹配成功；
 * 2. tree1 为空有两种情况:
 *   1) 如果 tree1 为空且 tree2 不为空，说明不匹配；
 *   2) 如果 tree1 为空，tree2 为空，说明匹配。
 *
 * 3. 实现两个函数，isSubStructure() 和 helper()
 *    3.1) isSubStructure() 用于递归遍历树 1 中的所有节点，并判断当前节点是否与树 2 中的根节点相同，
 *         相同则调用 help() 进一步判断；
 *    3.2) help() 用于检查树 2 中是否与树 1 的一个子树拥有相同的结构和节点值。
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

    // 简介实现
    public boolean isSubstructure1(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return dfs(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        return (A.val == B.val) && dfs(A.left, B.left) && dfs(A.right, B.right);
    }


    // 实现一
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null && B == null) {
            return true;
        }
        // 如果树 A或树 B其中有一个为空，则根据约定，空树不是任意一个树的子结构
        if (A == null || B == null) {
            return false;
        }

        boolean res = false;
        // 在树 A 中找到了树 B 的根节点时，进入 help 函数进行校验
        if (A.val == B.val) {
            res = helper(A, B);
        }
        // 如果 res == false，说明树 B 的根节点不在树 A 的树顶中，则进入 A 的左子树进行查询
        if (!res) {
            res = isSubStructure(A.left, B);
        }
        // 如果 res == false，说明树 B 的根节点不在树 A 的树顶和左子树中，则进入 A 的右子树进行查询
        if (!res) {
            res = isSubStructure(A.right, B);
        }

        return res;
    }

    public boolean helper(TreeNode A, TreeNode B) {
        // 如果 B 为空了，则说明 B 已经遍历完了
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }

        if (A.val != B.val) {
            return false;
        }
        // 分别检查 A 和 B 的左右子树
        return helper(A.left, B.left) && helper(A.right, B.right);
    }

    // 实现二
    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        boolean res = false;
        if (root1 != null && root2 != null) {
            if (root1.val == root2.val) {
                res = DoseTree1HasTree2(root1, root2);
            }
            if (!res) {
                res = hasSubtree(root1.left, root2);
            }
            if (!res) {
                res = hasSubtree(root1.right, root2);
            }
        }
        return res;
    }

    private boolean DoseTree1HasTree2(TreeNode root1, TreeNode root2) {
        // 说明树 2 已经遍历完，则返回 true
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return DoseTree1HasTree2(root1.left, root2.left) && DoseTree1HasTree2(root1.right, root2.right);
    }
}
