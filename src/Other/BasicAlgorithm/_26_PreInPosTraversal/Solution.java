package Other.BasicAlgorithm._26_PreInPosTraversal;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/*
 * 二叉树的前中后遍历（递归和非递归）
 *
 * 注意：
 * 在递归遍历的时候，依次访问到的节点有哪些；
 *                     1
 *                /        \
 *              2           3
 *            /   \       /   \
 *           4     5     6     7
 *         /  \   /  \  /  \  /  \
 *        n1  n2 n3 n4 n5  n6 n7  n8
 *  先序遍历：1->2->4->4->4->2->5->5->5->2->1->3->6->6->6->3->7->7->7->3->1
 *  可以看到，对于递归来说，每个节点都被访问到了 3 次。
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

    // 先序遍历(递归)
    public void preOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);
        preOrderRecursion(root.left);
        preOrderRecursion(root.right);
    }

    // 中序遍历(递归)
    public void inOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrderRecursion(root.left);
        System.out.println(root.val);
        inOrderRecursion(root.right);
    }

    // 后序遍历(递归)
    public void postOrderRecursion(TreeNode root) {
        if (root == null) {
            return;
        }

        postOrderRecursion(root.left);
        postOrderRecursion(root.right);
        System.out.println(root.val);
    }

    // 先序遍历(非递归)
    public void preOrderNoRecursion(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 当前节点在入栈的时候，先让右孩子入栈，再让左孩子入栈
            // 出栈的时候，就是先出左孩子，后出右孩子
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    // 中序遍历(非递归)
    // 对于当前节点来说，如果当前节点不为空，则会把它的左边界全都入栈，一压压一溜
    // 如果当前节点为空，那么就开始出栈吧，然后再来到当前节点的右孩子
    public void inOrderNoRecursion1(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        // root != null 表示在栈初始化的时候，先让 root 进去
        while (!stack.isEmpty() || root != null) {
            /*
            // 这里也可以使用 while，只要当前节点不为空，则不断的来到当前节点的左孩子的位置
             while (root != null) {
                stack.push(root);
                root = root.left;
             }
             root = stack.pop();
             System.out.println(root.val);
             root = root.right;
             */
            // 将当前节点的左边界全部入栈
            if (root != null) {
                stack.push(root);
                root = root.left;
                // 能进入 else，说明 while 满足的条件是 !stack.isEmpty()，并且当前节点为 null
                // 则从栈中弹出一个节点，然后打印输出，最后再来到当前节点的右侧
            } else {
                root = stack.pop();
                System.out.println(root.val);
                root = root.right;
            }
        }
    }

    // 后序遍历(非递归)
    // 之前的先序遍历是 中左右，即先压右孩子，再压左孩子；
    // 则可以通过先压左孩子，再压右孩子，变成 中右左；
    // 最后再将要输出的节点放到一个栈中，从而逆序输出后序的 左右中。
    public void postOrderNoRecursion(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            root = s1.pop();
            // 本来应该打印输出的，但这里将其存入到另外一个栈中
            s2.push(root);
            if (root.left != null) {
                s1.push(root.left);
            }
            if (root.right != null) {
                s1.push(root.right);
            }
        }
        // 最后再单独打印辅助栈即可
        while (!s2.isEmpty()) {
            System.out.println(s2.pop().val);
        }
    }

    // 层次遍历（从顶到底）
    public List<List<Integer>> levelOrderTopToBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    // 层次遍历（从底到顶）
    public List<List<Integer>> levelOrderBottomToTop(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            // 在头部插入
            res.addFirst(list);
        }
        return res;
    }

    // 从上到下，第一层从左到右，第二层从右到左
    // 若根节点是第一层，则只需要将偶数层倒叙即可
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            level++;
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                list.add(root.val);
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            // 如果当前是偶数层，则倒叙
            if ((level & 1) == 0) {
                Collections.reverse(list);
            }
            res.add(list);
        }
        return res;
    }
}