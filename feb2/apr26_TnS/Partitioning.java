package apr26;

public class Partitioning {

	private static void swap(int[] arr,int i,int j){
		//arr[i]=arr[i]^arr[j];
		//arr[j]=arr[i]^arr[j];
		//arr[i]=arr[i]^arr[j];
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
	//return index of last small element
	public static int partitioning(int[] arr,int pivot){
		//0 to p-1 -> less than pivot
		//p to i-1 -> greater than equal to pivot
		//i to n-1 -> unknowns
		int i=0,p=0;
		while(i<arr.length){
			if(arr[i]>=pivot)
				i++;
			else{
				swap(arr,p,i);
				i++;
				p++;
			}
		}
		return p-1;
	}
	public static void move0toend(int[] arr){
		int i=0,p=0;
		while(i<arr.length){
			if(arr[i]==0)
				i++;
			else{
				swap(arr,i,p);
				p++;
				i++;
			}
		}
	}
	public static void movenztoend(int[] arr){//preserving the order of non zeroes
		//n-1 to p-1 -> non zeroes
		//p to i-1 -> zeroes
		//i to 0 -> unknown
		int p=arr.length-1,i=arr.length-1;
		while(i>=0){
			if(arr[i]==0)
				i--;
			else{
				swap(arr,i,p);
				i--;
				p--;
			}
		}
	}
	public static void split012(int[] arr){
		//0 to z-1 -> 0
		//z to o-1 -> 1
		//o to t-1 -> 2
		//t to n-1 -> unknown
		int z=0,o=0,t=0;
		while(t<arr.length){
			if(arr[t]==2)
				t++;
			else if(arr[t]==1){
				swap(arr,t,o);
				t++;
				o++;
			}else{
				swap(arr,t,o);
				t++;
				swap(arr,o,z);
				o++;
				z++;
			}
		}
	}
	public static void main(String[] args) {
//		int[] arr={12,9,3,11,8,1,7,4};
//		int p=partitioning(arr, 8);
//		int[] arr={9,0,6,8,0,3,6,0,4,0,5,9,0};
//		move0toend(arr);
//		movenztoend(arr);
		int[] z12={0,1,1,2,0,2,1,0,1,0,2,0,2};
		split012(z12);
		for(int i=0;i<z12.length;i++)
			System.out.print(z12[i]+" ");
		System.out.println();
//		System.out.println(p);
	}

}
