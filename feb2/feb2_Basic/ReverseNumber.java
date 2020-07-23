package feb2;

import java.util.Scanner;

public class ReverseNumber {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("enter a number");
		int n = s.nextInt();
		int counter = 0;
		int a = 0;
		while (n != 0) {
			a = n % 10;
			System.out.print(a);
			n = n / 10;
		}
	}

}
