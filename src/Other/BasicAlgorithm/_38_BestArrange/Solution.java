package Other.BasicAlgorithm._38_BestArrange;

import java.util.Arrays;
import java.util.Comparator;

/*
 * 最多的宣讲场次
 *
 * 题目描述：
 * 一些项目需要占用一个会议室进行宣讲，会议室不能同时容纳两个项目进行宣讲。
 * 给定一个项目开始的时间和结束的时间，安排宣讲的同时要求会议室进行宣讲的场次最多。
 * 返回这个最多的宣讲场次。
 *
 * 思路：
 * 1. 看哪个项目结束的时间早；
 * 2. 选择最早结束的项目，然后淘汰掉因为该项目而不能做的项目；
 * 3. 然后再看哪个项目早结束，直至最后。
 */
public class Solution {

    public class Program {
        // 项目的开始与结束
        int start;
        int end;

        Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class ProgramComparator implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }
    }

    // cur 表示当前时刻
    public static int bestArrange(Program[] programs, int cur) {
        // 谁结束的时间早，谁就排在前面
        Arrays.sort(programs, new ProgramComparator());
        int res = 0;
        for (int i = 0; i < programs.length; i++) {
            // 由于已经将结束时间早的项目排在前面了，所以这里不断推进当前时刻
            // 没有进 if 的就是被淘汰的项目
            // 选择了一个项目，想要安排到该项目之后，则需要来到该项目的 end，即末尾
            if (cur <= programs[i].start) {
                res++;
                cur = programs[i].end;
            }
        }
        return res;
    }
}
