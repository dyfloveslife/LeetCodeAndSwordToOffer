package LeetCodeSolution.DataStructure._03_Tree._1028_RecoverATreeFromPreorderTraversal;

import java.util.Stack;

/*
 * 从先序遍历还原二叉树
 *
 * 题目描述：
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。
 * （如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * 给定该遍历的输出为 S，请还原树并返回其根节点 root。
 *
 * 思路：
 * 1. 注意题目给定的示例：[1-2--3--4-5--6--7] 每个节点之前的 '-' 数量表示这个节点所在树中的深度，节点 1 前面没有则说明它的深度为 0；
 * 2. 对于当前节点来说，它的深度肯定小于左右子树的深度；
 * 3. 既然是从根节点出发，往孩子节点走，节点的深度是递增的，具有单调性，因此可以使用单调栈；
 * 4. 先序遍历的顺序为：根左右。只要新节点的高度比上一个遍历到的节点（preNode）的深度要大，那么这个新节点就是上一个遍历节点（preNode）的孩子节点；
 * 5. 如果新节点的高度不大于 preNode，则说明这个 preNode 没有孩子了；
 * 6. 使用单调栈记录访问过的节点，如果新节点的深度大于栈顶节点的深度，则说明可以继续往孩子节点走，新节点入栈，并且可以构造父子关系：
 *    也就是如果 preNode 的 left 为空，则 preNode.left = newNode，否则 preNode.right = newNode；
 *    这里先构造左孩子的原因是因为：题目要求“如果节点只有一个子节点，那么保证该子节点为左子节点”。
 * 7. 如果新节点的深度小于栈顶节点的深度，则说明栈顶节点不可能再有孩子了，那么让栈顶节点出栈，继续比较新节点和栈顶节点的深度，直到新节点大于栈顶节点为止；
 * 8. 由于先遍历的是根节点，入栈的也是根节点，因此最后需要迭代处理最后一个节点。
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

    public TreeNode recoverFromPreorder(String S) {
        int level;
        int value;
        Stack<TreeNode> stack = new Stack<>();

        for (int i = 0; i < S.length(); ) {
            for (level = 0; i < S.length() && S.charAt(i) == '-'; i++) {
                level++;
            }
            for (value = 0; i < S.length() && S.charAt(i) != '-'; i++) {
                value = value * 10 + (S.charAt(i) - '0');
            }
            while (stack.size() > level) {
                stack.pop();
            }
            TreeNode node = new TreeNode(value);
            if (!stack.isEmpty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }
            stack.push(node);
        }
        // 除了根节点，其它的节点全都出栈
        while (stack.size() > 1) {
            stack.pop();
        }
        // 返回栈中仅剩的根节点
        return stack.pop();
    }
}
