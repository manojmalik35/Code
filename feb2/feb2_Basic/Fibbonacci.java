package feb2;

import java.util.Scanner;

public class Fibbonacci {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		
		System.out.println("please enter a number");
		int n=s.nextInt();
		int a=0,b=1,sum=0;
		
			
			for(int i=1;i<=n;i++){
				System.out.print(a+" ");
				sum=a+b;
				a=b;
				b=sum;
			}
		
		
	}

}
