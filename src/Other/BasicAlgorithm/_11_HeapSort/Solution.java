package Other.BasicAlgorithm._11_HeapSort;

import java.util.Arrays;

/*
 * 堆排序
 *
 * 不稳定
 * 时间复杂度：O(N*logN)
 * 空间复杂度：O(1)
 *
 * 如何定义大根堆？
 * 1. 所谓堆，就是一个用一维数组来表示一个完全二叉树的这么一个数据结构。
 * 2. 在一棵完全二叉树中，任何一棵子树的最大值都是这棵子树的头部，其形成的结构即大根堆。
 * 3. 不是说整棵树的最大值是这个数的头部，而是对于每一棵子树来说的。
 * 4. 可以使用二叉树的性质，即一个树的左孩子可以通过其索引除以 2 的方式找到它的父节点；
 *    一个树的右孩子可以通过其索引减一后再除以 2 的方式找到它的父节点，如下所示：
 *                    16-1
 *                    /  \
 *                   /    \
 *                 14-2   10-3
 *    其中以“节点值-索引值”的形式给出了一个二叉树，则节点 10 可以通过 (3-1)/2=1 的方式找到其父节点 16；
 *    节点 14 可通过 2/2=1 的方式找到其父节点 16。
 * 5. 当然，如果节点 16 的索引从 0 开始的话，则其左孩子的索引为 2*0+1=1，其右孩子的索引为 2*0+2=2。
 *
 * 给定一个数组如何建立成大根堆？
 * 在建立大根堆的过程中，如果出现了不满足大根堆的情况，如何进行调整？
 * 建立大根堆的时候，每插入一个值，则只和树的高度有关。代价是 O(logN)
 * 第 i 个节点加入到 0~i-1 中，代价是 log(i-1)，因此，所有节点加入的代价
 * 就是 log1+log2+log3+...+logN-1=O(N)
 *
 * heapify 过程：
 * 如果在一个大根堆中，某个数字变化了，则有可能不满足大顶堆条件，则需要进行堆化处理。
 * 找到当前节点的左右孩子，将当前节点与左右孩子中最大的那个孩子交换，依次执行下去即可。
 * 当前节点的左孩子就是 2*i+1，当前节点的右孩子就是 2*2+2。
 *
 * 减堆操作：
 * 拿大根堆来说，将根节点与最后一个节点进行交换，然后堆的长度减一，
 * 此时不一定满足大根堆的要求，所以要进行调整(heapify)成大根堆。
 *
 * 堆排序：
 * 1. 先让待排序的数组形成一个大根堆，然后堆顶和堆中最后一个元素交换，此时最大的数就是数组中最后一个数；
 * 2. 交换完成后，在 0~heapSize-1 的范围里进行 heapify 的过程，重新调整成大根堆；
 * 3. 随着堆中元素的减少，每次减少的元素都会从数组的最后往前填充；
 * 4. 每次都这么做，直到堆中的元素减完，最后就将原先无序的数组排好序了。
 */
public class Solution {
    public int[] heapSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }

        // 建立大根堆的过程
        for (int i = 0; i < nums.length; i++) {
            heapInsert(nums, i);
        }

        // 堆排序的过程：不断的将堆顶与堆中最后一个元素交换，然后再 heapify
        int heapSize = nums.length;
        swap(nums, 0, --heapSize);
        while (heapSize > 0) {
            heapify(nums, 0, heapSize);
            swap(nums, 0, --heapSize);
        }

        return nums;
    }

    // 从 0~(i-1) 位置已经构成大根堆了，现在需要把第 i 的位置上的元素加进去
    private void heapInsert(int[] nums, int i) {
        // 如果当前元素比父节点的元素大，则交换，
        // 然后当前元素的索引更新成原先父节点的索引
        while (nums[i] > nums[(i - 1) / 2]) {
            swap(nums, i, (i - 1) / 2);
            // i 往上走，即来到父节点的索引位置，继续进入 while，再次判断
            i = (i - 1) / 2;
        }
    }

    // heapSize 表示堆中有多少个数，它可能和数组的个数是没有关系的，但一定不会比数组的个数大
    // 此函数表示 0~heapSize-1 上已经形成了堆，由于 i 所指元素变小了，则开始进行堆化处理
    // 即 i 所指的元素开始往下沉
    public void heapify(int[] nums, int i, int heapSize) {
        int left = i * 2 + 1;

        // while 的条件说明：i 的左孩子也在堆中，没有越界
        while (left < heapSize) {
            // 左右孩子选出较大的
            // left + 1 表示 i 所指元素的右孩子
            // left + 1 < heapSize 表示 i 所指元素的右孩子也不越界
            // 如果右孩子越界，也就是 i 所指元素只有左孩子，即条件 left + 1 < heapSize 不成立，
            // 则较大的那个数就是 i 所指元素的左孩子
            int largest = (left + 1 < heapSize) && (nums[left] < nums[left + 1]) ? left + 1: left;
            // 从左右孩子中选出较大的之后，还得判断当前 i 所指元素的大小
            largest = (nums[largest] > nums[i]) ? largest : i;
            // 如果当前节点变了之后还是最大的，则就不往下沉了
            // 也就是当前节点和孩子之间的最大值还是当前节点，就不需要再往下沉了
            if (largest == i) {
                break;
            }
            // largest != i
            // 也就是当前的 i 所指的元素，与其左右较大那个的孩子交换
            swap(nums, largest, i);
            i = largest;
            left = i * 2 + 1;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = {3, 2, 1, 5, 6, 4};
        int[] arr2 = {1, 2, 3, 2, 5, 6};
        int[] arr3 = {5, 2, 3, 1};
        int[] arr4 = {5, 1, 1, 2, 0, 0};
        int[] arr5 = {-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};

        System.out.println(Arrays.toString(solution.heapSort(arr1)));
        System.out.println(Arrays.toString(solution.heapSort(arr2)));
        System.out.println(Arrays.toString(solution.heapSort(arr3)));
        System.out.println(Arrays.toString(solution.heapSort(arr4)));
        System.out.println(Arrays.toString(solution.heapSort(arr5)));
    }
}
