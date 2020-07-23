package feb2;

import java.util.Scanner;

public class PrintPrime {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("please enter a number");
		int n = s.nextInt();
		for (int t = 2; t <= n; t++) {
			boolean tisprime = true;

			int d = 2;
			while (d < t) {
				if (t % d == 0) {
					tisprime = false;
					break;
				}
				d++;
			}

			if (tisprime == true)
				System.out.println(t);

		}

	}

}
