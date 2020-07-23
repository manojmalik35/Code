package feb3;

import java.util.Scanner;

public class pat7 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a number");
		int n=sc.nextInt();
		
		int st=(n+1)/2,sp=1;
		for(int i=1;i<=n;i++){
				
			for(int j=1;j<=st;j++){
				System.out.print("*");
			}
			
			for(int j=1;j<=sp;j++){
				System.out.print(" ");
			}
			
			for(int j=1;j<=st;j++){
				System.out.print("*");
			}
			
				if(i<=n/2){
					sp+=2;
					st--;
				}
				else {
					sp-=2;
					st++;
				}
				
				
			System.out.println();
		}

	}

}
