package feb2;

import java.util.Scanner;

public class IsPrime {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		
		System.out.println("Please enter a number");
		int n=s.nextInt();
		int counter=2;
		while(counter<n){
			if(n%counter==0){
				System.out.println("composite no.");
				return;
			}	
			counter++;
		}
		System.out.println("prime no.");
		s.close();
	}
}


