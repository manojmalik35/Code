package feb2;

import java.util.Scanner;

public class CountDigits {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		System.out.println("enter a number");
		int n=s.nextInt();
		int counter=0;
		while(n!=0){
			counter++;
			n=n/10;
		}
		System.out.println(counter);
		s.close();
	}

}
