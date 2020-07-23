package mar10;
import java.util.*;
public class equisum {

	public static void eqsm(int[] arr,int sp,String s1,int sos1,String s2,int sos2){
		if(sp==arr.length){
			if(sos1==sos2)
				System.out.println(s1+"  &  "+s2);
			return;
		}
		
		eqsm(arr,sp+1,s1+arr[sp]+" ",sos1+arr[sp],s2,sos2);
		if(sp!=0)
			eqsm(arr,sp+1,s1,sos1,s2+arr[sp]+" ",sos2+arr[sp]);
	}
	public static void eqsma(int[] arr,int sp,ArrayList<Integer> s1,int sos1,ArrayList<Integer> s2,int sos2){
		if(sp==arr.length){
			if(sos1==sos2)
				System.out.println(s1+"  &  "+s2);
			return;
		}
		
		s1.add(arr[sp]);
		eqsma(arr,sp+1,s1,sos1+arr[sp],s2,sos2);
		s1.remove(s1.size()-1);
		if(sp!=0){
			s2.add(arr[sp]);
			eqsma(arr,sp+1,s1,sos1,s2,sos2+arr[sp]);
			s2.remove(s2.size()-1);
		}
	}
	public static void main(String[] args) {
		int[] arr={10,20,30,40,50,60,70};
		eqsm(arr, 0, "", 0, "", 0);
//		ArrayList<Integer> s1=new ArrayList<>();
//		ArrayList<Integer> s2=new ArrayList<>();
//		eqsma(arr, 0, s1, 0, s2, 0);
	}

}
