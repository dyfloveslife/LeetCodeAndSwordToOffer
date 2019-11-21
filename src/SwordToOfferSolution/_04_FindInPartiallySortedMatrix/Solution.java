package SwordToOfferSolution._04_FindInPartiallySortedMatrix;

/**
 * 题目描述：
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
 * 每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
 * 判断数组中是否含有该整数。
 * <p>
 * 题目详解：
 * 1、剑指offer的思路.就是比较矩阵的右上角的数与target的大小，如果target比这个矩阵右上角的数大，
 * 由于矩阵的右上角元素A是A所在行的最大的值，所以target肯定不在A所在的行了，
 * 所以这时候就应该就在除去第一行的剩下的行中去找这个target;
 * 2、如果target比矩阵右上角的数A小，那么由于A所在的列中A是最小的，那么target就在除去最右边的列的其它的列；
 * 3、如果相等，返回true;
 */
public class Solution {
	public boolean Find(int target, int[][] array) {
		if (array.length == 0 || array[0].length == 0)
			return false;
		int rows = array.length - 1;
		int columns = array[0].length - 1;
		int row = 0;
		int column = columns;
		while (row <= rows && column >= 0) {
			if (array[row][column] < target) {
				row++;
			} else if (array[row][column] > target) {
				column--;
			} else {
				return true;
			}
		}
		return false;
	}
}
