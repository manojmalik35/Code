package feb5;

import java.util.Scanner;

public class octalsub {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("enter 1st octal no.");
		int n1 = s.nextInt();
		System.out.println("enter 2nd octal no.");
		int n2 = s.nextInt();
		int sum = 0, b = 0, i = 1, r = 0;
		while (n1 != 0 || n2 != 0||b!=0) {
			int p1 = n1 % 10;
			int p2 = n2 % 10;
			
			if (p1 > p2) {
				r = p1 -b- p2;
				b=0;
			} else {
				r=p1+8-p2-b;
				b=1;
			}
			sum=sum+r*i;
			i=i*10;
			n1=n1/10;
			n2=n2/10;
		}
		System.out.println(sum);

	}

}
