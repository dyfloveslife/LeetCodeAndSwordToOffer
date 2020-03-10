package Other.BasicDataStructure;

import org.junit.Test;

/**
 * 单链表
 */
public class ListListTest {

    /**
     * 定义链表结构
     */
    static class LikedList {
        Node head;

        /**
         * 定义节点结构
         */
        static class Node {
            int key;
            Node next;

            Node(int key) {
                this.key = key;
                this.next = null;
            }
        }

        /**
         * 遍历链表
         */
        public void printList() {
            Node last = head;
            while (last != null) {
                System.out.print(last.key + " ");
                last = last.next;
            }
        }

        /**
         * 在链表头部插入节点
         *
         * @param new_data 新插入的节点
         */
        public void insertHead(int new_data) {
            Node new_node = new Node(new_data);
            new_node.next = head;
            head = new_node;
        }

        /**
         * 在链表尾部插入节点
         *
         * @param new_data 新插入的节点
         */
        public void insertTail(int new_data) {
            Node new_node = new Node(new_data);
            if (head == null) {
                head = new Node(new_data);
                return;
            }

            Node last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new_node;
            new_node.next = null;
        }


        /**
         * 在给定的节点之后插入节点
         *
         * @param prev_node 给定的节点
         * @param new_data  新插入的节点
         */
        public void insertAfter(Node prev_node, int new_data) {
            if (prev_node == null) {
                System.out.println("The given previous node cannot be null.");
                return;
            }

            Node new_node = new Node(new_data);
            new_node.next = prev_node.next;
            prev_node.next = new_node;

        }

        /**
         * 删除值为 key 的节点
         *
         * @param key 待删除节点的值
         */
        public void deleteByKey(int key) {
            if (head == null) {
                System.out.println("The LinkedList is empty.");
                return;
            }

            Node temp = head;
            Node prev = null;

            // If head node itself holds the key to be deleted
            if (temp != null && temp.key == key) {
                head = temp.next; // Changed head
                return;
            }

            while (temp != null && temp.key != key) {
                prev = temp;
                temp = temp.next;
            }

            // If key was not present in linked list
            if (temp == null) return;

            prev.next = temp.next;
        }

        /**
         * 根据指定的位置删除节点
         *
         * @param position 要删除元素的位置
         */
        public void deleteByPos(int position) {
            // If linked list is empty
            if (head == null) {
                return;
            }

            Node temp = head;

            if (position == 0) {
                head = temp.next;
                return;
            }

            // Find previous node of the node to be deleted
            for (int i = 0; (i < position - 1) && temp != null; i++) {
                temp = temp.next;
            }

            // If position is more than number of nodes
            if (temp == null || temp.next == null) {
                return;
            }

            // Node temp->next is the node to be deleted
            Node next = temp.next.next;
            temp.next = next;
        }
    }


    @Test
    public void deleteByKeyTest() {
        LikedList list = new LikedList();
        list.insertTail(6);
        list.insertTail(9);
        list.insertTail(5);
        list.insertHead(1);
        list.insertHead(3);
        list.insertAfter(list.head, 8);
        System.out.println("Created Linked List:");
        list.printList();

        list.deleteByKey(6);

        System.out.println("\nLinked List after Deletion of 6:");
        list.printList();
    }

    @Test
    public void deleteByOperationTest() {
        LikedList list = new LikedList();
        list.insertTail(6);
        list.insertTail(9);
        list.insertTail(5);
        list.insertHead(1);
        list.insertHead(3);
        list.insertAfter(list.head, 8);
        System.out.println("Created Linked List:");
        list.printList();

        list.deleteByPos(3);

        System.out.println("\nLinked List after Deletion at position 3:");
        list.printList();
    }
}

