package may7;
import java.util.*;
public class stackUse2 {

	private static class MHelper implements Comparable<MHelper>{
		int st;
		int et;
		MHelper(int st,int et){
			this.st=st;
			this.et=et;
		}
		public int compareTo(MHelper other){
			return this.st-other.st;
		}
	}
	public static void mergeOverlappingIntervals(int[] sts,int[] ets){//with stack
		MHelper[] marr=new MHelper[sts.length];
		for(int i=0;i<sts.length;i++)
			marr[i]=new MHelper(sts[i], ets[i]);
		Arrays.sort(marr);
		Stack<MHelper> stack=new Stack<>();
		stack.push(marr[0]);
		for(int i=1;i<marr.length;i++){
			if(marr[i].st<stack.peek().et)
					stack.peek().et=Math.max(stack.peek().et,marr[i].et);
			else
				stack.push(marr[i]);
		}
		while(stack.size()>0){
			MHelper m=stack.peek();
			System.out.println(m.st+"-"+m.et);
			stack.pop();
		}
	}
	public static void mergeOverlappingIntervals2(int[] sts,int[] ets){//without real stack
		MHelper[] marr=new MHelper[sts.length];
		for(int i=0;i<sts.length;i++)
			marr[i]=new MHelper(sts[i], ets[i]);
		Arrays.sort(marr);
		int t=0,i=1;;
		while(i<marr.length){
			if(marr[i].st<marr[t].et)
					marr[t].et=Math.max(marr[t].et,marr[i].et);
			else{
				t++;
				marr[t]=marr[i];
			}
			i++;
		}
		for(int j=0;j<=t;j++)
			System.out.println(marr[j].st+"-"+marr[j].et);
	}
	public static int identifyCelebrity(int[][] party){
		int l=0,r=party.length-1;
		while(l<r){
			if(party[l][r]==0)
				r--;
			else
				l++;
		}
		for(int i=0;i<party[0].length;i++){
			if(i!=l){
				if(party[i][r]==0 || party[l][i]==1)
					return -1;
			}
		}
		return l;
	}
	public static int petrolPumps(int[] oil,int[] distance){
		int start=0,end=0;
		int cp=0;//current petrol
		while(true){
			if(cp>=0){
				cp += oil[end] - distance[end];
				end = (end+1)%oil.length;
				if(end == start)
					break;
			}else{
				if(end<start)
					break;
				start=end;
				cp=0;
			}
		}
		if(cp>=0)
			return start;
		else
			return -1;
		
	}
	public static void main(String[] args) {
//		int[] st={1,2,3,12,17,19,22,31};
//		int[] et={7,6,9,15,22,24,23,37};
//		mergeOverlappingIntervals2(st, et);
//		int[][] party={{0,0,0,1,0,0},
//					   {0,0,0,1,0,1},
//				       {0,1,0,1,0,0},
//				       {0,0,0,0,0,0},
//				       {0,0,0,1,0,1},
//				       {1,0,0,1,0,0}};
//		System.out.println(identifyCelebrity(party));
		int[] oil={6,4,8,2,3,3,1,7,2};
		int[] distance={5,5,2,4,4,6,2,7,1};
		System.out.println(petrolPumps(oil, distance));
	}

}
