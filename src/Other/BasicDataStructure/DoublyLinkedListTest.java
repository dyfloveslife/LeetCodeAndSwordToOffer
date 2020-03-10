package Other.BasicDataStructure;

import org.junit.Test;

/**
 * 双向链表
 */
public class DoublyLinkedListTest {

    class DLL {
        Node head;

        class Node {
            int val;
            Node prev;
            Node next;

            Node(int val) {
                this.val = val;
            }
        }

        /**
         * 在双向链表的头部插入节点
         *
         * @param new_data 待插入的节点
         */
        public void insertAtFront(int new_data) {
            Node new_node = new Node(new_data);

            new_node.next = head;
            new_node.prev = null;

            if (head != null) {
                head.prev = new_node;
            }

            head = new_node;
        }

        /**
         * 在给定的节点之后插入新节点
         *
         * @param prev_node 给定的节点
         * @param new_data  待插入的节点
         */
        public void insertAfter(Node prev_node, int new_data) {
            if (prev_node == null) {
                System.out.println("The given previous node cannot be NULL.");
                return;
            }

            Node new_node = new Node(new_data);

            new_node.next = prev_node.next;
            prev_node.next = new_node;
            new_node.prev = prev_node;

            if (new_node.next != null) {
                new_node.next.prev = new_node;
            }
        }

        /**
         * 在双向链表的尾部插入新节点
         *
         * @param new_data 待插入的新节点
         */
        public void insertAtTail(int new_data) {
            Node new_node = new Node(new_data);
            new_node.next = null;

            // If the Linked List is empty, then make the new node as head
            if (head == null) {
                new_node.prev = null;
                head = new_node;
                return;
            }

            Node last = head;
            while (last.next != null) {
                last = last.next;
            }

            last.next = new_node;
            new_node.prev = last;
        }

        /**
         * 从给定的节点开始，打印链表内容
         *
         * @param node 给定的节点
         */
        public void printDLL(Node node) {
            Node last = null;
            System.out.println("Traversal in forward direction");
            while (node != null) {
                System.out.print(node.val + " ");
                last = node;
                node = node.next;
            }
            System.out.println();

            System.out.println("Traversal in reverse direction");
            while (last != null) {
                System.out.print(last.val + " ");
                last = last.prev;
            }
        }

        /**
         * 删除双向链表中的一个节点
         *
         * @param head_ref 指向头节点的指针
         * @param del      待删除的节点
         */
        public void deleteNode(Node head_ref, Node del) {
            if (head == null || del == null) {
                return;
            }

            if (head == del) {
                head = del.next;
            }

            if (del.next != null) {
                del.next.prev = del.prev;
            }

            if (del.prev != null) {
                del.prev.next = del.next;
            }
            return;
        }
    }


    @Test
    public void insertAtFrontTest() {
        DLL dll = new DLL();
        dll.insertAtTail(6);
        dll.insertAtFront(7);
        dll.insertAtFront(1);
        dll.insertAtTail(4);
        dll.insertAfter(dll.head.next, 8);

        System.out.println("Created DLL is:");
        dll.printDLL(dll.head);
    }

    @Test
    public void deleteNodeTest() {
        DLL dll = new DLL();
        dll.insertAtTail(6);
        dll.insertAtFront(7);
        dll.insertAtFront(1);
        dll.insertAtTail(4);
        dll.insertAfter(dll.head.next, 8);

        System.out.println("Created DLL is:");
        dll.printDLL(dll.head);
        System.out.println();

        dll.deleteNode(dll.head, dll.head);
        System.out.println("\nList after deleting first node: ");
        dll.printDLL(dll.head);
        System.out.println();

        dll.deleteNode(dll.head, dll.head.next);
        System.out.println("\nList after deleting middle node: ");
        dll.printDLL(dll.head);
    }
}
