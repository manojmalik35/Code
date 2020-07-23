package mar7;

public class coinchange {

	public static void ccperm(int[] coins, int amt,String asf) {
		if(amt==0){
			System.out.println(asf);
			return;
		}
		for(int i=0;i<coins.length;i++){
			if(coins[i]<=amt)
				ccperm(coins,amt-coins[i],asf+coins[i]+"");
		}
		
	}

	public static void cccomb(int[] coins, int amt,int li, String asf) {
		if(amt==0){
			System.out.println(asf);
			return;
		}
		for(int i=li;i<coins.length;i++){
			if(coins[i]<=amt)
				cccomb(coins,amt-coins[i],i,asf+coins[i]+"");
		}
	}

	public static void main(String[] args) {
		int[] coins={2,3,5};
		ccperm(coins,7,"");
		System.out.println();
		cccomb(coins,7,0,"");
	}

}
