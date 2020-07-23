package feb10;

public class reversearray {

	public static void main(String[] args) {
		int[] arr={ 10,20,30,40,50};
		
		int n=arr.length;
		for(int i=0,j=n-1;i<j;i++,j--){
				int temp=arr[i];
				arr[i]=arr[j];
				arr[j]=temp;
		}
		for(int i=0;i<n;i++)
			System.out.println(arr[i]);

	}

}
