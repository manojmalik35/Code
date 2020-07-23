package feb3;

public class pat9 {

	public static void main(String[] args) {
		int[] arr={2,7,3,4,5};
		int max=arr[0];
		for(int i=1;i<arr.length;i++){
			if(arr[i]>max)
				max=arr[i];
		}
		
		for(int i=1;i<=max;i++){
			for(int j=0;j<arr.length;j++){
				if(i>=max-arr[j]+1)
					System.out.print("*");
				else
					System.out.print(" ");
			}
			
			
			
			
			
			System.out.println();
		}
	}

}
