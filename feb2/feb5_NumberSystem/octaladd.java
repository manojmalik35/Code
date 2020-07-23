package feb5;

import java.util.Scanner;

public class octaladd {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("enter 1st octal no.");
		int n1=s.nextInt();
		System.out.println("enter 2nd octal no.");
		int n2=s.nextInt();
		int sum=0,c=0,i=1;
		while(n1!=0||n2!=0||c!=0){
			int p1=n1%10;
			int p2=n2%10;
			int r=(p1+p2+c)%8;
			sum=sum+(r*i);
			c=(p1+p2+c)/8;
			n1=n1/10;
			n2=n2/10;
			i=i*10;
		}
		System.out.println(sum);

	}

}
