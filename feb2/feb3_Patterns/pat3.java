package feb3;

import java.util.Scanner;

public class pat3 {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		int a=0,b=1;
		int c=0;
		for(int i=1;i<=n;i++){
			for(int j=1;j<=i;j++){
				System.out.print(a+" ");
				c=a+b;
				a=b;
				b=c;
				
			}
			System.out.println();
		}

	}

}
