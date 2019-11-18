package other;

public class FlipAndInvertImage {
	public static int[][] flipAndInvertImage(int[][] A) {
		int C = A[0].length;
		for (int[] row : A)
			for (int i = 0; i < (C + 1) / 2; ++i) {
				int tmp = row[i] ^ 1;
				row[i] = row[C - 1 - i] ^ 1;
				row[C - 1 - i] = tmp;
			}

		return A;
	}

	public static void main(String[] args) {
		int[][] arr = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
		int[][] arr2 = flipAndInvertImage(arr);
		for (int[] i : arr2) {
			for (int n : i) {
				System.out.print(n);
			}
			System.out.println();
		}
	}
}
