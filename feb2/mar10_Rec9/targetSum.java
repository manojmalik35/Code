package mar10;
import java.util.*;
public class targetSum {

	public static void tarsum(int[] arr,int sp,int tar,String set){
		if(sp==arr.length){
			if(tar==0){
				System.out.println(set);
			}
				return;
		}
		
		tarsum(arr,sp+1,tar-arr[sp],set+arr[sp]+" ");
		tarsum(arr,sp+1,tar,set);
	}
	
	public static void tarsuma(int[] arr,int sp,int tar,ArrayList<Integer> set){
		if(sp==arr.length){
			if(tar==0){
				System.out.println(set);
			}
				return;
		}
		
		set.add(arr[sp]);
		tarsuma(arr,sp+1,tar-arr[sp],set);
		set.remove(set.size()-1);
		tarsuma(arr,sp+1,tar,set);
		
	}
	public static void main(String[] args) {
		int[] arr={10,20,30,40,50};
//		tarsum(arr,0,50,"");
		ArrayList<Integer> a=new ArrayList<>();
		tarsuma(arr,0,60,a);
	}

}
