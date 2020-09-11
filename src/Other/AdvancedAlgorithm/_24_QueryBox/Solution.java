package Other.AdvancedAlgorithm._24_QueryBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * 查询给定范围内的个数
 *
 * 题目描述：
 * 给定数组 [3, 2, 2, 3, 1]，同时给定一个查询元组 (0, 3, 2) ，该元组表示查询从索引 0 到 3 范围内中，含有多少个数字 2。
 * 假设给定一个数组，对这个数组的查询非常频繁，也就是有多个元组，请返回所有元组的查询结果。
 *
 * 思路：
 * 1. 暴力的方法就是每当拿到一个元组的时候，根据索引的范围，我每次都在给定的数组中遍历一遍，从而统计元素的数量；
 * 2. 除了暴力以外，还可以使用 HashMap 的方法；
 * 3. HashMap 的 key 存储当前元素，而 value 存储一个 List，这个 List 中包含了指向所有 key 的索引；
 * 4. 例如给定数组 [3, 1, 2, 2, 3, 1, 3, 2, 1]
 *       其索引为  [0, 1, 2, 3, 4, 5, 6, 7, 8]
 * 5. 那么 HashMap 的映射结构为：
 *       3 -> [0, 4, 6]
 *       1 -> [1, 5, 8]
 *       2 -> [2, 3, 7]
 * 6. 对于一个给定的查询元组 (2, 6, 3) 来说，我们首先找到 key 为 3 的 List，即 [0, 4, 6]，由于该 List 是已经排好序的，因此
 *    可以直接使用二分的方式进行查询即可。
 * 7. 因此，该题的技巧在于：建立预处理结构 map。
 */
public class Solution {

    static class QueryBox {
        private HashMap<Integer, List<Integer>> map;

        // 根据数组 nums，初始化 map
        public QueryBox(int[] nums) {
            map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (!map.containsKey(nums[i])) {
                    map.put(nums[i], new ArrayList<>());
                }
                map.get(nums[i]).add(i);
            }
        }

        // 查询索引从 left 到 right 范围内，含有 val 元素的个数
        public int query(int left, int right, int val) {
            // 如果数组中没有 val 这个值，那么返回 -1
            if (!map.containsKey(val)) {
                return -1;
            }

            // 例如给定元组 (2, 6, 3)
            // 而通过 3 查询到的 List 为 [0, 4, 5, 7, 8, 11]
            // 那么就需要使用二分的方式，在 List 中找到在 2 到 6 范围内的值，也就是找到 4 和 5
            // 如何利用二分的方式进行高效查找呢？
            // 假如在 List 中，小于 2 的元素有 a 个，小于 7 的元素有 b 个，那么我们直接用 b 减去 a，就可以知道最终的结果
            List<Integer> indexArr = map.get(val);
            // 查询小于 left 的下标个数
            int a = countLess(indexArr, left);
            // 查询小于 right + 1 的下标个数
            int b = countLess(indexArr, right + 1);
            return b - a;
        }

        // 在一个有序数组中，查询小于 limit 元素的个数
        private int countLess(List<Integer> indexArr, int limit) {
            int left = 0;
            int right = indexArr.size() - 1;
            // 这里设置为 -1 也是有讲究的，如果 indexArr 中没有小于 limit 的元素，
            // 那么 countLess 最终会返回 0，也就是查询到了 0 个小于 limit 元素的个数
            int mostRight = -1;

            // 注意这里使用了 <=，因此在 if-else 条件进行判断的时候，也要相应的加 1 或者减 1
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (indexArr.get(mid) < limit) {
                    mostRight = mid;
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return mostRight + 1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 2, 3, 1, 3, 2, 1};
        QueryBox queryBox = new QueryBox(nums);
        System.out.println(queryBox.query(2, 6, 3));
    }
}
