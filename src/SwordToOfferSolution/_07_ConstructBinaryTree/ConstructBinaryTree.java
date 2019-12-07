package SwordToOfferSolution._07_ConstructBinaryTree;

/*
 * 重建二叉树
 * 思路：
 * 根据前序遍历和中序遍历可重建二叉树；
 * 根据中序遍历和后序遍历棵重建二叉树。
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
        this.val = val;
    }
}

class Solution {
    public TreeNode constructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length <= 0 || in.length <= 0 || pre.length != in.length) {
            return null;
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) {
            return null;
        }
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }
        }
        return root;
    }

/*    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = constructBinaryTree(pre, in);

        List<Integer> preorder = BinaryTreeOrder.preorderRecursively(root);
        List<Integer> inorder = BinaryTreeOrder.inorderRecursively(root);
        List<Integer> postorder = BinaryTreeOrder.postorderRecursively(root);
        System.out.println(preorder);
        System.out.println(inorder);
        System.out.println(postorder);
    }*/
}
