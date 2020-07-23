package feb10;

import java.util.Scanner;

public class rotation {
	private static void reverse(int[] ar, int lo, int hi) {
		for (int left = lo, right = hi; left < right; left++, right--) {
			int temp = ar[left];
			ar[left] = ar[right];
			ar[right] = temp;
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter r(by which rotation has to occur)");
		int r = sc.nextInt();

		int[] arr = { 2, 5, 7, 9, 3, 6, 1 };
		int n = arr.length;

		reverse(arr, 0, n - 1); // full
		reverse(arr, 0, r - 1); // part1
		reverse(arr, r, n - 1); // part2

		for (int i : arr) {
			System.out.print(i + " ");
		}
	}

}
