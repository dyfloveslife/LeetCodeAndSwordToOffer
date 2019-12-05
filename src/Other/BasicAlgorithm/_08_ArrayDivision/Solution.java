package Other.BasicAlgorithm._08_ArrayDivision;

/*
 * 数组划分问题
 * 给定一个数组 arr 和一个数 num，要求把小于等于 num 的数放在数组的左边，大于 num 的放在数组的右边。
 *
 * 思路：   https://i.loli.net/2019/12/05/W9ZmnI2SE35PL1q.png
 *  1. 用 x 表示小于等于 num 的区域，初始值指向数组的 -1 位置，维护一个指针，指向数组的第一个元素；
 *  2. 遍历数组 arr 中的每个数，如果当前元素大于 num，则指针直接后移；
 *     如果当前元素小于 num，则将当前元素与 x 所表示区域的下一个元素交换，然后 x 所表示的区域就向右扩大一个位置。
 *  3. 直到最后，数组 arr 就会被划分成小于等于 num 的在数组左边，大于 num 的在数组右边。
 */
public class Solution {

}
