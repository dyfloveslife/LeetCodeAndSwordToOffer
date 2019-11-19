package _15_NumberOf1InBinary;

public class Solution {
	private static int numberOf1(int n) {
		int count = 0;
		while (n != 0){
			count++;
			n = n & (n - 1);
		}
		return count;
	}

	public static void main(String[] args) {
		int i = numberOf1(7);
		System.out.println(i);
	}
}
