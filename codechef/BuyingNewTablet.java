package codechef;

import java.util.Scanner;

public class BuyingNewTablet {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int i = 0; i < t; i++) {
			int a = 0;
			int n = sc.nextInt();
			int b = sc.nextInt();
			for (int j = 0; j < n; j++) {
				int w = sc.nextInt();
				int h = sc.nextInt();
				int p = sc.nextInt();
				if (b >= p) {
					if ((w * h) > a)
						a = w * h;
				}

			}
			if (a == 0)
				System.out.println("no tablet");
			else
				System.out.println(a);

		}

	}

}
