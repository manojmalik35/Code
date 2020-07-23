package apr27;

public class RadixSort {

	
	public static void radixSort(int[] arr){
		int max=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++){
			if(arr[i]>max)
				max=arr[i];
		}
		int a=10,b=1;
		int[] out=new int[arr.length];
		while(max!=0){
			int[] fmap=new int[10];
			for(int i=0;i<arr.length;i++){
				int n=arr[i]%a;
				fmap[n/b]++;
			}
			for(int i=1;i<fmap.length;i++)
				fmap[i]+=fmap[i-1];
			for(int i=out.length-1;i>=0;i--){
				int n=arr[i]%a;
				out[fmap[n/b]-1]=arr[i];
				fmap[n/b]--;
			}
			a=a*10;
			b=b*10;
			max/=10;
			for(int i=0;i<out.length;i++)
				arr[i]=out[i];
		}
	}
	public static void radixsort(int[] arr){//sir vla
		int max=Integer.MIN_VALUE;
		for(int i=0;i<arr.length;i++)
			max=Math.max(max, arr[i]);
		int div=1;
		int[] out=new int[arr.length];
		while(max!=0){
			int[] fmap=new int[10];
			for(int i=0;i<arr.length;i++){
				int n=arr[i]/div%10;
				fmap[n]++;
			}
			for(int i=1;i<fmap.length;i++)
				fmap[i]+=fmap[i-1];
			for(int i=out.length-1;i>=0;i--){
				int n=arr[i]/div%10;
				out[fmap[n]-1]=arr[i];
				fmap[n]--;
			}
			div=div*10;
			max/=10;
			for(int i=0;i<out.length;i++)
				arr[i]=out[i];
		}
		
	}
	public static void main(String[] args) {
		int[] arr={74,362,87,92,371,544,19,47,496,382,211,631,345,912,900};
		radixsort(arr);
		for(int i=0;i<arr.length;i++)
			System.out.print(arr[i]+" ");
	}

}
