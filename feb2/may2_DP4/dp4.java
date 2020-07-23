package may2;

import java.util.*;

public class dp4 {

	public static void cabslnwnaz(int n) {
		// count all binary strings of length n with no adjacent zeroes
		int z = 1, o = 1;
		for (int i = 2; i <= n; i++) {
			int nz = o;
			int no = z + o;
			z = nz;
			o = no;
		}
		System.out.println(z + o);
	}

	public static void colony(int nob) {// nob= no. of blocks
		int[] strgb = new int[nob + 1];
		int[] strgs = new int[nob + 1];
		strgb[1] = strgs[1] = 1;
		for (int i = 2; i <= nob; i++) {
			strgb[i] = strgs[i - 1];
			strgs[i] = strgb[i - 1] + strgs[i - 1];
		}
		System.out.println((strgb[nob] + strgs[nob]) * (strgb[nob] + strgs[nob]));
	}

	public static void cosonapbpcp(String s) {// count of subsequences of the
												// nature a+b+c+
		int strga = 0;
		int strgab = 0;
		int strgabc = 0;
		ArrayList<String> aa = new ArrayList<>();
		ArrayList<String> ab = new ArrayList<>();
		ArrayList<String> ac = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			ArrayList<String> naa = new ArrayList<>();
			ArrayList<String> nab = new ArrayList<>();
			ArrayList<String> nac = new ArrayList<>();
			if (s.charAt(i) == 'a') {
				nab=ab;
				nac=ac;
				for (int j = 0; j < aa.size(); j++)
					naa.add(aa.get(j));
				for (int j = 0; j < aa.size(); j++)
					naa.add(aa.get(j) + "a");

				naa.add("a");
				strga = 2 * strga + 1;
			} else if (s.charAt(i) == 'b') {
				naa=aa;
				nac=ac;
				for (int j = 0; j < aa.size(); j++)
					nab.add(aa.get(j) + "b");
				for (int j = 0; j < ab.size(); j++)
					nab.add(ab.get(j));
				for (int j = 0; j < ab.size(); j++)
					nab.add(ab.get(j) + "b");
				strgab = strga + (2 * strgab);
			} else if(s.charAt(i) == 'c'){
				naa=aa;
				nab=ab;
				for (int j = 0; j < ab.size(); j++)
					nac.add(ab.get(j) + "c");
				for (int j = 0; j < ac.size(); j++)
					nac.add(ac.get(j));
				for (int j = 0; j < ac.size(); j++)
					nac.add(ac.get(j) + "c");
				strgabc = strgab + (2 * strgabc);
			}
			aa=naa;
			ab=nab;
			ac=nac;
		}
		System.out.println(ac);
		System.out.println(strgabc);
	}

	public static void lis(int[] arr) {// longest increasing subset
		int[] strg = new int[arr.length];
		for (int i = 0; i < strg.length; i++)
			strg[i] = 1;
		int max = 1;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j])
					strg[i] = Math.max(strg[i], strg[j] + 1);
			}
			max = Math.max(max, strg[i]);
		}
		System.out.println(max);
	}
	public static void lbs(int[] arr){//longest bitonic subset
		int[] lis = new int[arr.length];
		int[] lds = new int[arr.length];
		for (int i = 0; i < lis.length; i++){
			lis[i] = 1;
			lds[i] = 1;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j])
					lis[i] = Math.max(lis[i], lis[j] + 1);
			}
		}
		for(int i=arr.length-2;i>=0;i--){
			for(int j=arr.length-1;j>i;j--){
				if(arr[i]>arr[j])
					lds[i] = Math.max(lds[i], lds[j] + 1);
			}
		}
		int lbs=Integer.MIN_VALUE;
		for(int i=0;i<lis.length;i++)
			lbs = Math.max(lbs, lis[i]+lds[i]-1);
		System.out.println(lbs);
	}
	public static void main(String[] args) {
//		cabslnwnaz(4);
//		colony(4);
		cosonapbpcp("abcabc");
//		int[] arr = { 10, 22, 9, 33, 21, 50, 41, 60, 80, 1 };
//		lbs(arr);

	}

}
