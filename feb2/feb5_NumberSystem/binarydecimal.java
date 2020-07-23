package feb5;

import java.util.Scanner;

public class binarydecimal {
	
	public static int decimalbinary(int n){
		int b=0,sum=0;
		int i=1;//10^0
		while(n!=0){
			b=n%2;
			sum=sum+b*i;
			n=n/2;
			i=i*10;
		}
		return sum;
	}
	
	public static int binarytodecimal(int n){
		int b=0,sum=0;
		int i=1;//2^0
		while(n!=0){
			b=n%10;
			sum=sum+b*i;
			n=n/10;
			i=i*2;
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter a decimal number");
		int n=sc.nextInt();
		int db=decimalbinary(n);
		System.out.println(db);
		System.out.println("enter a binary number");
		int n1=sc.nextInt();
		int bd=binarytodecimal(n1);
		System.out.println(bd);

	}

}
