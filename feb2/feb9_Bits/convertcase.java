package feb9;

import java.util.Scanner;

public class convertcase {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter a character");
		char c=sc.next().charAt(0);
		
		
		if(c<='Z'&&c>='A'){
			System.out.println("Upper");
			char lc=(char)('a'+(c-'A'));
			System.out.println(lc);
		}
		else if(c<='z'&&c>='a'){
			System.out.println("Lower");
			char uc=(char)('A'+(c-'a'));
			System.out.println(uc);
		}
	

	}

}
