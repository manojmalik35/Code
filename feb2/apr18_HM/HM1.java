package apr18;
import java.util.*;
public class HM1 {

	public static Character hfc(String word){//print highest frequency character
		HashMap<Character,Integer> fqmap=new HashMap<>();
		for(int i=0;i<word.length();i++){
			char c=word.charAt(i);
			if(fqmap.containsKey(c))
				fqmap.put(c, fqmap.get(c)+1);
			else
				fqmap.put(c, 1);
		}
		Character ans=word.charAt(0);
		ArrayList<Character> keys=new ArrayList<>(fqmap.keySet());
		for(int i=0;i<keys.size();i++){
			Character key=keys.get(i);
			if(fqmap.get(key)>fqmap.get(ans)){
				ans=key;
			}
		}
		return ans;
	}
	public static void pce1(Integer[] one,Integer[] two){
		HashMap<Integer,Integer> fqmap=new HashMap<>();
		for(int i=0;i<one.length;i++){
			if(fqmap.containsKey(one[i]))
				fqmap.put(one[i], fqmap.get(one[i])+1);
			else
				fqmap.put(one[i], 1);
		}
		for(int i=0;i<two.length;i++){
			if(fqmap.containsKey(two[i])){
				System.out.print(two[i]+" ");
				fqmap.remove(two[i]);
			}
		}
	}
	public static void pce2(Integer[] one,Integer[] two){
		HashMap<Integer,Integer> fqmap=new HashMap<>();
		for(int i=0;i<one.length;i++){
			if(fqmap.containsKey(one[i]))
				fqmap.put(one[i], fqmap.get(one[i])+1);
			else
				fqmap.put(one[i], 1);
		}
		for(int i=0;i<two.length;i++){
			if(fqmap.containsKey(two[i]) && fqmap.get(two[i]) >0){
				System.out.print(two[i]+" ");
				fqmap.put(two[i],fqmap.get(two[i])-1);
			}
		}
	}
	public static void printMaxConsecutiveElements(Integer[] arr){
		HashMap<Integer, Boolean> hm=new HashMap<>();
		for(int i=0;i<arr.length;i++)
			hm.put(arr[i], true);
		
		for(int i=0;i<arr.length;i++){
			if(hm.containsKey(arr[i]-1))
				hm.put(arr[i], false);
		}
		int msp=0;//maximum starting point
		int mlen=Integer.MIN_VALUE; //maximum length
		for(int i=0;i<arr.length;i++){
			if(hm.get(arr[i])){
				int tsp=arr[i];
				int tlen=0;
				while(hm.containsKey(tsp+tlen))
					tlen++;
				if(tlen>mlen){
					msp=tsp;
					mlen=tlen;
				}
			}
		}
		System.out.println(msp+" "+mlen);
	}
	private static class Pair{
		int sp=-1;
		int ep=-1;
	}
	public static void mssmk(Integer[] arr,int k){//maximum sum subarray whose sum is multiple of k
		int[] ps=new int[arr.length];//prefix sum
		int sum=0;
		for(int i=0;i<arr.length;i++){
			sum+=arr[i];
			ps[i]=sum%k;
		}
		HashMap<Integer, Pair> hm=new HashMap<>();
		for(int i=0;i<k;i++){
			hm.put(i, new Pair());
		}
		hm.get(0).sp=0;
		int osp=-1;
		int oep=-2; //-2 bcoz at a point sp&ep both same so delta 0 then condition will be false below
		for(int i=0;i<ps.length;i++){
			if(hm.get(ps[i]).sp==-1)
				hm.get(ps[i]).sp=i+1;
			else
				hm.get(ps[i]).ep=i;
			
			if(hm.get(ps[i]).ep-hm.get(ps[i]).sp > oep-osp){//if condition false then oep and osp will not change
				oep=hm.get(ps[i]).ep;
				osp=hm.get(ps[i]).sp;
			}
		}
		System.out.println(osp+" "+oep);
//		for(int i=osp;i<=oep;i++)
//			System.out.print(arr[i]+" ");
	}
	public static void main(String[] args) {
//		Character c=hfc("aaaffffggggggaaaagjjj");
//		System.out.println(c);
//		Integer[] one={1,2,2,5,1,4,2};
//		Integer[] two={2,1,2,1,1,3,5};
//		pce1(one,two);
//		System.out.println();
//		pce2(one, two);
		Integer[] arr={2,12,9,16,10,5,3,20,25,11,1,8,6};
//		Integer[] ar={12,5,7,15,3,6,11,2,9,8,-1,7};
		printMaxConsecutiveElements(arr);
//		mssmk(ar, 5);
	}

}
