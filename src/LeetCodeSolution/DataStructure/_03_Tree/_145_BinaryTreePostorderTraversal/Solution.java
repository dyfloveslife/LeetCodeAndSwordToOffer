package LeetCodeSolution.DataStructure._03_Tree._145_BinaryTreePostorderTraversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * 二叉树的后序遍历
 *
 * 题目描述：
 * 给定一个二叉树，返回它的后序遍历。
 *
 * 思路：
 * 1. 后序遍历需要用到两个栈；
 * 2. 学会了前序遍历，那么后序遍历也就很清楚了；
 * 3. 只需要将前序遍历的入栈顺序修改一下，让其进入到另外一个栈中，则就称为了后序遍历；
 * 4. 当然也可以只使用一个栈，然后在最后的时候将这个 list 进行反转即可。
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

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);

        while (!s1.isEmpty()) {
            root = s1.pop();
            s2.push(root);
            if (root.left != null) {
                s1.push(root.left);
            }
            if (root.right != null) {
                s1.push(root.right);
            }
        }
        while (!s2.isEmpty()) {
            res.add(s2.pop().val);
        }

        return res;
    }
}
