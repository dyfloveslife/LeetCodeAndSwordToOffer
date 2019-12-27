package SwordToOfferSolution._62_LastNumberInCircle;

/*
 * 圆圈中最后剩下的数字
 * 详见：https://dyfloveslife.github.io/2019/12/27/offer-Josephus
 *
 * 思路：
 * 1. 从下往上计算（反过来计算），每删除一个数，然后链表就重新编号。由于最后所剩下的节点数只有一个数，所以这个节点的编号就是 1；
 * 2. 现在我们想要找到一个公式，从最底下往上推出初始的状态；
 * 3. 将剃刀函数往上以及往右进行移动，即 编号 =（报数 - 1）% i + 1，其中 i 表示链表的长度；
 * 4. 旧号长度是 i，由于删除了一个节点，则变成了新号长度是 i - 1 的；
 *     旧号的原来的节点是 s，则在新号中被删除了；
 *     旧号中原来的节点是 s + 1，则新号中的节点就是 1；
 *     旧号中原来的节点是 s + 2，则新号中的节点就是 2；
 *     旧号中原来的节点是 s - 1，则新号中的节点就是 i - 1；
 * 5. 旧号：1  2  3  4  5  6  7  8  9
 *               (s)
 *    新号：7  8  X  1  2  3  4  5  6
 *
 *    其中，新号 1 对应旧号 s+1=4，新号 2 对应旧号 s+2=5，新号 6 对应旧号 i=9，......，新号 i-s+1=9-3+1=7 对应旧号 1，新号 i-1=9-1=8 对应 s-1=2；
 * 6. 最后推导的公式：
 *     s = (m - 1) % i + 1
 *     旧 = (新 - 1 + s) % i + 1
 */
public class Solution {
    public static class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    // 用链表模拟环
    public static Node lastRemaining_Solution(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }

        // 得到链表总长度
        Node cur = head.next;
        int temp = 1;
        while (cur != head) {
            temp++;
            cur = cur.next;
        }

        // 得到最终存活节点的位置
        temp = getLive(temp, m);
        while (--temp != 0) {
            // 移动 head，找到最终存活的节点
            head = head.next;
        }
        // 让该存活的节点自己指向自己
        head.next = head;
        return head;
    }

    private static int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m -  1) % i + 1;
    }

    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.val + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.val+ " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.val);
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = head1;
        printCircularList(head1);
        head1 = lastRemaining_Solution(head1, 3);
        printCircularList(head1);
    }
}
