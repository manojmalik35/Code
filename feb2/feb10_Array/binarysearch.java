package feb10;

import java.util.Scanner;

public class binarysearch {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("enter number to search");
		int n = sc.nextInt();

		int[] arr = { 10, 10,10,20,20,20,20,20,20,30,30,30 };
		int low = 0, high = arr.length - 1,res=-1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (arr[mid] > n)
				high = mid - 1;
			else if (arr[mid] < n)
				low = mid + 1;
			else {
				res=mid;
				high=mid-1; //for finding the leftmost index of repeating element
				//low = mid +1; for finding the rightmost index of repeating element
			}
		}
		System.out.println(res);

	}

}
