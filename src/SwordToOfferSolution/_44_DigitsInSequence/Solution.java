package SwordToOfferSolution._44_DigitsInSequence;

/*
 * 数字序列中的某一位数字
 * 思路：
 * https://dyfloveslife.github.io/2019/11/29/offer-DigitsInSequence/
 */
public class Solution {
    public int digitsInSequence(int arr[], int n) {
        if (n < 0) {
            return -1;
        }
        if (n <= 9) {
            return n;
        }
        int index = 0;
        for (int i = 1; i <= 9; i++) {
            if (arr[i] >= n) {
                index = i;
                break;
            }
        }
        n = n - arr[index - 1];
        int temp1 = n / index;
        int temp2 = n % index;
        int temp3 = (int) (temp1 + Math.pow(10, index - 1));
        return (int) (temp3 / Math.pow(10, index - temp2 - 1)) % 10;
    }

    public static void main(String[] args) {
        int[] arr = {0, 10, 190, 2890, 38890, 488890, 5888890, 68888890, 788888890};
        Solution solution = new Solution();
        int i = solution.digitsInSequence(arr, 1001);
        System.out.println(i);
    }
}
