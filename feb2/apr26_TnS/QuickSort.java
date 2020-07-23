package apr26;

public class QuickSort {

	private static void swap(int[] arr,int i,int j){
		//arr[i]=arr[i]^arr[j];
		//arr[j]=arr[i]^arr[j];
		//arr[i]=arr[i]^arr[j];
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	private static int partitioning(int[] arr,int lo,int hi){
		int i=lo,p=lo;
		int pivot=arr[hi];
		while(i<=hi){
			if(arr[i]<=pivot){
				swap(arr,i,p);
				i++;
				p++;
			}else
				i++;
		}
		return p-1;
	}
	public static void quicksort(int[] arr,int lo,int hi){
		if(lo>=hi)
			return;
		int pi=partitioning(arr, lo, hi);
		quicksort(arr, lo, pi-1);
		quicksort(arr, pi+1, hi);
	}
	public static int quickselect(int[] arr,int k,int lo,int hi){//kth smallest in O(n)
		if(lo>=hi)
			return -1;
		int pi=partitioning(arr,lo,hi);
		if(k==pi)
			return arr[pi];
		else if(k>pi)
			return quickselect(arr, k, pi+1, hi);
		else
			return quickselect(arr, k, lo, pi-1);
	}
	public static void main(String[] args) {
		int[] arr={2,7,5,4,6,8,9,1};
//		quicksort(arr, 0, arr.length-1);
//		for(int i=0;i<arr.length;i++)
//			System.out.println(quickselect(arr, i, 0, arr.length-1));
//		System.out.println();
		System.out.println(quickselect(arr, 4, 0, arr.length-1));
//		for(int i=0;i<arr.length;i++)
//			System.out.print(arr[i]+" ");
		

	}

}
