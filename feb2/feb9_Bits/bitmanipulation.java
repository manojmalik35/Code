package feb9;

import java.util.Scanner;

public class bitmanipulation {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter number");
		int n=sc.nextInt();
		System.out.println("enter k(which bit)");
		int k=sc.nextInt();
		System.out.println(n+" "+Integer.toBinaryString(n));
		int bm=1<<k;
		System.out.println(bm+" "+Integer.toBinaryString(bm));
		if((n&bm)==bm){
			System.out.println("the bit is on");
			// n=n&~bm; to turn off the bit
			//n=n^bm; to toggle the bit
		}else{
			System.out.println("the bit is off");
			n=n|bm; //to turn on the bit
			System.out.println(n+" "+Integer.toBinaryString(n));
		}
		
		

	}

}
