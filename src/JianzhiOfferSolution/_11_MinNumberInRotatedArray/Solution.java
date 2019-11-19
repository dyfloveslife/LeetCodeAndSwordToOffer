package JianzhiOfferSolution._11_MinNumberInRotatedArray;

public class Solution {
	public int minNumberInRotateArray(int[] array) {
		if (array.length <= 0) return 0;

		int index1 = 0;
		int index2 = array.length - 1;
		int indexMid = index1;  //如果旋转后的数组是排序数组本身，则第一个元素就是最小的
		while (array[index1] >= array[index2]) {
			if (index2 - index1 == 1) {
				indexMid = index2;
				break;
			}
			indexMid = (index1 + index2) / 2;

			// 如果 index1、index2、indexMid 都相等的话，只能顺序查找
			if (array[index1] == array[index2] && array[indexMid] == array[index1]) {
				return minInOrder(array, index1, index2);
			}

			if (array[indexMid] >= array[index1]) index1 = indexMid;
			if (array[indexMid] <= array[index2]) index2 = indexMid;
		}
		return array[indexMid];
	}

	private int minInOrder(int[] array, int index1, int index2) {
		int res = array[index1];
		for (int i = index1 + 1; i < index2; i++) {
			if (array[i] < res) return array[i];
		}
		return res;
	}
}
