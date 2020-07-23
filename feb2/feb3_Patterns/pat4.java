package feb3;

import java.util.Scanner;

public class pat4 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a number");
		int n=sc.nextInt();
		
		for(int i=0;i<n;i++){
		int nc0=1;	
			for(int k=0;k<=i;k++){
				System.out.print(nc0+" ");
				nc0=nc0*(i-k)/(k+1);
			}
			System.out.println();
		}

	}

}
