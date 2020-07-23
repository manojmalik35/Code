package apr24;

public class HeapSort {

	private static void swap(int[] arr,int i,int j){
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	private static void downheapify(int[] arr,int index,int sz){//sz represents kitne size ko downheapify krna h or index is kaha se krna h
		int maxind=index;
		int lci=2*index+1;
		int rci=2*index+2;
		if(lci<sz && arr[lci]>arr[maxind])
			maxind=lci;
		if(rci<sz && arr[rci]>arr[maxind])
			maxind=rci;
		if(maxind!=index){
			swap(arr,maxind,index);
			downheapify(arr,maxind,sz);
		}
	}
	public static void heapSort(int[] arr){
		//Make priority queue for the first time
		for(int i=(arr.length/2)-1;i>=0;i--)
			downheapify(arr,i,arr.length);
		
		int cse=0;//count of sorted elements
		while(cse<arr.length-1){
			swap(arr,0,arr.length-1-cse);
			cse++;
			downheapify(arr, 0, arr.length-cse);
		}
	}
	
	public static void main(String[] args) {
		int[] arr={5,2,6,9,7,8,15,24,1};
		heapSort(arr);
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
		System.out.println();
		
	}

}
