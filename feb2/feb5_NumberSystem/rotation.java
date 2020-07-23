package feb5;

import java.util.Scanner;

public class rotation {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter a number");
		int n = sc.nextInt();
		System.out.println("enter r (number by which rotation has to occur)");
		int r = sc.nextInt();
		int temp = n, nod = 0;
		while (n != 0) {
			n = n / 10;
			nod++;
		}
		while(r<0){
			r=r+nod;
		}
		if(r>nod){
			r=r%nod;
		}
		n = temp;
		for (int i = 1; i <= r; i++) {
			int n1 = n % 10;
			n = n / 10;
			n=(int)(n+n1*(Math.pow(10,nod-1)));
		}
		System.out.println(n);
	}

}
