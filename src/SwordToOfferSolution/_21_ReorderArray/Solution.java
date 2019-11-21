package SwordToOfferSolution._21_ReorderArray;

public class Solution {
	public void reorderArray(int[] array) {
		int oddCount = 0;
		for (int x : array)
			if (!isEven(x)) oddCount++;
		int[] copyArray = array.clone();
		int i = 0, j = oddCount;
		for (int num : copyArray) {
			if (num % 2 == 1)
				array[i++] = num;
			else
				array[j++] = num;
		}
	}

	private boolean isEven(int x) {
		return x % 2 == 0;
	}

	public static void main(String[] args) {
		Solution array = new Solution();
		int[] a = new int[]{1, 2, 3, 4, 5};
		array.reorderArray(a);
		for (int num : a) {
			System.out.print(num);
		}
	}
}
