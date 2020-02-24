package Other.AdvancedAlgorithm._18_MaxHappy;

import java.util.ArrayList;
import java.util.List;

/*
 * 最大的活跃值
 */
public class Solution {

    public class Node {
        int actValue;
        List<Node> nexts;

        Node(int actValue) {
            this.actValue = actValue;
            this.nexts = new ArrayList<>();
        }
    }

    public class ReturnType {
        int come;
        int noCome;

        ReturnType(int come, int noCome) {
            this.come = come;
            this.noCome = noCome;
        }
    }

    public int getMaxValue(Node head) {
        ReturnType data = process(head);
        return Math.max(data.come, data.noCome);
    }

    public ReturnType process(Node head) {
        int come = head.actValue;
        int noCome = 0;
        for (int i = 0; i < head.nexts.size(); i++) {
            Node next = head.nexts.get(i);
            ReturnType nextData = process(head);
            come += nextData.noCome;
            noCome += Math.max(nextData.come, nextData.noCome);
        }
        return new ReturnType(come, noCome);
    }
}
