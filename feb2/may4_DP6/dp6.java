package may4;

import java.util.*;

public class dp6 {

	public static void eggdrop(int floors, int eggs) {
		int[][] strg = new int[eggs + 1][floors + 1];
		for (int e = 1; e < strg.length; e++) {
			for (int f = 0; f < strg[e].length; f++) {
				if (f == 0 || f == 1 || e == 1)
					strg[e][f] = f;
				else {
					// int min = Integer.MAX_VALUE;
					// for(int l=0,r=f-1;l<f&& r>=0;l++,r--){
					// int max=Math.max(strg[e-1][l], strg[e][r]);
					// min = Math.min(min, max);
					// }
					// strg[e][f] = min+1;
					strg[e][f] = Integer.MAX_VALUE;
					for (int k = 1; k <= f; k++) {
						int eb = strg[e - 1][k - 1];// egg breaks
						int es = strg[e][f - k];// egg survives
						int bl = Math.max(eb, es);// bad luck
						strg[e][f] = Math.min(strg[e][f], bl);
					}
					strg[e][f]++;
				}
			}
		}
		System.out.println(strg[eggs][floors]);
	}

	private static void printFloors(int[][] strg, int e, int f) {
		// galat hai
		if (f == 0 || f == 1 || e == 1)
			strg[e][f] = f;
		else {
			for (int k = 1; k <= f; k++) {
				int eb = strg[e - 1][k - 1];// egg breaks
				int es = strg[e][f - k];// egg survives
				int bl = Math.max(eb, es);// bad luck
				strg[e][f] = Math.min(strg[e][f], bl);
			}
		}
	}

	public static int personsandcaps(int cp, int tp, ArrayList<Integer>[] pcmap, boolean[] capst) {
		if (cp == tp)
			return 1;

		int myres = 0;
		for (int i = 0; i < pcmap[cp].size(); i++) {
			int cap = pcmap[cp].get(i);
			if (capst[cap] == false) {
				capst[cap] = true;
				int rres = personsandcaps(cp + 1, tp, pcmap, capst);
				myres += rres;
				capst[cap] = false;
			}
		}
		return myres;
	}

	public static int personsandcapsBM(int cp, int tp, ArrayList<Integer>[] pcmap, int capst) {
		if (cp == tp)
			return 1;

		int myres = 0;
		for (int i = 0; i < pcmap[cp].size(); i++) {
			int cap = pcmap[cp].get(i);
			if ((capst & (1 << cap)) == 0) {
				// capst = capst^(1<<cap);
				// int rres=personsandcapsBM(cp+1,tp,pcmap,capst);
				int rres = personsandcapsBM(cp + 1, tp, pcmap, (capst | 1 << cap));
				myres += rres;
				// capst = capst^(1<<cap);
			}
		}
		return myres;
	}

	// Memorization
	public static int personsandcapsBMDP(int cp, int tp, ArrayList<Integer>[] pcmap, int capst, int[][] qb) {
		if (cp == tp)
			return 1;
		if (qb[cp][capst] != 0)
			return qb[cp][capst];
		int myres = 0;
		for (int i = 0; i < pcmap[cp].size(); i++) {
			int cap = pcmap[cp].get(i);
			if ((capst & (1 << cap)) == 0) {
				int rres = personsandcapsBMDP(cp + 1, tp, pcmap, (capst | 1 << cap), qb);
				myres += rres;
			}
		}
		qb[cp][capst] = myres;
		return myres;
	}

	public static void personsandcapsTabulation(int persons, int caps, ArrayList<Integer>[] pcmap) {
		int[][] strg = new int[persons + 1][1 << caps];
		for (int i = strg.length - 1; i >= 0; i--) {
			for (int j = strg[0].length - 1; j >= 0; j--) {
				if (i == strg.length - 1)
					strg[i][j] = 1;
				else {
					for (int k = 0; k < pcmap[i].size(); k++) {
						int cap = pcmap[i].get(k);
						if ((j & (1 << cap)) == 0) {
							int temp = j;
							temp = temp | (1 << cap);
							strg[i][j] += strg[i + 1][temp];
						} 
					}
				}
			}
		}
		System.out.println(strg[0][0]);
	}

	public static void main(String[] args) {
		// eggdrop(10, 2);
		int p = 3;
		int c = 4;
		ArrayList<Integer>[] pcmap = new ArrayList[p];
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
		boolean[] capst = new boolean[c];
		System.out.println(personsandcaps(0, p, pcmap, capst));
		System.out.println(personsandcapsBM(0, p, pcmap, 0));
		int[][] qb = new int[p][1 << c];
		System.out.println(personsandcapsBMDP(0, p, pcmap, 0, qb));
		personsandcapsTabulation(p, c, pcmap);
	}
}
