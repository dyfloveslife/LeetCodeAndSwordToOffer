package Other.BasicAlgorithm._31_RandomPool;

import java.util.HashMap;

/*
 * 设计一种具有如下三种功能的结构：
 * 1) insert(key)：将某个 key 加入到该结构中，做到不重复加入；
 * 2) delete(key)：将原本在结构中的某个 key 移除；
 * 3) getRandom()：等概率随机返回结构中的任何一个 key；
 * 4) 要求以上方法的时间复杂度都是 O(1)。
 *
 * 思路：
 * 1. 使用两个哈希表，当表 1 存(A, 0)的时候，表 2 存 (0, A)；
 * 2. 根据 size 使用 Math.Random() 的方式从表 2 中取值；
 * 3. 删除记录的时候，应该保证 map 中的记录是连续的，所以应该将最后一条记录填充到中间被删除的记录中；
 */
public class Solution {
    class Pool<K> {
        private HashMap<K, Integer> keyIndexMap;
        private HashMap<Integer, K> indexKeyMap;
        private int size;

        public Pool() {
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
            this.size = 0;
        }

        // 插入操作
        public void insert(K key) {
            if (!keyIndexMap.containsKey(key)) {
                keyIndexMap.put(key, size);
                indexKeyMap.put(size++, key);
            }
        }

        // 删除操作
        public void delete(K key) {
            if (keyIndexMap.containsKey(key)) {
                int deleteIndex = keyIndexMap.get(key);
                int lastIndex = --size;
                K lastKey = indexKeyMap.get(lastIndex);
                keyIndexMap.put(lastKey, deleteIndex);
                indexKeyMap.put(deleteIndex, lastKey);
                keyIndexMap.remove(key);
                indexKeyMap.remove(lastIndex);
            }
        }

        // 等概率返回 key
        public K getRandom() {
            if (size == 0) {
                return null;
            }
            // 0 ~ size - 1
            int randomIndex = (int) (Math.random() * size);
            return indexKeyMap.get(randomIndex);
        }
    }
}
