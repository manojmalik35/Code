package apr28;

public class targetSum {

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
	private static void quicksort(int[] arr,int lo,int hi){
		if(lo>=hi)
			return;
		int pi=partitioning(arr, lo, hi);
		quicksort(arr, lo, pi-1);
		quicksort(arr, pi+1, hi);
	}
	public static void targetsumPair(int[] arr,int tar){//O(n)
		quicksort(arr, 0, arr.length-1);
		int left=0,right=arr.length-1;
		while(left<right){
			if(arr[left]+arr[right]<tar)
				left++;
			else if(arr[left]+arr[right]>tar)
				right--;
			else{
				System.out.println(arr[left]+" "+arr[right]);
				left++;
				right--;
			}
		}	
	}
	public static void targetSumTriplet(int[] arr,int tar){
		quicksort(arr, 0, arr.length-1);
		for(int i=0;i<arr.length;i++){
			int left=i+1;
			int right=arr.length-1;
			int temp=tar-arr[i];
			while(left<right){
				if(arr[left]+arr[right]<temp)
					left++;
				else if(arr[left]+arr[right]>temp)
					right--;
				else{
					System.out.println(arr[i]+" "+arr[left]+" "+arr[right]);
					left++;
					right--;
				}
			}
		}
	}
	public static void targetSumQuad(int[] arr,int tar){
		
	}
	public static void main(String[] args) {
		int[] arr={2,9,3,1,7,8,4,6,0};
		targetsumPair(arr, 11);
//		targetSumTriplet(arr, 11);

	}

}
