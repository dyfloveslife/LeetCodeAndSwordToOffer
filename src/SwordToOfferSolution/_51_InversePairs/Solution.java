package SwordToOfferSolution._51_InversePairs;

import java.util.Arrays;

/*
 * 数组中的逆序对的数量
 *
 */
public class Solution {
    public static void main(String[] args) {
        int[] help = {-10, 1, 1, 3, 4, 5, 6, 7, 12};
        int[] arr = new int[help.length];
        for (int i = 0; i< arr.length; i++) {
            arr[i] = help[i];
        }
        System.out.println(Arrays.toString(help));
        System.out.println(Arrays.toString(arr));
    }

}
