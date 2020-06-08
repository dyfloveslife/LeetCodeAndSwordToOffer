package LeetCodeSolution.DataStructure._08_LinkedList._109_ConvertSortedListToBinarySearchTree;

/*
 * 有序链表转换成二叉搜索树
 *
 * 题目描述：
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 思路：
 * 1. 由于链表是有序的，因此我们可以通过快慢指针的方式找到链表的中间节点，然后将中间节点设置为二叉搜索树的根；
 * 2. 然后再分别递归根的左右部分，以此不断地构成二叉搜索树；
 * 3. 还有一种办法，就是先将链表转换成数组，然后再找数组的中间元素，最后通过递归的方式分别对这两部分进行构建二叉搜索树。
 */
public class Solution {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // 时间复杂度：O(nlogn)，因为对于 n 个元素来说，每次都需要找到中心的元素
    // 空间复杂度：O(logn)，递归调用栈的空间就是树的高度，而平衡二叉树的高度为 O(logn)
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null){
            return null;
        }
        // head 和 null 分别表示当前子链表的头和尾
        return process(head, null);
    }

    private TreeNode process(ListNode head, ListNode tail) {
        if (head == tail) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        // 注意这里的判断条件，原先的方式是判断快指针是否等于 null，
        // 而这里的 tail 就相当于它的尾部，也就相当于 null，
        // 执行完 while 后，slow 会来到当前子链表的中间节点
        while (fast != tail && fast.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 注意 slow 指向的是中间节点，slow 将链表划分成两部分，
        // 然后再对这两部分分别进行递归处理
        TreeNode root = new TreeNode(slow.val);
        root.left = process(head, slow);
        root.right = process(slow.next, tail);

        return root;
    }

    // 时间复杂度：O(n)，因为将链表转换成了数组，查找中间元素的时间为 O(1)
    // 空间复杂度：O(n)，因为开辟了一个长度为 n 的数组
    public TreeNode sortedListToBST1(ListNode head) {
        if (head == null) {
            return null;
        }
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int[] nums = new int[len];
        cur = head;
        int index = 0;
        while (cur != null) {
            nums[index++] = cur.val;
            cur = cur.next;
        }

        return process1(nums, 0, nums.length);
    }

    private TreeNode process1(int[] nums, int start, int end) {
        if (start == end) {
            return null;
        }

        int middle = end + ((start - end) >> 1);
        TreeNode root = new TreeNode(nums[middle]);
        root.left = process1(nums, start, middle);
        root.right = process1(nums, middle + 1, end);
        return root;
    }
}

