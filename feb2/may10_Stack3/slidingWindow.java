package may10;
import java.util.*;
public class slidingWindow {

	public static void swmax(int[] arr,int k){
		int[] res=new int[arr.length];
		Stack<Integer> st=new Stack<>();
		res[arr.length-1] = arr.length;
		st.push(arr.length-1);
		for(int i=arr.length-2;i>=0;i--){
			while(st.size()>0 && arr[st.peek()]<arr[i])
				st.pop();
			
			res[i] = st.size()>0 ? st.peek() : arr.length;
			st.push(i);
		}
		int[] ans=new int[arr.length-k+1];
		int j=0;
		for(int i=0;i<ans.length;i++){
			if(j<i)
				j=i;
			while(res[j] < i+k)
				j=res[j];
			ans[i]=arr[j];
		}
		for(int i=0;i<ans.length;i++)
			System.out.print(ans[i]+" ");
		
	}
	public static void main(String[] args) {
		int[] arr={3,5,1,2,4,2,7,4,2,1,3,5,5,8,1,3,2};
		swmax(arr, 5);

	}

}
