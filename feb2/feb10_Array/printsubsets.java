package feb10;

public class printsubsets {

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3 };
		int n = arr.length;

		for (int i = 0; i < 1 << n; i++) {
			for (int b = n - 1, j = 0; b >= 0; b--, j++) {
				int bm = 1 << b;
				if ((i & bm) == bm) {
					System.out.print(arr[j] + " ");
				} else {
					System.out.print("x ");
				}
			}

			System.out.println();
		}

	}

}
