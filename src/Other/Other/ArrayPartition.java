package Other.Other;

public class ArrayPartition {
	public static int arrayPairSum(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = 0; j < nums.length - i - 1; j++) {
				if (nums[j] > nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
				}
			}
		}

		for (int num : nums) {
			System.out.print(num + " ");
		}
		System.out.println();
		int sum = 0;
		for (int k = 0; k < nums.length - 1; k++) {
			if ((k % 2) == 0) sum += nums[k];
		}
		return sum;
	}

	public static void main(String[] args) {
		int[] arr = {1, 4, 3, 2};
		int i = arrayPairSum(arr);
		System.out.println(i);
	}

}
