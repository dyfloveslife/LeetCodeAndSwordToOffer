package SwordToOfferSolution._32_03_PrintTreesInZigzag;

import java.util.*;

/*
 * 之字形打印二叉树（换行）
 *
 * 题目描述：
 * 请实现一个函数按照之字形顺序打印二叉树，
 * 即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 *
 * 思路一：栈
 * 1. 使用两个栈，对于二叉树中的奇数行，由于是从左到右打印的，所以存入到栈 1 中；
 * 2. 对于偶数行，由于是从右到左打印的，所以存入栈 2 中。
 *
 * 思路二：双端队列
 * 1. 假设根节点为第一行，将奇数行所在节点添加到队列头部，而偶数行所在节点添加到队列尾部；
 * 2. 这里使用结果集 (res.size() % 2) 来判断当前节点属于奇数行还是偶数行；
 * 3. 格外注意 addLast() 和 addFirst() 的使用。
 *
 * 思路三：反转 list
 * 1. 使用 Collections.reverse(list) 将奇数行中的元素进行反转。
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

    // 双端队列
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 双端队列
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 将偶数行的每个元素都添加到队列的头部
                if (res.size() % 2 == 0) {
                    list.addLast(node.val);
                    // 将奇数行的每个元素添加到队列的尾部
                } else {
                    list.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    // 反转
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (res.size() % 2 == 1) {
                Collections.reverse(list);
            }
            res.add(list);
        }
        return res;
    }
}