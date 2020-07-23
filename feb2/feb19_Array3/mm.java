package feb19;

public class mm {

	public static void main(String[] args) {
		int[][] one = { { 10, 0, 0 }, { 0, 10, 1 } };

		int[][] two = { { 1, 0, 1, 0 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 } };
		int r1 = one.length;
		int c1 = one[0].length;
		int r2 = two.length;
		int c2 = two[0].length;
		int[][] prod = new int[r1][c2];
		if (r2 == c1) {
			for (int r = 0; r < r1; r++) {
				for (int c = 0; c < c2; c++) {
					for (int k = 0; k < c1; k++)
						prod[r][c] = prod[r][c] + (one[r][k] * two[k][c]);
					System.out.print(prod[r][c] + " ");
				}
				System.out.println();
			}

		}

	}
}
