package SwordToOfferSolution._35_CopyComplexList;

import java.util.HashMap;

/*
 * 复杂链表的复制
 *
 * 题目描述：
 * 请实现函数用于复制一个复杂链表。
 * 在复杂链表中，每个结点除了有一个 m_pNext 指针指向下一个结点外，还有一个 m_pSibling 指向链表中的任意结点或者 nullptr。
 *
 * 思路一：使用哈希表
 * 1. 开一个哈希表，key 存储当前节点， value 存储由当前节点复制过来的节点，即 A->A'；
 * 2. 然后后移，将下一个节点以及其复制节点也存储到哈希表中，即 B->B'；
 * 3. 重复此操作后，就有了一个节点及其拷贝节点之间的关系；
 *
 * 关系确定了之后，如何确定拷贝节点的 next 与 random 的指针呢？
 * 1. 我们知道原链表 A 的 next 是 B，用 B 去查哈希表，得到 B'，所以 A' 的 random 就指向 B'；
 * 2. 原链表 A 的 random 是 C，用 C 去查哈希表，得到 C'，所以 A' 的 random 就指向 C'。
 * 重复以上操作即可。
 *
 * 思路二：
 * 1. 将当前节点的拷贝节点放在当前节点的后面，也就是说放在下一个节点之前。
 * 2. 由于 A 的 random 是 C，那我们直接找到 C 的 next，即 C'，让 A' 的 random 指向 C' 即可。
 * 3. 重复以上操作后，再将原链表和复制后的链表进行分离即可。
 */

public class Solution {
    class RandomListNode {
        int label = 0;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    // 方法一：使用额外空间：哈希表
    public RandomListNode clone1(RandomListNode pHead) {
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = pHead;
        // 在复制节点的同时将 每个节点 以及 每个节点对应的拷贝节点 存储到哈希表中
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = pHead;
        // 指定拷贝后链表中每个节点的 next 和 random
        while (cur != null) {
            // 当前节点的拷贝节点的 next 指针，指向的是 当前节点的下一个节点的拷贝节点
            map.get(cur).next = map.get(cur.next);
            // 当前节点的拷贝节点的 random 指针，指向的是 当前节点的 random 节点的拷贝节点
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 返回的是原链表头节点的拷贝节点
        return map.get(pHead);
    }

    // 方法二：将每个节点的拷贝节点放在其节点之后，类似于 A->A'->B->B'
    public RandomListNode clone2(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }

        // 复制链表的同时，将复制后的节点放到原节点之后，只关心 next，不关系 random
        RandomListNode cur = pHead;
        RandomListNode next = null;
        while (cur != null) {
            next = cur.next;
            // 原节点指向复制后的节点
            cur.next = new RandomListNode(cur.label);
            // 复制后的节点指向下一个新的节点
            cur.next.next = next;
            // cur 移动到该新的节点
            cur = next;
        }
        // 设置每个节点的 random 指针
        cur = pHead;
        RandomListNode curCopy = null;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        // 将两个链表进行分离
        cur = pHead;
        //不能设置为 cur.next，因为 cur 是变化的，而 head 是不变的
        RandomListNode res = pHead.next;
        while (cur != null) {
            next = cur.next.next;
            curCopy = cur.next;
            // 先连接原链表
            cur.next = next;
            // 再连接拷贝链表
            curCopy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
