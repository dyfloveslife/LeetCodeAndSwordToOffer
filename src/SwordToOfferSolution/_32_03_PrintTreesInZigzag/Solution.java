package SwordToOfferSolution._32_03_PrintTreesInZigzag;

import java.util.*;

/*
 * 之字形打印二叉树（换行）
 * 思路：
 * 使用两个栈，对于二叉树中的奇数行，由于是从左到右打印的，所以存入到栈1中；
 * 对于偶数行，由于是从右到左打印的，所以存入栈2中。
 */

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    // 方法一：使用两个栈实现
    public ArrayList<ArrayList<Integer>> printTreesInZigzag1(TreeNode root) {
        int layer = 1;
        // 存放结果
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        // 用于存放奇数行和偶数行的节点
        Stack<TreeNode> stackOdd = new Stack<>();
        Stack<TreeNode> stackEven = new Stack<>();

        stackOdd.push(root);

        while (!stackOdd.isEmpty() || !stackEven.isEmpty()) {
            // 奇数层
            if (layer % 2 != 0) {
                ArrayList<Integer> list = new ArrayList<>();
                while (!stackOdd.isEmpty()) {
                    TreeNode node = stackOdd.pop();
                    if (node != null) {
                        list.add(node.val);
                        stackEven.push(node.left);
                        stackEven.push(node.right);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                    layer++;
                    System.out.println();
                }
            }
            // 偶数层
            else {
                ArrayList<Integer> list = new ArrayList<>();
                while (!stackEven.isEmpty()) {
                    TreeNode node = stackEven.pop();
                    if (node != null) {
                        list.add(node.val);
                        stackOdd.push(node.right);
                        stackOdd.push(node.left);
                    }
                }
                if (!list.isEmpty()) {
                    res.add(list);
                    layer++;
                    System.out.println();
                }
            }
        }
        return res;
    }

    // 用队列实现，只不过是在 分行打印 的基础上将每次的 list 进行翻转后再加入到 res 中。
    public ArrayList<ArrayList<Integer>> printTreesInZigzag2(TreeNode root) {
        boolean reverse = false;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int count = queue.size();
            while (count-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                list.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (reverse) {
                Collections.reverse(list);
            }
            reverse = !reverse;
            if (list.size() == 0) {
                res.add(list);
            }
        }
        return res;
    }
}