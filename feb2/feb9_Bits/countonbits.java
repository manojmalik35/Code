package feb9;

import java.util.Scanner;

public class countonbits {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a number");
		int n=sc.nextInt();
		int counter=0;
		while(n!=0){
			int rsbm=n&(-n);
			n=n-rsbm;
			counter++;
		}
		System.out.println(counter);

	}

}
