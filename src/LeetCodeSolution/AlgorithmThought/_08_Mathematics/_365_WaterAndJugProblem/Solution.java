package LeetCodeSolution.AlgorithmThought._08_Mathematics._365_WaterAndJugProblem;

/*
 * 水壶问题
 *
 * 题目描述：
 * 有两个容量分别为 x 升和 y 升的水壶以及无限多的水。
 * 请判断能否通过使用这两个水壶，从而可以得到恰好 z 升的水？
 * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z 升水。
 *
 * 思路：
 * 1. 如果满足： z 能够整除 x 和 y 的最大公约数，则 x 和 y 就能取得 z 升水；
 * 2. 根据裴蜀定理，对于任何整数 a、b 和 m，关于未知数 x 和 y 的裴蜀等式为：
 *    ax + by = m
 *    当有整数解时，当且仅当 m 是 a 和 b 的最大公约数 d 的倍数。
 */
public class Solution {
    public static boolean canMeasureWater(int x, int y, int z) {
        // 两个水壶都装满了，此时还达不到 z 升水，则返回 false
        // 说明给的两个水壶太小了，我把两个水壶的水都装满，也达不到 z 升水的要求
        if (x + y < z) {
            return false;
        }

        // 分为三种情况：
        // 情况 1：对于 x 升水的水壶，它正好能装满，而对于 y 升水的水壶，它正好没有水；
        // 情况 2：对于 y 升水的水壶，它正好能装满，而对于 x 升水的水壶，它正好没有水；
        // 情况 3：两个水壶中的水在装满的情况下，正好达到了 z 升水的要求；
        // 以上情况只要满足其一，即可返回 true
        if (x == z || y == z || x + y == z) {
            return true;
        }

        // 如果 z 能够整除 x 和 y 的最大公约数，则能够满足达到 z 升水的要求
        return (z % gcd(x, y)) == 0;
    }

    // 此方法用于求解任意两个数的最大公约数
    // 例如 2 和 6 的最大公约数是 2
    // 6 和 8 的最大公约数是 2
    // 3 和 5 的最大公约数是 1
    private static int gcd(int x, int y) {
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return x;
    }

    public static void main(String[] args) {
        System.out.println(canMeasureWater(3, 5, 4)); // true
        System.out.println(canMeasureWater(2, 6, 5)); // false
    }
}
