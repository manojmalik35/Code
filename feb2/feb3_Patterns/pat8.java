package feb3;

import java.util.Scanner;

public class pat8 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter a number");
		int n = sc.nextInt();

		int sp = n / 2, st = 1, p = 1;
		for (int i = 1; i <= n; i++) {
			int v=p;
			for (int j = 1; j <= sp; j++) {
				System.out.print(" ");
			}

			for (int k = 1; k <= st; k++) {
				System.out.print(v);
				if(k<=st/2)
					v++;
				else
					v--;
			}

			if (i <= n / 2) {
				sp--;
				st += 2;
				p++;
			} else {
				sp++;
				st -= 2;
				p--;
			}

			System.out.println();

		}

	}

}
