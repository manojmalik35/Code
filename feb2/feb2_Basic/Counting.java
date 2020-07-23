package feb2;

import java.util.Scanner;

public class Counting {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		 System.out.println("Please enter a number");
		 
		 int n=s.nextInt();
		 int counter=1;
		 while(counter<=n){
			 System.out.println(counter);
			 counter++;
		 }
	}

}
