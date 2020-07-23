package mar4;
import java.util.*;
public class targetSum {

	//get target subsets
	public static ArrayList<ArrayList<Integer>> gts(int[] arr,int target,int sp){
		if(sp==arr.length){
			ArrayList<ArrayList<Integer>> bsets=new ArrayList<>();
			if(target==0)
				bsets.add(new ArrayList<>());
			return bsets;
		}
		
		ArrayList<ArrayList<Integer>> isets=gts(arr,target-arr[sp],sp+1);//include sets
		ArrayList<ArrayList<Integer>> esets=gts(arr,target,sp+1);//exclude sets
		ArrayList<ArrayList<Integer>> msets=new ArrayList<>();
		for(int i=0;i<isets.size();i++){
			isets.get(i).add(arr[sp]);
			msets.add(isets.get(i));
		}
		for(int i=0;i<esets.size();i++)
			msets.add(esets.get(i));
		return msets;
	}
	public static void main(String[] args) {
		int[] arr={10,20,30,40,50};
		System.out.println(gts(arr,50, 0));

	}

}
