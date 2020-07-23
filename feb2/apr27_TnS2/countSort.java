package apr27;

public class countSort {

	//with negative elements
	public static void countsort(int[] arr){
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++){
			if(arr[i]<min)
				min=arr[i];
			if(arr[i]>max)
				max=arr[i];
		}
		int[] fmap=new int[Math.abs(min)+Math.abs(max)];
		
	}
	public static void main(String[] args) {
		int[] arr={2,9,9,8,1,7,1,3,2,6,6,5,4,1,1,5,2,7,8,1};
		int[] fmap=new int[10];
		//make frequency map
		for(int i=0;i<arr.length;i++)
			fmap[arr[i]]++;
		//make prefix sum array
		for(int i=1;i<fmap.length;i++)
			fmap[i]+=fmap[i-1];
		int out[]=new int[arr.length];
		//reverse loop to make the sorting stable i.e. to preserve the relative order of the elements
		for(int i=arr.length-1;i>=0;i--){
			out[fmap[arr[i]]-1]=arr[i];
			fmap[arr[i]]--;
		}
		for(int i=0;i<out.length;i++)
			System.out.print(out[i]+" ");
		System.out.println();
	}

}
