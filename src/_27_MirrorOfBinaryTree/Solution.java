package _27_MirrorOfBinaryTree;

/**
 * 求二叉树的镜像
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
	public void mirrorOfBinaryTree(TreeNode root) {
		if (root == null) return;
		if (root.left == null && root.right == null) return;

		// swap
		TreeNode tempNode = root.left;
		root.left = root.right;
		root.right = tempNode;

		if (root.left != null) mirrorOfBinaryTree(root.left);
		if (root.right != null) mirrorOfBinaryTree(root.right);
	}
}
