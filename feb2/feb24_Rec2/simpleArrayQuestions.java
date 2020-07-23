package feb24;

public class simpleArrayQuestions {

	public static void main(String[] args) {
		int[] arr={2,9,3,7,1};
		displayr(arr,0);
//		System.out.println(max(arr,0));
		System.out.println(findstupid(arr, 0, 9));
//		System.out.println(findsmart(arr, 0, 9));

	}
	
	//dr(arr,0)=1,7,3,9,2
	//dr(arr,1)=1,7,3,9
	//dr(arr,0)=dr(arr,1) arr[0]
	public static void displayr(int[] arr,int sp){
		if(sp==arr.length)
			return;
		displayr(arr, sp+1);
		System.out.println(arr[sp]);
	}
	
	//max(arr,0)= max of 0 to size-1
	//max(arr,1)= max of 1 to size-1
	//max(arr,0)= if(arr([0]<maxof1) then 
	public static int max(int[] arr,int sp){
		if(sp==arr.length)
			return Integer.MIN_VALUE; //kyuki minus infinity se sare bade hote h
//		OR
//		if(sp==arr.length-1)
//			return arr[sp];
		int misa=max(arr,sp+1);
		
		if(arr[sp]>misa)
			return arr[sp];
		else
			return misa;
	}
	
	public static boolean findstupid(int[] arr,int sp,int data){
		if(sp==arr.length)
			return false;
		boolean result=findstupid(arr, sp+1, data);
		if(result==true)
			return true;
		else if(arr[sp]==data)
			return true;
		else
			return false;
	}
	
	public static boolean findsmart(int[] arr,int sp,int data){
		if(sp==arr.length)
			return false;
		if(arr[sp]==data)
			return true;
		boolean result=findsmart(arr, sp+1, data);
		return result;
	}

}
