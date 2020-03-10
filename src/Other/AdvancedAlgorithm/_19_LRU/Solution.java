package Other.AdvancedAlgorithm._19_LRU;

import java.util.HashMap;

/*
 * Least Recently Used，最近最少使用，选择最近最久未使用的页面予以淘汰。
 *
 * 思路 1：
 * 1. 使用哈希表 + 双向链表；
 * 2. 在双向链表中，从尾部加入节点，头部是优先级低的，尾部是优先级高的；
 * 3. 对于 get(node) 操作，则直接将 node 移到尾部即可。
 *
 * 思路 2：
 * 0. 自己实现的双端链表中，对于节点而言，其包含一个 key 和一个 value，其中，key 和 HashMap 中的 key 是对应的；
 * 1. 还是使用 map + 双向链表；
 * 2. 最近使用的放在队头，久未使用的放在队尾；
 * 3. 在队头插入数据，删除的时候，将队尾久未使用的数据删除；
 * 4. 每次 get 数据的时候，要把该数据提到队头；
 * 5. 如果在容量满了的情况下，插入数据，则将队尾的数据删除，再将新的数据放进队头；
 * 6. 如果要覆盖数据，则数据修改后，也要将其提到队头。
 */
public class Solution {

    // 思路 1
    class Node {
        // 每个节点都包含 key、value、前驱指针 prev、后继指针 next
        int key;
        int val;
        Node next;
        Node prev;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoublyList {
        // 双端链表的头尾虚节点
        Node head;
        Node tail;
        int size;

        DoublyList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // 在链表头部添加 x 节点
        // 其实就是在 head 节点和第一个节点之间插入 x 节点
        public void addFirst(Node x) {
            // 注意：head 一开始是虚节点，将其设置为虚节点，便于操作
            x.next = head.next;
            x.prev = head;
            head.next.prev = x;
            head.next = x;
            size++;
        }

        // 删除链表中的 x 节点
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        // 删除链表中最后一个节点，并返回该节点
        public Node removeLast() {
            if (tail.prev == head) {
                return null;
            }

            // 注意：tail 是尾部的虚节点，真实的最后一个节点是 tail.prev
            Node last = tail.prev;
            remove(last);
            return last;
        }

        // 返回链表的长度
        public int size() {
            return size;
        }
    }

    class LRUCache {
        private HashMap<Integer, Node> map;
        private DoublyList cache;
        private int cap;

        public LRUCache(int cap) {
            this.cap = cap;
            map = new HashMap<>();
            cache = new DoublyList();
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }

            int val = map.get(key).val;
            // 提到前面来
            put(key, val);
            return val;
        }

        public void put(int key, int val) {
            Node x = new Node(key, val);

            if (map.containsKey(key)) {
                // 删除旧的，将新的节点插到头部
                cache.remove(map.get(key));
                cache.addFirst(x);
                // 更新 map 中对应的数据
                map.put(key, x);
            } else {
                if (cap == cache.size()) {
                    // 在插入之前，如果容量满了，则删除最后一个元素
                    Node last = cache.removeLast();
                    map.remove(last.key);
                }
                // 将新节点插入到头部
                cache.addFirst(x);
                map.put(key, x);
            }
        }
    }

    /* 思路 2：
    // 定义双向链表的节点
    public static class Node<K, V> {
        K key;
        V val;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // 定义双向链表以及需要实现的功能
    public static class DoubleLinkedList<K, V> {
        // 定义头尾指针
        Node<K, V> head;
        Node<K, V> tail;

        DoubleLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void addNode(Node<K, V> newNode) {
            if (newNode == null) {
                return;
            }
            // 在加入新节点之前，如果双向链表本身就是空的，则让新节点自己指向自己
            // 否则让新节点在尾部插入
            if (this.head == null) {
                this.head = newNode;
                this.tail = newNode;
            } else {
                this.tail.next = newNode;
                newNode.prev = this.tail;
                this.tail = newNode;
            }
        }

        // 将某节点移动到双向链表的尾部
        public void moveNodeToTail(Node<K, V> node) {
            // 如果尾节点已经是 node 了，就不用移动了好吧
            if (this.tail == node) {
                return;
            }
            // 如果当前节点是头节点
            if (this.head == node) {
                this.head = node.next;
                this.head.prev = null;
                // 普通节点（中间节点）
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            // 新到尾部的节点 prev 应该指向之前的老尾部
            node.prev = this.tail;
            node.next = null;
            // 对之前老尾部的指针进行更新
            this.tail.next = node;
            this.tail = node;
        }

        // 移除头部并将其返回
        public Node<K, V> removeHead() {
            if (this.head == null) {
                return null;
            }
            Node<K, V> res = this.head;
            // 只有一个节点的情况
            if (this.head == this.tail) {
                this.head = null;
                this.tail = null;
            } else {
                this.head = res.next;
                res.next = null;
                this.head.prev = null;
            }
            return res;
        }
    }

    // 定义两个 map，通过 key 查 value，以及通过 value 查 key
    public static class MyCache<K, V> {
        HashMap<K, Node<K, V>> keyNodeMap;
        DoubleLinkedList<K, V> doubleLinkedList;
        int capacity;

        MyCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("Should be more than 0.");
            }
            this.keyNodeMap = new HashMap<>();
            this.doubleLinkedList = new DoubleLinkedList<>();
            this.capacity = capacity;
        }

        public V get(K key) {
            if (this.keyNodeMap.containsKey(key)) {
                Node<K, V> res = this.keyNodeMap.get(key);
                // 由于进行了 get 操作，所以需要将 key 移动到尾部，让其优先级变高
                this.doubleLinkedList.moveNodeToTail(res);
                return res.val;
            }
            return null;
        }

        public void set(K key, V value) {
            if (this.keyNodeMap.containsKey(key)) {
                // 将它的值查出来，改成新的 value，再将其移动到尾部
                Node<K, V> node = this.keyNodeMap.get(key);
                node.val = value;
                this.doubleLinkedList.moveNodeToTail(node);
            } else {
                Node<K, V> newNode = new Node<>(key, value);
                this.keyNodeMap.put(key, newNode);
                this.doubleLinkedList.addNode(newNode);
                // 再添加完 newNode 之后，如果超过了最大容量，则将最近最少使用的节点移除
                if (this.keyNodeMap.size() == this.capacity + 1) {
                    this.removeMostUnused();
                }
            }
        }

        public void removeMostUnused() {
            Node<K, V> removeNode = this.doubleLinkedList.removeHead();
            this.keyNodeMap.remove(removeNode.key);
        }
    }

    public static void main(String[] args) {
        MyCache<String, Integer> myCache = new MyCache<>(3);
        myCache.set("a", 1);
        myCache.set("b", 2);
        myCache.set("c", 3);
        System.out.println(myCache.get("b"));
        System.out.println(myCache.get("a"));
        myCache.set("d", 4);
        System.out.println(myCache.get("d"));
        System.out.println(myCache.get("c"));
    }
    */
}
