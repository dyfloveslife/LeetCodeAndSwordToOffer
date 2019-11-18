package other;

/**
 * 在数组中找到一个局部最小的位置
 */
public class GetLessIndex {
	public static int getLessIndex(int[] arr) {
		if (arr == null || arr.length < 1) return -1;
		if (arr.length == 1 || arr[0] < arr[1]) return 0;
		if (arr[arr.length - 1] < arr[arr.length - 2]) return arr.length - 1;

		int left = 1, right = arr.length - 2, mid = 0;
		while (left < right) {
			mid = (left + right) / 2;
			if (arr[mid] > arr[mid - 1]) { // 说明局部最小在arr[left]到arr[mid-1]范围
				right = mid - 1;
			} else if (arr[mid] > arr[mid + 1]) { // 说明局部最小在arr[m+1]到arr[right]范围
				left = mid + 1;
			} else {    // arr[mid]<arr[mid-1] 且 arr[mid]<arr[mid+1], 则 mid 就是最小
				return mid;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] arr = {48, 78};
		System.out.println(getLessIndex(arr));//0
		int[] arr1 = {-1, 2};
		System.out.println(getLessIndex(arr1));//0
		int[] arr2 = {4, 4, 3};
		System.out.println(getLessIndex(arr2));//1
	}
}
