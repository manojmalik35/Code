package feb9;

import java.util.Scanner;

public class celsiustofarhenheit {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter starting farhenheit");
		int sf=sc.nextInt();
		System.out.println("enter ending farhenheit");
		int ef=sc.nextInt();
		System.out.println("enter the interval");
		int gap=sc.nextInt();
		for(int i=sf;i<=ef;i+=gap){
			System.out.print(i+" ");
			int cel=(int)((5.0/9) * (i-32));
			System.out.println(cel);
		}

	}

}
