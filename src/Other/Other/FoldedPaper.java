package Other.Other;

/**
 * 折纸问题
 */
public class FoldedPaper {
	public static void printAllFold(int n) {
		processFold(1, n, true);
	}

	private static void processFold(int i, int n, boolean down) {
		if (i > n) return;

		processFold(i + 1, n, true);
		System.out.println(down ? "down" : "up");
		processFold(i + 1, n, false);
	}

	public static void main(String[] args) {
		printAllFold(3);
	}
}
