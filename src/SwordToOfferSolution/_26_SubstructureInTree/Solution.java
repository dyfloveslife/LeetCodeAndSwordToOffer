package SwordToOfferSolution._26_SubstructureInTree;

/*
 * 树的子结构
 * 注意 null的条件，HasSubTree 中，如果两棵树都不为空才进行判断，
 * DoesTree1HasTree2 中，如果 Tree2 为空，则说明第二棵树遍历完了，即匹配成功，
 * tree1 为空有两种情况:
 * （1）如果 tree1 为空 && tree2 不为空，说明不匹配，
 * （2）如果 tree1 为空，tree2 为空，说明匹配。
 */
class TreeNode {
    int val = 0;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}

public class Solution {
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
