package may5;

import java.util.Arrays;

public class greedy {

	public static int maxsumNoAdjacentElements(int[] arr){
		int inc=arr[0];
		int exc=0;
		for(int i=1;i<arr.length;i++){
			int ninc = arr[i] + exc;
			int nexc = Math.max(inc, exc);
			inc = ninc;
			exc= nexc;
		}
		return Math.max(inc, exc);
	}
	private static class Pair implements Comparable<Pair>{
		int sp;
		int ep;
		Pair(int sp,int ep){
			this.sp=sp;
			this.ep=ep;
		}
		public int compareTo(Pair other){
			return this.ep-other.ep;
		}
	}
	public static void ActivitySelection(int[] sps,int[] eps){
		Pair[] activities=new Pair[sps.length];
		for(int i=0;i<sps.length;i++){
			Pair p=new Pair(sps[i], eps[i]);
			activities[i]=p;
		}
		Arrays.sort(activities);
		int ft=activities[0].ep;
		int count=1;
		for(int i=1;i<activities.length;i++){
			if(activities[i].sp>=ft){
				ft=activities[i].ep;
				count++;
			}
		}
		System.out.println(count);
	}
	public static void minPlatformsRequired(int[] ats,int[] dts){
		//we are assuming that both the arrays are sorted
		int a=0,d=0;
		int countts=0,minp=0;//count train at stations,minimum platforms
		while(a<ats.length && d<dts.length){
			if(ats[a] < dts[d]){
				a++;
				countts++;
			}
			else{
				d++;
				countts--;
			}
			if(countts > minp)
				minp = countts;
		}
		System.out.println(minp);
	}
	private static class Helper implements Comparable<Helper>{
		int weight;
		int value;
		double ratio;
		Helper(int w,int v){
			this.weight=w;
			this.value=v;
			this.ratio=v*1.0/w;
		}
		public int compareTo(Helper other){
			if(this.ratio==other.ratio)
				return 0;
			else if(this.ratio<other.ratio)
				return -1;
			else
				return 1;
		}
	}
	public static void fractionalKnapsack(int[] weights,int[] values,int capacity){
		Helper[] items=new Helper[weights.length];
		for(int i=0;i<weights.length;i++){
			Helper h=new Helper(weights[i], values[i]);
			items[i] = h;
		}
		Arrays.sort(items);
		int i=items.length-1,vib=0;
		while(capacity > 0 && i>=0){
			Helper th=items[i];
			if(th.weight<capacity){
				capacity -= th.weight;
				vib += th.value;
			}else{
				vib += th.ratio*capacity;
				capacity=0;
			}
			i--;
		}
		System.out.println(vib);
	}
	//not greedy... this is dp
	public static int countSquaresRecursive(int h,int v,int[][] strg){
		if(h==0 || v==0)
			return 0;
		else if(h==v)
			return 1;
		if(strg[h][v] != 0)
			return strg[h][v];
		int sd=Math.min(h, v);
		int res=Integer.MAX_VALUE;
		for(int x=1;x<=sd;x++){
			int p11h=h-x;
			int p11v=x;
			int p12h=h;
			int p12v=v-x;
			int c1=countSquaresRecursive(p11h, p11v, strg) + countSquaresRecursive(p12h, p12v, strg) + 1;
			
			int p21h=h-x;
			int p21v=v;
			int p22h=x;
			int p22v=v-x;
			int c2=countSquaresRecursive(p21h, p21v, strg) + countSquaresRecursive(p22h, p22v, strg) + 1;
			
			res = Math.min(res, Math.min(c1, c2));
		}
		strg[h][v]=res;
		return res;
	}
	public static void maxSizeSquareSubmatrixof1(int[][] arr){
		int[][] strg=new int[arr.length][arr[0].length];
		int max=Integer.MIN_VALUE;
		for(int i=strg.length-1;i>=0;i--){
			for(int j=strg[i].length-1;j>=0;j--){
				if(i == strg.length-1 || j == strg[0].length-1)
					strg[i][j] = arr[i][j];
				else if(arr[i][j] == 0)
					strg[i][j] = 0;
				else{
					strg[i][j] = Math.min(strg[i][j+1], Math.min(strg[i+1][j], strg[i+1][j+1])) + 1;
					max=Math.max(max, strg[i][j]);
				}
			}
		}
		System.out.println(max);
	}
	public static void main(String[] args) {
//		int[] arr={5,6,10,100,10,5};
//		System.out.println(maxsumNoAdjacentElements(arr));
//		int[] sps={3,0,1,8,5,5};
//		int[] eps={4,6,2,9,9,7};
//		ActivitySelection(sps, eps);
//		int[] ats={900,940,950,1100,1500,1800};
//		int[] dts={910,1120,1130,1200,1900,2000};
//		minPlatformsRequired(ats, dts);
//		int[] wts={10,40,20,30};
//		int[] vls={60,40,100,120};
//		fractionalKnapsack(wts, vls, 50);
//		int h=36,v=30;
//		int[][] strg=new int[h+1][v+1];
//		System.out.println(countSquaresRecursive(h,v,strg));
		int[][] arr={{0,1,1,0,0,1,0},
					 {1,0,1,1,0,1,1},
					 {1,1,1,1,0,1,0},
					 {1,1,1,1,0,1,0},
					 {0,1,1,1,1,1,0},
					 {1,1,0,0,0,1,1}};
		maxSizeSquareSubmatrixof1(arr);
	}

}
