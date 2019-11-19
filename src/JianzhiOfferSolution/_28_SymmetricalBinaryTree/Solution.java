package JianzhiOfferSolution._28_SymmetricalBinaryTree;

class TreeNode {
	int val = 0;
	TreeNode left = null;
	TreeNode right = null;

	TreeNode(int val) {
		this.val = val;
	}
}

/**
 * 对称的二叉树
 * 思路：
 * 可以使用前序遍历和前序对称遍历的输出是否相同来解决。
 * 对于二叉树节点值都相同的情况，可以将 null 值也算进比较的范围里。
 *
 *             pRoot1                        pRoot2
 *             /     \                       /     \
 *     pRoot1.left  pRoot1.right      pRoot2.left  pRoot2.right
 */
public class Solution {
	boolean isSymmetrical(TreeNode pRoot) {
		return isSymmertical(pRoot, pRoot);
	}

	private boolean isSymmertical(TreeNode pRoot1, TreeNode pRoot2) {
		if (pRoot1 == null && pRoot2 == null) return true;

		if (pRoot1 == null || pRoot2 == null) return false;

		if (pRoot1.val != pRoot2.val) return false;

		return isSymmertical(pRoot1.left, pRoot2.right) && isSymmertical(pRoot1.right, pRoot2.left);
	}
}
