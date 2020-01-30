package Other.BasicAlgorithm._34_TrieTree;

/*
 * 前缀树
 */
public class Solution {
    public static class TrieNode {
        // 到达过当前节点的次数
        public int path;
        // 有多少个字符串是以当前节点结尾的
        public int end;
        // 包含 a 到 z 条路
        public TrieNode[] nexts;

        public TrieNode() {
            path = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }

    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }

            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                // 如果当前节点没有走过当前字母的路，则新建一个节点
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                // node 后移
                node = node.nexts[index];
                node.path++;
            }
            // 如果到了结尾的话，将 end 进行增加
            node.end++;
        }

        // 查找 word 在前缀树中出现的次数
        public int search(String word) {
            if (word == null) {
                return 0;
            }

            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                // 如果没插入过，则返回 0
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i] - 'a';
                    // 如果某个节点的 path 已经是 0 了，则将该节点后面的所有节点释放，然后该节点指向 null 即可
                    if (--node.nexts[index].path == 0) {
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        // 查询有多少个以某个字符串的前缀开始的字符串的数量
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }

            char[] chars = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("duan")); // 0

        trie.insert("duan");
        System.out.println(trie.search("duan")); // 1

        // 这里删除了 duan 之后，查询结果返回的仍是 1
        // 是因为 duan 在遇到第一个 d 的时候，判断如果 path 置为 0 的情况时候为 null，就直接 return，
        // 但 duan 的最后一个字符的 end 仍是 1
        trie.delete("duan");
        System.out.println(trie.search("duan")); // 1

        trie.insert("duan");
        trie.insert("duan");
        trie.delete("duan");
        System.out.println(trie.search("duan")); // 2

        trie.delete("duan");
        System.out.println(trie.search("duan")); // 2

        trie.delete("duan");
        System.out.println(trie.search("duan")); // 1

        trie.insert("duana");
        trie.insert("duanac");
        trie.insert("duanab");
        trie.insert("duanad");
        trie.delete("duana");
        System.out.println(trie.search("duana")); // 0

        System.out.println(trie.prefixNumber("duan")); // 4
    }
}
