package feb24;

public class displayArray {

	//display(arr,0)=2,9,3,7,1
	//display(arr,1)=9,3,7,1
	//display(arr,0)=arr[0] display(arr,1)
	public static void display(int[] arr,int sp){
		if(sp==arr.length)
			return;
		
		System.out.println(arr[sp]);
		display(arr,sp+1);
	}
	public static void main(String[] args) {
		int[] arr={2,9,3,7,1};
		display(arr,0);

	}

}
