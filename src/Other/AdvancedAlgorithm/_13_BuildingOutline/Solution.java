package Other.AdvancedAlgorithm._13_BuildingOutline;

import java.util.*;
import java.util.Map.Entry;

/*
 * LeetCode-218 天际线问题
 *
 * 思路：
 * 使用 TreeMap。
 */
public class Solution {
    public static class Node {
        boolean isUp;
        int posi;
        int h;

        Node(boolean bORe, int position, int height) {
            this.isUp = bORe;
            this.posi = position;
            this.h = height;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node a, Node b) {
            if (a.posi != b.posi) {
                return a.posi - b.posi;
            }
            // 如果一个位置既有上去，又有下来，则上的那个排在下的那个前面
            if (a.isUp != b.isUp) {
                return a.isUp ? -1 : 1;
            }
            return 0;
        }
    }

    public static List<List<Integer>> buildOutline(int[][] buildings) {
        // 每一个大楼生成两个信息，包括 (位置, 高度, 上)、(位置, 高度, 下)
        Node[] nodes = new Node[buildings.length * 2];
        for (int i = 0; i < buildings.length; i++) {
            nodes[i * 2] = new Node(true, buildings[i][0], buildings[i][2]);
            nodes[i * 2 + 1] = new Node(false, buildings[i][1], buildings[i][2]);
        }
        // 按照位置排序
        Arrays.sort(nodes, new NodeComparator());
        // key 表示高度，value 表示高度出现的次数
        TreeMap<Integer, Integer> htMap = new TreeMap<>();
        // 收集每个位置上的最大高度，使用 pmMap 做出所有的轮廓线
        // key 表示每个位置
        TreeMap<Integer, Integer> pmMap = new TreeMap<>();
        for (int i = 0; i < nodes.length; i++) {
            // 如果当前的高度是 上
            if (nodes[i].isUp) {
                // 再看是不是第一次出现，如果是第一次出现
                if (!htMap.containsKey(nodes[i].h)) {
                    htMap.put(nodes[i].h, 1);
                    // 如果不是第一次出现，则高度就变成之前的高度在加一
                } else {
                    htMap.put(nodes[i].h, htMap.get(nodes[i].h) + 1);
                }
                // 如果当前的高度是 下
            } else {
                if (htMap.containsKey(nodes[i].h)) {
                    // 如果当前高度的次数已经是 1 了，则再进行 下 的话，直接移除该高度
                    if (htMap.get(nodes[i].h) == 1) {
                        htMap.remove(nodes[i].h);
                        // 否则就是之前高度减一
                    } else {
                        htMap.put(nodes[i].h, htMap.get(nodes[i].h) - 1);
                    }
                }
            }
            // 收集每个位置上的最大高度
            if (htMap.isEmpty()) {
                pmMap.put(nodes[i].posi, 0);
            } else {
                pmMap.put(nodes[i].posi, htMap.lastKey());
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;
        int height = 0;
        for (Entry<Integer, Integer> entry : pmMap.entrySet()) {
            // curPosition 是从小到大的
            int curPosition = entry.getKey();
            int curMaxHeight = entry.getValue();
            // 如果之前的高度不等于当前的最大高度，则开始产生轮廓线
            if (height != curMaxHeight) {
                if (height != 0) {
                    List<Integer> newRecord = new ArrayList<>();
                    newRecord.add(start);
                    newRecord.add(curPosition);
                    newRecord.add(height);
                    res.add(newRecord);
                }
                // 由于之前的高度是 0，所以不足以构成轮廓线
                start = curPosition;
                height = curMaxHeight;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {2, 9, 10},
                {3, 7, 15},
                {5, 12, 12},
                {15, 20, 10},
                {19, 24, 8}};
        System.out.println(buildOutline(arr));
    }
}
