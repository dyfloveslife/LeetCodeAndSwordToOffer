package other;

import java.util.HashMap;

/**
 * 1.给定一个数组，值全是正数，请返回累加和为给定值 k 的最长子数组长度。
 * 2.给定一个数组，值可以为正、负和0，请返回累加和为给定值 k 的最长子数组长度。
 */
public class GetMaxLength {
	public static int getMaxLength1(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k < 0) return 0;

		int left = 0;
		int right = 0;
		int sum = arr[0];
		int len = 0;
		while (right < arr.length) {
			if (sum == k) {
				len = Math.max(len, right - left + 1);
				sum -= arr[left++];
			} else if (sum < k) {
				right++;
				if (right == arr.length) {
					break;
				}
				sum += arr[right];
			} else {
				sum -= arr[left++];
			}
		}
		return len;
	}

	public static int getMaxLength2(int[] arr, int k) {
		if (arr == null || arr.length == 0) return 0;
		int len = 0;
		int sum = 0;

		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, -1);
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if (map.containsKey(sum - k)) len = Math.max(len, i - map.get(sum - k));
			if (!map.containsKey(sum)) map.put(sum, i);
		}
		return len;
	}

	public static void main(String[] args) {
		int[] arr = {7, 3, 2, 1, 1, -6, 7};
		int k = 7;
		System.out.println(getMaxLength2(arr, k));
	}
}
