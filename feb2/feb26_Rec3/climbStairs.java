package feb26;

import java.util.ArrayList;

public class climbStairs {
	
	// Expectation cs(4) = 1111,22,13,31,112,121,211
	// Faith cs(3) = 111,12,21,3
	// Faith cs(2) = 11,2
	// Faith cs(1) = 1
	// E<-F  cs(4) = 1cs(3) + 2cs(2) +3cs(1)
	private static ArrayList<String> climbSteps(int n){
		if(n==0){
			ArrayList<String> bres=new ArrayList<>();
			bres.add("");
			return bres;
		}
		ArrayList<String> mres=new ArrayList<>();
		ArrayList<String> rres1=climbSteps(n-1);
		for(int i=0;i<rres1.size();i++)
			mres.add('1'+rres1.get(i));
		if(n>=2){
		ArrayList<String> rres2=climbSteps(n-2);
		for(int j=0;j<rres2.size();j++)
			mres.add('2'+rres2.get(j));
		}
		if(n>=3){
		ArrayList<String> rres3=climbSteps(n-3);
		for(int k=0;k<rres3.size();k++)
			mres.add('3'+rres3.get(k));
		}
		return mres;
	}
	public static void main(String[] args) {
		ArrayList<String> list=climbSteps(4);
		System.out.println(list);
		System.out.println(list.size());

	}

}
