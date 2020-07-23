package apr30;

public class dp2 {

	public static int coinChangePerm(int[] coins,int amt){
		int[] strg=new int[amt+1];
		strg[0]=1;
		for(int i=1;i<strg.length;i++){
				for(int j=0;j<coins.length;j++){
					if(i>=coins[j])
						strg[i] += strg[i-coins[j]] ;
				}
			}
		return strg[amt];
	}
	public static int coinChangeComb(int[] coins,int amt){
		int[] strg=new int[amt+1];
		strg[0]=1;
		for(int j=0;j<coins.length;j++){
			for(int i=1;i<strg.length;i++){
					if(i>=coins[j])
						strg[i] += strg[i-coins[j]] ;
				}
			}
		return strg[amt];
	}
	public static int targetsumSubsets(int[] arr,int tar){
		int[][] strg=new int[arr.length+1][tar+1];//count of how many ways the sum could be formed
		for(int i=0;i<strg.length;i++){
			for(int j=0;j<strg[i].length;j++){
				if(i==0 && j==0)
					strg[i][j]=1;
				else if(i==0)
					strg[i][j]=0;
				else if(j==0)
					strg[i][j] = 1;
				else{
					strg[i][j] = strg[i-1][j];
					if(j>=arr[i-1])
					strg[i][j] += strg[i-1][j-arr[i-1]];
				}
			}
		}
		for(int i=0;i<strg.length;i++){
			for(int j=0;j<strg[0].length;j++)
				System.out.print(strg[i][j]+" ");
			System.out.println();
		}
		return strg[strg.length-1][strg[0].length-1];	
	}
	public static void knapsack01(int[] prices,int[] weights,int capacity){
		//ya to element bag me aayga ya nhi aayga
		int[][] strg=new int[prices.length+1][capacity+1];
		for(int i=1;i<strg.length;i++){
			for(int j=1;j<strg[i].length;j++){
				strg[i][j] = strg[i-1][j];
					if(weights[i-1] <= j){
						int temp=prices[i-1]+strg[i-1][j-weights[i-1]];
						if(temp>strg[i][j])
							strg[i][j] = temp;
					}
				}
			}
		System.out.println(strg[strg.length-1][strg[0].length-1]);
	}
	public static void unboundedKnapsack(int[] prices,int[] weights,int capacity){
		//isko shi se samjhna h
		int[] strg=new int[capacity+1]; 
		for(int i=1;i<strg.length;i++){
			for(int j=0;j<weights.length;j++){
				if(i>= weights[j]){
					int temp=prices[j] + strg[i-weights[j]];
					if(temp>strg[i])
						strg[i]=temp;
				}
			}
		}
		System.out.println(strg[strg.length-1]);
	}
	public static void main(String[] args) {
//		int[] coins={2,3,5};
//		System.out.println(coinChangePerm(coins, 7));
//		System.out.println(coinChangeComb(coins, 7));
//		int[] local={5,2,4,3,7,6};
//		System.out.println(targetsumSubsets(local, 7));
		int[] prices={15,14,10,45,30};
		int[] weights={2,5,1,3,4};
//		knapsack01(prices, weights, 7);
		unboundedKnapsack(prices, weights, 7);
		
	}

}
