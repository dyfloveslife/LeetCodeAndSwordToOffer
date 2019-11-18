package other;

import java.util.*;

class TreeNode<Integer> {
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
public class BinaryTreeOrder {
	// 前序遍历-递归
	public static ArrayList<Integer> preorderRecursively(TreeNode<Integer> treeNode){
		ArrayList<Integer> list = new ArrayList<>();
		if(treeNode == null) return list;
		list.add(treeNode.val);
		list.addAll(preorderRecursively(treeNode.left));
		list.addAll(preorderRecursively(treeNode.right));
		return list;
	}

	// 中序遍历-递归
	public static ArrayList<Integer> inorderRecursively(TreeNode<Integer> treeNode){
		ArrayList<Integer> list = new ArrayList<>();
		if(treeNode == null) return list;
		list.addAll(inorderRecursively(treeNode.left));
		list.add(treeNode.val);
		list.addAll(inorderRecursively(treeNode.right));
		return list;
	}

	// 后序遍历-递归
	public static ArrayList<Integer> postorderRecursively(TreeNode<Integer> treeNode){
		ArrayList<Integer> list = new ArrayList<>();
		if(treeNode == null) return list;
		list.addAll(postorderRecursively(treeNode.left));
		list.addAll(postorderRecursively(treeNode.right));
		list.add(treeNode.val);
		return list;
	}

	// 前序遍历-非递归
	public static List<Integer> preorderIteratively(TreeNode<Integer> treeNode){
		// stack 栈顶元素是 cur 的父节点
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> cur = treeNode;
		List<Integer> list = new LinkedList<>(); // 使用链表
		if(treeNode == null) return list;
		while(cur != null || !stack.isEmpty()){
			if(cur != null) {
				list.add(cur.val);
				stack.push(cur);
				cur = cur.left;
			}else {
				cur = stack.pop().right;
			}
		}
		return list;
	}

	// 中序遍历-非递归
	public static List<Integer> inorderIteratively(TreeNode<Integer> treeNode){
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> cur = treeNode;
		List<Integer> list = new LinkedList<>(); // 使用链表
		if(treeNode == null) return list;
		while(cur != null || !stack.isEmpty()){
			if(cur != null) {
				stack.push(cur);
				cur = cur.left;
			}else {
				list.add(cur.val);
				cur = stack.pop().right;
			}
		}
		return list;
	}

	// 后序遍历-非递归
	public static List<Integer> postorderIteratively(TreeNode<Integer> treeNode){
		//prevVisted用于区分是从左子树还是右子树返回的
		Stack<TreeNode<Integer>> stack = new Stack<>();
		TreeNode<Integer> cur = treeNode;
		TreeNode<Integer> prevVisted = null;
		List<Integer> list = new LinkedList<>();
		while(cur!=null || !stack.isEmpty()){
			if(cur!=null){
				stack.push(cur);
				cur = cur.left;
			}
			else{
				cur = stack.peek().right;
				if(cur!=null && cur!=prevVisted){
					stack.push(cur);
					cur = cur.left;
				}
				else{
					prevVisted = stack.pop();
					list.add(prevVisted.val);
					cur = null;
				}
			}
		}
		return list;
	}

	// 层次遍历
	public static List<Integer> levelorder(TreeNode<Integer> treeNode){
		Queue<TreeNode<Integer>> queue = new LinkedList<>();
		List<Integer> list = new LinkedList<>();
		TreeNode<Integer> temp = null;
		if(treeNode == null) return list;

		queue.offer(treeNode);  // 入队
		while(!queue.isEmpty()){
			temp = queue.poll(); // 出队
			list.add(temp.val);
			if(temp.left != null)
				queue.offer(temp.left);
			if(temp.right != null)
				queue.offer(temp.right);
		}
		return list;
	}

	/**
	 *      1
	 *       \
	 *        2
	 *       /
	 *      3
	 */
	public static void main(String[] args) {
		TreeNode<Integer> root = new TreeNode<>(1);
		root.right = new TreeNode<>(2);
		root.right.left = new TreeNode<>(3);
		ArrayList<Integer> list = preorderRecursively(root);
		System.out.println("preorderRecursively: " + list + "\t");

		List<Integer> list1 = preorderIteratively(root);
		System.out.println("preorderIteratively: " + list1 + "\t");

		List<Integer> list2 = levelorder(root);
		System.out.println("levelorder: " + list2);
	}
}