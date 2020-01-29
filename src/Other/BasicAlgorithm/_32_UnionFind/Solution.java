package Other.BasicAlgorithm._32_UnionFind;

import java.util.HashMap;
import java.util.List;

/*
 * 并查集的使用
 *
 * 并查集的查找与合并操作
 *
 * 时间复杂度：
 * 假设元素的数量为 N，如果查询的次数与合并的次数逼近了 O(N) 及其以上的话，
 * 那么单次查询和单次合并的平均次数就是 O(1) 的。这得益于并查集的优化操作，即打平操作，下次查的时候会很快。
 */
public class Solution {

    class Node {

    }

    public static class UnionFindSet {
        // kye 表示孩子节点，value 表示父节点
        public HashMap<Node, Node> fatherMap;
        // 某一个节点 node，它所在的集合一共有多少节点
        public HashMap<Node, Integer> sizeMap;

        public UnionFindSet() {
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        // 初始化操作
        private void makeSets(List<Node> nodes) {
            fatherMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                // 当只有一个节点的时候，当前节点指向自己
                fatherMap.put(node, node);
                // 当前节点的代表节点也是自己
                sizeMap.put(node, 1);
            }
        }

        // 返回某个节点的代表节点，同时执行打平操作
        // 找到代表节点之后，会将代表节点传递给每个节点，从而每个节点都指向代表节点，即实现了打平操作
        private Node findHead(Node node) {
            Node father = fatherMap.get(node);
            if (father != node) {
                father = fatherMap.get(father);
            }
            fatherMap.put(node, father);
            return father;
        }

        // 根据查找某两个节点的代表节点是不是同一个，来判断这两个节点是否属于同一集合
        public boolean isSameSet(Node a, Node b) {
            return findHead(a) == findHead(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }

            Node aHead = findHead(a);
            Node bHead = findHead(b);
            // 如果不在同一个集合里的话，执行合并操作
            if (aHead != bHead) {
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                // 如果 a 比 b 的数量少，则将 a 中所有节点挂到 b 的下面
                if (aSetSize <= bSetSize) {
                    fatherMap.put(aHead, bHead);
                    // 此时，统计集合 b 的大小
                    sizeMap.put(bHead, aSetSize + bSetSize);
                } else {
                    fatherMap.put(bHead, aHead);
                    sizeMap.put(aHead, aSetSize + bSetSize);
                }
            }
        }
    }
}
