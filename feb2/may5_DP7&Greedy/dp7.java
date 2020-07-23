package may5;

import java.util.*;

public class dp7 {

	public static int countParty2Recursive(int cap,ArrayList<Integer>[] cpmap,boolean[] personsTaken){
		if(cap == cpmap.length){
			for(int i=0;i<personsTaken.length;i++){
				if(personsTaken[i] == false)
					return 0;
			}
			return 1;
		}
		
		int myres=0;
		myres+=countParty2Recursive(cap+1, cpmap, personsTaken);
		for(int i=0;i<cpmap[cap].size();i++){
			int person=cpmap[cap].get(i);
			if(personsTaken[person] == false){
				personsTaken[person] = true;
				int rres= countParty2Recursive(cap+1, cpmap, personsTaken);
				myres+=rres;
				personsTaken[person] = false;
			}
		}
		return myres;
	}
//	public static int countParty2RecursiveBM(int cap,ArrayList<Integer>[] cpmap,int personsTaken){
//		if(cap == cpmap.length){
//			
//			}
//			return 1;
//		}
//		
//		int myres=0;
//		myres+=countParty2RecursiveBM(cap+1, cpmap, personsTaken);
//		for(int i=0;i<cpmap[cap].size();i++){
//			int person=cpmap[cap].get(i);
//			if(personsTaken & (1<<person) ==0){
//				int rres= countParty2Recursive(cap+1, cpmap, personsTaken);
//				myres+=rres;
//			}
//		}
//		return myres;
//	}
	public static void countParty2Tabulation(int persons,int caps,ArrayList<Integer>[] cpmap){
		int[][] strg=new int[caps+1][1<<persons];
		for(int i=strg.length-1;i>=0;i--){
			for(int j=strg[0].length-1;j>=0;j--){
				if(i == strg.length-1 && j == strg[0].length-1)
					strg[i][j] = 1;
				else if(j == strg[0].length-1)
					strg[i][j] = 1;
				else if(i == strg.length-1)
					strg[i][j] = 0;
				else{
					strg[i][j] = strg[i+1][j];
					for(int k=0;k<cpmap[i].size();k++){
						int person=cpmap[i].get(k);
						if ((j & (1 << person)) == 0) {
							int temp = j;
							temp = temp | (1 << person);
							strg[i][j] += strg[i + 1][temp];
						}
					}
				}
			}
		}
//		for(int i=0;i<strg.length;i++){
//			for(int j=0;j<strg[0].length;j++)
//				System.out.print(strg[i][j]+" ");
//			System.out.println();
//		}
		System.out.println(strg[0][0]);
	}
	
	public static void main(String[] args) {
		int caps=4;
		int persons=3;
		ArrayList<Integer>[] pcmap = new ArrayList[persons];
		pcmap[0] = new ArrayList<>();
//		pcmap[0].add(5);
//		pcmap[0].add(7);
//		pcmap[0].add(19);
		pcmap[0].add(3);
		pcmap[0].add(1);

		pcmap[1] = new ArrayList<>();
		pcmap[1].add(1);
		pcmap[1].add(2);
//		pcmap[1].add(7);
		pcmap[1].add(3);

		pcmap[2] = new ArrayList<>();
//		pcmap[2].add(19);
//		pcmap[2].add(5);
		pcmap[2].add(0);
		pcmap[2].add(2);
		ArrayList<Integer>[] cpmap=new ArrayList[caps];
		for(int i=0;i<cpmap.length;i++)
			cpmap[i]=new ArrayList<>();
		for(int p=0;p<pcmap.length;p++){
			for(int ci=0;ci<pcmap[p].size();ci++){
				int cap=pcmap[p].get(ci);
				cpmap[cap].add(p);
			}
		}
		boolean[] personsTaken = new boolean[persons];
		System.out.println(countParty2Recursive(0, cpmap, personsTaken));
//		countParty2Tabulation(persons, caps, cpmap);
		
	}

}
