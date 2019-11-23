package SwordToOfferSolution._35_CopyComplexList;

import java.util.HashMap;

/**
 * 复杂链表的复制
 * 思路一：使用哈希表
 * 开一个哈希表，key 存储当前节点， value 存储由当前节点复制过来的节点，即 A->A'；
 * 然后后移，将下一个节点以及其复制节点也存储到哈希表中，即 B->B'；
 * 重复此操作后，就有了一个节点及其拷贝节点之间的关系；
 *
 * 关系确定了之后，如何确定拷贝节点的 next 与 random 的指针呢？
 * 1. 我们知道原链表 A 的 next 是 B，用 B 去查哈希表，得到 B'，所以 A' 的 random 就指向 B'；
 * 2. 原链表 A 的 random 是 C，用 C 去查哈希表，得到 C'，所以 A' 的 random 就指向 C'。
 * 重复以上操作即可。
 */
class RandomListNode {
    int label = 0;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class Solution {
    // 方法一：使用额外空间：哈希表
    public RandomListNode clone(RandomListNode pHead){
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
            // 当前节点的拷贝节点的 next 指针，指向的是 当前节点的下一个节点的拷贝节点。
            map.get(cur).next = map.get(cur.next);
            // 当前节点的拷贝节点的 random 指针，指向的是 当前节点的 random 节点的拷贝节点。
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 返回的是原链表头节点的拷贝节点
        return map.get(pHead);
    }
}
