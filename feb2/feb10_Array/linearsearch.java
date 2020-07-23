package feb10;

import java.util.Scanner;

public class linearsearch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter a number");
		int n = sc.nextInt();

		int ind = 0;
		int arr[] = { 55, 2, 33, 11, 77, 6 };
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == n) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);

	}
}