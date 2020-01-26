package Other.BasicAlgorithm._30_HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/*
 * 哈希函数以及哈希表的使用
 */
public class Solution {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("duan", "25");

        System.out.println(map.containsKey("duan")); // true
        System.out.println(map.containsKey("you")); // false
        System.out.println("==================");

        System.out.println(map.get("duan")); // 25
        System.out.println(map.get("you")); // null
        System.out.println("==================");

        System.out.println(map.isEmpty()); // false
        System.out.println(map.size()); // 1
        System.out.println("==================");

        System.out.println(map.remove("duan")); // 25
        System.out.println(map.containsKey("duan")); // false
        System.out.println(map.get("duan")); // null
        System.out.println(map.isEmpty()); // true
        System.out.println(map.size()); // 0
        System.out.println("==================");

        map.put("duan", "25");
        System.out.println(map.get("duan")); // 25
        map.put("duan", "30");
        System.out.println(map.get("duan")); // 30
        System.out.println(map.size()); // 1
        System.out.println("==================");

        map.put("duan", "25");
        map.put("ha", "26");
        map.put("xi", "27");
        for (String key : map.keySet()) {
            System.out.println(key); // duan xi ha
        }
        System.out.println("==================");

        for (String value : map.values()) {
            System.out.println(value); // 25 27 26 无序
        }
        System.out.println("==================");

        map.clear();
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        map.put("D", "4");
        map.put("E", "5");
        map.put("F", "6");
        map.put("G", "7");
        map.put("H", "8");
        map.put("I", "9");
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ": " + value);
        }
        System.out.println("==================");

        // 不能在迭代 map 的时候使用 remove 方法

        // 如果要删除其它元素，可先将不删除的元素收集起来，然后再删除其它元素
        List<String> removeKeys = new ArrayList<>();
        for (Entry<String, String> entry : map.entrySet()) {
            if (!entry.getValue().equals("1")) {
                // 将不需要删除的保存起来
                removeKeys.add(entry.getKey());
            }
        }
        for (String removeKey : removeKeys) {
            map.remove(removeKey);
        }
        for (Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + ": " + value);
        }
            System.out.println("==================");
    }
}
