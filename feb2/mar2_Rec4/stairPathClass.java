package mar2;

import java.util.ArrayList;

public class stairPathClass {

	public static void psp(int n,String psf){
		if(n==0){
			System.out.print(psf+" ");
			return;
		}
		if(n<0)
			return;
		
		psp(n-1,psf+'1');
		psp(n-2,psf+'2');
		psp(n-3,psf+'3');
	}
	
	private static ArrayList<String> climbSteps(int n){
		if(n==0){
			ArrayList<String> bres=new ArrayList<>();
			bres.add("");
			return bres;
		}
		
		if(n<0){
			ArrayList<String> bres=new ArrayList<>();
			return bres;
		}
		ArrayList<String> rres1=climbSteps(n-1);
		ArrayList<String> rres2=climbSteps(n-2);
		ArrayList<String> rres3=climbSteps(n-3);
		ArrayList<String> mres=new ArrayList<>();
		for(int i=0;i<rres1.size();i++)
			mres.add(1+rres1.get(i));
		
		for(int j=0;j<rres2.size();j++)
			mres.add(2+rres2.get(j));
		
		
		for(int k=0;k<rres3.size();k++)
			mres.add(3+rres3.get(k));
		
		return mres;
	}
	public static void main(String[] args) {
		System.out.println(climbSteps(4));
		psp(4,"");
		
	}

}
