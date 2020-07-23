package apr26;

public class mergeSort {

	public static void merge2sorted(int[] one,int[] two,int[] merged){
		int i=0,j=0,k=0;
		while(i<one.length && j<two.length){
			if(one[i]<two[j]){
				merged[k]=one[i];
				k++;
				i++;
			}else{
				merged[k]=two[j];
				k++;
				j++;
			}
		}
		while(i<one.length){
			merged[k]=one[i];
			k++;
			i++;
		}
		while(j<two.length){
			merged[k]=two[j];
			k++;
			j++;
		}
	}
	private static int merge(int[] arr,int s,int mid,int e){
		int[] narr=new int[e-s+1];
		int ci=0;
		int i=s,j=mid+1,k=0;
		while(i<=mid && j<=e){
			if(arr[i]<arr[j]){
				narr[k]=arr[i];
				k++;
				i++;
			}else{
				narr[k]=arr[j];
				k++;
				j++;
				ci+=mid-i+1;
			}
		}
		while(i<=mid){
			narr[k]=arr[i];
			k++;
			i++;
		}
		while(j<=e){
			narr[k]=arr[j];
			k++;
			j++;
		}
		for(int a=0;a<narr.length;a++)
			arr[a+s]=narr[a];
		return ci;
	}
	public static void mergesort(int[] arr,int lo,int hi){
		if(lo==hi)
			return;
		int mid=(lo+hi)/2;
		mergesort(arr, lo, mid);
		mergesort(arr,mid+1,hi);
		merge(arr,lo,mid,hi);
	}
	public static int countInversions(int[] arr,int lo,int hi){
		if(lo==hi)
			return 0;
		int mid=(lo+hi)/2;
		int li=countInversions(arr,lo,mid);
		int ri=countInversions(arr, mid+1, hi);
		int ci=merge(arr,lo,mid,hi);
		return li+ri+ci;
	}
	public static void main(String[] args) {
//		int[] one={10,20,30,40,50};
//		int[] two={11,15,19,22,28,65,67,70,80};
//		int[] merged=new int[one.length+two.length];
//		merge2sorted(one, two, merged);
//		for(int i=0;i<merged.length;i++)
//			System.out.print(merged[i]+" ");
//		System.out.println();
		int[] arr={2,7,5,4,6,8,9,1};
//		mergesort(arr, 0, arr.length-1);
//		for(int i=0;i<arr.length;i++)
//			System.out.print(arr[i]+" ");
//		System.out.println();
		int i=countInversions(arr, 0, arr.length-1);
		System.out.println(i);
	}

}
